import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TranslateDialog extends JDialog{
	int cantTrans[]=new int[2];
	JLabel lbl1,lbl2,lbl3;
	JTextField tf1,tf2;
	JButton btnAllow,btnCancel;
	
	public TranslateDialog(MainInterface w,boolean modal) {
		super(w.W,modal);
		setTitle("Trasladar la figura");
		setSize(600,120);
		setLayout(new FlowLayout());
		setLocationRelativeTo(w.W);
		this.setBackground(w.uno);
		
		URL ruta = getClass().getResource("/Resources/move.png");
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
		add(lbl1); add(lbl2); add(tf1); add(lbl3); add(tf2); add(btnAllow); add(btnCancel);
		
		btnAllow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String res1 = tf1.getText(), res2 = tf2.getText();
				try {
					cantTrans[0]=Integer.parseInt(res1);
					cantTrans[1]=Integer.parseInt(res2);
				}catch (NumberFormatException e1) {
					cantTrans[0]=0;
					cantTrans[1]=0;
				}
				setVisible(false);
				dispose();
			}	
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cantTrans[0]=0;
				cantTrans[1]=0;
				setVisible(false);
				dispose();
			}
		});
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}
	
	public int[] showDialog() {
		setVisible(true);
		return cantTrans;
	}
}
