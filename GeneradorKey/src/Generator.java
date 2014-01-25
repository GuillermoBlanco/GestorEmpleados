import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class Generator {

	public static void main(String[] args) {
		try {
			KeyGenerator kg = KeyGenerator.getInstance("DES");
			kg.init(new SecureRandom());
			SecretKey key = kg.generateKey();
			
//			SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
//			Class spec = Class.forName("javax.crypto.spec.DESKeySpec");
//			DESKeySpec ks = (DESKeySpec) skf.getKeySpec(key, spec);
//			
//			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("keyfile"));
//			oos.writeObject(ks.getKey());
			
			FileOutputStream fos = new FileOutputStream("keyfile");
		      byte[] kb = key.getEncoded();
		      fos.write(kb);
			
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
