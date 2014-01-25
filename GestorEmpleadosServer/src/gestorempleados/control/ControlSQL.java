package gestorempleados.control;
import gestorempleados.persistencia.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *  Encargado del control de la base de datos
 *  
 * @author Guillermo Blanco Martínez
 *
 */

@SuppressWarnings({"rawtypes","unchecked"})
public class ControlSQL implements iControl
{

	private SessionFactory sessionFactory;
	
	
	public ControlSQL() {
		
		sessionFactory = buildSessionFactory();
	}

	/**
	 * Guarda el empleado en la BBDD
	 * 
	 * @param e {@link Empleado} Empleado que se requiere guardar.
	 */
	
	public void saveEmpleado(Empleado e){
		
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        session.saveOrUpdate(e);
        session.getTransaction().commit();
	}
	
	/**
	 * Recoge un empleado de la BBDD
	 * 
	 * @param dni {@link String} DNI del empleado a recoger
	 * @return {@link Empleado} Empleado encontrado o null
	 */
	
	public Empleado getEmpleadoDni(String dni)
	{
		Empleado e=null;
		
		Session session = sessionFactory.getCurrentSession();
		
        session.beginTransaction();
		e = (Empleado) session.get(Empleado.class, dni);
        session.getTransaction().commit();
		
        return e;
		
	}
	
	/**
	 *	Recoge el total de empleados almacenados en la BBDD.
	 *	
	 *	@return {@link List} La lista de empleados.
	 */
	

	public ArrayList<Empleado> getEmpleados()
	{
		Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List empleadosList = session.createQuery("from Empleado").list();
        session.getTransaction().commit();

        return new ArrayList<Empleado>(empleadosList);
	}

	/**
	 *	Recoge el total de departamentos almacenados en la BBDD. 
	 *
	 *	@return	{@link List} La lista de departamentos.
	 */
	
	public ArrayList<Departamento> getDepartamentos() {
		Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List departamentosList = session.createQuery("from Departamento").list();
        session.getTransaction().commit();
        
        return new ArrayList<Departamento>(departamentosList);
	}
	
	/**
	 * 	Recoge los cargos almacenados en la BBDD. 
	 * 
	 * @return	{@link List} La lista de cargos.
	 */
	public ArrayList<Cargo> getCargos() {

		Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List cargosList = session.createQuery("from Cargo").list();
        session.getTransaction().commit();
        
        return new ArrayList<Cargo>(cargosList);
	}
	
	/**
	 * Recoge un usuario de la BBDD.
	 * 
	 * @param name {@link String} El usuario a recoger.
	 * @return {@link Usuarios} El usuario recogido o null.
	 */
	public Usuarios getuser(String name)
	{
		Usuarios u=null;
		
		Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        u = (Usuarios) session.get(Usuarios.class, name);
     
        return u;
        
	}
	
	/**
	 * 	Recoge los contratos almacenados en la BBDD. 
	 * 
	 * @return	{@link List} La lista de contratos.
	 */
	public List getContratos(){
		
		Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        
        List contratosList = session.createQuery("from Contrato").list();
        session.getTransaction().commit();

        return contratosList;
	}
	

	/**
	 * Comprueba si el empleado tiene un contrato activo.
	 * @param e {@link Empleado} Empleado que existe
	 * @return False o true
	 */
	public boolean comprobarContratoActivo(Empleado e)
	{

        List contratosList = getContratos();
        
		boolean existe=false;

		for (Iterator it = contratosList.iterator(); it.hasNext();){
			Contrato c = (Contrato) it.next();
			if (c.isActivo())existe=true;
		}
		return existe;
	}
	
    @SuppressWarnings("deprecation")
	private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
        	System.out.println("Sesion factori ERROR");
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
