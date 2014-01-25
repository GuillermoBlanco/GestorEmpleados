package gestorempleados.interficie;

import gestorempleados.control.Preferencias;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

/**
 * Panel de login de usuario.
 * 
 * @author Guillermo Blanco Martínez
 *
 */
@SuppressWarnings("serial")
public class Panel_Login extends JPanel {


	private JLabel passLbl;
	private JLabel user;
	private JTextField nameTxt;
	private JPasswordField passTxt;
	
	private JButton btnIp;
	
	public Panel_Login() {
		
		setForeground(Color.WHITE);
		setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setBounds(411, 227, 159, 236);
		setLayout(null);
		
		user = new JLabel("Usuario");
		user.setForeground(SystemColor.activeCaptionText);
		user.setFont(new Font("Tahoma", Font.BOLD, 24));
		user.setBounds(24, 11, 108, 29);
		add(user);
		user.setHorizontalAlignment(SwingConstants.CENTER);
		
		passLbl = new JLabel("Pass");
		passLbl.setForeground(SystemColor.activeCaptionText);
		passLbl.setFont(new Font("Tahoma", Font.BOLD, 24));
		passLbl.setBounds(24, 77, 108, 29);
		add(passLbl);
		passLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		nameTxt = new JTextField();
		nameTxt.setToolTipText("Indica el usuario");
		nameTxt.setBounds(10, 43, 139, 27);
		add(nameTxt);
		nameTxt.setColumns(10);
		
		passTxt = new JPasswordField();
		passLbl.setLabelFor(passTxt);
		passTxt.setToolTipText("Introduce el pass");
		passTxt.setBounds(11, 106, 139, 29);
		add(passTxt);
		passTxt.setColumns(10);
		
		JButton validateBtn = new JButton("Log in");
		validateBtn.setToolTipText("Pulsa para logearte");
		validateBtn.setBounds(25, 158, 108, 23);
		add(validateBtn);
		
		btnIp = new JButton("IP");
		btnIp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String ip = JOptionPane.showInputDialog("Indica la IP del servidor", "localhost");
				Preferencias.setHost(ip);
			}
		});
		btnIp.setBounds(50, 192, 59, 23);
		add(btnIp);
		validateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((StartClient) SwingUtilities.getWindowAncestor((JButton)arg0.getSource())).confirmaUsuario(nameTxt.getText(),String.valueOf(passTxt.getPassword()));
			}
		});
	}
}
