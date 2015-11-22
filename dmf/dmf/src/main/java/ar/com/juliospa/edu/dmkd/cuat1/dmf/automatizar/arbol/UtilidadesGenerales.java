package ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ibm.statistics.plugin.StatsException;
import com.ibm.statistics.plugin.StatsUtil;

public class UtilidadesGenerales {

	/**
	 * @param commands
	 */
	public static void ejecutarComandosSpss(String[] commands) {
		try {
			StatsUtil.start();
			StatsUtil.submit(commands);
			StatsUtil.stop();
		} catch (StatsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Comando ejecutado");
			for (String string : commands) {
				System.out.println(string);
			}
		}
	}

	public static String getTimeStamp(Date date, String format) {
		Date defaultDate = (date != null) ? date : new Date();
		String defaulFormat = (format != null) ? format : "yyyyMMdd_HHmmss";
	
		SimpleDateFormat lSDF = new SimpleDateFormat(defaulFormat);
		return lSDF.format(defaultDate);
	}

}
