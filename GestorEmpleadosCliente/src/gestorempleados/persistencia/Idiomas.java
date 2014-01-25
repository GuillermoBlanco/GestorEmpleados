package gestorempleados.persistencia;

// Generated 26-may-2013 10:06:52 by Hibernate Tools 3.4.0.CR1

/**
 * Idioma hablado por el empleado.
 * 
 * @author Guillermo Blanco Martínez
 */

@SuppressWarnings("serial")
public class Idiomas implements java.io.Serializable {

	private Integer id;
	private Empleado empleado;
	private String idioma;

	public Idiomas() {
	}

	/**
	 * 
	 * @param empleado {@link Empleado} Empleado que registra el idioma.
	 * @param idioma {@link String} Idioma hablado.
	 */
	public Idiomas(Integer id, Empleado empleado, String idioma) {
		this.id = id;
		this.empleado = empleado;
		this.idioma = idioma;
	}
	
	/**
	 * 
	 * @param empleado {@link Empleado} Empleado que registra el idioma.
	 * @param idioma {@link String} Idioma hablado.
	 */
	public Idiomas(Empleado empleado, String idioma) {
		this.empleado = empleado;
		this.idioma = idioma;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id= id;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public String getIdioma() {
		return this.idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	@Override
	public String toString() {
		return idioma;
	}
}
