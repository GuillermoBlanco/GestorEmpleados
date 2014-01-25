package gestorempleados.conector;

import gestorempleados.persistencia.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Paquete de comunicación.
 * 
 * @author Guillermo Blanco Martínez
 *
 */
@SuppressWarnings({"serial","unused"})
public class Packet implements Serializable{
	
	private int type;
	
	private Usuarios user;
	
	private ArrayList<Cargo> cargos;
	private ArrayList<Departamento> departamentos;
	private ArrayList<Idiomas> idiomas;
	
	private ArrayList<Empleado> empleados;

	private String[] filtro; //Elementos a filtrar
	private byte[] archivo;

	public int getType() {return type;}
	public void setType(int type) {this.type=type;}
	
	public Packet(int type){
		
		this.type=type;
	}
	
	/**
	 * Constructor con un vector de parámetros
	 * 
	 * @param type {@link PacketType} Define el tipo de comunicación
	 * @param filtro {@link StringArray} Elementos que filtran el tipo de comunicación
	 */
	public Packet(int type, String[] filtro) {

		this.type=type;
		this.filtro=filtro;
	}
	
	/**
	 * Constructor para la validación del usuario que se conecta
	 * 
	 * @param type {@link PacketType} Define el tipo de comunicación
	 * @param user El {@link Usuarios} que se valida
	 */
	public Packet(int type, Usuarios user) {

		this.type=type;
		this.user=user;
	}
	
	/**
	 * 
	 * @return El {@link Usuarios} contenido en el paquete.
	 */
	public Usuarios getUser() {
		return user;
	}
	
	/**
	 * 
	 * @return {@link List} de {@link Empleado} contenidos en el paquete. 
	 */
	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}
	
	/**
	 *  Almacena un listado de empleados.
	 *  
	 * @param e El {@link List} de {@link Empleado} a guardar.
	 */
	public void setEmpleados(ArrayList<Empleado> e) {
		empleados=e;
	}
	
	/**
	 * 
	 * @return {@link List} de {@link Departamento} contenidos en el paquete. 
	 */
	public ArrayList<Departamento> getDepartamentos() {
		return departamentos;
	}
	
	/**
	 * Almacena un listado de {@link Departamento}.
	 * 
	 * @param e {@link List} de {@link Departamento}.
	 */
	public void setDepartamentos(ArrayList<Departamento> e) {
		departamentos=e;
	}
	
	/**
	 * 
	 * @return {@link List} Los {@link Cargo} almacenados en el paquete.
	 */
	public ArrayList<Cargo> getCargos() {
		return cargos;
	}
	
	/**
	 * Almacena un listado de {@link Cargo}.
	 * 
	 * @param e {@link List} de {@link Cargo}.
	 */
	public void setCargos(ArrayList<Cargo> e) {
		cargos=e;
	}
	
	/**
	 * 
	 * @return Los valores a flitrar por el paquete.
	 */
	public String[] getFiltro() {
		return filtro;
	}
	
	/**
	 * Almacena los filtros para el paquete.
	 * 
	 * @param {@link StringArray} con los valores del filtro
	 */
	public void setFiltro(String[] filtro) {

		this.filtro=filtro;
	}
	
	/**
	 * 
	 * @return {@link Byte} del archivo incluido en el paquete.
	 */
	public byte[] getArchivo() {
		return archivo;
	}
	
	/**
	 * Almacena un archivo en el paquete.
	 * 
	 * @param archivo los {@link Byte} del archivo.
	 */
	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}
	
	
}
