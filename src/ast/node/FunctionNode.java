package ast.node;

import Semantic.Environment;
import Semantic.STentry;
import Semantic.SemanticError;

import java.util.ArrayList;

public class FunctionNode implements Node {
	// function    : (type | 'void') ID
	//              '(' (param (',' param)* )? ')'
	//              '[' (aparam (',' aparam)* )? ']'
	//	          '{' field* statement* '}' ;
	private final Node type;
	private final String id;
	private final ArrayList<Node> params;
	private final ArrayList<Node> aparams;
	private final ArrayList<Node> body_params; // param inside function
	private final ArrayList<Node> statements;
	private final ArrayList<Node> return_statement;


	public FunctionNode(String id, Node typenode, ArrayList<Node> params, ArrayList<Node> aparams, ArrayList<Node> body_params, ArrayList<Node> statements, ArrayList<Node> return_statement) {
		this.id = id;
		this.type = typenode;
		this.params = params;
		this.aparams = aparams;
		this.body_params = body_params;
		this.statements = statements;
		this.return_statement = return_statement;
	}

	public String toPrint(String indent) {
		StringBuilder s = new StringBuilder(indent + "FunctionNode\n");

		s.append(indent).append("\tid: ").append(id).append("\n");
		s.append(indent).append("\ttype: ").append(type.toPrint(" ")).append("\n");
		s.append(indent).append("\tParams: \n");

		for (Node n : params) s.append(n.toPrint(indent + "\t\t"));
		s.append(indent).append("\tAparams: \n");

		for (Node n : aparams) s.append(n.toPrint(indent + "\t\t"));
		s.append(indent).append("\tBody Params: \n");

		for (Node n : body_params) s.append(n.toPrint(indent + "\t\t"));
		s.append(indent).append("\tStatements: \n");

		for (Node n : statements) s.append(n.toPrint(indent + "\t\t"));
		for (Node n : return_statement) s.append(n.toPrint(indent + "\t\t"));

		return s.toString();
	}

	public Node typeCheck() {
		if (type!=null && return_statement.size()==0) {
			throw new RuntimeException("Type mismatch -> Function " + id + " has return type but no return statement");
		}
		for (Node n : return_statement){
			Node retType = n.typeCheck();
			if(!retType.equals(type)){
				throw new RuntimeException("Type mismatch -> Function " + id + " return type " +retType.toPrint("")+" does not match function type " +type.toPrint(""));
			}
		}

		for (Node n : body_params) {
			n.typeCheck();
		}

		for (Node n : statements) {
			n.typeCheck();
		}

		return type;
	}

	public String codeGeneration() {
		return null;
	}

	public ArrayList<SemanticError> checkSemantics(Environment env) {
		STentry entry = new STentry(env.getNestingLevel(),-1,type);
		ArrayList<SemanticError> errors = new ArrayList<>();

		SemanticError f_error = env.addDecl(id,entry);
		if(f_error != null) {
			errors.add(f_error);
		}

		env.newEmptyScope();
		for(Node n : params) {
			errors.addAll(n.checkSemantics(env));
		}

		for(Node n : aparams) {
			errors.addAll(n.checkSemantics(env));
		}

		for(Node n : body_params) {
			errors.addAll(n.checkSemantics(env));
		}

		for(Node n : statements) {
			errors.addAll(n.checkSemantics(env));
		}

		env.exitScope();
		return errors;
	}
}
