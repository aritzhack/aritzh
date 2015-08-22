import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author Aritz Lopez
 */
public class Table {

	private final Column[] columns;
	private final Set<String[]> entries = Sets.newHashSet();

	public Table(Column[] columns) {
		this.columns = columns;
	}

	public void addEntry(String... entry) {
		this.entries.add(entry);
	}
}
