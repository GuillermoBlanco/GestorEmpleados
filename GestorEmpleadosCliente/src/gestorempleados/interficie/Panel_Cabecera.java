package gestorempleados.interficie;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * Panel con la cabecera de la aplicación.
 * 
 * @author Guillermo Blanco Martínez.
 *
 */
@SuppressWarnings("serial")

public class Panel_Cabecera extends JPanel {
	
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel logoLbl;
	private ImageIcon logo;
	
	/**
	 * Create the panel.
	 */
	public Panel_Cabecera(String name) {
		
		//AJUSTAR ELEMENTOS EN CABECERA
		setLayout(new BorderLayout());
		
		logo = new ImageIcon("img/Logo.png");
		logoLbl = new JLabel(logo);
		add(logoLbl,BorderLayout.WEST);
		
		lblNewLabel = new JLabel("Usuario: "+name.toUpperCase());
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(800, 9, 200, 60);
		add(lblNewLabel,BorderLayout.SOUTH);

		lblNewLabel_1 = new JLabel("por Guillermo Blanco");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(603, 62, 128, 14);
		add(lblNewLabel_1,BorderLayout.NORTH);
		
		lblNewLabel_2 = new JLabel("Gestor de empleados");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tekton Pro", Font.PLAIN, 64));
		lblNewLabel_2.setBounds(27, 138, 436, 180);
		add(lblNewLabel_2,BorderLayout.CENTER);
		
		

	}
}
