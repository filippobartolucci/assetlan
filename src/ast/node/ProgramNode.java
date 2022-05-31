package ast.node;

import Semantic.GammaEnv;
import Semantic.SemanticError;
import Semantic.SigmaEnv;
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

	/**
	 * Check semantic errors for this node in a given environment
	 * @param env the environment
	 * @return the semantic errors
	 */
	@Override
	public ArrayList<SemanticError> checkSemantics(GammaEnv env){
		ArrayList<SemanticError> errors = new ArrayList<>();

		env.newEmptyScope();	// Initial Empy Scope [ ]
		env.decOffset(2);	// Reserving space for the transfer of assets

		for (Node f : fields) // Var Dec
			errors.addAll(f.checkSemantics(env)); // \gamma' = \gamma U {x: t}

		for (Node a : assets) // Asset Dec
			errors.addAll(a.checkSemantics(env)); // \gamma' = \gamma U {x: asset}

		for (Node f : functions) // Func Dec
			errors.addAll(f.checkSemantics(env)); // \gamma' = \gamma U {f: t, x1: t1, ..., xn: t1, a1: asset, ..., an: asset}

		errors.addAll(initcallnode.checkSemantics(env)); 
		env.exitScope(); //  [ ]

		return errors;
	}

	@Override
	public SigmaEnv checkEffects(SigmaEnv env){
		env.newEmptyScope();	// Initial Empy Scope [ ]

		for (Node f : fields){
			env = f.checkEffects(env);
		}

		for (Node a : assets){
			env = a.checkEffects(env);
		}

		/* Nothing to do here...
		for (Node f : functions){
			env = f.checkEffects(env);
		}
		*/

		initcallnode.checkEffects(env);

		for (Node n : assets) {
			if (n instanceof AssetNode a) {
				if (env.lookup(a.toString()).getStatus()) {
					env.addError(new SemanticError("Liquidity not respected -> "+ a+" is not empty"));
				}
			}
		}
		env.exitScope(); //  [ ]
		return env;
	}
	
}

