import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

public class MainInterface extends JFrame implements ActionListener, MouseWheelListener{
	JFrame W;
	JPanel pesta?a1,pesta?a2;
	JMenuBar menuBar;
	JMenuItem menuOptions[];
	JMenu menu1,menu2;
	JToolBar toolBar;
	String mOptions[];
	Color uno,dos,tres,cuatro;
	JTabbedPane pesta?as;
	URL ruta;
	Figure fig1,fig2;
	Image Background,icon;
	boolean move=false;
	
	public MainInterface() {
		setSize(1000,600);
		setLocationRelativeTo(this);
		setResizable(false);
		setTitle("Trasformaciones UI");

		
		//Icono de la interfaz
		icon = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Resources/icon.png"));
		setIconImage(icon);
		
		uno = new Color(247,247,247);
		dos = new Color(59,9,68);
		tres = new Color(95,24,84);
		cuatro = new Color(26,187,156);
		
		buildMenu();
		buildToolBar();
		buildTabbedPane();
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//CONSTRUYENDO LAS PESTA?AS QUE MUESTRAN LAS FIGURAS
	public void buildTabbedPane() {
		pesta?as = new JTabbedPane();
		pesta?as.setBackground(cuatro);
		add(pesta?as);
		
		ruta = getClass().getResource("/Resources/grid1.jpg"); //Fonde de las pesta?as
		Background = new ImageIcon(ruta).getImage();
		
		//Pintar figura en la pesta?a 1
		pesta?a1 = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(Background, 0, 0,pesta?a1.getWidth(),pesta?a1.getHeight(),pesta?a1);
				int maxx=getWidth(),maxy=getHeight();
				g.setColor(tres);
				g.fillRect(maxx-300,maxy-200,300,200);
				fig1.drawPointVector(g,1);
				fig1.windowMapping(maxx, maxx-300, maxy, maxy-200, maxx, maxy, g, 1);
			}
		};
		
