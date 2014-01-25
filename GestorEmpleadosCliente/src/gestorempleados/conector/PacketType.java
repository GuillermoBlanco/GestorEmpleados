package gestorempleados.conector;

/**
 * Define los tipos de paquete que determinan la comunicaci�n
 * 
 * @author Guillermo Blanco Mart�nez
 *
 */
public abstract class PacketType {

	public static final int Validate=0;
	public static final int Consulta=1;
	public static final int Alta = 2;
	public static final int Confirmacion=3;
	public static final int ConsultaDepartamentos = 4;
	public static final int ConsultaCargos = 5;
	public static final int ModifEmpleado = 6;
	public static final int ConsultaIdiomas = 7;
	public static final int Reporte = 8;
	public static final int ValidateId = 9;
	
	public static final int Error=-1;
	

}
