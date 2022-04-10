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

	@Override
	public String toPrint(String indent) {
		String s ="";

		for (Node f : fields) {
			s += f.toPrint(indent + "\t");
		}

		for (Node a : assets) {
			s += a.toPrint(indent + "\t");
		}

		for (Node f : functions) {
			s += f.toPrint(indent + "\t");
		}

		if (initcallnode != null) {
			s += initcallnode.toPrint(indent + "\t");
		}

		return "\n---BEGINNING AST---\nProgram\n" + s +"---ENDING AST---";
	}

	@Override
	public Node typeCheck() {return null;}

	@Override
	public String codeGeneration() {return null;}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env){return null;}
}
