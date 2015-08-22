import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author Aritz Lopez
 */
public enum ColumnType {
	INT("int"), DATE("date"), STRING("string");

	private static final Map<String, ColumnType> nameToType = Maps.newHashMap();

	static {
		for (ColumnType ct : ColumnType.values()) {
			nameToType.put(ct.getName(), ct);
		}
	}

	private final String name;

	ColumnType(String name) {
		this.name = name;
	}

	public static ColumnType parse(String name) {
		final String name2 = name.trim().toLowerCase();
		if (nameToType.containsKey(name2)) {
			return nameToType.get(name2);
		}
		throw new FormatException("Column type \"" + name + "\" could not be recognised");
	}

	public String getName() {
		return name;
	}
}
