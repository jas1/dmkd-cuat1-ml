package ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.db;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.old.AutomatizarCorridasArbolJulio;

/**
 * para cargar datos en la DB.
 * 
 * deben tener instalado mysql , deben tener suficiente espacio en el disco ,
 * deben tener creado usuario con permisos para cargar
 * 
 * @author julio
 *
 */
public class DMFinanzasDBLoader {

	// son 171 columnas
	private final static String insertTableSQL = "INSERT INTO dm_finanzas_historia VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private List<Integer> listaBigints;
	private List<Integer> listaText;
	private List<Integer> listaDoubles;
	private List<Integer> listaBools;

	public DMFinanzasDBLoader() {
		int[] bigintList = { 6, 68, 143, 79, 63, 87, 111, 7, 47, 104, 129, 83, 149, 86, 152, 139, 108, 126, 81, 161, 113, 130, 1, 165, 115, 64, 3, 123, 38, 62, 106, 148, 102, 45, 89, 85, 4, 92, 94, 35, 98, 5, 43, 100, 127, 96, 2, 13, 41, 151, 121 };
		int[] textList = { 171 };
		// 27 y 9 eran big int pero revento a mitad del proceso , el 27 revento ya muy avanzado el procesamiento en lso 3 millones y pico 
		// el 9 revento casi al inicio
		int[] doubleList = { 27,9,128, 166, 138, 133, 164, 103, 32, 99, 82, 124, 153, 76, 159, 11, 107, 29, 39, 56, 158, 140, 53, 157, 109, 51, 155, 67, 78, 74, 142, 54, 101, 169, 25, 12, 150, 84, 50, 162, 135, 105, 26, 70, 46, 31, 10, 136, 163, 95, 137, 36, 66, 154, 72, 160, 147, 156, 93, 141, 48, 134, 97, 131, 8, 132, 144, 90, 30, 122, 44, 42, 33, 88, 80 };
		int[] tinyintList = { 16, 17, 14, 15, 37, 60, 22, 20, 170, 21, 18, 19, 59, 24, 28, 65, 91, 58, 23, 119, 112, 75, 125, 61, 117, 57, 167, 146, 77, 110, 71, 168, 145, 49, 34, 116, 114, 52, 55, 118, 40, 69, 120, 73 };

		listaBigints = DBFinanzasDBQueryBuilder.converToLista(bigintList);
		listaText =  DBFinanzasDBQueryBuilder.converToLista(textList);
		listaDoubles =  DBFinanzasDBQueryBuilder.converToLista(doubleList);
		listaBools =  DBFinanzasDBQueryBuilder.converToLista(tinyintList);
	}

	public void cargarBaseGrande(String lines, Connection con) {

		System.out.println("inicio proceso: " + AutomatizarCorridasArbolJulio.getTimeStamp(new Date(), null) );
		
		try {
			FileInputStream fis = new FileInputStream(lines);

			// Construct BufferedReader from InputStreamReader
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));

//		par air guardando de a 1k
			final int batchSize = 1000;
			int batchCount = 0;

			PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
			String line = null;
			while ((line = br.readLine()) != null) {

				String[] line2 = line.split("\t");
//				System.out.println(line2.length);
				
				
				// Map<String, String> mapaValores = mapaValores(line);

				try {

					// flag por el ultimo campo 
					boolean set171=false;
					int currentIndex = 1;
					for (String currentCampo : line2) {

						if (listaBigints.contains(currentIndex)) {
							if (currentCampo.trim().length() > 0) {
								preparedStatement.setLong(currentIndex, Long.parseLong(currentCampo));
							} else {
								preparedStatement.setNull(currentIndex, Types.BIGINT);
							}
						}

						if (listaBools.contains(currentIndex)) {
							if (currentCampo.trim().length() > 0) {
								preparedStatement.setInt(currentIndex, currentCampo.equals("S") ? 1 : 0);
							} else {
								preparedStatement.setNull(currentIndex, Types.TINYINT);
							}
						}

						if (listaDoubles.contains(currentIndex)) {
							if (currentCampo.trim().length() > 0) {
								preparedStatement.setDouble(currentIndex, Double.parseDouble(currentCampo));
							} else {
								preparedStatement.setNull(currentIndex, Types.DOUBLE);
							}
						}

						if (listaText.contains(currentIndex)) {
							
							// si es el ultimo
							if (currentIndex == 171) {
								set171=true;
							}
							
							if (currentCampo.trim().length() > 0) {
								preparedStatement.setString(currentIndex, currentCampo);
							} else {
								preparedStatement.setNull(currentIndex, Types.VARCHAR);
							}
						}

						currentIndex++;
					}// fin agregado dinamico de columnas
					
					// si no se seteo el ultimo
					if (!set171) {
						// lo seteo como null
						preparedStatement.setNull(171, Types.VARCHAR);
					}
					
					
					
//					preparedStatement.toString();
					preparedStatement.addBatch();
					if (++batchCount % batchSize == 0) {
						preparedStatement.executeBatch();
						preparedStatement.clearBatch(); // not sure if this is
														// necessary
					}

				} catch (SQLException | NumberFormatException e) {
					System.out.println(line);
					System.out.println("revento proceso: " + AutomatizarCorridasArbolJulio.getTimeStamp(new Date(), null) );
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			// para los ultimos
			preparedStatement.executeUpdate();
			br.close();
		} catch (Exception e) {
			
			System.out.println("revento proceso: " + AutomatizarCorridasArbolJulio.getTimeStamp(new Date(), null) );
			e.printStackTrace();
			throw new RuntimeException(e);

		}
		
		System.out.println("fin proceso: " + AutomatizarCorridasArbolJulio.getTimeStamp(new Date(), null) );
	}
}
