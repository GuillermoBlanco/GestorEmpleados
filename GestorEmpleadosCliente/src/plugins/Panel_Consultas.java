package plugins;

import gestorempleados.control.NegocioCliente;
import gestorempleados.interficie.ExcelExporter;
import gestorempleados.interficie.Panel_Contrato;
import gestorempleados.interficie.Panel_Curriculo;
import gestorempleados.interficie.Panel_DatosPersonales;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Panel para la consulta de los empleado.
 * 
 * @author Guillermo Blanco Martínez
 *
 */
@SuppressWarnings({"serial","rawtypes","unchecked"})
public class Panel_Consultas extends JPanel implements Serializable{
	
	private DefaultTableModel tableModel;
	
	private String[] columNames = {"DNI","NOMBRE","APELLIDO1","APELLIDO2","NACIONALIDAD","NSS","DEPARTAMENTO","ACTIVO"};
	Class[] types = new Class[]{Object.class, Object.class, Object.class,Object.class,Object.class,Object.class,Object.class,Boolean.class};
	private JTable table;
	
	private JButton refreshBtn;
	public JButton update;
	
	private JFrame edicion;
	
	private Panel_Contrato contratosPanel;
	
	/**
	 * Create the panel.
	 */
	public Panel_Consultas() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		
		JLabel lblConsultas = new JLabel("Consultas");
		lblConsultas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblConsultas.setBounds(23, 26, 158, 14);
		add(lblConsultas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 97, 755, 406);
		add(scrollPane);
		
		table = new JTable();
		tableModel = new DefaultTableModel(){
            public Class getColumnClass(int columnIndex) {return types[columnIndex];}
			@Override
			public boolean isCellEditable(int row, int column) {return false;}
		};
		tableModel.setColumnIdentifiers(columNames);
		table.setAutoCreateRowSorter(true);
		table.setModel(tableModel);
		table.addMouseListener(new MouseAdapter() {
			   public void mouseClicked(MouseEvent e) {
			      if (e.getClickCount() == 2) {
			        JTable target = (JTable)e.getSource();
			        int row = target.getSelectedRow();
			        
			        String dni = (String) target.getValueAt(row, target.getColumn("DNI").getModelIndex());

					editarDatosEmpleado(dni);
			        }
			   }
			});
		
		scrollPane.setViewportView(table);
		
		refreshBtn = new JButton("Refresh");
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//CREAR FILTROS!!!!!
				
				System.out.println("Refresh!!!!");
				
				tableModel.getDataVector().removeAllElements();
				NegocioCliente.getinstance().getEmpleados(tableModel);
				table.setModel(tableModel);
				table.repaint();
				revalidate();				
				
