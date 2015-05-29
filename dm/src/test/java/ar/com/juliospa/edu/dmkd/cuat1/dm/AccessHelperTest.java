package ar.com.juliospa.edu.dmkd.cuat1.dm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.DataType;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

public class AccessHelperTest {

	@Test
	public void getTablasAsCsv() {
		final String TAB = "\t";
		final String ENTER = "\n";
		final String ENCODE = "UTF-8";
		final String path = "C:/Users/Julio Spairani/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_1_data_mining/TP1/";
		
		Writer writer = null;
		try {

			String nombreMdb = "20150511_BaseTP.accdb";
			Set<String> nombres = DatabaseBuilder.open(new File(path + nombreMdb)).getTableNames();
			for (String nombre : nombres) {
//				System.out.println(nombre);
				Table table = DatabaseBuilder.open(new File(path + nombreMdb)).getTable(nombre);
				
				StringBuilder lBuild = new StringBuilder();
				for (Column col : table.getColumns()) {

					lBuild.append(col.getName()).append(TAB);
				}
				lBuild.deleteCharAt(lBuild.length() - 1);
				lBuild.append(ENTER);
				
				
				for (Row row : table) {
					for (Column col : table.getColumns()) {
						lBuild.append(row.get(col.getName())).append(TAB);
					}
					lBuild.deleteCharAt(lBuild.length() - 1);
					lBuild.append(ENTER);
				}
				
				String fileName = getTimeStamp(null,null)+nombre;
				
				writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(path + fileName +".txt"), ENCODE));
				writer.write(lBuild.toString());

			}
		
//			"TP_Categoría"
//			"TP_Clientes"
//			"TP_Precio_Sugerido"
//			"TP_Productos"
//			"TP_Sub_Categoría"
//			"TP_Ventas"
//			"TP_Ventas_Prod"

			
			
//			table = DatabaseBuilder.open(new File(path + "20150511_BaseTP.accdb")).getTableNames()
/*
			StringBuilder lBuild = new StringBuilder();
			for (Column col : table.getColumns()) {

				lBuild.append(col.getName()).append(TAB);
			}
			lBuild.deleteCharAt(lBuild.length() - 1);
			lBuild.append(ENTER);

			for (Row row : table) {
				for (Column col : table.getColumns()) {

					if (col.getType() == DataType.DOUBLE) {

						BigDecimal big = new BigDecimal((Double) row.get(col
								.getName()));

						lBuild.append(big.toPlainString()).append(TAB);
					} else {
						lBuild.append(row.get(col.getName())).append(TAB);
					}

				}
				lBuild.deleteCharAt(lBuild.length() - 1);
				lBuild.append(ENTER);
			}
			lBuild.deleteCharAt(lBuild.length() - 1);

			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(path + "2015-1.txt"), ENCODE));
			writer.write(lBuild.toString());
*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Assert.fail();
				}
			}
		}

	}
	
	/**
	 * da un timestamp para fecha / hora
	 * 
	 * @param date
	 *            : default now
	 * @param format
	 *            : default yyyyMMdd - HH:mm:ss
	 * @return
	 */
	public static String getTimeStamp(Date date, String format) {
		Date defaultDate = (date != null) ? date : new Date();
		String defaulFormat = (format != null) ? format : "yyyyMMdd_HHmmss_";

		SimpleDateFormat lSDF = new SimpleDateFormat(defaulFormat);
		return lSDF.format(defaultDate);
	}
	
}
