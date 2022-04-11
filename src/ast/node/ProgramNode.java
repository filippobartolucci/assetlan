package ast.node;

import Semantic.Environment;
import Semantic.SemanticError;

import java.util.ArrayList;

public class ProgramNode implements Node {
	private final ArrayList<Node> fields;
	private final ArrayList<Node> assets;
	private final ArrayList<Node> functions;
	private final Node initcallnode;

	public ProgramNode(ArrayList<Node> fields, ArrayList<Node> assets, ArrayList<Node> functions, Node initcallnode) {
		this.fields = fields;
		this.assets = assets;
		this.functions = functions;
		this.initcallnode = initcallnode;
	}

	@Override
	public String toPrint(String indent) {
		StringBuilder s = new StringBuilder();

		for (Node f : fields) s.append(f.toPrint(indent + "\t"));
		for (Node a : assets) s.append(a.toPrint(indent + "\t"));
		for (Node f : functions) s.append(f.toPrint(indent + "\t"));

		if (initcallnode != null) {
			s.append(initcallnode.toPrint(indent + "\t"));
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