				System.out.println("Tabla creada");
			}
		});
		refreshBtn.setBounds(689, 63, 89, 23);
		add(refreshBtn);
		
		JButton btnUpdeteCv = new JButton("CV");
		btnUpdeteCv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row;
				if ((row=table.getSelectedRow())!=-1)
				{
			        String dni = (String) table.getValueAt(row, table.getColumn("DNI").getModelIndex());
		         
			        editarCVEmpleado(dni);
				}
				
			}
		});
		btnUpdeteCv.setBounds(206, 63, 70, 23);
		add(btnUpdeteCv);
		
		JButton btnNewButton_1 = new JButton("Datos personales");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row;
				if ((row=table.getSelectedRow())!=-1)
				{
			        String dni = (String) table.getValueAt(row, table.getColumn("DNI").getModelIndex());
		         
					editarDatosEmpleado(dni);
				}
			}
		});
		btnNewButton_1.setBounds(33, 63, 152, 23);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Contrato");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row;
				if ((row=table.getSelectedRow())!=-1)
				{
			        String dni = (String) table.getValueAt(row, table.getColumn("DNI").getModelIndex());
		         
					editarContratosEmpleado(dni);
				}
			}
		});
		btnNewButton_2.setBounds(299, 63, 97, 23);
		add(btnNewButton_2);
		

		JButton exportBtn = new JButton("xls");
		exportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser(){
					
					@Override
					public void approveSelection() {

						File f = getSelectedFile();
				        if(f.exists() && getDialogType() == SAVE_DIALOG){
				            int result = JOptionPane.showConfirmDialog(this,"The file exists, overwrite?","Existing file",JOptionPane.YES_NO_CANCEL_OPTION);
				            switch(result){
				                case JOptionPane.YES_OPTION:
				                    super.approveSelection();
				                    return;
				                case JOptionPane.NO_OPTION:
				                    return;
				                case JOptionPane.CLOSED_OPTION:
				                    return;
				                case JOptionPane.CANCEL_OPTION:
				                    cancelSelection();
				                    return;
				            }
				        }
						
						super.approveSelection();
					}
				};
				
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setApproveButtonText("Export");
				fileChooser.showSaveDialog(null);
				
				ExcelExporter exportador = new ExcelExporter();
					File temp = fileChooser.getSelectedFile();
					if (temp!=null){

						File archivo = new File(fileChooser.getSelectedFile().getPath());
						exportador.fillData(table, archivo);
						
						JOptionPane.showMessageDialog(null, "Data saved at " +
		                        fileChooser.getSelectedFile().getAbsolutePath()+" successfully", "Succes!!",
		                        JOptionPane.INFORMATION_MESSAGE);
					}
				

			}
		});
		
		exportBtn.setBounds(708, 29, 70, 23);
		add(exportBtn);
		refreshBtn.doClick();
	}

	/**
	 * Carga el panel de edición de datos personales del alumno.
	 * 
	 * @param dni {@link String} DNI del empleado a editar.
	 */
	protected void editarDatosEmpleado(final String dni) {
		
		JButton update = new JButton("Update");
		update.setBounds(300, 430, 120, 30);
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				NegocioCliente.getinstance().modifDatosEmpleado((Panel_DatosPersonales)edicion.getContentPane(),dni);
				edicion.dispose();
				refreshBtn.doClick();
			}
		});
		
		Panel_DatosPersonales datosPanel = new Panel_DatosPersonales();
		NegocioCliente.getinstance().showDatosEmpleado(datosPanel, dni);
		datosPanel.disableKey();
		
		edicion = new JFrame("Editar");

		edicion.getContentPane().setLayout(null);
		edicion.setContentPane(datosPanel);
		edicion.getContentPane().add(update);
		edicion.setPreferredSize(new Dimension(750, 520));
		edicion.pack();
		edicion.setLocationRelativeTo(this);
		edicion.setVisible(true);
		
	}
	
	/**
	 * Carga el panel de edición de datos curriculares del alumno.
	 * 
	 * @param dni {@link String} DNI del empleado a editar.
	 */
	protected void editarCVEmpleado(final String dni) {
		
		update = new JButton("Update");
		update.setBounds(300, 430, 120, 30);
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				NegocioCliente.getinstance().modifDatosEmpleado((Panel_Curriculo)edicion.getContentPane(),dni);
				edicion.dispose();
				refreshBtn.doClick();
			}
		});
		
		Panel_Curriculo curriculoPanel = new Panel_Curriculo();
		NegocioCliente.getinstance().showCVEmpleado(curriculoPanel, dni);
		
		edicion = new JFrame("Editar");

		edicion.getContentPane().setLayout(null);
		edicion.setContentPane(curriculoPanel);
		edicion.getContentPane().add(update);
		edicion.setPreferredSize(new Dimension(750, 520));
		edicion.pack();
		edicion.setLocationRelativeTo(this);
		edicion.setVisible(true);
		
	}
	
	/**
	 * Carga el panel de edición de datos curriculares del alumno.
	 * 
	 * @param dni {@link String} DNI del empleado a editar.
	 */
	protected void editarContratosEmpleado(final String dni) {
		
		JButton update = new JButton("Update");
		update.setBounds(300, 430, 120, 30);
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				if(contratosPanel.nuevo_contratoPanel.isVisible())
				{
				NegocioCliente.getinstance().modifDatosEmpleado((Panel_Contrato)edicion.getContentPane(),dni);
				edicion.dispose();
				refreshBtn.doClick();
				}
				else JOptionPane.showMessageDialog(null, "Debe seleccionar un contrato para editar", "Acción no válida", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		contratosPanel = new Panel_Contrato();
		NegocioCliente.getinstance().showContratoEmpleado(contratosPanel, dni);
		
//		update.setEnabled(contratosPanel.nuevo_contratoPanel.isVisible());
		
		edicion = new JFrame("Editar");
		edicion.getContentPane().setLayout(null);
		edicion.setContentPane(contratosPanel);
		edicion.getContentPane().add(update);
		edicion.setPreferredSize(new Dimension(750, 520));
		edicion.pack();
		edicion.setLocationRelativeTo(this);
		edicion.setVisible(true);
		
	}
}
