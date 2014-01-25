package gestorempleados.conector;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import gestorempleados.conector.ServerHilo;
import gestorempleados.control.ControlSQL;
import gestorempleados.control.NegocioServer;

/**
 * Inicializa los sockets de comunicación e instancia las clases de control de la BBDD y del negocio.
 * 
 * @author Guillermo Blanco Martínez
 *
 */

public class StartServer {

	public ServerSocket socketServer;
	public final int port=9999;
	public NegocioServer negocio;
	
	private Socket socketClient;
	
	public ControlSQL modelo;
	
	/**
	 *  Instancia las clases necesarias para el funcionamiento de la comunicacion.
	 *  Llama a los métodos de inicialización del server y su puesta en escucha.
	 */
	
	public StartServer() {
		// TODO Auto-generated constructor stub
		
		modelo = new ControlSQL();
		negocio = new NegocioServer(modelo);
		
//		connectServer();
//		escuchar();
	}

	/**
	 *	Crea los sockets necesarios para la conexión.
	 */
    public void connectServer() {
		// TODO Auto-generated constructor stub
    	
    	try {
			socketServer = new ServerSocket(port);
			socketClient = new Socket();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	escuchar();
    }
    
    /**
     * Registra la escucha en el sistema a través del puerto definido.
     */
    public void escuchar(){
    	
    	while(true)
    	{
			try {
				System.out.println("SERVER Escuchando");
				
				socketClient = socketServer.accept();
				System.out.println("Conexion entrante");
				
				ServerHilo servHilo = new ServerHilo(socketClient,negocio);
				servHilo.start();
//				servHilo.join();  //Espero a que acabe el hilo
				
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("No puc crear socket escoltant al port desitjat: " + e.getMessage());
			}
    	}
    	
    	
    }
    
    public void desconectar(){
    	
    	try {
			socketClient.close();
			socketServer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	socketClient=null;
    	
    }
	
//	@SuppressWarnings("unused")
//	public static void main(String[] args) {
//		
//		StartServer negocioRRHH = new StartServer();
//	}
}

