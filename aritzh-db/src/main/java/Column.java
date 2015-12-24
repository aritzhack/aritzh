
/**
 * @author Aritz Lopez
 */
public class Column {
	private String name;
	private ColumnType type;
	private String[] formatArgs;

	private boolean isUnique = false;
	private boolean isIndex = false;

	public Column(String name, String format) {
		this.name = name;
		String[] tokens = format.split(":");
		this.type = ColumnType.parse(tokens[0]);
		this.formatArgs = new String[tokens.length - 1];
		System.arraycopy(tokens, 1, this.formatArgs, 0, tokens.length - 1);

		for(String a : this.formatArgs) {
			if("unique".equalsIgnoreCase(a.trim())) {
				this.isUnique = true;
			} else if("index".equalsIgnoreCase(a.trim())) {
				this.isIndex = true;
			}
		}
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

	public boolean isUnique() {
		return isUnique;
	}

	public boolean isIndex() {
		return isIndex;
	}
}