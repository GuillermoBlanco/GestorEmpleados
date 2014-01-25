package gestorempleados.interficie;

import gestorempleados.control.NegocioCliente;
import gestorempleados.control.Paises;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gestorempleados.control.RestriccionesCeldas;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.BorderLayout;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Panel que muestra/recoge los datos personales del empleado.
 * 
 * @author Guillermo Blanco Martínez
 *
 */
@SuppressWarnings({"serial","rawtypes","unchecked"})
public class Panel_DatosPersonales extends JPanel implements DocumentListener {

	private JPanel dniPanel;
	private JTextField dniTxt;
	private JPanel nssPanel;
	private JTextField nssTxt;
	private JLabel nssLbl;
	private JPanel nombrePanel;
	private JTextField nombreTxt;
	private JPanel apellido1Panel;
	private JTextField apellido1Txt;
	private JPanel apellido2Panel;
	private JTextField apellido2Txt;
	private JPanel nacionalidadPanel;
	private JComboBox nacionalidadCmb;
	private Calendario calendario;
	private JTextField ccTxt;
	private JPanel direccionPanel;
	private JTextArea direccTxtA;
	private JPanel poblacionPanel;
	private JTextField poblacionTxt;
	private JPanel paisPanel;
	private JComboBox paisCmb;
	private JPanel telefonoPanel;
	private JTextField telefonoTxt;
	private JButton btnFoto;
	
	public boolean camposDatosOK;
	protected File temp;
	
