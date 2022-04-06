package ast.node;

import Semantic.Environment;
import Semantic.SemanticError;

import java.util.ArrayList;

public class ProgramNode implements Node {
	private ArrayList<Node> fields;
	private ArrayList<Node> assets;
	private ArrayList<Node> functions;
	private Node initcallnode;

	public ProgramNode(){
		fields = new ArrayList<Node>();
		assets = new ArrayList<Node>();
		functions = new ArrayList<Node>();
		initcallnode = null;
	}

	public ProgramNode(ArrayList<Node> fields, ArrayList<Node> assets, ArrayList<Node> functions, Node initcallnode) {
		this.fields = fields;
		this.assets = assets;
		this.functions = functions;
		this.initcallnode = initcallnode;
	}

	public ArrayList<SemanticError> checkSemantics(Environment env){
		// SCRITTA DA COPILOT, c'è nelle slide come si dovrebbe fare
		/*
		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
		for(Node n : fields){
			errors.addAll(n.checkSemantics(env));
		}
		for(Node n : assets){
			errors.addAll(n.checkSemantics(env));
		}
		for(Node n : functions){
			errors.addAll(n.checkSemantics(env));
		}
		if(initcallnode != null){
			errors.addAll(initcallnode.checkSemantics(env));
		}
		return errors;*/

		return null;
	}

	/*
	DA IMPLEMENTARE
	public String toPrint(String indent);
	Node typeCheck();
	String codeGeneration();
	*/
}

