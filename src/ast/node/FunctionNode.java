package ast.node;

import Semantic.Environment;
import Semantic.SemanticError;

import java.util.ArrayList;

public class FunctionNode implements Node {
	// function    : (type | 'void') ID
	//              '(' (param (',' param)* )? ')'
	//              '[' (aparam (',' aparam)* )? ']'
	//	          '{' param* statement* '}' ;
	private final TypeNode type;
	private final String id;
	private final ArrayList<Node> params;
	private final ArrayList<Node> aparams;
	private final ArrayList<Node> body_params; // param inside function
	private final ArrayList<Node> statements;

	public FunctionNode(){
		this.type = null;
		this.id = null;
		this.params = new ArrayList<>();
		this.aparams = new ArrayList<>();
		this.body_params = new ArrayList<>();
		this.statements = new ArrayList<>();
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
		String s = indent + "FunctionNode\n";
		s += indent + "\tid: " + id + "\n";
		s += indent + "\ttype: " + type.toPrint("  ") + "\n";
		s += indent + "\tParams: \n";
		for (Node n : params) {
			s += n.toPrint(indent + "\t	");
		}
		s += indent + "\tAparams: \n";
		for (Node n : aparams) {
			s += n.toPrint(indent + "\t	");
		}
		s += indent + "\tBody Params: \n";
		for (Node n : body_params) {
			s += n.toPrint(indent + "\t	");
		}
		s += indent + "\tStatements: \n";
		for (Node n : statements) {
			s += n.toPrint(indent + "\t");
		}
		return s;

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
