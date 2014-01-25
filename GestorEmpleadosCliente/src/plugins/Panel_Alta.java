package plugins;

import gestorempleados.control.NegocioCliente;
import gestorempleados.interficie.Panel_Contrato;
import gestorempleados.interficie.Panel_Curriculo;
import gestorempleados.interficie.Panel_DatosPersonales;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Panel para el alta de un nuevo empleado.
 * 
 * @author Guillermo Blanco Martínez
 *
 */
@SuppressWarnings("serial")
public class Panel_Alta extends JPanel implements Serializable{

	private JLabel altaPanelLabel;
	
	JPanel nuevo_contratoPanel;

	private JTabbedPane tabbedPane;
	
	public Panel_DatosPersonales datos_personales;
	public Panel_Curriculo datos_curriculo;
	public Panel_Contrato datos_contrato;
	
	/**
	 * Create the panel.
	 */
	public Panel_Alta()
	{

		datos_personales= new Panel_DatosPersonales();
		datos_curriculo= new Panel_Curriculo();
		datos_contrato= new Panel_Contrato();
		
		crearPanel();
	}

	public void crearPanel() {
		setBorder(null);
		setLayout(null);
		
		altaPanelLabel = new JLabel("Nuevo Empleado");
		altaPanelLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		altaPanelLabel.setBounds(24, 36, 158, 25);
		add(altaPanelLabel);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(27, 80, 708, 467);
		add(tabbedPane);
		
		tabbedPane.addTab("Datos", new ImageIcon(Panel_Alta.class.getResource("/com/sun/java/swing/plaf/windows/icons/DetailsView.gif")), datos_personales, null);
		tabbedPane.addTab("Curr\u00EDculo", new ImageIcon(Panel_Alta.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")), datos_curriculo, null);
		tabbedPane.addTab("Contrato", new ImageIcon(Panel_Alta.class.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")), datos_contrato, null);
		
		JButton guardarBtn = new JButton("Guardar");
		guardarBtn.setBounds(541, 49, 181, 35);
		add(guardarBtn);
		guardarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				NegocioCliente.getinstance().setEmpleado((Panel_Alta)((JButton)arg0.getSource()).getParent());
			}
		});
		guardarBtn.setIcon(new ImageIcon(Panel_Alta.class.getResource("/com/sun/java/swing/plaf/windows/icons/HardDrive.gif")));
		guardarBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));

	}
		
//	@Override
//	public void keyTyped(KeyEvent e) {
//		
//		JTextField source = (JTextField)e.getComponent();
//
//			if (source == nombreTxt || source == apellido1Txt || source == apellido2Txt) {
//				
//			     Pattern p = Pattern.compile("[^a-zA-Z0-9\\s]");  
//			     Matcher m = p.matcher(e.getKeyChar());  
//			  
//			     if (m.find())  
//			     {  
//			    	 System.out.println("Special Characers not Allowed", OAException.ERROR);  
//			     }
//			     
//		    	if (Character.isDigit(e.getKeyChar())){
//		    		nombreTxt.setForeground(Color.RED);
//		    		System.out.println(e.getKeyChar());
//		    	}
//		    } else if (source == apellido1Txt) {
//		    } else if (source == apellido2Txt) {
//		    }
//	}
	
	
	
	public boolean isCamposDatosOK() {
		return datos_personales.camposDatosOK;
	}

	public boolean isCamposContratoOK() {
		return datos_contrato.camposContratoOK;
	}
}
