package Semantic;

import ast.node.Node;

public class STentry {
	private final int nestingLevel;
	private final int offset;
	private final Node type;

	public STentry(){
		this.nestingLevel = -1;
		this.offset = -1;
		this.type = null;
	}

	public STentry(STentry s){
		this.nestingLevel = s.nestingLevel;
		this.offset = s.offset;
		this.type = s.type;
	}

	public STentry(int nestingLevel, int offset, Node t) {
		this.nestingLevel = nestingLevel;
		this.offset = offset;
		this.type = t;
	}

	public Node getEntry() {
		return type;
	}

	public int getOffset () {
		return offset;
	}

	public int getNestinglevel() {
		return this.nestingLevel;
	}

	public String toPrint(String s) { //
		assert type != null;
		return s+"STentry: nestlev " + this.nestingLevel +"\n"+
				s+"STentry: type\n" +
				type.toPrint(s+"  ") +
				s+"STentry: offset " + this.offset + "\n";
	}

	@Override
	public String toString() {
		assert type != null;
		return "STentry: nestlev " + this.nestingLevel +"\n"+
				"STentry: type " +
				type.toPrint("") +
				"\nSTentry: offset " + this.offset + "\n";
	}
}
