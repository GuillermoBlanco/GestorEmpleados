/**
 * 
 */
package gestorempleados.conector;

import gestorempleados.control.Preferencias;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.JOptionPane;


/**
 * Inicializa el socket de comunicación con los valores definidos en {@link Preferencias}.
 * Contiene un elemento para cifrado/descifrado de los paquetes enviados/recibidos al socket del server.
 * 
 * @author Guillermo
 */
	
public class ConectorCliente {
	
	public Socket socketClient;	
	public ObjectInputStream lector;
	public ObjectOutputStream escritor;
	
	public FileInputStream fis;
	
	public CipherOutputStream escritorCifrado;
	public CipherInputStream lectorCifrado;
	
	private SecretKey key;
	private Cipher c;
	
	/**
	 * Al crear la instancia carga la llave y crea el objecto de cifrado. 
	 */
	public ConectorCliente() 
	{
		try {
			fis = new FileInputStream("key/keyfile");
			
			int kl = fis.available();
			byte[] kb = new byte[kl];
			fis.read(kb);
			fis.close();
		    
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
			
			KeySpec ks = new DESKeySpec(kb);
				
			
			key = skf.generateSecret(ks);
			c = Cipher.getInstance("DES/CFB8/NoPadding");
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Fichero key no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 * 
	 * @param outPacket {@link Packet} El paquete a enviar al servidor.
	 * @return {@link Packet} El paquete de respuesta del servidor.
	 */
	public Packet sendPacket(Packet outPacket)
	{
		System.out.println("enviar packete conector");
		
		Packet inPacket=null;
	
		try {
		
			socketClient = new Socket(Preferencias.getHost(),Preferencias.getPort());
			
			c.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec("houseold".getBytes()));

			escritorCifrado = new CipherOutputStream(socketClient.getOutputStream(), c);
			escritor = new ObjectOutputStream(escritorCifrado);
			
			escritor.writeObject(outPacket);		
			
			c.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec("houseold".getBytes()));
			lectorCifrado = new CipherInputStream(socketClient.getInputStream(), c);
			lector = new ObjectInputStream(lectorCifrado);
			
			inPacket = (Packet) lector.readObject();
			
			socketClient.close();
			
		} catch (IOException e) {
			System.out.println("ERROR de conexión");
			JOptionPane.showMessageDialog(null,"No se ha podido establecer la conexión","ERROR de conexión", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Los datos recibidos no son los esperados (Packet)");
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}

		
		return inPacket;
	}
}
