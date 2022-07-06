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

	/**
	 * Check semantic errors for this node in a given environment
	 * @param env the environment
	 * @return the semantic errors
	 */
	@Override
	public ArrayList<SemanticError> checkSemantics(GammaEnv env){
		ArrayList<SemanticError> errors = new ArrayList<>();

		env.newEmptyScope();	// Initial Empty Scope [ ]
		env.decOffset(0);

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
		}catch (Exception e){ // catch for type error
			System.err.println(e.getMessage());
			System.exit(ExitCode.SEMANTIC_ERROR.ordinal());
		}
		return type;
	}

	@Override
	public SigmaEnv checkEffects(SigmaEnv env){
		env.newEmptyScope();	// Initial Empty Scope [ ]

		for (Node f : fields){
			env = f.checkEffects(env);
		}

		for (Node a : assets){
			env = a.checkEffects(env);
		}

		// checkEffects of functions is empty, function effects are evaluated in the function call

		// Init...
		env = initcallnode.checkEffects(env);

		// Checking liquidity, every global asset must be equal to 0;
		for (Node a : assets) { // lookup for each asset
			if (env.lookup(a).getStatus()) {
				env.addError(new SemanticError("Liquidity not respected -> "+a+" is not empty"));
			}
		}
		env.exitScope(); //  [ ]
		return env;
	}

	@Override
	public String codeGeneration() {
		StringBuilder out = new StringBuilder();

		out.append("//BEGIN PROGRAM\n\n");
		out.append("//BLOCK \n");

		out.append("push 0\n".repeat(fields.size() + assets.size()));

		out.append("mv $sp $fp //Load $fp for initial block\n");
		out.append("// GLOBAL FIELDS ASG\n");

		for (Node f : fields){
			out.append(f.codeGeneration());
		}

		out.append("\n//INITCALL\n");
		out.append(initcallnode.codeGeneration());
		out.append("\nhalt //exit program...\n\n");

		out.append("//FUNCTIONS\n\n");
		for (Node f : functions) out.append(f.codeGeneration());

		return out.toString();
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
	
}

