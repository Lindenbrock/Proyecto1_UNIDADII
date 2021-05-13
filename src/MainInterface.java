import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.text.AttributedCharacterIterator;

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

public class MainInterface extends JPanel implements ActionListener{
	JFrame W;
	JPanel pestaña1,pestaña2;
	JMenuBar menuBar;
	JMenuItem menuOptions[]; //opcRestore,opcScale,opcSheary,opcReflection,opsTranstale,opcRotation,opcExit,opcAbout,opcHelp
	JMenu menu1,menu2;
	JToolBar toolBar;
	String mOptions[];
	Color uno,dos,tres,cuatro;
	JTabbedPane pestañas;
	URL ruta;
	Figure fig1,fig2;
	
	public MainInterface() {
		W = new JFrame("Transformaciones");
		W.setSize(1000,600);
		W.setLocationRelativeTo(this);
		W.setResizable(false);
		W.add(this);
		
		uno = new Color(247,247,247);
		dos = new Color(59,9,68);
		tres = new Color(95,24,84);
		cuatro = new Color(26,187,156);
		
		buildMenu();
		buildToolBar();
		buildTabbedPane();
		
		fig1 = new Figure();
		fig2 = new Figure();
		
		W.setVisible(true);
		W.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//CREANDO LAS PESTAÑAS QUE CONTENDRÁN LAS FIGURAS
	private void buildTabbedPane() {
		pestañas = new JTabbedPane();
		pestañas.setBackground(cuatro);
		W.add(pestañas);
		
		//Pintar figura en la pestaña 1
		pestaña1 = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				int maxx=getWidth(),maxy=getHeight();
				g.setColor(tres);
				g.fillRect(maxx-300,maxy-200,300,200);
				fig1.DibujarVecPuntos(g,1);
				fig1.windowMap(maxx, maxx-300, maxy, maxy-200, maxx, maxy, g, 1);
			}
		};
		
