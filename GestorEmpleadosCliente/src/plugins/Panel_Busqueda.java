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
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Panel para la búsqueda y consulta de un empleado.
 * 
 * @author Guillermo Blanco Martínez
 *
 */
@SuppressWarnings("serial")
public class Panel_Busqueda extends JPanel implements Serializable{

	private JLabel altaPanelLabel;
	
	JPanel nuevo_contratoPanel;

	private JTabbedPane tabbedPane;
	
	public Panel_DatosPersonales datos_personales;
	public Panel_Curriculo datos_curriculo;
	public Panel_Contrato datos_contrato;
	
	private JButton btnInforme;
	private JTextField buscarDniTxt;
	
	/**
	 * Create the panel.
	 */
	public Panel_Busqueda()
	{

		datos_personales= new Panel_DatosPersonales();
//		datos_personales.setCamposDisable();
		datos_curriculo= new Panel_Curriculo();
		datos_contrato= new Panel_Contrato();
		
		crearPanel();
	}

	public void crearPanel() {
		setBorder(null);
		setLayout(null);
		
		altaPanelLabel = new JLabel("Consulta Empleado");
		altaPanelLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		altaPanelLabel.setBounds(24, 36, 225, 25);
		add(altaPanelLabel);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(27, 80, 708, 467);
		add(tabbedPane);
		
		tabbedPane.addTab("Datos", new ImageIcon(Panel_Busqueda.class.getResource("/com/sun/java/swing/plaf/windows/icons/DetailsView.gif")), datos_personales, null);
		tabbedPane.addTab("Curr\u00EDculo", new ImageIcon(Panel_Busqueda.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")), datos_curriculo, null);
		tabbedPane.addTab("Contrato", new ImageIcon(Panel_Busqueda.class.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")), datos_contrato, null);
		
		JPanel buscarPanel = new JPanel();
		buscarPanel.setBorder(new TitledBorder(null, "Introduce el DNI del empleado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		buscarPanel.setBounds(442, 31, 276, 53);
		add(buscarPanel);
		buscarPanel.setLayout(null);
		
		JButton buscarBtn = new JButton("Buscar");
		buscarBtn.setBounds(152, 19, 114, 25);
		buscarPanel.add(buscarBtn);
		buscarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!buscarDniTxt.getText().equals(""))
				{
					if(NegocioCliente.getinstance().showDatosEmpleado(datos_personales,buscarDniTxt.getText()))
					{
					NegocioCliente.getinstance().showContratoEmpleado(datos_contrato,buscarDniTxt.getText());
					NegocioCliente.getinstance().showCVEmpleado(datos_curriculo,buscarDniTxt.getText());
					
					btnInforme.setVisible(true);
					}
					
				}
			}
		});
		buscarBtn.setIcon(new ImageIcon(Panel_Busqueda.class.getResource("/com/sun/java/swing/plaf/windows/icons/HardDrive.gif")));
		buscarBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		buscarDniTxt = new JTextField();
		buscarDniTxt.setBounds(15, 23, 114, 20);
		buscarPanel.add(buscarDniTxt);
		buscarDniTxt.setColumns(10);
		
		btnInforme = new JButton("Informe");
		btnInforme.setVisible(false);
		btnInforme.setBounds(325, 51, 89, 23);
		btnInforme.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				NegocioCliente.getinstance().getReporte("Empleado", buscarDniTxt.getText());
			}
		});
		
		add(btnInforme);

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