		//Pintar figura en la pesta?a 2
		pesta?a2 = new JPanel(){
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(Background, 0, 0,pesta?a2.getWidth(),pesta?a2.getHeight(),pesta?a2);
				int maxx=getWidth(),maxy=getHeight();
				g.setColor(tres);
				g.fillRect(maxx-300,maxy-200,300,200);
				fig2.drawPointVector(g,2);
				fig2.windowMapping(maxx, maxx-300, maxy, maxy-200, maxx, maxy, g, 2);
			}
		};
		
		pesta?as.add(pesta?a1,"Figura 1");
		pesta?as.add(pesta?a2,"Figura 2");
		
		fig1 = new Figure();
		fig2 = new Figure();
		
		pesta?as.addMouseWheelListener(this); //Evento para editar tama?o con la rueda del rat?n
		
		//EVENTOS PARA ROTAR FIGURA CON CLICKS
		pesta?as.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int cX = e.getX(),cY = e.getY(),nf;
				Point pfx,pfy;
				if(pesta?as.getSelectedIndex() == 0) {
					pfx = fig1.getFigureXCoordinates(1);
					pfy = fig1.getFigureYCoordinates(1);
					nf = 1;
				} else {
					pfx = fig2.getFigureXCoordinates(2);
					pfy = fig2.getFigureYCoordinates(2);
					nf = 2;
				}
					
				if(cX < pfx.x && e.getClickCount() >= 2)
					if(nf == 1)
						fig1.rotateCosHPoint(5, nf);
					else
						fig2.rotateCosHPoint(5, nf);
				else
					if(cX > pfx.y && e.getClickCount() >= 2)
						if(nf == 1)
							fig1.rotateSinHPoint(5, nf);
						else
							fig2.rotateSinHPoint(5, nf);
				
				if((cX > pfx.x && cX < pfx.y) && (cY > pfy.x && cY < pfy.y))
					move = true;
				else
					move = false;
					
				pesta?as.repaint(); 
			}
		});
		
		//EVENTO PARA MOVER LA FIGURA CON EL ARRASTRE DEL RAT?N
		pesta?as.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				int cX = e.getX(), cY = e.getY(), tX, tY, pcX, pcY, nf;
				
				if(move) {
					if(pesta?as.getSelectedIndex() == 0) {
						pcX = (int) fig1.fig1[0].x;
						pcY = (int) fig1.fig1[0].y;
						nf = 1;
					}else {
						pcX = (int) fig2.fig2[0].x;
						pcY = (int) fig2.fig2[0].y;
						nf = 2;
					}
					tX = cX - pcX;
					tY = cY - pcY;
					if(nf == 1)
						fig1.movePoint(tX, tY, nf);
					else
						fig2.movePoint(tX, tY, nf);
					pesta?as.repaint();
				}
			}
		});
	}
	
	//CONSTRUYENDO LA BARRA DE HERRAMIENTAS
	public void buildToolBar() {
		toolBar = new JToolBar("",JToolBar.VERTICAL);
		toolBar.setBackground(uno);
		toolBar.setFloatable(false);
		add(toolBar,BorderLayout.WEST);
		
		ruta = getClass().getResource("/Resources/undo.png"); //Bot?n de restaurar
		Action A1=new AbstractAction("", new ImageIcon(ruta)){
            public void actionPerformed(ActionEvent arg0) {
            	boolean ans = new RestoreDialog(MainInterface.this,true).showDialog();
    			if(ans)
    				if(pesta?as.getSelectedIndex() == 0)
    					fig1.restorePoint(1);
    				else
    					fig2.restorePoint(2);
            	pesta?as.repaint();
            }};
        A1.putValue(Action.SHORT_DESCRIPTION,"Devuelve la figura a su estado original");
        JButton btnA1 = new JButton(A1);
        btnA1.setBorder(null);
        btnA1.setBackground(uno);
        toolBar.add(btnA1);
        
        ruta = getClass().getResource("/Resources/rotate-left.png"); //Bot?n de girar a la izquierda
		Action A2=new AbstractAction("", new ImageIcon(ruta)){
            public void actionPerformed(ActionEvent arg0) {
            	if(pesta?as.getSelectedIndex() == 0)
            		fig1.rotateCosHPoint(5,1);
            	else
            		fig2.rotateCosHPoint(5,2);
            	pesta?as.repaint();
            }};
        A2.putValue(Action.SHORT_DESCRIPTION,"Gira la figura a la izquerda");
        JButton btnA2 = new JButton(A2);
        btnA2.setBorder(null);
        btnA2.setBackground(uno);
        toolBar.add(btnA2);
        
        ruta = getClass().getResource("/Resources/rotate-right.png"); //Bot?n de girar a al derecha
		Action A3=new AbstractAction("", new ImageIcon(ruta)){
            public void actionPerformed(ActionEvent arg0) {
            	if(pesta?as.getSelectedIndex() == 0)
            		fig1.rotateSinHPoint(5,1);
            	else
            		fig2.rotateSinHPoint(5,2);
            	pesta?as.repaint();
            }};
        A3.putValue(Action.SHORT_DESCRIPTION,"Rota la figura a la derecha");
        JButton btnA3 = new JButton(A3);
        btnA3.setBorder(null);
        btnA3.setBackground(uno);
        toolBar.add(btnA3);
        
        ruta = getClass().getResource("/Resources/refx.png"); //Bot?n de reflejar respecto al eje X
		Action A4=new AbstractAction("",new ImageIcon(ruta)){
            public void actionPerformed(ActionEvent arg0) {
            	if(pesta?as.getSelectedIndex() == 0)
            		fig1.reflectHPoint(1,-1,1);
            	else
            		fig2.reflectHPoint(1,-1,2);
            	pesta?as.repaint();
            }};
        A4.putValue(Action.SHORT_DESCRIPTION,"Refleja la figura con respecto al eje X");
        JButton btnA4 = new JButton(A4);
        btnA4.setBorder(null);
        btnA4.setBackground(uno);
        toolBar.add(btnA4);
		
        ruta = getClass().getResource("/Resources/refy.png"); //Bot?n de reflejar respecto al eje Y
        Action A5=new AbstractAction("",new ImageIcon(ruta)){
            public void actionPerformed(ActionEvent arg0) {
            	if(pesta?as.getSelectedIndex() == 0)
            		fig1.reflectHPoint(-1,1,1);
            	else
            		fig2.reflectHPoint(-1,1,2);
            	pesta?as.repaint();
            }};
        A5.putValue(Action.SHORT_DESCRIPTION,"Refleja la figura con respecto al eje Y");
        JButton btnA5 = new JButton(A5);
        btnA5.setBorder(null);
        btnA5.setBackground(uno);
        toolBar.add(btnA5);
        
        ruta = getClass().getResource("/Resources/refxy.png"); //Bot?n de reflejar respecto a ambos ejes
        Action A6=new AbstractAction("",new ImageIcon(ruta)){
            public void actionPerformed(ActionEvent arg0) {
            	if(pesta?as.getSelectedIndex() == 0)
            		fig1.reflectHPoint(-1,-1,1);
            	else
            		fig2.reflectHPoint(-1,-1,2);
            	pesta?as.repaint();
            }};
        A6.putValue(Action.SHORT_DESCRIPTION,"Refleja la figura sobre ambos ejes");
        JButton btnA6 = new JButton(A6);
        btnA6.setBorder(null);
        btnA6.setBackground(uno);
        toolBar.add(btnA6);
        
        ruta = getClass().getResource("/Resources/close.png"); //Bot?n de aumentar tama?o
        Action A7=new AbstractAction("",new ImageIcon(ruta)){
            public void actionPerformed(ActionEvent arg0) {
            	if(pesta?as.getSelectedIndex() == 0)
            		fig1.scaleHPoint(1.1,1);
            	else
            		fig2.scaleHPoint(1.1,2);
            	pesta?as.repaint();
            }};
        A7.putValue(Action.SHORT_DESCRIPTION,"Aumenta el tama?o de la figura");
        JButton btnA7 = new JButton(A7);
        btnA7.setBorder(null);
        btnA7.setBackground(uno);
        toolBar.add(btnA7);
        
        ruta = getClass().getResource("/Resources/far.png"); //Bot?n de disminuir tama?o
        Action A8=new AbstractAction("",new ImageIcon(ruta)){
            public void actionPerformed(ActionEvent arg0) {
            	if(pesta?as.getSelectedIndex() == 0)
            		fig1.scaleHPoint(0.9,1);
            	else
            		fig2.scaleHPoint(0.9,2);
            	pesta?as.repaint();
            }};
        A8.putValue(Action.SHORT_DESCRIPTION,"Disminuye el tama?o de la figura");
        JButton btnA8 = new JButton(A8);
        btnA8.setBorder(null);
        btnA8.setBackground(uno);
        toolBar.add(btnA8);
        
        ruta = getClass().getResource("/Resources/exit.png"); //Salir del programa
        Action A9=new AbstractAction("",new ImageIcon(ruta)){
            public void actionPerformed(ActionEvent arg0) {
            	boolean ans = new ExitDialog(MainInterface.this,true).showDialog();
				if(ans)
					System.exit(0);
            }};
        A9.putValue(Action.SHORT_DESCRIPTION,"Salir del programa");
        JButton btnA9 = new JButton(A9);
        btnA9.setBorder(null);
        btnA9.setBackground(uno);
        toolBar.add(btnA9);
	}
	
	//CONSTRUYENDO EL MENU
	private void buildMenu() {
		menuBar = new JMenuBar();
		menuBar.setBackground(dos);
		setJMenuBar(menuBar);
		
		menu1 = new JMenu("Trasformaciones");
		menu1.setForeground(uno);
		
		menu2 = new JMenu("Acerca de");
		menu2.setForeground(uno);
		
		menuBar.add(menu1); menuBar.add(menu2);
		
		mOptions = new String[] {"Restaurar","Escalar","Deformar","Rotar","Trasladar",  //Se crea arreglo de nombres de las opciones del menu
								"Reflejar","Salir","Autor","Ayuda"};		   //para no hacer el
		menuOptions = new JMenuItem[9];	//Se crea arreglo de pciones
		
		for(int i=0;i<mOptions.length;i++) {		//Ciclo para crear cada uno de los item del men? en un ciclo
			menuOptions[i] = new JMenuItem(mOptions[i]);
			menuOptions[i].setBackground(uno);
		}
		for(int i=0;i<7;i++) {//Ciclo para a?adir los items al menu
			menu1.add(menuOptions[i]);
		}
		menu2.add(menuOptions[7]); menu2.add(menuOptions[8]); //Los ?ltimos items se agregan al otro menu
		
		menuOptions[0].addActionListener(this);  //Opci?n para restaurar
		menuOptions[0].setMnemonic('R');
		menuOptions[0].setToolTipText("Restaura la figura a su forma originaL");
		ruta = getClass().getResource("/Resources/undo.png");
		menuOptions[0].setIcon(new ImageIcon(ruta));
		menuOptions[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,InputEvent.ALT_MASK));
		
		menuOptions[1].addActionListener(this);  //Opci?n para escalar
		menuOptions[1].setMnemonic('E');
		menuOptions[1].setToolTipText("Cambia el tama?o de la figura");
		ruta = getClass().getResource("/Resources/scale.png");
		menuOptions[1].setIcon(new ImageIcon(ruta));
		menuOptions[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,InputEvent.ALT_MASK));
		
		menuOptions[2].addActionListener(this);  //Opci?n para deformar
		menuOptions[2].setMnemonic('D');
		menuOptions[2].setToolTipText("Cambia la forma de la figura");
		ruta = getClass().getResource("/Resources/sheary.png");
		menuOptions[2].setIcon(new ImageIcon(ruta));
		menuOptions[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.ALT_MASK));
		
		menuOptions[3].addActionListener(this);  //Opci?n para girar
		menuOptions[3].setMnemonic('G');
		menuOptions[3].setToolTipText("Gira la figura");
		ruta = getClass().getResource("/Resources/rotate-right.png");
		menuOptions[3].setIcon(new ImageIcon(ruta));
		menuOptions[3].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,InputEvent.ALT_MASK));
		
		menuOptions[4].addActionListener(this);  //Opci?n para trasladar
		menuOptions[4].setMnemonic('T');
		menuOptions[4].setToolTipText("Traslada la figura a un pundo deseado");
		ruta = getClass().getResource("/Resources/move.png");
		menuOptions[4].setIcon(new ImageIcon(ruta));
		menuOptions[4].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,InputEvent.ALT_MASK));
		
		menuOptions[5].addActionListener(this); //Opci?n para felejar
		menuOptions[5].setMnemonic('R');
		menuOptions[5].setToolTipText("Refleja la figura");
		ruta = getClass().getResource("/Resources/refy.png");
		menuOptions[5].setIcon(new ImageIcon(ruta));
		menuOptions[5].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.ALT_MASK));
		
		menuOptions[6].addActionListener(this);  //Opci?n para salir del programa
		menuOptions[6].setMnemonic('S');
		menuOptions[6].setToolTipText("Salir del programa");
		ruta = getClass().getResource("/Resources/exit.png");
		menuOptions[6].setIcon(new ImageIcon(ruta));
		menuOptions[6].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,InputEvent.ALT_MASK));
		
		menuOptions[7].addActionListener(this);  //Opci?n para informaci?n del desarrollador
		menuOptions[7].setMnemonic('A');
		menuOptions[7].setToolTipText("Muestra la informaci?n del desarrollador");
		ruta = getClass().getResource("/Resources/developer.png");
		menuOptions[7].setIcon(new ImageIcon(ruta));
		menuOptions[7].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.ALT_MASK));
		
		menuOptions[8].addActionListener(this); //Opci?n para ventana de ayuda
		menuOptions[8].setMnemonic('H');
		menuOptions[8].setToolTipText("Muestra una ayudar sobre el funcionamiento del programa");
		ruta = getClass().getResource("/Resources/help.png");
		menuOptions[8].setIcon(new ImageIcon(ruta));
		menuOptions[8].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,InputEvent.ALT_MASK));
	}
	
	//EVENTOS OPCIONES DE LA BARRA DE MENU
	public void actionPerformed(ActionEvent ev) {
		if(ev.getSource() == menuOptions[0]) {  //Evento menu Restaurar
			boolean ans = new RestoreDialog(MainInterface.this,true).showDialog();
			if(ans)
				if(pesta?as.getSelectedIndex() == 0)
					fig1.restorePoint(1);
				else
					fig2.restorePoint(2);
		} else
			if(ev.getSource() == menuOptions[1]) {  //Evento menu Escalar
				double esc = new ScaleDialog(MainInterface.this,true).showDialog();
				if(pesta?as.getSelectedIndex() == 0)
					fig1.scaleHPoint(esc,1);
				else
					fig2.scaleHPoint(esc,2);
			}else
				if(ev.getSource() == menuOptions[2]) {  //Evento menu Deformar
					double[] she = new ShearyDialog(MainInterface.this,true).showDialog();
					if(pesta?as.getSelectedIndex() == 0)
						fig1.shearyHPoint(she[0],she[1],1);
					else
						fig2.shearyHPoint(she[0],she[1],2);
				}else
					if(ev.getSource() == menuOptions[3]) {  //Evento menu Rotar
						int[] rot = new RotateDialog(MainInterface.this,true).showDialog();
						if(rot[1] == 1)
							if(pesta?as.getSelectedIndex() == 0)
								fig1.rotateCosHPoint(rot[0],1);
							else
								fig2.rotateCosHPoint(rot[0],2);
						else
							if(pesta?as.getSelectedIndex() == 0)
								fig1.rotateSinHPoint(rot[0],1);
							else
								fig2.rotateSinHPoint(rot[0],2);
					}else
						if(ev.getSource() == menuOptions[4]) {  //Evento menu Trasladar
							int[] move= new TranslateDialog(MainInterface.this,true).showDialog();
							if(pesta?as.getSelectedIndex() == 0)
								fig1.movePoint(move[0],move[1],1);
							else
								fig2.movePoint(move[0],move[1],2);
						}else
							if(ev.getSource() == menuOptions[5]) {  //Evento menu Reflejar
								int ans = new ReflectDialog(MainInterface.this,true).showDialog(),refx,refy;
								if(ans == 1) {
									refx = 1; refy = -1;
								} else 
									if(ans == 2) {
										refx = -1; refy = 1;
									} else 
										if(ans == 3) {
											refx = -1; refy = -1;
										}
									
										else {
											refx = 1; refy = 1;
										}
								
								if(pesta?as.getSelectedIndex() == 0)
									fig1.reflectHPoint(refx,refy,1);
								else
									fig2.reflectHPoint(refx,refy,2);
							}else
								if(ev.getSource() == menuOptions[6]) {  //Evento menu Salir
									boolean ans = new ExitDialog(MainInterface.this,true).showDialog();
									if(ans)
										System.exit(0);
								}else
									if(ev.getSource() == menuOptions[7]) //Evento menu Informaci?n del desarrollado
										new AboutDialog(MainInterface.this,true).showDialog();
									else
										if(ev.getSource() == menuOptions[8]) //Evento menu Ayuda
											new HelpDialog(MainInterface.this,true).showDialog();
		pesta?as.repaint();
	}
	
	//EVENTO DE RUEDA DEL RAT?N PARA AUMENTAR O DISMINUIR EL TAMA?O
	@Override
	public void mouseWheelMoved(MouseWheelEvent ev) {
		int wSense = ev.getWheelRotation();
		double esc;
		if(wSense < 0)
			esc = 0.95;
		else
			esc = 1.05;
		
		if(pesta?as.getSelectedIndex() == 0)
			fig1.scaleHPoint(esc,1);
		else
			fig2.scaleHPoint(esc,2);
		pesta?as.repaint();
	}
	
	//M?TODO MAIN PARA INICIAR PROGRAMA
	public static void main(String[] args) {
		new MainInterface();
	}
}
