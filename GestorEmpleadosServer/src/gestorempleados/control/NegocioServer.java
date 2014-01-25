package gestorempleados.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gestorempleados.persistencia.Cargo;
import gestorempleados.persistencia.Departamento;
import gestorempleados.persistencia.Empleado;
import gestorempleados.persistencia.Usuarios;

/**
 * Controla las peticiones de los clientes y se encarga de hacer las llamadas al controlador de la BBDD
 * 
 * @author Guillermo Blanco Martínez
 *
 */

@SuppressWarnings("rawtypes")
public class NegocioServer {
	
	public static iControl control;
	
	@SuppressWarnings("static-access")
	public NegocioServer(iControl control) {
		
		this.control= control;
	}

	/**
	 * Comprueba si el empleado existe
	 * 
	 * @param e {@link Empleado} El empleado a comprobar
	 * @return {@link Boolean} Si existe o no
	 */

	public boolean comprobarEmpleado(Empleado e)
	{
        
        List empleadosList = control.getEmpleados();
        
		boolean existe=false;
		
		for (Iterator it = empleadosList.iterator(); it.hasNext();){
			Empleado empleadoExiste = (Empleado) it.next();
			
			//COMPROBAR EXISTENCIA DE EMPLEADO (DNI, NSS - únicos)
			if (empleadoExiste.getDni().equals(e.getDni()) || empleadoExiste.getNss().equals(e.getNss()) )  existe=true;
		}
		return existe;
	}
	
	/**
	 * Comprueba si el empleado existe
	 * 
	 * @param e {@link String} El id del {@link Empleado} a comprobar
	 * @return {@link Boolean} Si existe o no
	 */
	public boolean comprobarEmpleado(String id)
	{
        
        List empleadosList = control.getEmpleados();
        
		boolean existe=false;
		
		for (Iterator it = empleadosList.iterator(); it.hasNext();){
			Empleado empleadoExiste = (Empleado) it.next();
			
			//COMPROBAR EXISTENCIA DE EMPLEADO (DNI, NSS - únicos)
			if (empleadoExiste.getDni().equals(id) || empleadoExiste.getNss().toString().equals(id) )  existe=true;
		}
		return existe;
	}
	
	/**
	 * Guarda el empleado
	 * 
	 * @param e {@link Empleado} Empleado a guardar.
	 * @return {@link Boolean} Si se aguardado o no.
	 */
	public boolean guardarEmpleado(Empleado e)
	{
		boolean guardado;
		if(comprobarEmpleado(e)) guardado=false;
		else {control.saveEmpleado(e); guardado=true;}

		return guardado;
	}
	
	/**
	 * Solicita la modificación del empleado. Primeramente comprueba si existe o no.
	 * 
	 * @param e {@link Empleado} El empleado a modificar
	 * @return {@link Boolean} Si se ha modificado.
	 */
	public boolean modifEmpleado(Empleado e) {

        List empleadosList = control.getEmpleados();
        
		boolean modificado=false;
		
		for (Iterator it = empleadosList.iterator(); it.hasNext();){
			Empleado empleadoExiste = (Empleado) it.next();
			
			//COMPROBAR EXISTENCIA DE EMPLEADO (DNI, NSS - únicos)
			if (empleadoExiste.getDni().equals(e.getDni()))
			{
				
				empleadoExiste.setNombre(e.getNombre());				
				empleadoExiste.setApellido1(e.getApellido1());				
				empleadoExiste.setApellido2(e.getApellido2());
				empleadoExiste.setNacionalidad(e.getNacionalidad());
				empleadoExiste.setDireccion(e.getDireccion());
				empleadoExiste.setPoblacion(e.getPoblacion());
				empleadoExiste.setTelefono(e.getTelefono());
				empleadoExiste.setFechaNacimiento(e.getFechaNacimiento());
				empleadoExiste.setTitulacion(e.getTitulacion());
				empleadoExiste.setIdiomas(e.getIdiomas());
				empleadoExiste.setExperiencia(e.getExperiencia());
				empleadoExiste.setCuentaBancaria(e.getCuentaBancaria());
				empleadoExiste.setFoto(e.getFoto());

				empleadoExiste.setContratos(e.getContratos());
				
				control.saveEmpleado(empleadoExiste);
				modificado=true;
			}
		}
		
		return modificado;
	}

	/**
	 * Consulta los empleados
	 * 
	 * @return {@link List} Todos los empleados.
	 */
	public ArrayList<Empleado> consultaEmpleados()
	{
		return control.getEmpleados();
	}

	/**
	 * Consulta los departamentos
	 *  
	 * @return {@link List} Todos los departamentos.
	 */
	public ArrayList<Departamento> consultaDepartamentos() {
		return control.getDepartamentos();
	}
	
	/**
	 * Consulta los cargos
	 * 
	 * @return {@link List} Todos los cargos
	 */
	public ArrayList<Cargo> consultaCargos() {
		return control.getCargos();
	}
	
	/**
	 * Valida el usuario
	 * 
	 * @param usuario {@link String} Nombre y contraseña del usuario.
	 * @return {@link Usuarios} El usuario o null en caso no validarse.
	 */
	public Usuarios validUser(String[] usuario) {

		
		Usuarios us = control.getuser(usuario[0]);
		
		if(us!=null)
			if(!us.getPass().equals(usuario[1]))
			{
				us=null;
			}
		return us;
		
	}
	
}
