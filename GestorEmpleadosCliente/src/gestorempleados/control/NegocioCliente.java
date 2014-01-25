package gestorempleados.control;

import gestorempleados.conector.ConectorCliente;
import gestorempleados.conector.Packet;
import gestorempleados.conector.PacketType;
import gestorempleados.interficie.Panel_Contrato;
import gestorempleados.interficie.Panel_Curriculo;
import gestorempleados.interficie.Panel_DatosPersonales;
import gestorempleados.persistencia.*;
import gestorempleados.persistencia.Idiomas;
import plugins.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


import plugins.Panel_Alta;

/**
 * Determina el tipo de llamadas que se harán al sevidor creando los tipo de paquetes necesarios.
 * 
 * @author Guillermo Blanco Martínez
 *
 */
@SuppressWarnings({"rawtypes","unchecked","unused"})
public class NegocioCliente {
	
	private static NegocioCliente control= null;
	private ConectorCliente conector;
	
	private Usuarios usuario;

	private List empleados;
	
	private String dni;
	private Integer nss;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String nacionalidad;
	private String direccion;
	private String poblacion;
	private Integer telefono;
	private Date fechaNacimiento;
	private String titulacion;
	private Set idiomas;
	private String experiencia;
	private Integer cuentaBancaria;
	private byte[] foto;
	
	private Cargo cargo;
	private String motivo;
	private String origen;
	private String sede;
	private Integer salario;
	private String modalidad;
	private String clase;
	private Date fechaInicio;
	private Date fechaFin;
	private Integer periodoPrueba;
	private Integer preaviso;
	private boolean clausula;
	private boolean salud;
	private boolean vida;
	private byte[] fichero;
	private String causaBaja;
	
	private Date fechaInicioDesplaz;
	private Date fechaFinDesplaz;

	private boolean activo;
	
	/**
	 * Instancia el conector que enviará los paquetes al servidor.
	 */
	public NegocioCliente() {
		
		conector = new ConectorCliente();
	}
	
	public static NegocioCliente getinstance(){
		
		if (control==null)
		{
			control= new NegocioCliente();
		}
		
		return control;
	}
	
	/**
	 * Crea la petición del registro de empleados almacenado en el servidor.
	 * 
	 * @param tableModel {@link DefaultTableModel} La tabla que en la que se cargarán los empleados.
	 */
	public void getEmpleados(DefaultTableModel tableModel) {
		
		System.out.println("get empleados!!!!");
		
		Packet outPacket = new Packet(PacketType.Consulta);
		Packet inPacket = conector.sendPacket(outPacket);
		
		System.out.println("paquete devuelto!!!!");
		
		empleados = inPacket.getEmpleados();
		
		for(Iterator it = empleados.iterator(); it.hasNext();)
		{
		
			Empleado e =(Empleado) it.next();
			boolean activo=false;
			String departamento = "";
			
			for(Iterator itContrato = e.getContratos().iterator(); itContrato.hasNext();)
			{
				Contrato contrato = (Contrato) itContrato.next();
				if (contrato.isActivo()) 
				{
					activo=true;
					departamento=contrato.getCargo().getDepartamento().getDescripcion();
				}
			}
			
			Object[] o = new Object[8];

			o[0]= e.getDni();
			o[1]= e.getNombre();
			o[2]= e.getApellido1();
			o[3]= e.getApellido2();
			o[4]= e.getNacionalidad();
			o[5]= e.getNss();
			o[6]= departamento;
			o[7]= activo;
			
			tableModel.addRow(o);
		}
	}
	
