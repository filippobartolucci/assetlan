package ast;

public class STentry {
	private int nestingLevel = -1;
	private int offset = -1;
	// private typeNode type;

	public STentry(int nestingLevel, int offset) {
		this.nestingLevel = nestingLevel;
		this.offset = offset;
	}

	public STentry(STentry s) {
		this.nestingLevel = s.nestingLevel;
		this.offset = s.offset;
		// this.type = s.type;
	}



	/*
	public STentry(int nestingLevel, int offset, typeNode t) {
		this.nestingLevel = nestingLevel;
		this.offset = offset;
		this.type = t;
	}
	public void addType(typeNode t){
		this.type = t
	}

	public Node getType () {
		return type;
	}
	*/

	public int getOffset () {
		return offset;
	}

	public int getNestinglevel() {
		return nestingLevel;
	}

	/*
	public String toPrint(String s) { //
		return s+"STentry: nestlev " + Integer.toString(nestinglevel) +"\n"+
				s+"STentry: type\n" +
				type.toPrint(s+"  ") +
				s+"STentry: offset " + Integer.toString(offset) + "\n";
	}
	*/

}
