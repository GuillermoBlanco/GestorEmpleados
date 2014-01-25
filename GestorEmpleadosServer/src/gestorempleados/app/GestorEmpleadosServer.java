package gestorempleados.app;

import gestorempleados.conector.StartServer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GestorEmpleadosServer extends JFrame {

	private JPanel contentPane;
	private JButton btnStartServer;
	
	private JLabel status;
	
	private StartServer negocioRRHH;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestorEmpleadosServer frame = new GestorEmpleadosServer();
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
	public GestorEmpleadosServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		negocioRRHH = new StartServer();
		
		
		btnStartServer = new JButton("Start Server");
		btnStartServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Thread thread = new Thread(new Runnable() {
					
					@Override
					public void run() {
						negocioRRHH.connectServer();
											
					}
				});
				
				thread.start();
				btnStartServer.setEnabled(false);
				status.setText("CONENCTED");
				
			}
		});
		btnStartServer.setBounds(113, 91, 178, 81);
		contentPane.add(btnStartServer);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(112, 215, 46, 14);
		contentPane.add(lblStatus);
		
		status = new JLabel("DISCONECTED");
		status.setBounds(192, 215, 100, 14);
		contentPane.add(status);
		
		JLabel lblGestorEmpleadosServer = new JLabel("Gestor Empleados Server");
		lblGestorEmpleadosServer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGestorEmpleadosServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestorEmpleadosServer.setBounds(26, 11, 357, 50);
		contentPane.add(lblGestorEmpleadosServer);
	}
}
