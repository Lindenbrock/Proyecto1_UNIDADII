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

public class ReflectDialog extends JDialog{
	int rotType;
	JLabel lbl1,lbl2,lbl3,lblimg;
	JRadioButton rb1,rb2,rb3;
	ButtonGroup rbg;
	JButton btnAllow,btnCancel;
	
	public ReflectDialog(MainInterface w,boolean modal) {
		super(w.W,modal);
		setTitle("Reflejar la figura");
		setSize(500,200);
		setLayout(new FlowLayout());
		setLocationRelativeTo(w.W);
		this.setBackground(w.uno);
		
		Image icon = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Resources/icon.png"));
		setIconImage(icon);
		
		URL ruta = getClass().getResource("/Resources/refx.png");
		lbl1 = new JLabel(new ImageIcon(ruta));
		ruta = getClass().getResource("/Resources/refy.png");
		lbl2 = new JLabel(new ImageIcon(ruta));
		ruta = getClass().getResource("/Resources/refxy.png");
		lbl3 = new JLabel(new ImageIcon(ruta));
		rbg = new ButtonGroup();
		rb1 = new JRadioButton("Reflejar en X");
		rb1.setForeground(w.dos);
		rb1.setSelected(true);
		rb2 = new JRadioButton("Reflejar en Y");
		rb2.setForeground(w.dos);
		rb3 = new JRadioButton("Reflejar en X y Y");
		rb3.setForeground(w.dos);
		rbg.add(rb1); rbg.add(rb2); rbg.add(rb3);
		btnAllow = new JButton("Aceptar");
		btnAllow.setBackground(w.tres);
		btnAllow.setForeground(w.uno);
		btnCancel = new JButton("Cancelar");
		btnCancel.setBackground(w.tres);
		btnCancel.setForeground(w.uno);
		
		ruta = getClass().getResource("/Resources/refg.gif");
		lblimg = new JLabel(new ImageIcon(ruta));
		
		add(lbl1); add(rb1); add(lbl2); add(rb2); add(lbl3); add(rb3); add(lblimg); add(btnAllow); add(btnCancel);
		
		btnAllow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rb1.isSelected()) 
					rotType = 1;
				else
					if(rb2.isSelected())
						rotType = 2;
					else
						rotType = 3;
				setVisible(false);
				dispose();
			}	
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rotType = 4;
				setVisible(false);
				dispose();
			}
		});
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}
	
	public int showDialog() {
		setVisible(true);
		return rotType;
	}
}
