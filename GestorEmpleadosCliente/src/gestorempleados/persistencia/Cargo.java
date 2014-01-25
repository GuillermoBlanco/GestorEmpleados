package gestorempleados.persistencia;

// Generated 14-may-2013 23:20:29 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * El cargo que puede desempeñar un empleado en la empresa.
 * 
 * @author Guillermo Blanco Martínez
 */

@SuppressWarnings({"serial","rawtypes"})
public class Cargo implements java.io.Serializable {

	private Integer id;
	private Departamento departamento;
	private String rango;
	private String funcion;
	private Integer indGeneral;
	private Integer indPersonal;
	private Set contratos = new HashSet(0);

	public Cargo() {
	}

	/**
	 * 
	 * @param id {@link Integer} El id del cargo en la BBD
	 * @param departamento {@link Departamento} El departamento asociado al cargo.
	 * @param rango {@link String} Descripción del cargo
	 * @param funcion {@link String} Las funciones que tendrá el cargo.
	 * @param indGeneral {@link Integer} Indicador general de la empresa.
	 * @param indPersonal {@link Integer} Indicador asociado al cargo.
	 */
	public Cargo(Integer id, Departamento departamento, String rango,
			String funcion, Integer indGeneral, Integer indPersonal) {
		this.id = id;
		this.departamento = departamento;
		this.rango = rango;
		this.funcion = funcion;
		this.indGeneral = indGeneral;
		this.indPersonal = indPersonal;
	}

	/**
	 * 
	 * @param id {@link Integer} El id del cargo en la BBD
	 * @param departamento {@link Departamento} El departamento asociado al cargo.
	 * @param rango {@link String} Descripción del cargo
	 * @param funcion {@link String} Las funciones que tendrá el cargo.
	 * @param indGeneral {@link Integer} Indicador general de la empresa.
	 * @param indPersonal {@link Integer} Indicador asociado al cargo.
	 * @param contratos {@link Set} Contratos asociados al cargo.
	 */
	public Cargo(Integer id, Departamento departamento, String rango,
			String funcion, Integer indGeneral, Integer indPersonal, Set contratos) {
		this.id = id;
		this.departamento = departamento;
		this.rango = rango;
		this.funcion = funcion;
		this.indGeneral = indGeneral;
		this.indPersonal = indPersonal;
		this.contratos = contratos;
	}

	/**
	 * 
	 * @return {@link Integer} del id del {@link Cargo}.
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Almacena el id del {@link Cargo}.
	 * 
	 * @param id {@link Integer} que define el id del {@link Cargo}.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 
	 * @return {@link Departamento} en el que se desempeña el {@link Cargo}
	 */
	public Departamento getDepartamento() {
		return this.departamento;
	}

	/**
	 * Almacena el {@link Departamento} del {@link Cargo}.
	 * 
	 * @param departamento {@link Departamento} en el se desempeña el {@link Cargo}.
	 */
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	/**
	 * 
	 * @return {@link String} que define el {@link Cargo}.
	 */
	public String getRango() {
		return this.rango;
	}

	/**
	 * Almacena la definición del cargo.
	 * 
	 * @param rango {@link String} que define el {@link Cargo}.
	 */
	public void setRango(String rango) {
		this.rango = rango;
	}

	/**
	 * 
	 * @return {@link String} con la función del {@link Cargo}.
	 */
	public String getFuncion() {
		return this.funcion;
	}

	/**
	 * Almacena la función del {@link Cargo}.
	 * 
	 * @param {@link String} funcion del {@link Cargo}.
	 */
	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	/**
	 * 
	 * @return El {@link Integer} con el índice porcentual sobre el resultado de la empresa.
	 */
	public Integer getIndGeneral() {
		return this.indGeneral;
	}

	/**
	 * Almacena el índice porcentual sobre el resultado de la empresa.
	 * 
	 * @param indGeneral {@link Integer} con el índice porcentual sobre el resultado de la empresa.
	 */
	public void setIndGeneral(Integer indGeneral) {
		this.indGeneral = indGeneral;
	}

	/**
	 * 
	 * @return El {@link Integer} con el índice porcentual sobre el resultado del {@link Cargo}.
	 */
	public Integer getIndPersonal() {
		return this.indPersonal;
	}

	/**
	 * Almacena el índice porcentual sobre el resultado del {@link Cargo}.
	 * 
	 * @param indPersonal {@link Integer} con el índice porcentual sobre el resultado del {@link Cargo}.
	 */
	public void setIndPersonal(Integer indPersonal) {
		this.indPersonal = indPersonal;
	}

	/**
	 * 
	 * @return {@link Set} con los {@link Contrato} asociados al {@link Cargo}.
	 */
	public Set getContratos() {
		return this.contratos;
	}

	/**
	 * Almacena los {@link Contrato} asociados al {@link Cargo}.
	 * 
	 * @param {@link Set} con los {@link Contrato} asociados al {@link Cargo}.
	 */
	public void setContratos(Set contratos) {
		this.contratos = contratos;
	}

	@Override
	public String toString() {
		return rango;
	}
}
