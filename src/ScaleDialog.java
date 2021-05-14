import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ScaleDialog extends JDialog{
	double cantSca;
	JLabel lbl1,lbl2,lblimg;
	JTextField tf1;
	JButton btnAllow,btnCancel;
	
	public ScaleDialog(MainInterface w,boolean modal) {
		super(w.W,modal);
		setTitle("Escalar la figura");
		setSize(350,200);
		setLayout(new FlowLayout());
		setLocationRelativeTo(w.W);
		this.setBackground(w.uno);
		
		Image icon = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Resources/icon.png"));
		setIconImage(icon);
		
		URL ruta = getClass().getResource("/Resources/scale.png");
		lbl1 = new JLabel(new ImageIcon(ruta));
		lbl2 = new JLabel("Cantidad a escalar");
		lbl2.setForeground(w.dos);
		tf1 = new JTextField(10);
		btnAllow = new JButton("Aceptar");
		btnAllow.setBackground(w.tres);
		btnAllow.setForeground(w.uno);
		btnCancel = new JButton("Cancelar");
		btnCancel.setBackground(w.tres);
		btnCancel.setForeground(w.uno);
		
		ruta = getClass().getResource("/Resources/scaleg.gif");
		lblimg = new JLabel(new ImageIcon(ruta));
		
		add(lbl1); add(lbl2); add(tf1); add(lblimg); add(btnAllow); add(btnCancel);
		
		btnAllow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String res = tf1.getText();
				try {
					cantSca=Double.parseDouble(res);
				}catch (NumberFormatException e1) {
					cantSca=1.0;
				}
				setVisible(false);
				dispose();
			}	
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cantSca=1.0;
				setVisible(false);
				dispose();
			}
		});
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}
	
	public double showDialog() {
		setVisible(true);
		return cantSca;
	}
}
