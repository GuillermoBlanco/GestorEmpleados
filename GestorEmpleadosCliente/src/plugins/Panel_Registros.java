package plugins;

import gestorempleados.control.NegocioCliente;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.Serializable;

/**
 * Panel para la obtención de reportes.
 * 
 * @author Guillermo Blanco Martínez
 *
 */
@SuppressWarnings("serial")
public class Panel_Registros extends JPanel implements Serializable{

	/**
	 * Create the panel.
	 */
	public Panel_Registros() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		
		JLabel reportesLbl = new JLabel("Creaci\u00F3n de reportes");
		reportesLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		reportesLbl.setBounds(33, 25, 230, 25);
		add(reportesLbl);
		
		JButton btnEmpleados = new JButton("Empleados CON contrato activo");
		btnEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				NegocioCliente.getinstance().getReporte("empleadosConContrato", null);
			}
		});
		btnEmpleados.setBounds(35, 97, 179, 107);
		add(btnEmpleados);
		
		JButton btnEmpleadosSinContrato = new JButton("Empleados SIN contrato activo");
		btnEmpleadosSinContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NegocioCliente.getinstance().getReporte("empleadosSinContrato", null);
			}
		});
		btnEmpleadosSinContrato.setBounds(251, 97, 179, 107);
		add(btnEmpleadosSinContrato);

	}

	/**
	 * Muestra una ventana de selección de archivo
	 * 
	 * @return {@link File} El archivo selecionado.
	 */
	protected File selectFile() {
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
		
		return fileChooser.getSelectedFile();
	}
}
