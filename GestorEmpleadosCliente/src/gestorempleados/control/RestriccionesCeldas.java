package gestorempleados.control;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Define la cantidad y el tipo de caracteres que se pueden 
 * insertar en cada campo de los paneles de la aplicación.
 * 
 * @author Guillermo Blanco Martínez.
 *
 */
@SuppressWarnings("serial")
public class RestriccionesCeldas extends PlainDocument{

	public static final int NOMBRE= 20;
	public static final int APELLIDO1= 20;
	public static final int APELLIDO2= 20;
	public static final int DIRECCION= 40;
	public static final int POBLACION= 20;
	public static final int TELEFONO= 9;
	public static final int TITULACION= 30;
	public static final int NSS = 9;
	public static final int EXPE = 100;
	public static final int CC = 9;
	public static final int DNI = 8;
	
	public tipo TYPE;
	public static enum tipo {NOMBRE,APELLIDO1,APELLIDO2,DIRECCION,POBLACION,TELEFONO,TITULACION,NSS,DNI,EXPE,CC};
	
	public int currentRESTRIC;
	
	/**
	 * Crea la restricción indicada.
	 * 
	 * @param RESTRIC {@link tipo} El tipo de restricción a aplicar.
	 */
	public RestriccionesCeldas(tipo RESTRIC) {
		
		switch (RESTRIC) {
		case NOMBRE:
			currentRESTRIC=NOMBRE;
			TYPE=tipo.NOMBRE;
			break;

		case APELLIDO1:
			currentRESTRIC=APELLIDO1;
			TYPE=tipo.APELLIDO1;
			break;
			
		case APELLIDO2:
			currentRESTRIC=APELLIDO2;
			TYPE=tipo.APELLIDO2;
			break;
			
		case DIRECCION:
			currentRESTRIC=DIRECCION;
			TYPE=tipo.DIRECCION;
			break;
			
		case POBLACION:
			currentRESTRIC=POBLACION;
			TYPE=tipo.POBLACION;
			break;
			
		case TELEFONO:
			currentRESTRIC=TELEFONO;
			TYPE=tipo.TELEFONO;
			break;
			
		case TITULACION:
			currentRESTRIC=TITULACION;
			TYPE=tipo.TITULACION;
			break;
			
		case NSS:
			currentRESTRIC=NSS;
			TYPE=tipo.NSS;
			break;
			
		case EXPE:
			currentRESTRIC=EXPE;
			TYPE=tipo.EXPE;
			break;

		case CC:
			currentRESTRIC=CC;
			TYPE=tipo.CC;
			break;
			
		case DNI:
			currentRESTRIC=DNI;
			TYPE=tipo.DNI;
			break;
			
		default:
			break;
		}				
	}
	
	  public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
		    if (str == null) return;
		    
		    //RESTRICCION DE TIPO DE CARACTERES
		    for (int i=0; i<str.length();i++)
		    {
		    	
	    		if (!Character.isDigit(str.charAt(i))&&(TYPE==tipo.TELEFONO || TYPE==tipo.NSS || TYPE==tipo.CC))
	    		{
	    			return;
	    		}

		    }
		    if ((getLength() + str.length()) <= currentRESTRIC) {
			      super.insertString(offset, str, attr);
			    }
		    
	  }
}
