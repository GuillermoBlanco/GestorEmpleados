package gestorempleados.conector;

import gestorempleados.control.NegocioServer;
import gestorempleados.persistencia.*;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyException;
import java.security.spec.KeySpec;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 * Encargado de gestionar los paquetes entre el servidor y el cliente.
 * Contiene un elemento para cifrado/descifrado de los paquetes enviados/recibidos al socket del cliente.
 * 
 * @author Guillermo Blanco Martínez
 *
 */

@SuppressWarnings({"rawtypes","unchecked"})
public class ServerHilo extends Thread {

	public Socket socketClient;
	public NegocioServer negocio;
	public ObjectInputStream lector;
	public ObjectOutputStream escritor;
	
	public FileInputStream fis;
	
	public CipherOutputStream escritorCifrado;
	public CipherInputStream lectorCifrado;
	
	private SecretKey key;
	private Cipher c;
	
	private String entrada = "";
	
	private HashMap hm = new HashMap();
	
	/**
	 * Al instanciar la clase se lee la llave de cifrado/descifrado, 
	 * mediante la cual se lee/escribe directamente al socket.
	 * Discrimina el tipo de paquete, para hacer las operaciones oportunas.
	 * 
	 * @param socketClient El socket a través del cual se comunicará el servidor
	 * @param negocio Instacia a la cual se hará las llamadas a las acciones requeridas
	 */
	
	public ServerHilo(Socket socketClient, NegocioServer negocio)  {

		this.socketClient = socketClient;
		this.negocio=negocio;

		
		try {
//			fis = new FileInputStream("bin/gestorempleados/key/keyfile");
			fis = new FileInputStream("key"+File.separator+"keyfile");
			
			int kl = fis.available();
			byte[] kb = new byte[kl];
			fis.read(kb);
			fis.close();
		    
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
			
			KeySpec ks = new DESKeySpec(kb);
				
			
			key = skf.generateSecret(ks);
			c = Cipher.getInstance("DES/CFB8/NoPadding");

			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (KeyException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} 

	}
	
	@Override
	public void run() {
		super.run();
	
		System.out.println("HILO creado");
		
		try {
			
			c.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec("houseold".getBytes()));

			lectorCifrado = new CipherInputStream(socketClient.getInputStream(), c);
			lector = new ObjectInputStream( lectorCifrado );
			
			System.out.println("RECIBIDO");
			

			Packet inPacket= (Packet) lector.readObject();
			
			Packet outPacket;
			
			System.out.println("PACKET CASTEADO");
			
			switch (inPacket.getType()) 
			{
			case PacketType.Consulta:
				outPacket = new Packet(PacketType.Consulta);
				outPacket.setEmpleados(negocio.consultaEmpleados());

				c.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec("houseold".getBytes()));
				escritorCifrado = new CipherOutputStream(socketClient.getOutputStream(), c);
				escritor = new ObjectOutputStream( escritorCifrado );
				escritor.writeObject(outPacket);
				
				break;
			
			case PacketType.ConsultaDepartamentos:
				outPacket = new Packet(PacketType.ConsultaDepartamentos);
				outPacket.setDepartamentos(negocio.consultaDepartamentos());

				c.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec("houseold".getBytes()));
				escritorCifrado = new CipherOutputStream(socketClient.getOutputStream(), c);
				escritor = new ObjectOutputStream( escritorCifrado );
				escritor.writeObject(outPacket);
				
				break;
			
			case PacketType.ConsultaCargos:
				outPacket = new Packet(PacketType.ConsultaCargos);
				outPacket.setCargos(negocio.consultaCargos());

				c.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec("houseold".getBytes()));
				escritorCifrado = new CipherOutputStream(socketClient.getOutputStream(), c);
				escritor = new ObjectOutputStream( escritorCifrado );
				escritor.writeObject(outPacket);
				
				break;
				
			case PacketType.Validate:
				
				String[] usuario = inPacket.getFiltro();
				Usuarios us;
				
				if((us = negocio.validUser(usuario))!=null)
				{	
					outPacket = new Packet(PacketType.Validate, us);					
				}
				else
				{
					outPacket = new Packet(PacketType.Error,new String[]{"Usuario no válido"});
				};
				
