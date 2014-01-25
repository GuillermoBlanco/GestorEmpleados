package gestorempleados.control;

import gestorempleados.persistencia.Cargo;
import gestorempleados.persistencia.Departamento;
import gestorempleados.persistencia.Empleado;
import gestorempleados.persistencia.Usuarios;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de control de la BBDD
 * Define los métodos necesarios para el control de la BBDD.
 * 
 * @author Guillermo Blanco Martínez
 *
 */

@SuppressWarnings("rawtypes")
public interface iControl {
	
	public ArrayList<Empleado> getEmpleados();
	public void saveEmpleado(Empleado e);
	public List getContratos();
	public Usuarios getuser(String name);
	public ArrayList<Departamento> getDepartamentos();
	public ArrayList<Cargo> getCargos();
	public boolean comprobarContratoActivo(Empleado e);

}