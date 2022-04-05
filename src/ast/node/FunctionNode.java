package ast.node;

import java.util.ArrayList;

public class FunctionNode implements Node {
	// function    : (type | 'void') ID
	//              '(' (param (',' param)* )? ')'
	//              '[' (aparam (',' aparam)* )? ']'
	//	          '{' param* statement* '}' ;
	TypeNode type;
	String id;
	ArrayList<Node> params;
	ArrayList<Node> aparams;
	ArrayList<Node> param2; // param inside function
	ArrayList<Node> statements;

	public FunctionNode(){
		this.type = null;
		this.id = null;
		this.params = new ArrayList<Node>();
		this.aparams = new ArrayList<Node>();
		this.param2 = new ArrayList<Node>();
		this.statements = new ArrayList<Node>();
	}
}
