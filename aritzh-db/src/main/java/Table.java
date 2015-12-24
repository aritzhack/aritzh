import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.github.aritzhack.aritzh.util.Nullable;

import java.util.Map;
import java.util.Set;

/**
 * @author Aritz Lopez
 */
public class Table {

	private final Column[] columns;
	private final Set<String[]> entries = Sets.newHashSet();

	private final Map<String, Map<String, String[]>> indices = Maps.newHashMap();
	private final Set<Integer> indexColumns = Sets.newHashSet();

	public Table(Column[] columns) {
		this.columns = columns;
		for (int i = 0; i < this.columns.length; i++) {
			if (this.columns[i].isIndex()) {
				indexColumns.add(i);
				indices.put(this.columns[i].getName().toLowerCase().trim(), Maps.<String, String[]>newHashMap());
			}
		}
	}

	public void addEntry(String... entry) {
		this.entries.add(entry);

		for (Integer i : this.indexColumns) {
			indices.get(this.columns[i].getName().toLowerCase().trim()).put(entry[i].toLowerCase().trim(), entry);
		}
	}

	@Nullable
	public String[] getEntry(String columnName, String value) {
		if(this.indices.containsKey(columnName.trim().toLowerCase())) {
			Map<String, String[]> index = this.indices.get(columnName);
			if(index.containsKey(value)) {
				return index.get(value);
			} else return null;
		} else throw new IllegalArgumentException("Column name " + columnName + " is not an index column!");
	}
}
