package ast.node;

import Semantic.Environment;
import Semantic.SemanticError;

import java.util.ArrayList;

public class FunctionNode implements Node {
	// function    : (type | 'void') ID
	//              '(' (param (',' param)* )? ')'
	//              '[' (aparam (',' aparam)* )? ']'
	//	          '{' param* statement* '}' ;
	private TypeNode type;
	private String id;
	private ArrayList<Node> params;
	private ArrayList<Node> aparams;
	private ArrayList<Node> body_params; // param inside function
	private ArrayList<Node> statements;

	public FunctionNode(){
		this.type = null;
		this.id = null;
		this.params = new ArrayList<Node>();
		this.aparams = new ArrayList<Node>();
		this.body_params = new ArrayList<Node>();
		this.statements = new ArrayList<Node>();
	}

	public FunctionNode(String id, Node typenode, ArrayList<Node> params, ArrayList<Node> aparams, ArrayList<Node> body_params, ArrayList<Node> statements) {
		this.id = id;
		this.type = (TypeNode)typenode;
		this.params = params;
		this.aparams = aparams;
		this.body_params = body_params;
		this.statements = statements;
	}

	public String toPrint(String indent) {
		return null;
	}

	public Node typeCheck() {
		return null;
	}

	public String codeGeneration() {
		return null;
	}

	public ArrayList<SemanticError> checkSemantics(Environment env) {
		return null;
	}
}