	/**
	 * Create the panel.
	 */
	public Panel_DatosPersonales() {

		setBorder(null);
		setBorder(null);
		setLayout(null);
		
		dniPanel = new JPanel();
		dniPanel.setBounds(25, 22, 242, 41);
		dniPanel.setBorder(new TitledBorder(new CompoundBorder(), "DNI", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(dniPanel);
		dniPanel.setLayout(null);
		
		dniTxt = new JTextField();
		dniTxt.setBounds(50, 3, 89, 20);
		dniTxt.setDocument(new RestriccionesCeldas(RestriccionesCeldas.tipo.DNI));
		dniTxt.getDocument().putProperty("owner", dniTxt);
		dniTxt.getDocument().addDocumentListener(this);
		dniPanel.add(dniTxt);
		
		JButton validateDNIBtn = new JButton("Validate");
		validateDNIBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!dniTxt.equals(""))	
					if (NegocioCliente.getinstance().validateId(dniTxt.getText()))
						JOptionPane.showMessageDialog(null, "No hay ningún empleado dado de alta con ese DNI", "Validate", JOptionPane.INFORMATION_MESSAGE);
					else JOptionPane.showMessageDialog(null, "Ya existe un empleado dado de alta con ese DNI", "Validate", JOptionPane.ERROR_MESSAGE);
			}
		});
		validateDNIBtn.setBounds(175, 4, 59, 20);
		dniPanel.add(validateDNIBtn);
		validateDNIBtn.setFont(new Font("Tahoma", Font.PLAIN, 7));
		
		nssPanel = new JPanel();
		nssPanel.setBounds(25, 73, 249, 39);
		add(nssPanel);
		nssPanel.setBorder(new TitledBorder(new CompoundBorder(), "N.S.S.", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		nssPanel.setLayout(null);
		
		// NSS  --- data
		
		nssTxt = new JTextField();
		nssTxt.setBounds(49, 0, 114, 20);
		nssTxt.setDocument(new RestriccionesCeldas(RestriccionesCeldas.tipo.NSS));
		nssTxt.getDocument().putProperty("owner", nssTxt);
		nssTxt.getDocument().addDocumentListener(this);
		nssTxt.setColumns(10);
		nssPanel.add(nssTxt);
		
		nssLbl = new JLabel("* Debe figurar");
		nssLbl.setVisible(false);
		nssLbl.setForeground(Color.RED);
		nssLbl.setBounds(58, 22, 99, 14);
		nssPanel.add(nssLbl);
		
		JButton validateNSSBtn = new JButton("Validate");
		validateNSSBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!nssTxt.equals(""))	
					if (NegocioCliente.getinstance().validateId(nssTxt.getText()))
						JOptionPane.showMessageDialog(null, "No hay ningún empleado dado de alta con ese NSS", "Validate", JOptionPane.INFORMATION_MESSAGE);
					else JOptionPane.showMessageDialog(null, "Ya existe un empleado dado de alta con ese NSS", "Validate", JOptionPane.ERROR_MESSAGE);
			}
		});
		validateNSSBtn.setFont(new Font("Tahoma", Font.PLAIN, 7));
		validateNSSBtn.setBounds(175, 0, 59, 20);
		nssPanel.add(validateNSSBtn);
		
		nombrePanel = new JPanel();
		nombrePanel.setBounds(25, 121, 154, 51);
		add(nombrePanel);
		nombrePanel.setBorder(new TitledBorder(new CompoundBorder(), "Nombre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		nombrePanel.setLayout(null);
		
		nombreTxt = new JTextField();
		nombreTxt.setBounds(10, 20, 130, 20);
		nombreTxt.setDocument(new RestriccionesCeldas(RestriccionesCeldas.tipo.NOMBRE));
		nombreTxt.getDocument().putProperty("owner", nombreTxt);
		nombreTxt.getDocument().addDocumentListener(this);
		nombreTxt.setColumns(10);
		nombrePanel.add(nombreTxt);
		
		apellido1Panel = new JPanel();
		apellido1Panel.setBounds(25, 174, 160, 51);
		add(apellido1Panel);
		apellido1Panel.setBorder(new TitledBorder(new CompoundBorder(), "Primer apellido", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		apellido1Panel.setLayout(null);
		
		apellido1Txt = new JTextField();
		apellido1Txt.setBounds(10, 20, 130, 20);
		apellido1Txt.setDocument(new RestriccionesCeldas(RestriccionesCeldas.tipo.APELLIDO1));
		apellido1Txt.getDocument().putProperty("owner", apellido1Txt);
		apellido1Txt.getDocument().addDocumentListener(this);
		apellido1Panel.add(apellido1Txt);
		apellido1Txt.setColumns(10);
		
		apellido2Panel = new JPanel();
		apellido2Panel.setBounds(186, 174, 155, 48);
		add(apellido2Panel);
		apellido2Panel.setBorder(new TitledBorder(new CompoundBorder(), "Segundo apellido", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		apellido2Panel.setLayout(null);
		
		apellido2Txt = new JTextField();
		apellido2Txt.setBounds(10, 17, 130, 20);
		apellido2Txt.setDocument(new RestriccionesCeldas(RestriccionesCeldas.tipo.APELLIDO2));
		apellido2Txt.getDocument().putProperty("owner", apellido2Txt);
		apellido2Txt.getDocument().addDocumentListener(this);
		apellido2Panel.add(apellido2Txt);
		apellido2Txt.setColumns(10);
		
		nacionalidadPanel = new JPanel();
		nacionalidadPanel.setBounds(24, 247, 155, 51);
		add(nacionalidadPanel);
		nacionalidadPanel.setBorder(new TitledBorder(new CompoundBorder(), "Nacionalidad", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		nacionalidadPanel.setLayout(null);
		
		nacionalidadCmb = new JComboBox();
		nacionalidadCmb.setModel(new DefaultComboBoxModel(Paises.values()));
		nacionalidadCmb.setBounds(10, 20, 129, 20);
		nacionalidadPanel.add(nacionalidadCmb);
		
		JPanel fecha_nacimientoPanel = new JPanel();
		fecha_nacimientoPanel.setBorder(new TitledBorder(null, "Fecha de nacimiento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		fecha_nacimientoPanel.setBounds(23, 319, 274, 90);
		add(fecha_nacimientoPanel);
		fecha_nacimientoPanel.setLayout(null);
		
		calendario = new Calendario();
		calendario.setBounds(12, 26, 252, 54);
		fecha_nacimientoPanel.add(calendario);
		
		JPanel ccPanel = new JPanel();
		ccPanel.setBounds(394, 22, 249, 50);
		add(ccPanel);
		ccPanel.setBorder(new TitledBorder(new CompoundBorder(), "Cuenta corriente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		ccPanel.setLayout(null);
		
		// CUENTA CORRIENTE -- data
		
		ccTxt = new JTextField();
		ccTxt.setBounds(10, 22, 210, 20);
		ccTxt.setDocument(new RestriccionesCeldas(RestriccionesCeldas.tipo.CC));
		ccTxt.getDocument().putProperty("owner", ccTxt);
		ccTxt.getDocument().addDocumentListener(this);
		ccPanel.add(ccTxt);
		ccTxt.setColumns(10);
		
		JPanel datos_contactoPanel = new JPanel();
		datos_contactoPanel.setBounds(391, 83, 300, 337);
		add(datos_contactoPanel);
		datos_contactoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos de contacto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		datos_contactoPanel.setLayout(null);
		
		direccionPanel = new JPanel();
		direccionPanel.setBounds(10, 25, 278, 109);
		datos_contactoPanel.add(direccionPanel);
		direccionPanel.setBorder(new TitledBorder(new CompoundBorder(), "Direcci\u00F3n postal", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		direccionPanel.setLayout(null);
		
		JPanel direccTxtP = new JPanel();
		direccTxtP.setBorder(new LineBorder(new Color(0, 0, 0)));
		direccTxtP.setBounds(10, 25, 257, 82);
		direccionPanel.add(direccTxtP);
		direccTxtP.setLayout(new BorderLayout(0, 0));
		
		// DIRECCION  --- data
		
		direccTxtA = new JTextArea(3,5);
		direccTxtA.setTabSize(10);
		direccTxtA.setWrapStyleWord(true);
		direccTxtA.setLineWrap(true);
		direccTxtA.setDocument(new RestriccionesCeldas(RestriccionesCeldas.tipo.DIRECCION));
		direccTxtA.getDocument().putProperty("owner", direccTxtA);
		direccTxtA.getDocument().addDocumentListener(this);
		direccTxtP.add(direccTxtA, BorderLayout.CENTER);
		
		poblacionPanel = new JPanel();
		poblacionPanel.setBorder(new TitledBorder(new CompoundBorder(), "Poblaci\u00F3n", TitledBorder.LEFT, TitledBorder.BELOW_TOP, null, null));
		poblacionPanel.setBounds(20, 145, 169, 47);
		datos_contactoPanel.add(poblacionPanel);
		poblacionPanel.setLayout(null);
		
		poblacionTxt = new JTextField();
		poblacionTxt.setBounds(11, 27, 149, 20);
		poblacionTxt.setDocument(new RestriccionesCeldas(RestriccionesCeldas.tipo.POBLACION));
		poblacionTxt.getDocument().putProperty("owner", poblacionTxt);
		poblacionTxt.getDocument().addDocumentListener(this);
		poblacionTxt.setColumns(10);
		poblacionPanel.add(poblacionTxt);
		
		paisPanel = new JPanel();
		paisPanel.setBorder(new TitledBorder(new CompoundBorder(), "Pa\u00EDs", TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, null));
		paisPanel.setBounds(20, 200, 134, 47);
		datos_contactoPanel.add(paisPanel);
		paisPanel.setLayout(null);
		
		// PAIS  --- data
		
		paisCmb = new JComboBox();
		paisCmb.setModel(new DefaultComboBoxModel(Paises.values()));
		paisCmb.setBounds(10, 27, 122, 20);
		paisPanel.add(paisCmb);
		
		telefonoPanel = new JPanel();
		telefonoPanel.setBounds(20, 276, 128, 47);
		datos_contactoPanel.add(telefonoPanel);
		telefonoPanel.setBorder(new TitledBorder(new CompoundBorder(), "Tel\u00E9fono de contacto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		telefonoPanel.setLayout(null);
		
		// TELEFONO -- data
		
		telefonoTxt = new JTextField();
		telefonoTxt.setBounds(10, 22, 118, 20);
		telefonoTxt.setDocument(new RestriccionesCeldas(RestriccionesCeldas.tipo.TELEFONO));
		telefonoTxt.getDocument().putProperty("owner", telefonoTxt);
		telefonoTxt.getDocument().addDocumentListener(this);
		telefonoPanel.add(telefonoTxt);
		telefonoTxt.setColumns(10);
		
		btnFoto = new JButton("Foto");
		btnFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooseImage = new JFileChooser();
				
				chooseImage.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooseImage.setApproveButtonText("Seleccionar");
				chooseImage.showOpenDialog(null);
				
				temp = chooseImage.getSelectedFile();
				
				if(temp!=null)setFoto(temp);
			}
		});
		btnFoto.setToolTipText("Pulse para cambiar la foto");
		btnFoto.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnFoto.setBackground(Color.LIGHT_GRAY);
		btnFoto.setBounds(277, 18, 94, 106);
		add(btnFoto);
	}

	public String getNombre() {
		if (nombreTxt.getText().equals(""))
		{
			camposDatosOK=false;
		}
		return nombreTxt.getText();
	}
	
	public void setNombre(String nombre) {
		this.nombreTxt.setText(nombre);
	}
	
	public String getApellido1() {
		String apellido1;
		if (apellido1Txt.getText().equals(""))apellido1=null;
		else apellido1=apellido1Txt.getText();
		return apellido1;
	}
	
	public void setApellido1(String apellido1Txt) {
		this.apellido1Txt.setText(apellido1Txt);
	}
	
	public String getApellido2() {
		String apellido2;
		if (apellido2Txt.getText().equals(""))apellido2=null;
		else apellido2=apellido2Txt.getText();
		return apellido2;
	}
	
	public void setApellido2(String apellido2Txt) {
		this.apellido2Txt.setText(apellido2Txt);
	}
	
	public Integer getNss() {
		Integer nss;
		if (!nssTxt.getText().equals(""))
		{
			nssLbl.setVisible(false);
			nss=Integer.parseInt(nssTxt.getText());
			camposDatosOK=true;
		}
		else {
			nss=null;
			nssLbl.setVisible(true);
			camposDatosOK=false;
		}
		return nss;
	}
	
	public void setNss(String nssTxt) {
		this.nssTxt.setText(nssTxt);
	}
	
	public String getDni() {
		String dni;
		if (dniTxt.getText().equals(""))
		{
			dni=null;
			camposDatosOK=false;
			
		}else dni=dniTxt.getText();
		return dni;
	}
	
	public void setDni(String dniLbl) {
		this.dniTxt.setText(dniLbl);
	}
	
	public String getNacionalidad() {
		
		return nacionalidadCmb.getSelectedItem().toString();
	}
	
	//SELECCIONAR NACIONALIDAD EMPLEADO
	public void setNacionalidad(String nacionalidad) {
		
		for (int i=0; i<nacionalidadCmb.getItemCount();i++)
		{
			if (nacionalidadCmb.getItemAt(i).toString().equals(nacionalidad))
			{
				nacionalidadCmb.setSelectedIndex(i);
			}
		}
	}
	
	//COMPLETAR OBTENCION DE FECHA
	public Date getFecha_nacimiento() {
		Date result=null;
		try {
		    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		    
			result = df.parse(calendario.getDia()+"-"+calendario.getMes()+"-"+calendario.getAno());

		} catch (ParseException e) {
			System.out.println("Fecha incorrecta");
			e.printStackTrace();
		} 
		return result;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha_nacimiento);
		
		calendario.setDia(cal.get(Calendar.DAY_OF_MONTH));
		calendario.setMes(cal.get(Calendar.MONTH)+1);
		calendario.setAno(cal.get(Calendar.YEAR));
	}
	
	public String getDireccion() {
		String direcc;
		if (direccTxtA.getText().equals(""))direcc=null;	
		else direcc=direccTxtA.getText();
		return direcc;
	}
	
	public void setDireccion(String direcc) {
		this.direccTxtA.setText(direcc);
	}
	
	public String getPoblacion() {
		String poblacion;
		if (poblacionTxt.getText().equals(""))poblacion=null;	
		else poblacion=poblacionTxt.getText();
		return poblacion;
	}
	
	public void setPoblacion(String poblacion) {
		this.poblacionTxt.setText(poblacion);
	}
	public Integer getTelefono() {
		Integer telef;
		if (!telefonoTxt.getText().equals("")) telef=Integer.parseInt(telefonoTxt.getText());
		else  telef=null;
		return telef;
	}
	
	public void setTelefono(int telefono) {
		this.telefonoTxt.setText(String.valueOf(telefono));
	}
	
	public Integer getCc() {
		Integer cc;
		if (!ccTxt.getText().equals("")) cc=Integer.parseInt(ccTxt.getText());
		else  cc=null;
		return cc;
	}
	
	public void setCc(int cc) {
		this.ccTxt.setText(String.valueOf(cc));
	}
	
	//HACER CARGA / VISTA FOTO (EXTRAER IMAGENES DEL ICONO)
	public File getFoto() {
		return temp;
	}
	public void setFoto(File foto) {
		this.btnFoto.setIcon(new ImageIcon(foto.getPath()));
	}
	
	@Override
	public void changedUpdate(DocumentEvent arg0) {

	}

	@Override
	public void insertUpdate(DocumentEvent e) {
Object source = e.getDocument().getProperty("owner");
		
		if (source == nombreTxt) {
			
			String texto = nombreTxt.getText();

		    Pattern p = Pattern.compile("[^a-zA-Z]");  
		    Matcher m = p.matcher(texto);  
		  
		    if (m.find())  
		    {  
		    	nombreTxt.setForeground(Color.RED);
		    	camposDatosOK=false;
		    	System.out.println("Special Characers not Allowed");
		    }
		    else {
		    	nombreTxt.setForeground(Color.BLACK);
		    	camposDatosOK=true;
		    }

	    } else if (source == apellido1Txt) {
	    	
			String texto = apellido1Txt.getText();

		    Pattern p = Pattern.compile("[^a-zA-Z]");  
		    Matcher m = p.matcher(texto);  
		  
		    if (m.find())  
		    {  
		    	apellido1Txt.setForeground(Color.RED);
		    	camposDatosOK=false;
		    	System.out.println("Special Characers not Allowed");
		    }
		    else {
		    	apellido1Txt.setForeground(Color.BLACK);
		    	camposDatosOK=true;
		    }
	    	
	    } else if (source == apellido2Txt) {
	    	
			String texto = apellido2Txt.getText();

		    Pattern p = Pattern.compile("[^a-zA-Z]");  
		    Matcher m = p.matcher(texto);  
		  
		    if (m.find())  
		    {  
		    	apellido2Txt.setForeground(Color.RED);
		    	camposDatosOK=false;
		    	System.out.println("Special Characers not Allowed");
		    }
		    else {
		    	apellido2Txt.setForeground(Color.BLACK);
		    	camposDatosOK=true;
		    }
	    	
	    }

	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		insertUpdate(e);		
	}
	
	public void disableKey()
	{
		dniTxt.setEditable(false);
		nssTxt.setEditable(false);
	}

}
