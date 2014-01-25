package gestorempleados.interficie;

import gestorempleados.persistencia.Departamento;
import gestorempleados.control.NegocioCliente;
import gestorempleados.control.Paises;
import gestorempleados.control.Sedes;
import gestorempleados.control.Temporales;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;

/**
 * Panel que muestra/recoge los datos de contrato del empleado.
 * 
 * @author Guillermo Blanco Martínez
 *
 */
@SuppressWarnings({ "rawtypes", "serial" ,"unchecked"})

public class Panel_Contrato extends JPanel {

	public JPanel contratosPanel;
	public JList contratosList;
	public  JPanel panelEstado;
	public JPanel nuevo_contratoPanel;
	private JComboBox departamentoCmb;
	private JPanel departamentoPanel;
	private JPanel cargoPanel;
	private JComboBox cargoCmb;
	private JPanel origenPanel;
	private JComboBox origenCmb;
	private JPanel sedePanel;
	private JComboBox sedeCmb;
	private JPanel modalidaPanel;
	private JCheckBox temporalchckbx;
	private JCheckBox indefinidoChckbx;
	private ButtonGroup checkgroup;
	public JPanel datos_temporalPanel;
	private JPanel motivoPanel;
	private JTextField motivoTxt;
	private JPanel clasePanel;
	private JComboBox claseCmb;
	private JPanel salarioPanel;
	private JTextField salarioTxt;
	private JLabel lblbrutosao;
	private JPanel pruebaPanel;
	private JTextField pruebaTxt;
	private JLabel prueba_mesesLbl;
	private JLabel periodoLbl;
	private JPanel preavisoPanel;
	private JTextField preavisoTxt;
	private JLabel preaviso_mesesLbl;
	private JLabel preavisoLbl;
	private JPanel clausulaPanel;
	private JCheckBox clausulaChck;
	private JLabel calusulaLbl;
	private JPanel medicoPanel;
	private JCheckBox saludChk;
	private JLabel lblSeguroMdico;
	private JPanel vidaPanel;
	private JCheckBox vidaChk;
	private JLabel lblSeguroDeVida;
	private JCheckBox fechafinChkBx;
	private JPanel fecha_inicioPanel;
	private Calendario calendario_inicio;
	private Calendario calendario_fin;
	private JLabel contratoLbl;
	private JLabel num_contratoLbl;
	
