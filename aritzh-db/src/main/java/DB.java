import com.google.common.collect.Maps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.zip.ZipFile;

/**
 * @author Aritz Lopez
 */
public class DB {

	public static DB readDB(ZipFile zf) throws IOException {
		DB db = new DB();
		readMainFile(zf, db, zf.getInputStream(zf.getEntry("main.db"))); // TODO Maybe not ".db"
		return db;
	}

	private static DB readMainFile(ZipFile zf, DB db, InputStream is) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

			String line = br.readLine();
			int tableCount = Integer.parseInt(line);
			line = br.readLine();
			String[] tables = line.split(",");
			if (tables.length != tableCount)
				throw new FormatException("Table count and number of tables listed are unequal!");

			for (String tableName : tables) {
				readTable(zf, db, tableName);
			}
		} catch (IOException e) {
			System.err.println("Error reading main file");
			e.printStackTrace();
		} catch (FormatException e) {
			System.err.println("Illegal DB format!");
			e.printStackTrace();
		}
		return db;
	}

	private static DB readTable(ZipFile zf, DB db, String tableName) throws IOException {
		InputStream is = zf.getInputStream(zf.getEntry(tableName + ".db")); // TODO Maybe not ".db"

		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

			String[] tokens = br.readLine().split(",");
			int columnCount = Integer.parseInt(tokens[0]);
			if (tokens.length != columnCount + 1) throw new FormatException("");
			String[] columnNames = new String[columnCount];
			System.arraycopy(tokens, 1, columnNames, 0, columnCount);
			String[] columnType = br.readLine().split(",");
			Column[] columns = new Column[columnCount];
			for(int i = 0; i<columnCount; i++) {
				columns[i] = new Column(columnNames[i], columnType[i]);
			}

			String line;
			while((line = br.readLine()) != null) {
				String[] values = line.split(",");

			}

		} catch (IOException e) {
			System.err.println("Error reading main file");
			e.printStackTrace();
		} catch (FormatException e) {
			System.err.println("Illegal DB format!");
			e.printStackTrace();
		}

		return db;
	}

	private enum ColumnType {
		INT("int"), DATE("date"), STRING("string");

		private static final Map<String, ColumnType> nameToType = Maps.newHashMap();

		static {
			for(ColumnType ct : ColumnType.values()) {
				nameToType.put(ct.getName(), ct);
			}
		}

		private final String name;

		ColumnType(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public static ColumnType parse(String name) {
			final String name2 = name.trim().toLowerCase();
			if (nameToType.containsKey(name2)) {
				return nameToType.get(name2);
			}
			throw new FormatException("Column type \"" + name + "\" could not be recognised");
		}
	}

	private static class Column {
		private String name;
		private ColumnType type;
		private String[] formatArgs;

		public Column(String name, String format) {
			this.name = name;
			String[] tokens = format.split(":");
			this.type = ColumnType.parse(tokens[0]);
			this.formatArgs = new String[tokens.length - 1];
			System.arraycopy(tokens, 1, this.formatArgs, 0, tokens.length - 1);
		}

		public String getName() {
			return name;
		}

		public ColumnType getType() {
			return type;
		}

		public String[] getFormatArgs() {
			return formatArgs;
		}
	}
}