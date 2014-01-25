package gestorempleados.persistencia;

// Generated 14-may-2013 23:20:29 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Departamentos en la empresa.
 * 
 * @author Guillermo Blanco Martínez
 */
@SuppressWarnings({"serial","rawtypes"})
public class Departamento implements java.io.Serializable {

	private int id;
	private String descripcion;
	private Set cargos = new HashSet(0);

	public Departamento() {
	}

	/**
	 * 
	 * @param id {@link Integer} El id del departamento.
	 * @param descripcion {@link String} La descripción del departamento.
	 */
	public Departamento(int id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	/**
	 * 
	 * @param id {@link Integer} El id del departamento.
	 * @param descripcion {@link String} La descripción del departamento.
	 * @param cargos {@link Set} Cargos creados en el departamento.
	 */
	public Departamento(int id, String descripcion, Set cargos) {
		this.id = id;
		this.descripcion = descripcion;
		this.cargos = cargos;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set getCargos() {
		return this.cargos;
	}

	public void setCargos(Set cargos) {
		this.cargos = cargos;
	}

	@Override
	public String toString() {
		return descripcion;
	}
}
