import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class RotateDialog extends JDialog{
	int cantRot[] = new int[2];
	JLabel lbl1,lbl2,lbl3,lblimg;
	JTextField tf1,tf2,tf3;
	JRadioButton rb1,rb2;
	ButtonGroup rbg;
	JButton btnAllow,btnCancel;
	
	public RotateDialog(MainInterface w,boolean modal) {
		super(w.W,modal);
		setTitle("Rotar la figura");
		setSize(600,180);
		setLayout(new FlowLayout());
		setLocationRelativeTo(w.W);
		this.setBackground(w.uno);
		
		Image icon = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Resources/icon.png"));
		setIconImage(icon);
		
		lbl1 = new JLabel("Cantidad a rotar");
		lbl1.setForeground(w.dos);
		tf1 = new JTextField(10);
		rbg = new ButtonGroup();
		URL ruta = getClass().getResource("/Resources/rotate-left.png");
		lbl2 = new JLabel(new ImageIcon(ruta));
		rb1 = new JRadioButton("Rotar a la izquerda");
		rb1.setForeground(w.dos);
		rb1.setSelected(true);
		ruta = getClass().getResource("/Resources/rotate-right.png");
		lbl3 = new JLabel(new ImageIcon(ruta));
		rb2 = new JRadioButton("Rotar a la derecha");
		rb2.setForeground(w.dos);
		rbg.add(rb1); rbg.add(rb2);
		btnAllow = new JButton("Aceptar");
		btnAllow.setBackground(w.tres);
		btnAllow.setForeground(w.uno);
		btnCancel = new JButton("Cancelar");
		btnCancel.setBackground(w.tres);
		
		ruta = getClass().getResource("/Resources/rotateg.gif");
		lblimg = new JLabel(new ImageIcon(ruta));
		
		btnCancel.setForeground(w.uno);
		add(lbl1); add(tf1); add(lbl2); add(rb1); add(lbl3); add(rb2); add(lblimg); add(btnAllow); add(btnCancel);
		
		btnAllow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {					
				String res = tf1.getText();
				try {
					cantRot[0]=Integer.parseInt(res);
				}catch (NumberFormatException e1) {
					cantRot[0]=0;
				}
				if(rb1.isSelected())
					cantRot[1]=1;
				else
					cantRot[1]=2;
					
				setVisible(false);
				dispose();
			}	
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cantRot[0]=0;
				cantRot[1]=1;
				setVisible(false);
				dispose();
			}
		});
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}
	
	public int[] showDialog() {
		setVisible(true);
		return cantRot;
	}
}
