package ast.node;
import Semantic.Environment;
import Semantic.STentry;
import Semantic.SemanticError;

import java.util.ArrayList;

public class FieldNode implements Node{
	private final String id;
	private final Node type;
	private final Node exp;

	/**
	 * Constructor
	 */
	public FieldNode(String id, Node type, Node exp) {
		this.id = id;
		this.type = type;
		this.exp = exp;
	}

	// Node interface to implement

	/**
	 * Check semantic errors for this node in a given environment
	 */
	public ArrayList<SemanticError> checkSemantics(Environment env){
		STentry entry = new STentry(env.getNestingLevel(), -1, this);
		ArrayList<SemanticError> errors = new ArrayList<>();
		SemanticError error=env.addDecl(id, entry);
		if(error!=null) {
			errors.add(error);
		}
		errors.addAll(exp.checkSemantics(env));
		return errors;
	}

	/**
	 * Generate code for this node
	 */
	public Node typeCheck(){
		return null;
	}

	/**
	 * Generate code for this node
	 */
	public String codeGeneration(){
		return "";
	}

	/**
	 * Print this node
	 */
	public String toPrint(String indent){
		String s = indent+"FieldNode\n";
		s += indent+"\tid: "+id+"\n";
		s += indent+"\ttype: "+type.toPrint(indent)+"\n";
		//s += indent+"\t exp: "+exp.toPrint(indent)+"\n";
		return s;
	}
}
