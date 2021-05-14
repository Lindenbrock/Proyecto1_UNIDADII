import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class AboutDialog extends JDialog{
	boolean ans;
	JLabel lbl1,lbl2,lbl3,lbl4;
	JButton btnClose;
	
	public AboutDialog(MainInterface w,boolean modal) {
		super(w.W,modal);
		setTitle("Acerca de");
		setSize(270,200);
		setLayout(null);
		setLocationRelativeTo(w.W);
		this.setBackground(w.uno);
		
		Image icon = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Resources/icon.png"));
		setIconImage(icon);
		
		lbl1 = new JLabel("Desarrollado por:\n");
		lbl1.setForeground(w.dos);
		lbl1.setBounds(10, 10, 250, 20);
		lbl2 = new JLabel("Luis José Ixta Zamudio\n");
		lbl2.setForeground(w.dos);
		lbl2.setBounds(10, 32, 250, 20);
		lbl3 = new JLabel("No. Control: 17420565\n");
		lbl3.setForeground(w.dos);
		lbl3.setBounds(10, 54, 250, 20);
		lbl4 = new JLabel("Ingeniería en Sistemas Computacionales");
		lbl4.setForeground(w.dos);
		lbl4.setBounds(10, 76, 250, 20);
		btnClose = new JButton("Cerrar");
		btnClose.setBackground(w.tres);
		btnClose.setForeground(w.uno);
		btnClose.setBounds(87, 120, 80, 25);
		
		add(lbl1); add(lbl2); add(lbl3); add(lbl4); add(btnClose);
		
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}
	
	public void showDialog() {
		setVisible(true);
	}
	
}
