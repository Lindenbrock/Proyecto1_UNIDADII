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

public class ShearyDialog extends JDialog{
	double cantShe[]=new double[2];
	JLabel lbl1,lbl2,lbl3,lblimg;
	JTextField tf1,tf2;
	JButton btnAllow,btnCancel;
	
	public ShearyDialog(MainInterface w,boolean modal) {
		super(w.W,modal);
		setTitle("Deformar la figura");
		setSize(600,220);
		setLayout(new FlowLayout());
		setLocationRelativeTo(w.W);
		this.setBackground(w.uno);
		w.setBackground(w.uno);
		
		Image icon = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Resources/icon.png"));
		setIconImage(icon);
		
		URL ruta = getClass().getResource("/Resources/sheary.png");
		lbl1 = new JLabel(new ImageIcon(ruta));
		lbl2 = new JLabel("Cantidad a deformar en x");
		lbl2.setForeground(w.dos);
		tf1 = new JTextField(10);
		lbl3 = new JLabel("Cantidad a deformar en y");
		lbl3.setForeground(w.dos);
		tf2 = new JTextField(10);
		btnAllow = new JButton("Aceptar");
		btnAllow.setBackground(w.tres);
		btnAllow.setForeground(w.uno);
		btnCancel = new JButton("Cancelar");
		btnCancel.setBackground(w.tres);
		btnCancel.setForeground(w.uno);
		
		ruta = getClass().getResource("/Resources/shearyg.gif");
		lblimg = new JLabel(new ImageIcon(ruta));
		
		add(lbl1); add(lbl2); add(tf1); add(lbl3); add(tf2); add(lblimg); add(btnAllow); add(btnCancel);
		
		btnAllow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String res1 = tf1.getText(), res2 = tf2.getText();
				try {
					cantShe[0]=Double.parseDouble(res1);
					cantShe[1]=Double.parseDouble(res2);
				}catch (NumberFormatException e1) {
					cantShe[0]=0.0;
					cantShe[1]=0.0;
				}
				setVisible(false);
				dispose();
			}	
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cantShe[0]=0.0;
				cantShe[1]=0.0;
				setVisible(false);
				dispose();
			}
		});
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}
	
	public double[] showDialog() {
		setVisible(true);
		return cantShe;
	}
}
