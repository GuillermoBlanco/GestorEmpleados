package gestorempleados.persistencia;

// Generated 14-may-2013 22:44:40 by Hibernate Tools 3.4.0.CR1

/**
 * Usuarios que acceden al sistema.
 * 
 * @author Guillermo Blanco Martínez
 */
@SuppressWarnings("serial")
public class Usuarios implements java.io.Serializable {

	private String nombre;
	private String pass;

	public Usuarios() {
	}

	/**
	 * 
	 * @param nombre {@link String} Nombre del usuario.
	 * @param pass {@link String} Password del usuario.
	 */
	public Usuarios(String nombre, String pass) {
		this.nombre = nombre;
		this.pass = pass;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