		//Pintar figura en la pestaña 2
		pestaña2 = new JPanel(){
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				int maxx=getWidth(),maxy=getHeight();
				g.setColor(tres);
				g.fillRect(maxx-300,maxy-200,300,200);
				fig2.DibujarVecPuntos(g,2);
				fig2.windowMap(maxx, maxx-300, maxy, maxy-200, maxx, maxy, g, 2);
			}
		};
		
		pestañas.add(pestaña1,"Figura 1");
		pestañas.add(pestaña2,"Figura 2");
	}
	
	//CONSTRUYENDO LA BARRA DE HERRAMIENTAS
	public void buildToolBar() {
		toolBar = new JToolBar("",JToolBar.VERTICAL);
		toolBar.setBackground(uno);
		toolBar.setFloatable(false);
		W.add(toolBar,BorderLayout.WEST);
		
		URL ruta = getClass().getResource("/Resources/undo.png"); //Botón de restaurar
		Action A1=new AbstractAction("", new ImageIcon(ruta)){
            public void actionPerformed(ActionEvent arg0) {
            	if(pestañas.getSelectedIndex() == 0)
            		fig1.restore(1);
            	else
            		fig2.restore(2);
            	pestañas.repaint();
            }};
        A1.putValue(Action.SHORT_DESCRIPTION,"Devuelve la figura a su estado original");
        JButton btnA1 = new JButton(A1);
        btnA1.setBorder(null);
        btnA1.setBackground(uno);
        toolBar.add(btnA1);
        
        ruta = getClass().getResource("/Resources/rotate-left.png"); //Botón de girar a la izquierda
		Action A2=new AbstractAction("", new ImageIcon(ruta)){
            public void actionPerformed(ActionEvent arg0) {
            	if(pestañas.getSelectedIndex() == 0)
            		fig1.rotateCosHPoint(5,1);
            	else
            		fig2.rotateCosHPoint(5,2);
            	pestañas.repaint();
            }};
        A2.putValue(Action.SHORT_DESCRIPTION,"Gira la figura a la izquerda");
        JButton btnA2 = new JButton(A2);
        btnA2.setBorder(null);
        btnA2.setBackground(uno);
        toolBar.add(btnA2);
        
        ruta = getClass().getResource("/Resources/rotate-right.png"); //Botón de girar a al derecha
		Action A3=new AbstractAction("", new ImageIcon(ruta)){
            public void actionPerformed(ActionEvent arg0) {
            	if(pestañas.getSelectedIndex() == 0)
            		fig1.rotateSinHPoint(5,1);
            	else
            		fig2.rotateSinHPoint(5,2);
            	pestañas.repaint();
            }};
        A3.putValue(Action.SHORT_DESCRIPTION,"Gira la figura a la derecha");
        JButton btnA3 = new JButton(A3);
        btnA3.setBorder(null);
        btnA3.setBackground(uno);
        toolBar.add(btnA3);
        
        ruta = getClass().getResource("/Resources/refx.png"); //Botón de reflejar respecto al eje X
		Action A4=new AbstractAction("",new ImageIcon(ruta)){
            public void actionPerformed(ActionEvent arg0) {
            	if(pestañas.getSelectedIndex() == 0)
            		fig1.reflectHPoint(1,-1,1);
            	else
            		fig2.reflectHPoint(1,-1,2);
            	pestañas.repaint();
            }};
        A4.putValue(Action.SHORT_DESCRIPTION,"Refleja la figura con respecto al eje X");
        JButton btnA4 = new JButton(A4);
        btnA4.setBorder(null);
        btnA4.setBackground(uno);
        toolBar.add(btnA4);
		
        ruta = getClass().getResource("/Resources/refy.png"); //Botón de reflejar respecto al eje Y
        Action A5=new AbstractAction("",new ImageIcon(ruta)){
            public void actionPerformed(ActionEvent arg0) {
            	if(pestañas.getSelectedIndex() == 0)
            		fig1.reflectHPoint(-1,1,1);
            	else
            		fig2.reflectHPoint(-1,1,2);
            	pestañas.repaint();
            }};
        A5.putValue(Action.SHORT_DESCRIPTION,"Refleja la figura con respecto al eje Y");
        JButton btnA5 = new JButton(A5);
        btnA5.setBorder(null);
        btnA5.setBackground(uno);
        toolBar.add(btnA5);
        
        ruta = getClass().getResource("/Resources/refxy.png"); //Botón de reflejar respecto a ambos ejes
        Action A6=new AbstractAction("",new ImageIcon(ruta)){
            public void actionPerformed(ActionEvent arg0) {
            	if(pestañas.getSelectedIndex() == 0)
            		fig1.reflectHPoint(-1,-1,1);
            	else
            		fig2.reflectHPoint(-1,-1,2);
            	pestañas.repaint();
            }};
        A6.putValue(Action.SHORT_DESCRIPTION,"Refleja la figura sobre ambos ejes");
        JButton btnA6 = new JButton(A6);
        btnA6.setBorder(null);
        btnA6.setBackground(uno);
        toolBar.add(btnA6);
        
        ruta = getClass().getResource("/Resources/close.png"); //Botón de aumentar tamaño
        Action A7=new AbstractAction("",new ImageIcon(ruta)){
            public void actionPerformed(ActionEvent arg0) {
            	if(pestañas.getSelectedIndex() == 0)
            		fig1.scaleHPoint(1.1,1);
            	else
            		fig2.scaleHPoint(1.1,2);
            	pestañas.repaint();
            }};
        A7.putValue(Action.SHORT_DESCRIPTION,"Aumenta el tamaño de la figuras");
        JButton btnA7 = new JButton(A7);
        btnA7.setBorder(null);
        btnA7.setBackground(uno);
        toolBar.add(btnA7);
        
        ruta = getClass().getResource("/Resources/far.png"); //Botón de disminuir tamaño
        Action A8=new AbstractAction("",new ImageIcon(ruta)){
            public void actionPerformed(ActionEvent arg0) {
            	if(pestañas.getSelectedIndex() == 0)
            		fig1.scaleHPoint(0.9,1);
            	else
            		fig2.scaleHPoint(0.9,2);
            	pestañas.repaint();
            }};
        A8.putValue(Action.SHORT_DESCRIPTION,"Disminuye el tamaño de la figura");
        JButton btnA8 = new JButton(A8);
        btnA8.setBorder(null);
        btnA8.setBackground(uno);
        toolBar.add(btnA8);
        
        ruta = getClass().getResource("/Resources/exit.png"); //Salir del programa
        Action A9=new AbstractAction("",new ImageIcon(ruta)){
            public void actionPerformed(ActionEvent arg0) {
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
		W.setJMenuBar(menuBar);
		
		menu1 = new JMenu("Trasformaciones");
		menu1.setForeground(uno);
		
		menu2 = new JMenu("Acerca de");
		menu2.setForeground(uno);
		
		menuBar.add(menu1); menuBar.add(menu2);
		
		mOptions = new String[] {"Restaurar","Escalar","Deformar","Girar","Trasladar",  //Se crea arreglo de nombres de las opciones del menu
								"Reflejar","Salir","Desarrollador","Ayuda"};		   //para no hacer el
		menuOptions = new JMenuItem[9];	//Se crea arreglo de pciones
		
		for(int i=0;i<mOptions.length;i++) {		//Ciclo para crear cada uno de los item del menú en un ciclo
			menuOptions[i] = new JMenuItem(mOptions[i]);
			menuOptions[i].setBackground(uno);
		}
		for(int i=0;i<7;i++) {//Ciclo para añadir los items al menu
			menu1.add(menuOptions[i]);
		}
		menu2.add(menuOptions[7]); menu2.add(menuOptions[8]); //Los últimos items se agregan al otro menu
		
		menuOptions[0].addActionListener(this);
		menuOptions[0].setMnemonic('R');
		menuOptions[0].setToolTipText("Restaura la figura a su forma originaL");
		ruta = getClass().getResource("/Resources/undo.png");
		menuOptions[0].setIcon(new ImageIcon(ruta));
		menuOptions[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,InputEvent.ALT_MASK));
		
		menuOptions[1].addActionListener(this);
		menuOptions[1].setMnemonic('E');
		menuOptions[1].setToolTipText("Cambia el tamaño de la figura");
		ruta = getClass().getResource("/Resources/scale.png");
		menuOptions[1].setIcon(new ImageIcon(ruta));
		menuOptions[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,InputEvent.ALT_MASK));
		
		menuOptions[2].addActionListener(this);
		menuOptions[2].setMnemonic('D');
		menuOptions[2].setToolTipText("Cambia la forma de la figura");
		ruta = getClass().getResource("/Resources/sheary.png");
		menuOptions[2].setIcon(new ImageIcon(ruta));
		menuOptions[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.ALT_MASK));
		
		menuOptions[3].addActionListener(this);
		menuOptions[3].setMnemonic('G');
		menuOptions[3].setToolTipText("Gira la figura");
		ruta = getClass().getResource("/Resources/rotate-right.png");
		menuOptions[3].setIcon(new ImageIcon(ruta));
		menuOptions[3].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,InputEvent.ALT_MASK));
		
		menuOptions[4].addActionListener(this);
		menuOptions[4].setMnemonic('T');
		menuOptions[4].setToolTipText("Traslada la figura a un pundo deseado");
		ruta = getClass().getResource("/Resources/move.png");
		menuOptions[4].setIcon(new ImageIcon(ruta));
		menuOptions[4].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,InputEvent.ALT_MASK));
		
		menuOptions[5].addActionListener(this);
		menuOptions[5].setMnemonic('R');
		menuOptions[5].setToolTipText("Refleja la figura");
		ruta = getClass().getResource("/Resources/refy.png");
		menuOptions[5].setIcon(new ImageIcon(ruta));
		menuOptions[5].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.ALT_MASK));
		
		//menuOptions[6].addActionListener(this);
		menuOptions[6].setMnemonic('S');
		menuOptions[6].setToolTipText("Salir del programa");
		ruta = getClass().getResource("/Resources/exit.png");
		menuOptions[6].setIcon(new ImageIcon(ruta));
		menuOptions[6].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,InputEvent.ALT_MASK));
		
		menuOptions[7].setMnemonic('A');
		menuOptions[7].setToolTipText("Muestra la información del desarrollador");
		ruta = getClass().getResource("/Resources/developer.png");
		menuOptions[7].setIcon(new ImageIcon(ruta));
		menuOptions[7].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.ALT_MASK));
		
		menuOptions[8].setMnemonic('H');
		menuOptions[8].setToolTipText("Muestra una ayudar sobre el funcionamiento del programa");
		ruta = getClass().getResource("/Resources/help.png");
		menuOptions[8].setIcon(new ImageIcon(ruta));
		menuOptions[8].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,InputEvent.ALT_MASK));
	}
	
	//EVENTOS AL ESCOGER UNA DE LAS OPCIONES DE LA BARRA DE MENU
	public void actionPerformed(ActionEvent ev) {
		if(ev.getSource() == menuOptions[0])  //Restaurar figura con Restautrar o Alt+R
			if(pestañas.getSelectedIndex() == 0)
				fig1.restore(1);
			else
				fig2.restore(2);
			
		else
			if(ev.getSource() == menuOptions[1]) { 
				double esc = new ScaleDialog(MainInterface.this,true).showDialog();
				if(pestañas.getSelectedIndex() == 0)
					fig1.scaleHPoint(esc,1);
				else
					fig2.scaleHPoint(esc,2);
			}else
				if(ev.getSource() == menuOptions[2]) {
					double[] she = new ShearyDialog(MainInterface.this,true).showDialog();
					if(pestañas.getSelectedIndex() == 0)
						fig1.shearyHPoint(she[0],she[1],1);
					else
						fig2.shearyHPoint(she[0],she[1],2);
				}else
					if(ev.getSource() == menuOptions[3]) {
						int[] rot = new RotateDialog(MainInterface.this,true).showDialog();
						if(rot[1] == 1)
							if(pestañas.getSelectedIndex() == 0)
								fig1.rotateCosHPoint(rot[0],1);
							else
								fig2.rotateCosHPoint(rot[0],2);
						else
							if(pestañas.getSelectedIndex() == 0)
								fig1.rotateSinHPoint(rot[0],1);
							else
								fig2.rotateSinHPoint(rot[0],2);
					}else
						if(ev.getSource() == menuOptions[4]) {
							int[] move= new TranslateDialog(MainInterface.this,true).showDialog();
							if(pestañas.getSelectedIndex() == 0)
								fig1.movePoint(move[0],move[1],1);
							else
								fig2.movePoint(move[0],move[1],2);
						}else
							if(ev.getSource() == menuOptions[5]) {
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
								
								if(pestañas.getSelectedIndex() == 0)
									fig1.reflectHPoint(refx,refy,1);
								else
									fig2.reflectHPoint(refx,refy,2);
							}
		pestañas.repaint();
	}
	
	//MÉTODO MAIN PARA INICIAR PROGRAMA
	public static void main(String[] args) {
		new MainInterface();
	}
}
