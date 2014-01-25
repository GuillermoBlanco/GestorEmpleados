package gestorempleados.persistencia;

// Generated 14-may-2013 23:20:29 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Departamentos en la empresa.
 * 
 * @author Guillermo Blanco Martínez
 */
@SuppressWarnings({"serial","rawtypes","unchecked"})
public class Empleado implements java.io.Serializable {

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
	private String experiencia;
	private Integer cuentaBancaria;
	private byte[] foto;
	private Set<Contrato> contratos = new HashSet<Contrato>(0);
	private Set<Idiomas> idiomas = new HashSet<Idiomas>(0);
	
	public Empleado() {
	}

	/**
	 * 
	 * @param String dni {@link String} El documetno identificativo del empleado.
	 * @param int nss {@link Integer} El número de afiliación a la Seguridad Social.
	 * @param String nombre {@link String} Nombre del empleado.
	 * @param String apellido1 {@link String} Primer apellido del empleado.
	 * @param String apellido2 {@link String} Segundo apellido del empleado.
	 * @param String nacionalidad {@link String} Nacionalidad del empleado.
	 * @param String direccion {@link String} Dirección postal del empleado.
	 * @param String poblacion {@link String} La población de residencia del empleado.
	 * @param int telefono {@link Integer} El teléfono de contacto del empleado.
	 * @param Date fechaNacimiento {@link Date} La fecha de nacimiento.
	 * @param String titulacion {@link String} El título académico del empleado.
	 * @param String experiencia {@link String} Extracto de la experiencia aportada por el empleado.
	 * @param int cuentaBancaria {@link Integer} La cuenta bancaria para los ingresos.
	 * @param int foto {@link Integer} La referencia a la foto del empleado.
	 */
	public Empleado(String dni, Integer nss, String nombre, String apellido1,
			String apellido2, String nacionalidad, String direccion,
			String poblacion, Integer telefono, Date fechaNacimiento,
			String titulacion, String experiencia,
			Integer cuentaBancaria, byte[] foto) {
		this.dni = dni;
		this.nss = nss;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.nacionalidad = nacionalidad;
		this.direccion = direccion;
		this.poblacion = poblacion;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.titulacion = titulacion;
//		this.idiomas = idiomas;
		this.experiencia = experiencia;
		this.cuentaBancaria = cuentaBancaria;
		this.foto = foto;
	}

	/**
	 * 
	 * @param String dni {@link String} El documetno identificativo del empleado.
	 * @param int nss {@link Integer} El número de afiliación a la Seguridad Social.
	 * @param String nombre {@link String} Nombre del empleado.
	 * @param String apellido1 {@link String} Primer apellido del empleado.
	 * @param String apellido2 {@link String} Segundo apellido del empleado.
	 * @param String nacionalidad {@link String} Nacionalidad del empleado.
	 * @param String direccion {@link String} Dirección postal del empleado.
	 * @param String poblacion {@link String} La población de residencia del empleado.
	 * @param int telefono {@link Integer} El teléfono de contacto del empleado.
	 * @param Date fechaNacimiento {@link Date} La fecha de nacimiento.
	 * @param String titulacion {@link String} El título académico del empleado.
	 * @param String experiencia {@link String} Extracto de la experiencia aportada por el empleado.
	 * @param int cuentaBancaria {@link Integer} La cuenta bancaria para los ingresos.
	 * @param int foto {@link Integer} La referencia a la foto del empleado.
	 * @param contratos {@link Set} Contratos del empleado con la empresa.
	 * @param idiomas {@link Set} Los idiomas que habla el empleado.
	 */
	public Empleado(String dni, Integer nss, String nombre, String apellido1,
			String apellido2, String nacionalidad, String direccion,
			String poblacion, Integer telefono, Date fechaNacimiento,
			String titulacion, String experiencia,
			Integer cuentaBancaria, byte[] foto, Set contratos, Set idiomas) {
		this.dni = dni;
		this.nss = nss;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.nacionalidad = nacionalidad;
		this.direccion = direccion;
		this.poblacion = poblacion;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.titulacion = titulacion;
		this.experiencia = experiencia;
		this.cuentaBancaria = cuentaBancaria;
		this.foto = foto;
		this.contratos = contratos;
		this.idiomas = idiomas;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Integer getNss() {
		return this.nss;
	}

	public void setNss(Integer nss) {
		this.nss = nss;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getNacionalidad() {
		return this.nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPoblacion() {
		return this.poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public Integer getTelefono() {
		return this.telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTitulacion() {
		return this.titulacion;
	}

	public void setTitulacion(String titulacion) {
		this.titulacion = titulacion;
	}

	public String getExperiencia() {
		return this.experiencia;
	}

	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

	public Integer getCuentaBancaria() {
		return this.cuentaBancaria;
	}

	public void setCuentaBancaria(Integer cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Set getContratos() {
		return this.contratos;
	}

	public void setContratos(Set contratos) {
		this.contratos = contratos;
	}

	public Set getIdiomas() {
		return this.idiomas;
	}

	public void setIdiomas(Set idiomas) {
		this.idiomas = idiomas;
	}
}
