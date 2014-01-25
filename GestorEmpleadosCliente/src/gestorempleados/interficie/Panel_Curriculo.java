package gestorempleados.interficie;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gestorempleados.control.RestriccionesCeldas;
import gestorempleados.control.Idiomas;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Panel que muestra/recoge los datos curriculares del empleado.
 * 
 * @author Guillermo Blanco Martínez
 *
 */
@SuppressWarnings({"serial","rawtypes","unchecked"})
public class Panel_Curriculo extends JPanel implements DocumentListener {

	private JPanel titulacionPanel;
	private JTextField titulacionTxt;
	private JPanel idiomasPanel;
	private JList idiomasList;
	private JPanel experienciaPanel;
	private JTextArea textArea;

	/**
	 * Create the panel.
	 */
	public Panel_Curriculo() {
		setLayout(null);
		setBorder(null);

		titulacionPanel = new JPanel();
		titulacionPanel.setBorder(new TitledBorder(new CompoundBorder(), "Titulaci\u00F3n", TitledBorder.LEFT, TitledBorder.BELOW_TOP, null, null));
		titulacionPanel.setBounds(20, 24, 291, 45);
		add(titulacionPanel);
		titulacionPanel.setLayout(null);
		
		// TITULACION  --- data
		
		titulacionTxt = new JTextField();
		titulacionTxt.setBounds(24, 25, 224, 20);
		titulacionTxt.setDocument(new RestriccionesCeldas(RestriccionesCeldas.tipo.TITULACION));
		titulacionTxt.getDocument().putProperty("owner", titulacionTxt);
		titulacionTxt.getDocument().addDocumentListener(this);
		titulacionTxt.setColumns(10);
		titulacionPanel.add(titulacionTxt);
		
		idiomasPanel = new JPanel();
		idiomasPanel.setBounds(514, 26, 159, 108);
		idiomasPanel.setBorder(new TitledBorder(new CompoundBorder(), "Idiomas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		idiomasPanel.setLayout(null);
		add(idiomasPanel);
		
		JScrollPane idiomasScroll = new JScrollPane();
		idiomasScroll.setBounds(20, 26, 115, 70);
		idiomasPanel.add(idiomasScroll);
		
		// IDIOMAS -- data
		
		idiomasList = new JList(Idiomas.values());
		idiomasList.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		idiomasList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		idiomasList.setVisibleRowCount(0);
		
		idiomasScroll.setViewportView(idiomasList);
		
		experienciaPanel = new JPanel();
		experienciaPanel.setBounds(20, 93, 484, 329);
		add(experienciaPanel);
		experienciaPanel.setBorder(new TitledBorder(new CompoundBorder(), "Extracto experiencia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		experienciaPanel.setLayout(null);
		
		// EXPERIENCIA - data
		
		textArea = new JTextArea();
		textArea.setBounds(0, 0, 4, 22);
		textArea.setDocument(new RestriccionesCeldas(RestriccionesCeldas.tipo.EXPE));
		textArea.getDocument().putProperty("owner", textArea);
		textArea.getDocument().addDocumentListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 25, 441, 293);
		scrollPane.setViewportView(textArea);
		experienciaPanel.add(scrollPane);
	}
	
	public String getTitulacion() {
		String titulacion;
		if (titulacionTxt.getText().equals(""))titulacion=null;	
		else titulacion=titulacionTxt.getText();
		return titulacion;
	}
	
	public void setTitulacion(String titulacion) {
		this.titulacionTxt.setText(titulacion);
	}
	
	public Set getIdiomas() {
		
//		LEER IDIOMAS SELECCIONADOS
		List idiomasSelec=idiomasList.getSelectedValuesList();;
		for(Object idioma : idiomasSelec)System.out.println(idioma.toString());

		return new HashSet(idiomasList.getSelectedValuesList());
	}
	
	//CARGAR IDIOMAS EN PANEL EMPLEADO
	public void setIdiomas(Set idiomas) {
		
		for(int pos = 0; pos<idiomasList.getComponentCount();pos++)
    	{
			for (Object idioma : idiomas)
			{
				
				if (idiomasList.getComponent(pos).toString().equals(idioma))
				{
					idiomasList.setSelectedIndex(pos);
				}
				System.out.println(idiomasList.getComponent(pos).toString());
			}
    	}

	}
	
	public String getExperiencia() {
		String exp;
		if (textArea.getText().equals(""))exp=null;	
		else exp=textArea.getText();
		return exp;
	}
	
	public void setExperiencia(String text) {
		this.textArea.setText(text);
	}
	
//	RESTRICIONES EN CELDAS
	@Override
	public void changedUpdate(DocumentEvent e) {}
	
	@Override
	public void insertUpdate(DocumentEvent e) {
			
	}
	
	@Override
	public void removeUpdate(DocumentEvent e) {		
		insertUpdate(e);
	}

}
