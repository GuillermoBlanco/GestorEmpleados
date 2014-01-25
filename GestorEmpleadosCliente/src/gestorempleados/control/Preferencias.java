package gestorempleados.control;

/**
 * Configuraci�n de la conexi�n con el server.
 * 
 * @author Guillermo
 *
 */
public abstract class Preferencias {

	private static final String localhost="localhost";
	private static int port=9999;
	
	private static String host=localhost;

	/**
	 * Definir la direcci�n Ip del servidor.
	 * 
	 * @param host {@link String} direcci�n ip. 
	 */
	public static void setHost(String newHost){
		host=newHost;
	}
	
	/**
	 * Reocge la ip definida para el server.
	 * 
	 * @return {@link String} direcci�n ip.
	 */
	public static String getHost(){
		return host;
	}
	
	/**
	 * Define el puerto de comunicaci�n con el server.
	 * 
	 * @param newPort {@link Integer} el puerto.
	 */
	public static void setPort(int newPort)
	{
		port=newPort;
	}
	
	public static int getPort(){
		
		return port;
	}
}
