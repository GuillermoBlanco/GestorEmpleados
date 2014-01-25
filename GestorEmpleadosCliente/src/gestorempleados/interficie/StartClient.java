package gestorempleados.interficie;

import gestorempleados.control.NegocioCliente;
import plugins.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.io.File;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;

/**
 * Inicializa la aplicación cliente, monta el panel de login 
 * y en caso de validares accede al resto de los paneles/plugins.
 * 
 * @author Guillermo Blanco Martínez
 *
 */
@SuppressWarnings({"serial","unused"})
public class StartClient extends JFrame {

	private JPanel contentPane;
	private JPanel cabecera;
	private JPanel principalPane;
	private JPanel menuPanel;
	
	private JLabel logoLb;
	private ImageIcon logo;
	
	private boolean validado=false;
	private String name;
	private String pass;
	private JPanel loginPanel;
	
	private GridBagConstraints gridConst;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartClient frame = new StartClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartClient() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(1024,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//COLOCAR IMAGEN FONDO
		logo = new ImageIcon("img/Fondo.png");
		
		principalPane = new JPanel(){
			@Override
			public void paint(Graphics g) {
				g.drawImage(logo.getImage(),0,0,getWidth(),getHeight(),this);
				setOpaque(false);
				
				super.paint(g);
			}
		};
		principalPane.setForeground(Color.WHITE);
		principalPane.setLayout(null);
		contentPane.add(principalPane, BorderLayout.CENTER);
		
		loginPanel = new Panel_Login();
		principalPane.add(loginPanel);
		
		menuPanel = new JPanel();
		contentPane.add(menuPanel, BorderLayout.WEST);
		menuPanel.setLayout(new GridBagLayout());
		gridConst = new GridBagConstraints();
				
	}

	/**
	 * Recoge los datos de los campos de validación de usuario y solicita la validación.
	 * 
	 * @param name {@link String} Nombre del usuario.
	 * @param pass {@link String} Password del usuario.
	 */
	protected void confirmaUsuario(String name, String pass) {
		
		if (!name.equals(null) && !pass.equals(null))
		{
			if (NegocioCliente.getinstance().validateUser(name, pass))
			{
				System.out.println("El usuario se ha validado!!!");
				validado=true;
				
				iniciarPanel(name);
				crearMenus();
			}
			else 
			{

			}; 
		}
		else {JOptionPane.showMessageDialog(this, "El nombre o usuario escritos no son válidos", "Escriba de nuevo", JOptionPane.INFORMATION_MESSAGE);};
	}

	private void iniciarPanel(String name) {
		
		cabecera = new Panel_Cabecera(name);
		
		contentPane.remove(principalPane);
		contentPane.add(cabecera, BorderLayout.NORTH);
		contentPane.repaint();
		contentPane.revalidate();
	}

	/**
	 * Carga los menús desde la carpeta de plugins.
	 * 
	 */
//	private void crearMenus() {
//
//		File folder = new File ("plugins"); 
//		
//		File[] listOfFiles = folder.listFiles();
//		
//		if (listOfFiles==null) System.out.println("No hay plugins!!!! en: "+folder.getAbsolutePath());
//		else{
//	    for (int i = 0; i < listOfFiles.length; i++) {
//	        final String fileName = listOfFiles[i].getName();
//			try
//			{
//			JPanel pane = (JPanel) Class.forName("plugins."+fileName.substring(0, fileName.length()-6)).newInstance();
//	        JButton btnVentanaReportes = new JButton(fileName.substring(6, fileName.length()-6));
//			gridConst.fill=GridBagConstraints.BOTH;
//			gridConst.insets=new Insets(25,25,25,25);
//			gridConst.weightx=0.5;
//			gridConst.anchor=GridBagConstraints.NORTH;
//			gridConst.gridy=i+1;
//			menuPanel.add(btnVentanaReportes,gridConst);
//			System.out.println("Hasta aqui llegó!!!!!");
//			btnVentanaReportes.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent arg0)
//				{
//					
//					if (validado)
//					{	
//					contentPane.remove(principalPane);
//					try {	
//						System.out.println("plugins."+fileName.substring(0, fileName.length()-6));
//						principalPane = (JPanel) Class.forName("plugins."+fileName.substring(0, fileName.length()-6)).newInstance();
//					
//					} catch (InstantiationException e) {
//						e.printStackTrace();
//					} catch (IllegalAccessException e) {
//						e.printStackTrace();
//					} catch (ClassNotFoundException e) {
//						e.printStackTrace();
//					}
//					contentPane.add(principalPane,BorderLayout.CENTER);
//					contentPane.revalidate();
//					}
//					else
//					{		JOptionPane.showMessageDialog(null, "Usuario no validado", "Error de validación", JOptionPane.ERROR_MESSAGE);
//					}
//				}
//			});
//			
//			contentPane.revalidate();
//			} catch (InstantiationException e) {
//				System.out.println("InstantiationException. "+e.getMessage());
//			} catch (IllegalAccessException e) {
//				System.out.println("IllegalAccessException. "+e.getMessage());
//			} catch (ClassNotFoundException e) {
//				System.out.println("ClassNotFoundException. "+e.getMessage());
//			} catch (NoClassDefFoundError e) {
//				System.out.println("ClassNotFoundException. "+e.getMessage());
//			}
//
//	    }
//		}
//	}
	
	private void crearMenus() {
			
	        JButton btnAlta = new JButton("Alta");
			gridConst.fill=GridBagConstraints.BOTH;
			gridConst.insets=new Insets(25,25,25,25);
			gridConst.weightx=0.5;
			gridConst.anchor=GridBagConstraints.NORTH;
			gridConst.gridy=0;
			menuPanel.add(btnAlta,gridConst);
			btnAlta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0)
				{
					
					if (validado)
					{	
					contentPane.remove(principalPane);
					principalPane = new Panel_Alta();
					contentPane.add(principalPane,BorderLayout.CENTER);
					contentPane.revalidate();
					}
					else
					{		JOptionPane.showMessageDialog(null, "Usuario no validado", "Error de validación", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
	        JButton btnBusqueda = new JButton("Búsqueda");
			gridConst.gridy=1;
			menuPanel.add(btnBusqueda,gridConst);
			btnBusqueda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0)
				{
					
					if (validado)
					{	
					contentPane.remove(principalPane);
					principalPane = new Panel_Busqueda();
					contentPane.add(principalPane,BorderLayout.CENTER);
					contentPane.revalidate();
					}
					else
					{		JOptionPane.showMessageDialog(null, "Usuario no validado", "Error de validación", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
	        JButton btnConsulta = new JButton("Consulta");
			gridConst.gridy=2;
			menuPanel.add(btnConsulta,gridConst);
			btnConsulta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0)
				{
					
					if (validado)
					{	
					contentPane.remove(principalPane);
					principalPane = new Panel_Consultas();
					contentPane.add(principalPane,BorderLayout.CENTER);
					contentPane.revalidate();
					}
					else
					{		JOptionPane.showMessageDialog(null, "Usuario no validado", "Error de validación", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
	        JButton btnRegistros = new JButton("Registros");
			gridConst.gridy=3;
			menuPanel.add(btnRegistros,gridConst);
			btnRegistros.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0)
				{
					
					if (validado)
					{	
					contentPane.remove(principalPane);
					principalPane = new Panel_Registros();
					contentPane.add(principalPane,BorderLayout.CENTER);
					contentPane.revalidate();
					}
					else
					{		JOptionPane.showMessageDialog(null, "Usuario no validado", "Error de validación", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
			contentPane.revalidate();
		}
}
