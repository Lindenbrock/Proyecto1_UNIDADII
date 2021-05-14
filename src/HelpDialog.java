import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class HelpDialog extends JDialog implements ActionListener{
	JLabel lbl1,lbl2;
	JButton btnClose,btnNext,btnBack;
	JTextArea ta;
	JPanel panel1,panelBtn;
	String txt1 = "<html>Cómo usar el programa:<p><p>"
			 	+ "Se pueden modificar las figuras de ambas pestañas, cada una<p>por separado, usando los botones de la barra de "
			 	+ " herramientas o las opciones en la barra de menu en la sección<p>Transformaciones.<p>"
			 	+ "La barra de herramientas ofrece transformaciones de restauración, rotación, reflección y escalamiento"
			 	+ " con valores predeterminados.<p>"
			 	+ "El menú ofrece opciones de restauración, escalamiento, deformación, rotación, traslación y reflección.<p>"
			 	+ "En la barra de herramientas y el menú está disponible la opción <p>de salida."
			 	+ "</html>",
		   txt2 = "<html>Puedes mover de lugar la figura manteniendola clickeada y arrastrándola a donde desees.<p>"
		   		+ "Puedes girar la figura dando doble click a los lados de la figura<p>"
		   		+ "Puedes modificar el tamaño de la figura girando la rueda del ratón<p><p>"
		   		+ "Teclas aceleradoras del menú:<p>"
		   		+ "Restaurar : Alt + U<p>"
		   		+ "Escalar : Alt + Z<p>"
		   		+ "Deformar : Alt + S<p>"
		   		+ "Rotar : Alt + R<p>"
		   		+ "Trasladar : Alt + T<p>"
		   		+ "Reflejar : Alt + F<p>"
		   		+ "Salir : Alt + E<p></html>";
	
	public HelpDialog(MainInterface w,boolean modal) {
		super(w.W,modal);
		setTitle("Ayuda");
		setSize(400,300);
		setLayout(new BorderLayout());
		setLocationRelativeTo(w.W);
		this.setBackground(w.uno);
		
		Image icon = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Resources/icon.png"));
		setIconImage(icon);
		
		panel1 = new JPanel();
		panel1.setBackground(w.uno);
		panel1.setLayout(null);
		panel1.setSize(400,230);
		
		lbl1 = new JLabel(txt1);
		lbl1.setForeground(w.dos);
		lbl1.setBounds(10, 0, 380, 220);
		
		panelBtn = new JPanel();
		panelBtn.setBackground(w.uno);
		panelBtn.setLayout(new FlowLayout());
		panelBtn.setSize(400,70);
		
		btnClose = new JButton("Cerrar");
		btnClose.setBackground(w.tres);
		btnClose.setForeground(w.uno);
		
		btnNext = new JButton("Más");
		btnNext.setBackground(w.tres);
		btnNext.setForeground(w.uno);
		
		btnBack = new JButton("Atrás");
		btnBack.setBackground(w.tres);
		btnBack.setForeground(w.uno);
		btnBack.setVisible(false);
		btnBack.addActionListener(this);
		
		panel1.add(lbl1);
		panelBtn.add(btnBack);
		panelBtn.add(btnClose);
		panelBtn.add(btnNext);
		
		add(panel1,BorderLayout.CENTER);
		add(panelBtn,BorderLayout.SOUTH);
		
		btnClose.addActionListener(this);
		btnNext.addActionListener(this);
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnClose) {
			setVisible(false);
			dispose();
		} else {
			if(e.getSource() == btnNext) {
				lbl1.setText(txt2);
				btnNext.setVisible(false);
				btnBack.setVisible(true);
			} else {
				lbl1.setText(txt1);
				btnNext.setVisible(true);
				btnBack.setVisible(false);
			}
		}
	}
	
	public void showDialog() {
		setVisible(true);
	}

	
}
