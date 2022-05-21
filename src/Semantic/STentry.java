package Semantic;

import ast.node.Node;

public class STentry {
	private int nestingLevel;
	private int offset;
	private Node type;

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
		return s+"STentry: nestlev " + Integer.toString(this.nestingLevel) +"\n"+
				s+"STentry: type\n" +
				type.toPrint(s+"  ") +
				s+"STentry: offset " + Integer.toString(this.offset) + "\n";
	}

	@Override
	public String toString() {
		return "STentry: nestlev " + Integer.toString(this.nestingLevel) +"\n"+
				"STentry: type " +
				type.toPrint("") +
				"\nSTentry: offset " + Integer.toString(this.offset) + "\n";
	}
}