				c.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec("houseold".getBytes()));
				escritorCifrado = new CipherOutputStream(socketClient.getOutputStream(), c);
				escritor = new ObjectOutputStream( escritorCifrado );
				escritor.writeObject(outPacket);
				
				break;

			case PacketType.ValidateId:
				
				String[] id = inPacket.getFiltro();
				
				if(!negocio.comprobarEmpleado(id[0]))
				{	
					outPacket = new Packet(PacketType.Validate);					
				}
				else
				{
					outPacket = new Packet(PacketType.Error);
				};
				
				c.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec("houseold".getBytes()));
				escritorCifrado = new CipherOutputStream(socketClient.getOutputStream(), c);
				escritor = new ObjectOutputStream( escritorCifrado );
				escritor.writeObject(outPacket);
				
				break;
				
			case PacketType.Alta:
				
				List empleados = inPacket.getEmpleados();
				
				if(!negocio.comprobarEmpleado((Empleado) empleados.get(0)))
						{
							if(negocio.guardarEmpleado((Empleado)empleados.get(0)))
							{	
								outPacket = new Packet(PacketType.Confirmacion, new String[]{"Usuario guardado"});					
							}
							else
							{
								outPacket = new Packet(PacketType.Error, new String[]{"Usuario no guardado"});
							}
						}

				else
				{
					outPacket = new Packet(PacketType.Error,new String[]{"Usuario ya existe"});
				};
				
				c.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec("houseold".getBytes()));
				escritorCifrado = new CipherOutputStream(socketClient.getOutputStream(), c);
				escritor = new ObjectOutputStream( escritorCifrado );
				escritor.writeObject(outPacket);
				
				break;
				
			case PacketType.ModifEmpleado:
				
				List empleadosModif = inPacket.getEmpleados();

				if(negocio.modifEmpleado((Empleado)empleadosModif.get(0)))
				{	
					outPacket = new Packet(PacketType.Confirmacion, new String[]{"Usuario actualizado"});					
				}
				else
				{
					outPacket = new Packet(PacketType.Error, new String[]{"Usuario no actualizado"});
				}
				
				c.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec("houseold".getBytes()));
				escritorCifrado = new CipherOutputStream(socketClient.getOutputStream(), c);
				escritor = new ObjectOutputStream( escritorCifrado );
				escritor.writeObject(outPacket);
				
				break;
				
			case PacketType.Reporte:
				
				String reporte = inPacket.getFiltro()[0];
				
				if (inPacket.getFiltro().length>1)
				{
					hm.put("dni", inPacket.getFiltro()[1]);
				}
				
//				if(reporte.equals("empleadosConContrato")) entrada = "bin/gestorempleados/reportes/empleadosConContrato.jasper";
//				else if (reporte.equals("empleadosSinContrato")) entrada ="bin/gestorempleados/reportes/empleadosSinContrato.jasper";
//				else if (reporte.equals("contratosActivos")) entrada ="bin/gestorempleados/reportes/contratosActivos.jasper";
//				else if (reporte.equals("contratosInactivos")) entrada="bin/gestorempleados/reportes/contratosInactivos.jasper";
//				else if (reporte.equals("Empleado")) entrada="bin/gestorempleados/reportes/Empleado.jasper";
				
				if(reporte.equals("empleadosConContrato")) entrada = "reportes"+File.separator+"empleadosConContrato.jasper";
				else if (reporte.equals("empleadosSinContrato")) entrada ="reportes"+File.separator+"empleadosSinContrato.jasper";
				else if (reporte.equals("contratosActivos")) entrada ="reportes"+File.separator+"contratosActivos.jasper";
				else if (reporte.equals("contratosInactivos")) entrada="reportes"+File.separator+"contratosInactivos.jasper";
				else if (reporte.equals("Empleado")) entrada="reportes"+File.separator+"Empleado.jasper";
				
				JasperPrint print = null;
				File temp= File.createTempFile("reporte", "pdf");
				
                try {
                	
                	Class.forName("com.mysql.jdbc.Driver");
					print = JasperFillManager.fillReport(entrada, hm,
					        DriverManager.getConnection("jdbc:mysql://localhost:3306/gestor_empleados", "root",""));

					 JRExporter exporter = new JRPdfExporter();			        
			            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, temp );
				        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
				        exporter.exportReport();
				        
				} catch (JRException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				if(temp.exists())
				{	
					outPacket = new Packet(PacketType.Reporte, new String[]{"Reporte generado"});
					outPacket.setArchivo(Files.readAllBytes(temp.toPath()));
					
					temp.delete();
				}
				else
				{
					outPacket = new Packet(PacketType.Error, new String[]{"Reporte no generado"});
				}
				
				c.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec("houseold".getBytes()));
				escritorCifrado = new CipherOutputStream(socketClient.getOutputStream(), c);
				escritor = new ObjectOutputStream( escritorCifrado );
				escritor.writeObject(outPacket);
				
				break;
					
			default:
					outPacket = new Packet(PacketType.Error);
				break;
			}
			
			lector.close();
			escritor.close();
			socketClient.close();
			
			socketClient=null;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		
	}
}
