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
	public Node typeCheck() {
		Node type = null;
		try{
			for (Node f : fields){
				f.typeCheck();
			}
			for (Node a : assets){
				a.typeCheck();
			}
			for (Node f : functions){
				f.typeCheck();
			}
			type = initcallnode.typeCheck();
		}catch (Exception e){
			System.err.println(e.getMessage());
			System.exit(ExitCode.SEMANTIC_ERROR.ordinal());
		}
		return type;
	}

	@Override
	public String codeGeneration() {
		StringBuilder out = new StringBuilder();
		for (Node f : fields) out.append(f.codeGeneration());
		for (Node a : assets) out.append(a.codeGeneration());
		for (Node f : functions) out.append(f.codeGeneration());
		if (initcallnode != null) out.append(initcallnode.codeGeneration());
		return out.toString();
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env){
		ArrayList<SemanticError> errors = new ArrayList<>();
		env.newEmptyScope();

		int offset = env.getOffset();
		offset--; // Reserve space for the transfer of assets
		env.setOffset(offset);

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

	@Override
	public ArrayList<SemanticError> checkEffects(){
		ArrayList<SemanticError> errors = new ArrayList<>();


		for (Node f : fields){
			errors.addAll(f.checkEffects());
		}

		for (Node a : assets){
			errors.addAll(a.checkEffects());
		}

		for (Node f : functions){
			errors.addAll(f.checkEffects());
		}

		errors.addAll(initcallnode.checkEffects());

		for (Node n : assets) {
			if (n instanceof AssetNode a) {
				if (a.getStatus()) {
					errors.add(new SemanticError("Contract not liquid -> "+ a+" is not empty"));
				}
			}
		}


		return errors;
	}
	
}

