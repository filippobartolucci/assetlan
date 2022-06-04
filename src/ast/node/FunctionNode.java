package ast.node;

import Semantic.*;
import Utils.LabelManager;
import Utils.TypeValue;
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
	private final ArrayList<Node> assets;
	private final String f_label;
	private final String end_label;



	public FunctionNode(String id, Node typenode, ArrayList<Node> params, ArrayList<Node> aparams, ArrayList<Node> body_params, ArrayList<Node> statements) {
		this.id = id;
		this.type = typenode;
		this.params = params;
		this.aparams = aparams;
		this.body_params = body_params;
		this.statements = statements;
		this.assets = new ArrayList<>();
		this.f_label = LabelManager.getFreshFunLabel();
		this.end_label = LabelManager.getEndFreshFunLabel();
	}

	/**
	 * Check semantic errors for this node in a given environment
	 * @param env the environment
	 * @return the semantic errors
	 */
	public ArrayList<SemanticError> checkSemantics(GammaEnv env) {
		STentry entry = new STentry(env.getNestingLevel(), env.decOffset(1), this);
		ArrayList<SemanticError> errors = new ArrayList<>();

		SemanticError f_error = env.addDecl(id,entry);

		if(f_error != null) {
			errors.add(f_error);
		}

		env.newEmptyScope();
		env.decOffset(1);


		for(Node n : params) {
			errors.addAll(n.checkSemantics(env));
		}

		for(Node n : aparams) {
			errors.addAll(n.checkSemantics(env));
			AssetNode a = ((AssetNode) n);
			assets.add(a);
		}

		for(Node n : body_params) {
			errors.addAll(n.checkSemantics(env));
		}

		env.setLastFunction(this);

		for(Node n : statements) {
			errors.addAll(n.checkSemantics(env));
		}

		env.exitScope();
		return errors;
	}

	public Node typeCheck() {
		for (Node n : body_params) {
			n.typeCheck();
		}

		boolean found_return = false;
		for (Node n : statements) {
			Node a_type = n.typeCheck();

			if (!a_type.equals(TypeValue.VOID)) {
				found_return = true;
				// Check if return type is equals to function type
				if (!a_type.equals(type)) {
					throw new RuntimeException("Type mismatch -> in " + id + " return type " + a_type.toPrint("") + " does not match declaration function type " + type.toPrint(""));
				}
			}
		}

		if (!found_return && !(type.equals(TypeValue.VOID))) {
			throw new RuntimeException("Type mismatch -> Function " + id + " has return type " + type + " but no return statement");
		}
		return type;
	}

	/**
	 * Function declaration has no effects
	 */
	public SigmaEnv checkEffects(SigmaEnv env) {
		// Function for effect is valuated in the call
		// Never called.
		return env;
	}

	/**
	 *  Check effect of the function body
	 */
	public SigmaEnv checkFunctionEffects(SigmaEnv env,ArrayList<Boolean> actualEffects){
		// Entering new scope...
		env.newEmptyScope();

		// Saving last function called name
		// Used to check recursive calls
		env.addFunctionCall(this.id);

		// Adding each parameter to SigmaEnv
		for(Node n : params) {
			env = n.checkEffects(env);
			// status of n is true
		}

		// Adding each asset parameter to SigmaEnv
		for(int n = 0; n < aparams.size(); n++) {
			Node a = aparams.get(n);
			env = a.checkEffects(env);
			// Setting effect of each asset according
			// to the actual effect of called parameters
			if(actualEffects.get(n)) {
				env.lookup(a).setTrue();
			}// Else not needed, false by default
		}

		// Adding each body parameter to SigmaEnv
		for(Node n : body_params) {
			env = n.checkEffects(env);
		}

		// checking effects of each statement
		for(Node n : statements) {
			env = n.checkEffects(env);
		}

		// Every asset parameter must have status == false (empty)
		for (Node a : assets) {
			if(env.lookup(a.toString()).getStatus()){
				env.addError(new SemanticError("Liquidity in " + id + " not respected -> "+ a +" is not empty "));
			}
		}

		env.exitScope();

		return env;
	}

	public String codeGeneration() {
		StringBuilder out = new StringBuilder();

		// Enter function
		out.append(f_label).append(": //").append(this.id).append("\n");
		out.append("mv $sp $fp\n");
		out.append("push $ra\n");

		// Body Function
		for (Node s:statements) {
			out.append(s.codeGeneration());
		}

		// Exit function
		out.append(end_label).append(":\n");

		out.append("subi $sp $fp 1 //Restore stack pointer as before block creation in a void function without return \n");
		out.append("lw $fp 0($fp) //Load old $fp pushed \n");
		out.append("lw $ra 0($sp)\n");
		out.append("pop\n");

		int parameter_size = params.size() + assets.size() + body_params.size();
		out.append("addi $sp $sp 0").append("\n");
		out.append("addi $sp $sp ").append(parameter_size).append("\n");
		out.append("pop\n");
		out.append("lw $fp 0($sp)\n");
		out.append("pop\n");
		out.append("jr $ra\n");
		out.append("//END OF ").append(f_label).append("\n");

		return out.toString();
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

	/*
		Getters
	 */
	public ArrayList<Node> getParams(){return params;}
	public ArrayList<Node> getAparams(){return aparams;}
	public ArrayList<Node> getBodyParams(){return body_params;}
	public TypeNode getType(){return (TypeNode) type;}
	public String getLabel(){return this.f_label;}
	public String getEndLabel(){return this.end_label;}

}