	/**
	 * Crea la petición para guardar los datos de un nuevo empleado en el servidor.
	 * Si todos los datos indicados son correctos muestra un mensaje de éxito en el guardado.
	 * 
	 * @param panel {@link Panel_Alta} El panel del cual se recogerán los datos del empleado.
	 */
	public void setEmpleado(Panel_Alta panel){
		
		System.out.println("set empleados!!!!");
		

		// Empleado
		dni=panel.datos_personales.getDni();
		nss=panel.datos_personales.getNss();
		nombre=panel.datos_personales.getNombre();
		apellido1=panel.datos_personales.getApellido1();
		apellido2=panel.datos_personales.getApellido2();
		nacionalidad=panel.datos_personales.getNacionalidad().toString();
		direccion=panel.datos_personales.getDireccion();
		poblacion=panel.datos_personales.getPoblacion();
		telefono=panel.datos_personales.getTelefono();
		fechaNacimiento=panel.datos_personales.getFecha_nacimiento();
		cuentaBancaria=panel.datos_personales.getCc();
		
		titulacion=panel.datos_curriculo.getTitulacion();
		idiomas=panel.datos_curriculo.getIdiomas();
		experiencia=panel.datos_curriculo.getExperiencia();
		
		try {
		if(panel.datos_personales.getFoto()!=null)
			foto=Files.readAllBytes(panel.datos_personales.getFoto().toPath());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//	Contrato
		cargo=(Cargo) panel.datos_contrato.getCargo();
		motivo=panel.datos_contrato.getMotivo();
		origen=panel.datos_contrato.getOrigen();
		sede=panel.datos_contrato.getSede();
		salario=panel.datos_contrato.getSalario();
		modalidad=panel.datos_contrato.getModalidad();
		clase=panel.datos_contrato.getClase();
		fechaInicio=panel.datos_contrato.getFecha_inicio();
		fechaFin=panel.datos_contrato.getFecha_fin();
		periodoPrueba=panel.datos_contrato.getPeriodoPrueba();
		preaviso=panel.datos_contrato.getPreaviso();
		clausula=panel.datos_contrato.getClausula();
		salud=panel.datos_contrato.getSalud();
		vida=panel.datos_contrato.getVida();
		fichero=null;
		causaBaja=null;
		activo=panel.datos_contrato.getActivo();
		fechaInicioDesplaz=new Date();
		fechaFinDesplaz=new Date();
		
		if(panel.isCamposDatosOK()  && panel.isCamposContratoOK())
		{
			//TEXTO VALIDADO SE CREA Y ENVIA EL EMPLEADO
			Empleado empleadoNuevo = new Empleado(dni,nss, 
					nombre, apellido1, apellido2, nacionalidad,
					direccion, poblacion, telefono, fechaNacimiento, 
					titulacion, experiencia, cuentaBancaria, foto);
			
			Set empleadoIdiomas = new HashSet();
			
			for (Object idioma : idiomas)
			{
				Idiomas i = new Idiomas(empleadoNuevo,idioma.toString());
				empleadoIdiomas.add(i);
			}
			
			empleadoNuevo.setIdiomas(empleadoIdiomas);

			 Contrato contrato = new Contrato(fichero, cargo, fichero, 
					 empleadoNuevo, fichero,  origen, modalidad, motivo, clase, 
					 periodoPrueba, preaviso, clausula, fechaInicio, fechaFin, causaBaja, 
					 activo, salario, sede, fechaInicioDesplaz, fechaFinDesplaz, salud, vida);
			 
			 Set<Contrato> contra = new HashSet<Contrato>();
			 contra.add(contrato);
			 
			 empleadoNuevo.setContratos(contra);
			 			
			ArrayList eList = new ArrayList<Empleado>();
			eList.add(empleadoNuevo);

			Packet outPacket = new Packet(PacketType.Alta);
			outPacket.setEmpleados(eList);
			
			Packet inPacket = conector.sendPacket(outPacket);
			
			switch (inPacket.getType()) {
			case PacketType.Confirmacion:
				
					//IMPLEMENTAR RESPUESTA DE ALTA RECIBIDA
				JOptionPane.showMessageDialog(null, inPacket.getFiltro(), "Guardar empleado", JOptionPane.INFORMATION_MESSAGE);
				panel.datos_contrato.camposContratoOK=true;
				panel.datos_personales.camposDatosOK=true;
				break;
			
			case PacketType.Error:
				
				JOptionPane.showMessageDialog(null, inPacket.getFiltro(), "Guardar empleado", JOptionPane.ERROR_MESSAGE);

				break;
			
			default:
				break;
			}
			System.out.println("paquete devuelto!!!!");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Faltan datos o son erróneos. Revise los campos señalados en rojo", "Guardar empleado", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/**
	 * Crea la petición para modificar el empleados indicado en el servidor.
	 * 
	 * @param panelEntrada {@link JPanel} El panel del cual se han modificado los datos del empleado.
	 * El método identifica el tipo de panel de la aplicación. 
	 * @param dni {@link String} El DNI que identifica al empleado ha modificar.
	 */
	public void modifDatosEmpleado(JPanel panelEntrada, String dni) {
		
		Empleado empleadoEditado = null;
		if (empleados==null)
		{
			Packet outPacket = new Packet(PacketType.Consulta);
			Packet inPacket = conector.sendPacket(outPacket);
			empleados = inPacket.getEmpleados();
		}
		for(Iterator it = empleados.iterator(); it.hasNext();)
		{
			Empleado e =(Empleado) it.next();
			if (e.getDni().equals(dni))empleadoEditado=e;
		}
		
		if (panelEntrada instanceof Panel_DatosPersonales) {
			Panel_DatosPersonales panel = (Panel_DatosPersonales) panelEntrada;

			empleadoEditado.setNombre(panel.getNombre());
			empleadoEditado.setApellido1(panel.getApellido1());
			empleadoEditado.setApellido2(panel.getApellido2());
			empleadoEditado.setNacionalidad(panel.getNacionalidad());
			empleadoEditado.setFechaNacimiento(panel.getFecha_nacimiento());
			if(panel.getCc()!=null) empleadoEditado.setCuentaBancaria(panel.getCc());
			empleadoEditado.setDireccion(panel.getDireccion());
			empleadoEditado.setPoblacion(panel.getPoblacion());
			empleadoEditado.setNacionalidad(panel.getNacionalidad());
			if(panel.getTelefono()!=null)empleadoEditado.setTelefono(panel.getTelefono());
			if(panel.getFoto()!=null)
				try {
					empleadoEditado.setFoto(Files.readAllBytes(panel.getFoto().toPath()));
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			
		}if (panelEntrada instanceof Panel_Curriculo) {
			Panel_Curriculo panel = (Panel_Curriculo) panelEntrada;
			
			Set empleadoIdiomas = new HashSet();
			
			for (Object idioma : panel.getIdiomas())
			{
				Idiomas i = new Idiomas(empleadoEditado,idioma.toString());
				empleadoIdiomas.add(i);
			}
			
			empleadoEditado.setIdiomas(empleadoIdiomas);
			
			empleadoEditado.setTitulacion(panel.getTitulacion());
			empleadoEditado.setExperiencia(panel.getExperiencia());
			
		}if (panelEntrada instanceof Panel_Contrato) {
			Panel_Contrato panel = (Panel_Contrato) panelEntrada;
			
			Set<Contrato> contratos = empleadoEditado.getContratos();
//			List departamentos = getDepartamentos();
			
			if (contratos.isEmpty())
			{
				Contrato cont = new Contrato(fichero, (Cargo)panel.getCargo(), fichero, 
						empleadoEditado, fichero,  panel.getOrigen(), panel.getModalidad(), panel.getMotivo(), panel.getClase(), 
						panel.getPeriodoPrueba(), panel.getPreaviso(), panel.getClausula(), panel.getFecha_inicio(), panel.getFecha_fin(),
						causaBaja, panel.getActivo(), panel.getSalario(), panel.getSede(), fechaInicioDesplaz, fechaFinDesplaz, panel.getSalud(), panel.getVida());
				contratos.add(cont);
			}
			else{
			for (Contrato cont : contratos)
			{
				if(cont.getId()==panel.getIDContrato())
				{
					cont.setActivo(panel.getActivo());
					cont.setCargo((Cargo)panel.getCargo());
					cont.setOrigen(panel.getOrigen());
					cont.setLugarTrabajo(panel.getSede());
					cont.setSalario(panel.getSalario());
					cont.setFechaInicio(panel.getFecha_inicio());
					cont.setFechaFin(panel.getFecha_fin());
					cont.setPreaviso(panel.getPreaviso());
					cont.setPeriodoPrueba(panel.getPeriodoPrueba());
					cont.setModalidad(panel.getModalidad());
					cont.setMotivo(panel.getMotivo());
					cont.setClase(panel.getClase());
					cont.setClausulaComp(panel.getClausula());
					cont.setSegMedico(panel.getSalud());
					cont.setSegVida(panel.getVida());
					
				}
			}
			}
			
			empleadoEditado.setContratos(contratos);
			
		}
		
		
		ArrayList eList = new ArrayList<Empleado>();
		eList.add(empleadoEditado);
		
		Packet outPacket = new Packet(PacketType.ModifEmpleado);
		outPacket.setEmpleados(eList);
		
		Packet inPacket = conector.sendPacket(outPacket);
		
		switch (inPacket.getType()) {
		case PacketType.Confirmacion:
			 
				//IMPLEMENTAR RESPUESTA DE ALTA RECIBIDA
			JOptionPane.showMessageDialog(null, inPacket.getFiltro(), "Guardar empleado", JOptionPane.INFORMATION_MESSAGE);

			break;
		
		case PacketType.Error:
			
			JOptionPane.showMessageDialog(null, inPacket.getFiltro(), "Guardar empleado", JOptionPane.ERROR_MESSAGE);

			break;
		
		default:
			break;
		}
		System.out.println("paquete devuelto!!!!");
		
	}
	
	/**
	 * Crea la petición para obtener el listado de departamentos existentes.
	 * 
	 * @return {@link List} de {@link Departamento}
	 */
	public List getDepartamentos() {

		System.out.println("get departamentos!!!!");
		
		Packet outPacket = new Packet(PacketType.ConsultaDepartamentos);
		Packet inPacket = conector.sendPacket(outPacket);
		
		System.out.println("paquete devuelto!!!!");
		
		return inPacket.getDepartamentos();
	}
	
	/**
	 * Crea la petición para obtener el listado de cargos existentes.
	 * 
	 * @return {@link List} de {@link Cargo}
	 */
	public List getCargos() {
		
		System.out.println("get cargos!!!!");
		
		Packet outPacket = new Packet(PacketType.ConsultaCargos);
		Packet inPacket = conector.sendPacket(outPacket);
		
		System.out.println("paquete devuelto!!!!");
		
		return inPacket.getCargos();
	}
	
	
	/**
	 * Crea la petición para obtener un reporte. 
	 * Si la creación del reporte es exitosa se solicita una ruta donde guardar el mismo,
	 * en caso contrario se indica a través de un diálogo. 
	 * 
	 * @param tipo {@link String} El tipo de reporte a obtener.
	 * @param values {@link String} Las variables necesarias para la obtención del reporte.
	 */
	public void getReporte(String tipo, String values) {
		
		System.out.println("get reportes!!!!");
		
		String[] filtro = {tipo,values};
		
		Packet outPacket = new Packet(PacketType.Reporte);
		outPacket.setFiltro(filtro);
		
		Packet inPacket = conector.sendPacket(outPacket);
		
		switch (inPacket.getType()) {
		case PacketType.Reporte:
			 
			JFileChooser archivo = new JFileChooser();
			switch (archivo.showSaveDialog(null)) {
			case JFileChooser.APPROVE_OPTION:
				
				try {
					Files.write(archivo.getSelectedFile().toPath(), inPacket.getArchivo());
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			case JFileChooser.CANCEL_OPTION:
				break;
			case JFileChooser.ERROR_OPTION:
				break;
			default:
				break;
			}
			
		break;
	
		case PacketType.Error:
			
			JOptionPane.showMessageDialog(null, inPacket.getFiltro(), "Generar reporte", JOptionPane.ERROR_MESSAGE);
		
			break;
		
		default:
			break;
		
		}
		
		System.out.println("paquete devuelto!!!!");
		
	}
	
	/**
	 * Valida si existe un empleado con el identificador indicado en el servidor.
	 * 
	 * @param i {@link String} El identificador a validar.
	 * @return {@link Boolean} TRUE si el id NO existe. 
	 * FALSE en caso de existir. 
	 */
	public boolean validateId(String i)
	{
		boolean validate=false;
		
		String [] id={i};
		Packet outPacket = new Packet(PacketType.ValidateId, id);
		
		Packet inPacket = conector.sendPacket(outPacket);
		
		switch (inPacket.getType()) {
		case PacketType.Validate:
			//id valido para nuevo empleado
			validate=true;
			break;
		case PacketType.Error:
			//id no válido, empleado existe
			validate=false;
			break;
		default:
			break;
		}
		return validate;
	}

	/**
	 * Solicita el login del usuario en la aplicación.
	 * 
	 * @param name {@link String} Nombre del usuario.
	 * @param pass {@link String} Password del usuario.
	 * @return {@link Boolean} TRUE o FALSE en caso de ser un usuario válido o no.
	 */
	public boolean validateUser(String name, String pass) {

		boolean existe=false;
		
		System.out.println("Validate USER!!!");
		String[] user ={name, pass};
		Packet outPacket = new Packet(PacketType.Validate, user);
		
		Packet inPacket = conector.sendPacket(outPacket);
		
		switch (inPacket.getType()) {
		case PacketType.Validate:
			existe=true;
			
			//Usuario validado almacenado
			usuario = inPacket.getUser();
			break;

		case PacketType.Error:
		
			//SI NO SE VALIDA EL USUARIO??????
			System.out.println("El usuario NO se ha validado!!!");
			JOptionPane.showMessageDialog(null, "El usuario indicado no es válido", "Escriba de nuevo",JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}

		return existe;
	}

	/**
	 * Petición para mostrar los datos personales del empleado.
	 * 
	 * @param datos {@link Panel_DatosPersonales} Panel donde se mostrarán los datos.
	 * @param dni {@link String} DNI del empleado del que se mostrarán los datos.
	 */
	public boolean showDatosEmpleado(Panel_DatosPersonales datos, String dni) {
		
		boolean empleadoOK=false;
		Empleado empleadoMostrar = null;
		
		if (empleados==null)
		{
			Packet outPacket = new Packet(PacketType.Consulta);
			Packet inPacket = conector.sendPacket(outPacket);
			empleados = inPacket.getEmpleados();
		}
		for(Iterator it = empleados.iterator(); it.hasNext();)
		{
			Empleado e =(Empleado) it.next();
			if (e.getDni().equals(dni))empleadoMostrar=e;
		}
		if(empleadoMostrar!=null)
		{
		empleadoOK=true;
		
		datos.setNombre(empleadoMostrar.getNombre());
		datos.setDni(empleadoMostrar.getDni());
		datos.setNss(String.valueOf(empleadoMostrar.getNss()));
		datos.setName(empleadoMostrar.getNombre());
		datos.setApellido1(empleadoMostrar.getApellido1());
		datos.setApellido2(empleadoMostrar.getApellido2());
		datos.setNacionalidad(empleadoMostrar.getNacionalidad());
		datos.setFecha_nacimiento(empleadoMostrar.getFechaNacimiento());
		if(empleadoMostrar.getCuentaBancaria()!=null) datos.setCc(empleadoMostrar.getCuentaBancaria());
		datos.setDireccion(empleadoMostrar.getDireccion());
		datos.setPoblacion(empleadoMostrar.getPoblacion());
		datos.setNacionalidad(empleadoMostrar.getNacionalidad());
		if(empleadoMostrar.getTelefono()!=null)datos.setTelefono(empleadoMostrar.getTelefono());
		if(empleadoMostrar.getFoto()!=null)
			try {
				File temp = File.createTempFile("imag", "jpg");
				Files.write(temp.toPath(),empleadoMostrar.getFoto());
				datos.setFoto(temp);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else JOptionPane.showMessageDialog(null, "No existe ningún empleado con ese Id");
		
		return empleadoOK;
	}

	/**
	 * Petición para mostrar los datos curriculares del empleado.
	 * 
	 * @param curriculo {@link Panel_Curriculo} Panel donde se mostrarán los datos. 
	 * @param dni {@link String} DNI del empleado del que se mostrarán los datos.
	 */
	public void showCVEmpleado(Panel_Curriculo curriculo, String dni) {
		
		Empleado empleadoMostrar = null;
		if (empleados==null)
		{
			Packet outPacket = new Packet(PacketType.Consulta);
			Packet inPacket = conector.sendPacket(outPacket);
			empleados = inPacket.getEmpleados();
		}
		
		for(Iterator it = empleados.iterator(); it.hasNext();)
		{
			Empleado e =(Empleado) it.next();
			if (e.getDni().equals(dni))empleadoMostrar=e;
		}
		
		curriculo.setTitulacion(empleadoMostrar.getTitulacion());
		curriculo.setExperiencia(empleadoMostrar.getExperiencia());
		curriculo.setIdiomas(empleadoMostrar.getIdiomas());
	}
	
	/**
	 * Petición para mostrar los datos curriculares del empleado.
	 * 
	 * @param contratosPanel {@link Panel_Contrato} Panel donde se mostrarán los datos. 
	 * @param dni {@link String} DNI del empleado del que se mostrarán los datos.
	 */
	public void showContratoEmpleado(final Panel_Contrato contratosPanel, String dni) {
		
		Empleado empleadoMostrar = null;
		
		if (empleados==null)
		{
			Packet outPacket = new Packet(PacketType.Consulta);
			Packet inPacket = conector.sendPacket(outPacket);
			empleados = inPacket.getEmpleados();
		}
		for(Iterator it = empleados.iterator(); it.hasNext();)
		{
			Empleado e =(Empleado) it.next();
			if (e.getDni().equals(dni))empleadoMostrar=e;
		}
		
		final Set<Contrato> contratos = empleadoMostrar.getContratos();
		
		boolean activo=false;
		for(Contrato con : contratos)
		{
			if(con.isActivo()){
				contratosPanel.setIDContrato(con.getId());
				contratosPanel.setActivo(con.isActivo());
				contratosPanel.setDepartamento(con.getCargo().getDepartamento().getDescripcion());
				contratosPanel.setCargo(con.getCargo().getRango());
				contratosPanel.setOrigen(con.getOrigen());
				contratosPanel.setSede(con.getLugarTrabajo());
				contratosPanel.setSalario(con.getSalario());
				contratosPanel.setFecha_inicio(con.getFechaInicio());
				contratosPanel.setFecha_fin(con.getFechaFin());
				if(con.getPreaviso()!=null)contratosPanel.setPreaviso(con.getPreaviso());
				if(con.getPeriodoPrueba()!=null)contratosPanel.setPeriodoPrueba(con.getPeriodoPrueba());
				contratosPanel.setModalidad(con.getModalidad());
				if(con.getModalidad().equals("Temporal")) contratosPanel.datos_temporalPanel.setVisible(true);
				contratosPanel.setMotivo(con.getMotivo());
				contratosPanel.setClase(con.getClase());
				contratosPanel.setClausula(con.isClausulaComp());
				contratosPanel.setSalud(con.isSegMedico());
				contratosPanel.setVida(con.isSegVida());
				
				activo=true;
			}
			
		}
		contratosPanel.nuevo_contratoPanel.setVisible(activo);
		contratosPanel.panelEstado.setVisible(activo);
		
		contratosPanel.contratosPanel.setVisible(true);
		contratosPanel.contratosList.setListData(contratos.toArray());
		contratosPanel.contratosList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Contrato contratoSeleccionado = (Contrato) contratosPanel.contratosList.getSelectedValue();
				
				for(Contrato con : contratos)
				{
					if(con.getId()==contratoSeleccionado.getId()){
						contratosPanel.setIDContrato(con.getId());
						contratosPanel.setActivo(con.isActivo());
						contratosPanel.setDepartamento(con.getCargo().getDepartamento().getDescripcion());
						contratosPanel.setCargo(con.getCargo().getRango());
						contratosPanel.setOrigen(con.getOrigen());
						contratosPanel.setSede(con.getLugarTrabajo());
						contratosPanel.setSalario(con.getSalario());
						contratosPanel.setFecha_inicio(con.getFechaInicio());
						contratosPanel.setFecha_fin(con.getFechaFin());
						if(con.getPreaviso()!=null)contratosPanel.setPreaviso(con.getPreaviso());
						if(con.getPeriodoPrueba()!=null)contratosPanel.setPeriodoPrueba(con.getPeriodoPrueba());
						contratosPanel.setModalidad(con.getModalidad());
						if(con.getModalidad().equals("Temporal")) contratosPanel.datos_temporalPanel.setVisible(true);
						contratosPanel.setMotivo(con.getMotivo());
						contratosPanel.setClase(con.getClase());
						contratosPanel.setClausula(con.isClausulaComp());
						contratosPanel.setSalud(con.isSegMedico());
						contratosPanel.setVida(con.isSegVida());
					}
					
				}
				contratosPanel.nuevo_contratoPanel.setVisible(true);
				contratosPanel.panelEstado.setVisible(true);
			}
		});		
	}
}
