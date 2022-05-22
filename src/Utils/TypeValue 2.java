package Utils;

public enum TypeValue {
	INT("int"),
	BOOL("bool"),
	ASSET("asset"),
	VOID("void");

	private final String str_type;

	private TypeValue(String s) {
		str_type = s;
	}

	@Override
	public String toString() {
		return this.str_type;
	}
}
