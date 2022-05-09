package ast.node;

import Semantic.Environment;
import Semantic.SemanticError;
import Utils.*;

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
		s.append("------------------------------------\n");
		for (Node f : fields) s.append(f.toPrint(indent + "\t"));
		s.append("------------------------------------\n");
		for (Node a : assets) s.append(a.toPrint(indent + "\t"));
		s.append("------------------------------------\n");
		for (Node f : functions) s.append(f.toPrint(indent + "\t"));
		s.append("------------------------------------\n");
		if (initcallnode != null) {
			s.append(initcallnode.toPrint(indent + "\t"));
		}

		return "\n---BEGINNING AST---\nProgram\n" + s +"---ENDING AST---";
	}

	@Override
	public Node typeCheck(Environment env) {
		Node type = null;
		env.newEmptyScope();
		try{
			for (Node f : fields){
				f.typeCheck(env);
			}
			for (Node a : assets){
				a.typeCheck(env);
			}
			for (Node f : functions){
				f.typeCheck(env);
			}
			type = initcallnode.typeCheck(env);
		}catch (Exception e){
			System.err.println(e.getMessage());
			System.exit(ExitCode.SEMANTIC_ERROR.ordinal());
		}
		env.exitScope();
		return type;
	}

	@Override
	public String codeGeneration() {return null;}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env){
		ArrayList<SemanticError> errors = new ArrayList<>();
		env.newEmptyScope();

		for (Node f : fields)
			errors.addAll(f.checkSemantics(env));
		for (Node a : assets)
			errors.addAll(a.checkSemantics(env));
		for (Node f : functions)
			errors.addAll(f.checkSemantics(env));

		errors.addAll(initcallnode.checkSemantics(env));

		env.exitScope();

		return errors;
	}
}

