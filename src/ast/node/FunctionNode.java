package ast.node;

import Semantic.Environment;
import Semantic.STentry;
import Semantic.SemanticError;
import ast.node.statement.CallNode;
import ast.node.statement.IteNode;
import ast.node.statement.RetNode;

import java.lang.reflect.Field;
import java.util.ArrayList;


public class FunctionNode implements Node {
	// function    : (type) ID
	//              '(' (param (',' param)* )? ')'
	//              '[' (aparam (',' aparam)* )? ']'
	//	          '{' field* statement* '}' ;
	private final Node type;
	private final String id;
	private final ArrayList<Node> params;
	private final ArrayList<Node> aparams;
	private final ArrayList<Node> body_params; // param inside function
	private final ArrayList<Node> statements;

	private ArrayList<AssetNode> assets;


	public FunctionNode(String id, Node typenode, ArrayList<Node> params, ArrayList<Node> aparams, ArrayList<Node> body_params, ArrayList<Node> statements) {
		this.id = id;
		this.type = typenode;
		this.params = params;
		this.aparams = aparams;
		this.body_params = body_params;
		this.statements = statements;
		this.assets = new ArrayList<>();
	}

	public FunctionNode(FunctionNode f){
		this.id = f.id;
		this.type = f.type;
		this.params = f.params;
		this.aparams = f.aparams;
		this.body_params = f.body_params;
		this.statements = f.statements;
		this.assets = f.assets;
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

		return s.toString();
	}

	public Node typeCheck() {
		for (Node n : body_params) {
			n.typeCheck();
		}

		boolean found_return = false;
		for (Node n : statements) {
			Node a_type = n.typeCheck();
			if (!a_type.equals("void")) {

				found_return = true;
				if (!a_type.equals(type)) {
					throw new RuntimeException("Type mismatch -> in " + id + " return type " + a_type.toPrint("") + " does not match declaration function type " + type.toPrint(""));
				}
			};
		}
		if (!found_return && !(type.equals("void"))) {
			throw new RuntimeException("Type mismatch -> Function " + id + " has return type " + type + " but no return statement");
		}
		return type;
	}

	public String codeGeneration() {
		return null;
	}

	public ArrayList<SemanticError> checkSemantics(Environment env) {
		STentry entry = new STentry(env.getNestingLevel(),-1,this);
		ArrayList<SemanticError> errors = new ArrayList<>();

		SemanticError f_error = env.addDecl(id,entry);


		if(f_error != null) {
			errors.add(f_error);
		}

		env.newEmptyScope();

		for(Node n : params) {;
			errors.addAll(n.checkSemantics(env));
			((ParamNode) n).setStatusRW();
		}

		for(Node n : aparams) {
			errors.addAll(n.checkSemantics(env));
			AssetNode a = ((AssetNode) n);
			assets.add(a);
			a.setStatus(true);
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

	public ArrayList<SemanticError> checkEffects() {
		return new ArrayList<>();
	}

	public ArrayList<SemanticError> checkFunctionEffects(){
		ArrayList<SemanticError> errors = new ArrayList<>();

		for(Node n : params) {
			errors.addAll(n.checkEffects());
		}

		for(Node n : aparams) {
			errors.addAll(n.checkEffects());
		}

		for(Node n : body_params) {
			errors.addAll(n.checkEffects());
		}

		for(Node n : statements) {
			StatementNode s = (StatementNode) n;
			if (s.getChild() instanceof CallNode c) {
				if (!c.getId().equals(id)) {
					errors.addAll(n.checkEffects());
				}
			}else{
				errors.addAll(n.checkEffects());
			}
		}

		for (AssetNode a : assets) {
			if(a.getStatus()){
				errors.add(new SemanticError("Liquidity in " + id + " not respected -> "+ a+" is not empty"));
			}
		}

		return errors;
	}

	public ArrayList<Node> getParams(){
		return params;
	}
	public ArrayList<Node> getAparams(){
		return aparams;
	}

	public TypeNode getType(){
		return (TypeNode) type;
	}

	private ArrayList<Node> getReturns(Node n){
		ArrayList<Node> ret_list = new ArrayList<>();
		if (n instanceof RetNode) {
			ret_list.add(n);
			return ret_list;
		}
		if (n instanceof IteNode ite) {
			//ret_list.addAll(getReturns(ite.getIf()));
			//ret_list.addAll(getReturns(ite.getElse()));
		}
		if (n instanceof StatementNode st) {
			ret_list.addAll(getReturns(st.getChild()));
		}
		return ret_list;
	}

}
