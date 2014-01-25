package gestorempleados.persistencia;

// Generated 14-may-2013 23:20:29 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Contrato creado por la empresa.
 * 
 * @author Guillermo Blanco Martínez
 */

@SuppressWarnings("serial")
public class Contrato implements java.io.Serializable {

	private Integer id;
	private byte[] ficheroByFichEntidad;
	private Cargo cargo;
	private byte[] ficheroByFichA1;
	private Empleado empleado;
	private byte[] ficheroByFichDesplaza;
	private String origen;
	private String modalidad;
	private String motivo;
	private String clase;
	private Integer periodoPrueba;
	private Integer preaviso;
	private boolean clausulaComp;
	private Date fechaInicio;
	private Date fechaFin;
	private String causaBaja;
	private boolean activo;
	private Integer salario;
	private String lugarTrabajo;
	private Date inicioDesplaza;
	private Date finDesplaza;
	private boolean segMedico;
	private boolean segVida;

	public Contrato() {
	}

	/**
	 * 
	 * @param ficheroByFichEntidad
	 * @param cargo {@link Cargo} El cargo asociado al contrato.
	 * @param ficheroByFichA1
	 * @param empleado {@link Empleado} Empleado que figura en el contrato.
	 * @param ficheroByFichDesplaza
	 * @param origen {@link String} Pais de contratación.
	 * @param modalidad {@link String} El tipo de contrato Indefinido o Temporal.
	 * @param motivo {@link String} El motivo que origina la contratación. 
	 * @param clase {@link String} La clase de contrato, para contratos Temporales. 
	 * @param periodoPrueba {@link String} El plazo de periodo de prueba para el contrato.
	 * @param preaviso {@link String} El plazo de preaviso que ha de dar el empleado para finalizar el contrato.
	 * @param clausulaComp {@link Boolean} Si el contrato contempla cláusula de no competencia.
	 * @param fechaInicio {@link Date} La fecha de inicio del contrato.
	 * @param fechaFin {@link Date} La fecha de fin del contrato, para algunos contratos temporales y contratos finalizados.
	 * @param causaBaja {@link String} La causa del fin del contrato.
	 * @param activo {@link Boolean} Si el contrato está activo.
	 * @param salario {@link Integer} El salario a retribuir.
	 * @param lugarTrabajo {@link String} La sede donde se desempeñará.
	 * @param inicioDesplaza {@link Date} En caso de ser desplazado la fecha del inicio del desplazamiento.
	 * @param finDesplaza {@link Date} En caso de ser desplazado la fecha del fin del desplazamiento.
	 * @param segMedico {@link Boolean} Si se le complementa con un seguro médico.
	 * @param segVida {@link Boolean} Si se le complementa con un seguro de vida.
	 */
	public Contrato(byte[] ficheroByFichEntidad, Cargo cargo,
			byte[] ficheroByFichA1, Empleado empleado,
			byte[] ficheroByFichDesplaza, String origen, String modalidad,
			String motivo, String clase, Integer periodoPrueba, Integer preaviso,
			boolean clausulaComp, Date fechaInicio, Date fechaFin,
			String causaBaja, boolean activo, Integer salario, String lugarTrabajo,
			Date inicioDesplaza, Date finDesplaza, boolean segMedico,
			boolean segVida) {

		this.ficheroByFichEntidad = ficheroByFichEntidad;
		this.cargo = cargo;
		this.ficheroByFichA1 = ficheroByFichA1;
		this.empleado = empleado;
		this.ficheroByFichDesplaza = ficheroByFichDesplaza;
		this.origen = origen;
		this.modalidad = modalidad;
		this.motivo = motivo;
		this.clase = clase;
		this.periodoPrueba = periodoPrueba;
		this.preaviso = preaviso;
		this.clausulaComp = clausulaComp;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.causaBaja = causaBaja;
		this.activo = activo;
		this.salario = salario;
		this.lugarTrabajo = lugarTrabajo;
		this.inicioDesplaza = inicioDesplaza;
		this.finDesplaza = finDesplaza;
		this.segMedico = segMedico;
		this.segVida = segVida;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getFicheroByFichEntidad() {
		return this.ficheroByFichEntidad;
	}

	public void setFicheroByFichEntidad(byte[] ficheroByFichEntidad) {
		this.ficheroByFichEntidad = ficheroByFichEntidad;
	}

	public Cargo getCargo() {
		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public byte[] getFicheroByFichA1() {
		return this.ficheroByFichA1;
	}

	public void setFicheroByFichA1(byte[] ficheroByFichA1) {
		this.ficheroByFichA1 = ficheroByFichA1;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public byte[] getFicheroByFichDesplaza() {
		return this.ficheroByFichDesplaza;
	}

	public void setFicheroByFichDesplaza(byte[] ficheroByFichDesplaza) {
		this.ficheroByFichDesplaza = ficheroByFichDesplaza;
	}

	public String getOrigen() {
		return this.origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getModalidad() {
		return this.modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public String getMotivo() {
		return this.motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getClase() {
		return this.clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public Integer getPeriodoPrueba() {
		return this.periodoPrueba;
	}

	public void setPeriodoPrueba(Integer periodoPrueba) {
		this.periodoPrueba = periodoPrueba;
	}

	public Integer getPreaviso() {
		return this.preaviso;
	}

	public void setPreaviso(Integer preaviso) {
		this.preaviso = preaviso;
	}

	public boolean isClausulaComp() {
		return this.clausulaComp;
	}

	public void setClausulaComp(boolean clausulaComp) {
		this.clausulaComp = clausulaComp;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getCausaBaja() {
		return this.causaBaja;
	}

	public void setCausaBaja(String causaBaja) {
		this.causaBaja = causaBaja;
	}

	public boolean isActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Integer getSalario() {
		return this.salario;
	}

	public void setSalario(Integer salario) {
		this.salario = salario;
	}

	public String getLugarTrabajo() {
		return this.lugarTrabajo;
	}

	public void setLugarTrabajo(String lugarTrabajo) {
		this.lugarTrabajo = lugarTrabajo;
	}

	public Date getInicioDesplaza() {
		return this.inicioDesplaza;
	}

	public void setInicioDesplaza(Date inicioDesplaza) {
		this.inicioDesplaza = inicioDesplaza;
	}

	public Date getFinDesplaza() {
		return this.finDesplaza;
	}

	public void setFinDesplaza(Date finDesplaza) {
		this.finDesplaza = finDesplaza;
	}

	public boolean isSegMedico() {
		return this.segMedico;
	}

	public void setSegMedico(boolean segMedico) {
		this.segMedico = segMedico;
	}

	public boolean isSegVida() {
		return this.segVida;
	}

	public void setSegVida(boolean segVida) {
		this.segVida = segVida;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ""+id;
	}
	
}
