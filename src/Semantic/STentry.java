package Semantic;

import ast.node.Node;
import Semantic.*;

public class STentry {
	private int nestingLevel;
	private int offset;
	private Node type;
	private Effects status;

	public STentry(){
		this.nestingLevel = -1;
		this.offset = -1;
		this.type = null;
		this.status = null;
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
		this.status = null;
	}
	public void addType(Node t){
		this.type = t;
	}

	public void setStatus(Effects status) {
		this.status = status;
	}

	public Node getType () {
		return type;
	}

	public int getOffset () {
		return offset;
	}

	public int getNestinglevel() {
		return nestingLevel;
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
