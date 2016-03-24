package mdb.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.DataType;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

public class MdbUtils {

	@Test
	public void extraerBalancesToTxt() {

//		String path = "C:/Users/jspairani/Desktop/DMKD/balances/";
		String path = "C:/Users/julio/Desktop/dmkd/balances/";

		List<String> nombreArchivos = new ArrayList<>();

		try {
			Stream<Path> paths = Files.list(new File(path).toPath()).filter(p -> !p.toFile().isDirectory());

			paths.forEach(p -> agarrarFileNames(p, nombreArchivos));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (String string : nombreArchivos) {
			String name = string;
			String nameTxt = string.replace(".mdb", ".txt");

			migrarATxt(path, name, nameTxt);
		}

	}

	private void agarrarFileNames(Path p, List<String> s) {
		String name = p.toFile().getName();
		if (name.endsWith(".mdb")) {
			s.add(name);
		}
	}

	private void migrarATxt(String path, String fileNameMdb, String outputFileNameTxt) {
		final String TAB = "\t";
		final String ENTER = "\n";
		final String ENCODE = "UTF-8";

		Writer writer = null;
		try {
			Table table = DatabaseBuilder.open(new File(path + fileNameMdb)).getTable("Balance");

			StringBuilder lBuild = new StringBuilder();
			for (Column col : table.getColumns()) {
				lBuild.append(col.getName()).append(TAB);
			}
			lBuild.deleteCharAt(lBuild.length() - 1);
			lBuild.append(ENTER);

			for (Row row : table) {
				for (Column col : table.getColumns()) {

					if (col.getType() == DataType.DOUBLE) {

						BigDecimal big = new BigDecimal((Double) row.get(col.getName()));

						lBuild.append(big.toPlainString()).append(TAB);
					} else {
						lBuild.append(row.get(col.getName())).append(TAB);
					}

				}
				lBuild.deleteCharAt(lBuild.length() - 1);
				lBuild.append(ENTER);
			}
			lBuild.deleteCharAt(lBuild.length() - 1);
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + outputFileNameTxt), ENCODE));
			writer.write(lBuild.toString());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
