package ast.node;
import Semantic.Environment;
import Semantic.STentry;
import Semantic.SemanticError;

import java.util.ArrayList;

public class FieldNode implements Node{
	private String id;
	private Node type;
	private Node value;

	/**
	 * Constructor
	 * @param id
	 * @param type
	 * @param value
	 */
	public FieldNode(String id, Node type, Node value) {
		this.id = id;
		this.type = type;
		this.value = value;
	}

	// Node interface to implement

	/**
	 * Check semantic errors for this node in a given environment
	 * @param env
	 * @return errors
	 */
	public ArrayList<SemanticError> checkSemantics(Environment env){
		STentry entry = new STentry(env.getNestingLevel(), -1, this);

		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
		SemanticError error=env.addDecl(id, entry);
		System.out.println(error);
		if(error!=null) {
			errors.add(error);
			throw new RuntimeException("Error in FieldNode " + id);
		}

		return errors;
	}

	/**
	 * Generate code for this node
	 * @param
	 * @return
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
	 *
	 * @param indent
	 */
	public String toPrint(String indent){
		String s = indent+"FieldNode\n";
		s += indent+"\tid: "+id+"\n";
		s += indent+"\ttype: "+type.toPrint(indent)+"\n";
		//s += indent+"\tvalue: "+value.toPrint(indent)+"\n";
		return s;
	}
}
