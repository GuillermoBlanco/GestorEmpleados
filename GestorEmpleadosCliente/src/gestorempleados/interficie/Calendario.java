package gestorempleados.interficie;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.SpinnerNumberModel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Vector;

/**
 * Instancia de un calendario.
 * 
 * @author Guillermo Blanco Mart�nez.
 *
 */
@SuppressWarnings({"serial","rawtypes","unchecked"})
public class Calendario extends JPanel{

	JPanel panel;
	JPanel panel_1;
	
	JSpinner spDia, spAno;
	JComboBox cbMes;
	
	JLabel lblNewLabel,lblMes, lblAo;
	
	Calendar calendario;
	Calendar calcopia;
	
	int diaMax;
	int inicioMes;

	String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	Vector<JLabel> vLabelNum;
	Vector<JLabel> vLabelLet;
		
	int dia;
	public int getDia() {
		return (Integer) spDia.getValue();
	}

	public void setDia(int dia) {
		spDia.setValue(dia);
		this.dia = dia;
	}

	int mes;
	public int getMes() {
		return cbMes.getSelectedIndex()+1;
	}


	public void setMes(int mes) {
		cbMes.setSelectedIndex(mes-1);
	}
	
	int year;
	public int getAno() {
		return (Integer) spAno.getValue();
	}

	public void setAno(int ano) {
		spAno.setValue(ano);
	}

	public Calendario() {
		
		panel = new JPanel();
		panel.setBounds(0, 0, 255, 52);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 51, 255, 129);
		panel_1.setLayout(new GridLayout(7, 7));
		
		lblNewLabel = new JLabel("Dia");
		lblNewLabel.setBounds(12, 0, 32, 15);
		
		lblMes = new JLabel("Mes");
		lblMes.setBounds(100, 0, 32, 15);
		
		lblAo = new JLabel("Ano");
		lblAo.setBounds(205, 0, 32, 15);
		
		spDia = new JSpinner();
		spDia.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		spDia.setBounds(0, 27, 45, 20);
		spDia.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent arg0) 
			{
				dia=(Integer) spDia.getValue();
				
				if(dia>diaMax)
				{
					dia=diaMax;
				}
				spDia.setModel(new SpinnerNumberModel(dia, 1, diaMax, 1));
				
				dibujar();
			}
		});

		spAno = new JSpinner();
		spAno.setModel(new SpinnerNumberModel(new Integer(2012), null, null, new Integer(1)));
		spAno.setBounds(169, 27, 74, 20);
		spAno.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent arg0) 
			{
				year=(Integer) spAno.getValue();
				
				Calendar clndrTemp = Calendar.getInstance();
				
				clndrTemp.set(year, mes, 1);
				if(dia>clndrTemp.getActualMaximum(Calendar.DAY_OF_MONTH))
				{
					dia=clndrTemp.getActualMaximum(Calendar.DAY_OF_MONTH);
					spDia.setModel(new SpinnerNumberModel(dia, 1, dia, 1));
				}
				else spDia.setModel(new SpinnerNumberModel(dia, 1, clndrTemp.getActualMaximum(Calendar.DAY_OF_MONTH), 1));
				
				dibujar();
			}
		});
		
		cbMes = new JComboBox(meses);
		cbMes.setBounds(69, 24, 88, 24);
		cbMes.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				mes=cbMes.getSelectedIndex();
				Calendar clndrTemp = Calendar.getInstance();
				
				clndrTemp.set(year, mes, 1);
				if(dia>clndrTemp.getActualMaximum(Calendar.DAY_OF_MONTH))
				{
					dia=clndrTemp.getActualMaximum(Calendar.DAY_OF_MONTH);
					spDia.setModel(new SpinnerNumberModel(dia, 1, dia, 1));
				}
				else spDia.setModel(new SpinnerNumberModel(dia, 1, clndrTemp.getActualMaximum(Calendar.DAY_OF_MONTH), 1));
				
				dibujar();
			}
		});
		
		
		
		//Vectores para dias de la semana y números del mes
		
		vLabelLet = new Vector<JLabel>();
		
		JLabel label_DL = new JLabel("L");
		vLabelLet.add(label_DL);
		
		JLabel label_DM = new JLabel("M");
		vLabelLet.add(label_DM);
		
		JLabel label_DX = new JLabel("X");
		vLabelLet.add(label_DX);
		
		JLabel label_DJ = new JLabel("J");
		vLabelLet.add(label_DJ);
		
		JLabel label_DV = new JLabel("V");
		vLabelLet.add(label_DV);
		
		JLabel label_DS = new JLabel("S");
		vLabelLet.add(label_DS);
		
		JLabel label_DG = new JLabel("D");
		vLabelLet.add(label_DG);
		
		//Instancia de calendario, ajuste primer dia semana a Lunes
		calendario = Calendar.getInstance();
		calendario.setFirstDayOfWeek(Calendar.MONDAY);
		
		dia=(Integer) spDia.getValue();
		mes=cbMes.getSelectedIndex();
		year=(Integer) spAno.getValue();
		
		//Llamada a la función que pinta el calendario
		dibujar();
	}
	
	public void dibujar()
	{
		
		panel.removeAll();
		panel_1.removeAll();
		
		panel.revalidate();
		panel_1.revalidate();
		
		calendario.clear();
		if (calcopia!=null) {calcopia.clear();}
		
		calendario.setFirstDayOfWeek(Calendar.MONDAY);
		vLabelNum= new Vector<JLabel>();
		
		calendario.set(year, mes, dia);
		calcopia = (Calendar) calendario.clone();
		calcopia.set(year,mes,1);
				
		diaMax=calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
		inicioMes=(5 + calcopia.get(Calendar.DAY_OF_WEEK))%7;
			
		System.out.println("Calendario en " + calendario.get(Calendar.YEAR)+" "+calendario.get(Calendar.MONTH)+" "+calendario.get(Calendar.DATE));
		System.out.println("CALCOPIA año" +calcopia.get(Calendar.YEAR)+" mes "+calcopia.get(Calendar.MONTH)+" dia: "+calcopia.get(Calendar.DATE));
		System.out.println("Dia de la semana " + (inicioMes+1)+" y ultimo dia mes "+diaMax);
		
		for (JLabel jlbl :vLabelLet)
		{
			panel_1.add(jlbl);
		}
		
		int init=0;
		
		while (init<inicioMes)
		{
			vLabelNum.add(new JLabel(""));
			init++;
		}
		for (int i=0; i<diaMax;i++)
		{
			JLabel jlb = new JLabel(""+(i+1));
			
			if((i+1)==dia)
			{
				jlb.setText("*"+(i+1));
			}
			vLabelNum.add(jlb);
		}
		while (vLabelNum.size()<42)
		{
			JLabel jlb2 = new JLabel("");
			vLabelNum.add(jlb2);
		}
		
		for( JLabel lbl : vLabelNum)
		{
			panel_1.add(lbl);
		}
		setLayout(null);
		
		
		//Añadir etiquetas de selectores
		panel.add(lblNewLabel);
		panel.add(lblMes);
		panel.add(lblAo);
		
		//Añadir selectores (spinner y combo)
		panel.add(spDia);
		panel.add(spAno);
		panel.add(cbMes);
		
		add(panel);
		add(panel_1);
		repaint();
	}
}
