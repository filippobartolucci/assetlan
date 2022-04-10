package ast.node;
import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.exp.ExpNode;

import java.util.ArrayList;

public class FieldNode implements Node{
	private String id;
	private Node type;
	private Node value;

	/**
	 * Empty Constructor
	 */
	public FieldNode(){
		this.id = "";
		this.type = null;
		this.value = null;
	}

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
		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
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
	 * @return
	 */
	public String toPrint(String indent){
		String s = indent+"FieldNode\n";
		s += indent+"\tid: "+id+"\n";
		s += indent+"\ttype: "+type.toPrint(indent)+"\n";
		//s += indent+"\tvalue: "+value.toPrint(indent)+"\n";
		return s;
	}
}
