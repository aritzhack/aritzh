
/**
 * @author Aritz Lopez
 */
public class Column {
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