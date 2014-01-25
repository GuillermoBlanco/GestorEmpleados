package gestorempleados.interficie;

import java.io.File;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Generador de tablas en Excel
 * 
 * @author Guillermo Blanco Martínez
 *
 */
public class ExcelExporter {

	public void fillData(JTable table, File file) {

        try {

            WritableWorkbook workbook1 = Workbook.createWorkbook(file);
            WritableSheet sheet1 = workbook1.createSheet("First Sheet", 0); 
            TableModel model = table.getModel();

            for (int i = 0; i < model.getColumnCount(); i++) {
                Label column = new Label(i, 0, model.getColumnName(i));
                sheet1.addCell(column);
            }
//            int j = 0;
//            for (int i = 0; i < model.getRowCount(); i++) {
//            	System.out.println(model.getRowCount());
//                for (j = 0; j < model.getColumnCount(); j++) {
//                	System.out.println(model.getColumnCount());
//                	Object objeto=table.getValueAt(j,i);
//                	sheet1.addCell(new Label(j, i+1, String.valueOf(objeto)));
//                }
//            }
//            
            workbook1.write();
            workbook1.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
}
