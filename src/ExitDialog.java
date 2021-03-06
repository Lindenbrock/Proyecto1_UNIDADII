import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class ExitDialog extends JDialog implements ActionListener{
	boolean ans;
	JLabel lbl;
	JButton btnAllow,btnCancel;
	
	public ExitDialog(MainInterface w,boolean modal) {
		super(w.W,modal);
		setTitle("Salir de Transformaciones UI");
		setSize(250,100);
		setLayout(new FlowLayout());
		setLocationRelativeTo(w.W);
		this.setBackground(w.uno);
		
		Image icon = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Resources/icon.png"));
		setIconImage(icon);
		
		lbl = new JLabel("?Est?s seguro de que deseas salir?");
		lbl.setForeground(w.dos);
		btnAllow = new JButton("Aceptar");
		btnAllow.setBackground(w.tres);
		btnAllow.setForeground(w.uno);
		btnCancel = new JButton("Cancelar");
		btnCancel.setBackground(w.tres);
		btnCancel.setForeground(w.uno);
		
		add(lbl); add(btnAllow); add(btnCancel);
		
		btnAllow.addActionListener(this);
		
		btnCancel.addActionListener(this);
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}
	
	public boolean showDialog() {
		setVisible(true);
		return ans;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAllow)
			ans = true;
		else
			ans = false;
		setVisible(false);
		dispose();
	}
}