	private JRadioButton activoRdbtn;
	private JRadioButton inactivoRdbtn;
	
	
	public boolean camposContratoOK;
	private JPanel fecha_finPanel;
	private JPanel idContrato;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	/**
	 * Create the panel.
	 */
	public Panel_Contrato() {
		setLayout(null);
		
		panelEstado = new JPanel();
		panelEstado.setBorder(new TitledBorder(null, "Estado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEstado.setBounds(550, 100, 130, 65);
		add(panelEstado);
		panelEstado.setLayout(new BorderLayout(0, 0));
		
		activoRdbtn = new JRadioButton("Activo");
		buttonGroup.add(activoRdbtn);
		panelEstado.add(activoRdbtn, BorderLayout.NORTH);
		
		inactivoRdbtn = new JRadioButton("Inactivo");
		buttonGroup.add(inactivoRdbtn);
		panelEstado.add(inactivoRdbtn, BorderLayout.SOUTH);
		
		idContrato = new JPanel();
		idContrato.setBounds(597, 20, 83, 39);
		add(idContrato);
		idContrato.setLayout(null);
		
		contratoLbl = new JLabel("Contrato");
		contratoLbl.setBounds(0, 0, 83, 14);
		idContrato.add(contratoLbl);
		contratoLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		contratoLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		num_contratoLbl = new JLabel("");
		num_contratoLbl.setBounds(37, 25, 46, 14);
		idContrato.add(num_contratoLbl);
		num_contratoLbl.setHorizontalAlignment(SwingConstants.RIGHT);

		contratosPanel = new JPanel();
		contratosPanel.setBorder(new TitledBorder(new CompoundBorder(), "Listado contratos", TitledBorder.RIGHT, TitledBorder.TOP, null, null));
		contratosPanel.setBounds(538, 200, 165, 177);
		add(contratosPanel);
		contratosPanel.setLayout(null);
		contratosPanel.setVisible(false);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 23, 145, 141);
		contratosPanel.add(scrollPane_1);
		
		contratosList = new JList();
		scrollPane_1.setViewportView(contratosList);
		
		nuevo_contratoPanel = new JPanel();
		nuevo_contratoPanel.setBounds(32, 11, 671, 411);
		add(nuevo_contratoPanel);
		nuevo_contratoPanel.setLayout(null);
		
		fechafinChkBx = new JCheckBox("Fecha fin");
		fechafinChkBx.setBounds(5, 330, 83, 23);
		nuevo_contratoPanel.add(fechafinChkBx);
		fechafinChkBx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fecha_finPanel.setVisible(!fecha_finPanel.isVisible());
			}
		});
		
		departamentoPanel = new JPanel();
		departamentoPanel.setBorder(new TitledBorder(new CompoundBorder(), "Departamento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		departamentoPanel.setBounds(0, 0, 190, 37);
		nuevo_contratoPanel.add(departamentoPanel);
		departamentoPanel.setLayout(null);
		
		departamentoCmb = new JComboBox();
		departamentoCmb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargoCmb.setModel(new DefaultComboBoxModel(((Departamento)departamentoCmb.getSelectedItem()).getCargos().toArray()));
			}
		});
		departamentoCmb.setBounds(10, 17, 170, 20);
		departamentoCmb.setModel(new DefaultComboBoxModel(NegocioCliente.getinstance().getDepartamentos().toArray()));
		departamentoPanel.add(departamentoCmb);
		
		cargoPanel = new JPanel();
		cargoPanel.setBounds(0, 48, 190, 41);
		nuevo_contratoPanel.add(cargoPanel);
		cargoPanel.setBorder(new TitledBorder(new CompoundBorder(), "Cargo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cargoPanel.setLayout(null);
		
		cargoCmb = new JComboBox();
		cargoCmb.setBounds(10, 17, 170, 20);
		cargoCmb.setModel(new DefaultComboBoxModel(((Departamento)departamentoCmb.getSelectedItem()).getCargos().toArray()));
		cargoPanel.add(cargoCmb);
		
		origenPanel = new JPanel();
		origenPanel.setBounds(0, 92, 190, 48);
		nuevo_contratoPanel.add(origenPanel);
		origenPanel.setLayout(null);
		origenPanel.setBorder(new TitledBorder(new CompoundBorder(), "Pais de contrataci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		origenCmb = new JComboBox();
		origenCmb.setModel(new DefaultComboBoxModel(Paises.values()));
		origenCmb.setBounds(10, 17, 139, 20);
		origenPanel.add(origenCmb);
		
		sedePanel = new JPanel();
		sedePanel.setBounds(0, 145, 233, 48);
		nuevo_contratoPanel.add(sedePanel);
		sedePanel.setBorder(new TitledBorder(new CompoundBorder(), "Lugar de trabajo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sedePanel.setLayout(null);
		
		sedeCmb = new JComboBox();
		sedeCmb.setModel(new DefaultComboBoxModel(Sedes.values()));
		sedeCmb.setBounds(12, 23, 137, 22);
		sedePanel.add(sedeCmb);
		
		modalidaPanel = new JPanel();
		modalidaPanel.setBounds(274, 11, 190, 54);
		nuevo_contratoPanel.add(modalidaPanel);
		modalidaPanel.setBorder(new TitledBorder(null, "Modalidad de contrato", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		modalidaPanel.setLayout(null);
		
		indefinidoChckbx = new JCheckBox("Indefinido");
		indefinidoChckbx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				datos_temporalPanel.setVisible(false);
			}
		});
		indefinidoChckbx.setBounds(6, 22, 84, 23);
		modalidaPanel.add(indefinidoChckbx);
		
		temporalchckbx = new JCheckBox("Temporal");
		temporalchckbx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				datos_temporalPanel.setVisible(true);
			}
		});
		temporalchckbx.setBounds(94, 22, 90, 23);
		modalidaPanel.add(temporalchckbx);
		
		checkgroup = new ButtonGroup();
		checkgroup.add(indefinidoChckbx);
		checkgroup.add(temporalchckbx);
		
		datos_temporalPanel = new JPanel();
		datos_temporalPanel.setBounds(274, 70, 190, 120);
		nuevo_contratoPanel.add(datos_temporalPanel);
		datos_temporalPanel.setBorder(new TitledBorder(null, "Detalles contrato temporal", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		datos_temporalPanel.setVisible(false);
		datos_temporalPanel.setLayout(null);
		
		motivoPanel = new JPanel();
		motivoPanel.setBorder(new TitledBorder(new CompoundBorder(), "Motivo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		motivoPanel.setBounds(10, 24, 170, 36);
		datos_temporalPanel.add(motivoPanel);
		motivoPanel.setLayout(null);
		
		motivoTxt = new JTextField();
		motivoTxt.setBounds(10, 16, 150, 20);
		motivoPanel.add(motivoTxt);
		motivoTxt.setColumns(10);
		
		clasePanel = new JPanel();
		clasePanel.setBorder(new TitledBorder(new CompoundBorder(), "Clase", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		clasePanel.setBounds(10, 63, 118, 50);
		datos_temporalPanel.add(clasePanel);
		clasePanel.setLayout(null);
		
		claseCmb = new JComboBox();
		claseCmb.setModel(new DefaultComboBoxModel(Temporales.values()));
		claseCmb.setBounds(10, 21, 108, 20);
		clasePanel.add(claseCmb);
		
		salarioPanel = new JPanel();
		salarioPanel.setBounds(0, 199, 233, 41);
		nuevo_contratoPanel.add(salarioPanel);
		salarioPanel.setLayout(null);
		salarioPanel.setBorder(new TitledBorder(new CompoundBorder(), "Salario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		salarioTxt = new JTextField();
		salarioTxt.setColumns(10);
		salarioTxt.setBounds(10, 17, 124, 20);
		salarioPanel.add(salarioTxt);
		
		lblbrutosao = new JLabel("\u20AC/bruto/a\u00F1o");
		lblbrutosao.setBounds(140, 21, 76, 14);
		salarioPanel.add(lblbrutosao);
		
		pruebaPanel = new JPanel();
		pruebaPanel.setBounds(231, 202, 233, 23);
		nuevo_contratoPanel.add(pruebaPanel);
		pruebaPanel.setLayout(null);
		pruebaPanel.setBorder(null);
		
		pruebaTxt = new JTextField();
		pruebaTxt.setColumns(10);
		pruebaTxt.setBounds(118, 0, 45, 20);
		pruebaPanel.add(pruebaTxt);
		
		prueba_mesesLbl = new JLabel("meses");
		prueba_mesesLbl.setBounds(169, 4, 52, 14);
		pruebaPanel.add(prueba_mesesLbl);
		
		periodoLbl = new JLabel("Periodo de prueba");
		periodoLbl.setBounds(12, 3, 106, 14);
		pruebaPanel.add(periodoLbl);
		
		preavisoPanel = new JPanel();
		preavisoPanel.setBounds(231, 238, 233, 23);
		nuevo_contratoPanel.add(preavisoPanel);
		preavisoPanel.setLayout(null);
		preavisoPanel.setBorder(null);
		
		preavisoTxt = new JTextField();
		preavisoTxt.setColumns(10);
		preavisoTxt.setBounds(118, 0, 45, 20);
		preavisoPanel.add(preavisoTxt);
		
		preaviso_mesesLbl = new JLabel("meses");
		preaviso_mesesLbl.setBounds(169, 4, 52, 14);
		preavisoPanel.add(preaviso_mesesLbl);
		
		preavisoLbl = new JLabel("Preaviso");
		preavisoLbl.setBounds(61, 3, 58, 14);
		preavisoPanel.add(preavisoLbl);
		
		clausulaPanel = new JPanel();
		clausulaPanel.setBounds(306, 295, 190, 34);
		nuevo_contratoPanel.add(clausulaPanel);
		clausulaPanel.setLayout(null);
		clausulaPanel.setBorder(null);
		
		clausulaChck = new JCheckBox();
		clausulaChck.setBounds(8, 9, 21, 20);
		clausulaPanel.add(clausulaChck);
		
		calusulaLbl = new JLabel("Cl\u00E1usula no competencia");
		calusulaLbl.setBounds(35, 13, 152, 14);
		clausulaPanel.add(calusulaLbl);
		
		medicoPanel = new JPanel();
		medicoPanel.setBounds(306, 328, 190, 34);
		nuevo_contratoPanel.add(medicoPanel);
		medicoPanel.setLayout(null);
		medicoPanel.setBorder(null);
		
		saludChk = new JCheckBox();
		saludChk.setBounds(8, 9, 21, 20);
		medicoPanel.add(saludChk);
		
		lblSeguroMdico = new JLabel("Seguro m\u00E9dico");
		lblSeguroMdico.setBounds(35, 13, 152, 14);
		medicoPanel.add(lblSeguroMdico);
		
		vidaPanel = new JPanel();
		vidaPanel.setBounds(306, 362, 190, 34);
		nuevo_contratoPanel.add(vidaPanel);
		vidaPanel.setLayout(null);
		vidaPanel.setBorder(null);
		
		vidaChk = new JCheckBox();
		vidaChk.setBounds(8, 9, 21, 20);
		vidaPanel.add(vidaChk);
		
		lblSeguroDeVida = new JLabel("Seguro de vida");
		lblSeguroDeVida.setBounds(35, 13, 152, 14);
		vidaPanel.add(lblSeguroDeVida);
		
		fecha_inicioPanel = new JPanel();
		fecha_inicioPanel.setBounds(0, 243, 267, 86);
		nuevo_contratoPanel.add(fecha_inicioPanel);
		fecha_inicioPanel.setBorder(new TitledBorder(new CompoundBorder(), "Fecha inicio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		fecha_inicioPanel.setLayout(null);
		
		calendario_inicio = new Calendario();
		calendario_inicio.setBounds(12, 23, 243, 55);
		fecha_inicioPanel.add(calendario_inicio);
		
		fecha_finPanel = new JPanel();
		fecha_finPanel.setBorder(new TitledBorder(new CompoundBorder(), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		fecha_finPanel.setBounds(0, 340, 255, 60);
		fecha_finPanel.setVisible(false);
		nuevo_contratoPanel.add(fecha_finPanel);
		fecha_finPanel.setLayout(null);
		
		calendario_fin = new Calendario();
		calendario_fin.setBounds(10, 11, 245, 50);
		fecha_finPanel.add(calendario_fin);
		
		camposContratoOK=true;
		
	}
	
	public Object getDepartamento() {
		if(departamentoCmb.getSelectedItem().toString().equals(""))
		{
			camposContratoOK=false;
		}
		
		return departamentoCmb.getSelectedItem();
	}
	
	public void setDepartamento(String departamento){

		for (int i=0; i<departamentoCmb.getItemCount();i++)
		{
			if (departamentoCmb.getItemAt(i).toString().equals(departamento))
			{
				departamentoCmb.setSelectedIndex(i);
			}
		}
	}
	
	public Object getCargo() {
		if(cargoCmb.getSelectedItem().toString().equals(""))
		{
			camposContratoOK=false;
		}
		
		return cargoCmb.getSelectedItem();
	}

	public void setCargo(String cargo) {
		for (int i=0; i<cargoCmb.getItemCount();i++)
		{
			if (cargoCmb.getItemAt(i).toString().equals(cargo))
			{
				cargoCmb.setSelectedIndex(i);
			}
		}
	}
	
	public String getMotivo() {
		String motivo;
		if(temporalchckbx.isSelected())
		{
			if (motivoTxt.getText().equals(""))
			{
				camposContratoOK=false;
				motivo=null;
			}
			else motivo=motivoTxt.getText();
		}
		else motivo=null;
		return motivo;
	}

	public void setMotivo(String motivo) {
		motivoTxt.setText(motivo);
	}

	public String getOrigen() {
		return origenCmb.getSelectedItem().toString();
	}

	public void setOrigen(String origen) {
		
		for (int i=0; i<origenCmb.getItemCount();i++)
		{
			if (origenCmb.getItemAt(i).toString().equals(origen))
			{
				origenCmb.setSelectedIndex(i);
			}
		}
		
	}
	
	public String getSede() {
		return sedeCmb.getSelectedItem().toString();
	}

	public void setSede(String sede) {
		
		for (int i=0; i<sedeCmb.getItemCount();i++)
		{
			if (sedeCmb.getItemAt(i).toString().equals(sede))
			{
				sedeCmb.setSelectedIndex(i);
			}
		}
	}
	
	public Integer getSalario() {
		Integer salario;
		if (!salarioTxt.getText().equals(""))
		{
//			nssLbl.setVisible(false);
			salario=Integer.parseInt(salarioTxt.getText());
			camposContratoOK=true;
		}
		else {
			salario=null;
//			nssLbl.setVisible(true);
			camposContratoOK=false;
		}
		return salario;
	}

	public void setSalario(int salario) {
		salarioTxt.setText(String.valueOf(salario));
	}
	
	public String getModalidad() {
		String mod;
		if (indefinidoChckbx.isSelected()) mod="Indefinido";
		else mod="Temporal";
		return mod;
	}

	public void setModalidad(String modalidad) {

		if (modalidad.equals("Indefinido"))
		{
			indefinidoChckbx.setSelected(true);
		}
		else { temporalchckbx.setSelected(true);}
	}
	
	public String getClase() {
		
		return claseCmb.getSelectedItem().toString();
	}

	public void setClase(String clase) {
		
		for (int i=0; i<claseCmb.getItemCount();i++)
		{
			if (claseCmb.getItemAt(i).toString().equals(clase))
			{
				claseCmb.setSelectedIndex(i);
			}
		}
	}
	
	public Date getFecha_inicio() {
		Date result=null;
		try {
		    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		    
			result = df.parse(calendario_inicio.getDia()+"-"+calendario_inicio.getMes()+"-"+calendario_inicio.getAno());

		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return result;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha_inicio);
		
		calendario_inicio.setDia(cal.get(Calendar.DAY_OF_MONTH));
		calendario_inicio.setMes(cal.get(Calendar.MONTH)+1);
		calendario_inicio.setAno(cal.get(Calendar.YEAR));
	}
	
	public Date getFecha_fin() {
		Date result=null;
		try {
		    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		    
			result = df.parse(calendario_fin.getDia()+"-"+calendario_fin.getMes()+"-"+calendario_fin.getAno());

		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return result;
	}

	public void setFecha_fin(Date fecha_fin) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha_fin);
		
		calendario_fin.setDia(cal.get(Calendar.DAY_OF_MONTH));
		calendario_fin.setMes(cal.get(Calendar.MONTH)+1);
		calendario_fin.setAno(cal.get(Calendar.YEAR));
	}
	
	public Integer getPeriodoPrueba() {
		Integer pPrueba;
		if (!pruebaTxt.getText().equals(""))
		{
			pPrueba=Integer.parseInt(pruebaTxt.getText());
			camposContratoOK=true;
		}
		else 
		{
			pPrueba=null;
			camposContratoOK=false;
		}
		return pPrueba;
	}

	public void setPeriodoPrueba(int pPrueba) {
		pruebaTxt.setText(String.valueOf(pPrueba));
	}
	
	public Integer getPreaviso() {
		Integer preaviso;
		if (!preavisoTxt.getText().equals(""))
		{
			preaviso=Integer.parseInt(preavisoTxt.getText());
			camposContratoOK=true;
		}
		else 
		{
			preaviso=null;
			camposContratoOK=false;
		}
		return preaviso;
	}

	public void setPreaviso(int preaviso) {
		preavisoTxt.setText(String.valueOf(preaviso));
	}
	
	public boolean getClausula() {
		return clausulaChck.isSelected();
	}

	public void setClausula(Boolean clausula) {
		clausulaChck.setSelected(clausula);
	}
	
	public boolean getSalud() {
		return saludChk.isSelected();
	}

	public void setSalud(boolean salud) {
		saludChk.setSelected(salud);
	}

	public boolean getVida() {
		return vidaChk.isSelected();
	}

	public void setVida(boolean vida) {
		vidaChk.setSelected(vida);
	}
	
	public void setIDContrato(int id)
	{
		num_contratoLbl.setText(""+id);
	}
	
	public int getIDContrato()
	{
		int id=-1;
		if (!num_contratoLbl.getText().equals("")) id=Integer.parseInt(num_contratoLbl.getText());
		return id;
	}
	
	public boolean getActivo()
	{
		return activoRdbtn.isSelected();
	}
	
	public void setActivo(boolean i)
	{
		activoRdbtn.setSelected(i);
	}
}
