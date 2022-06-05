package ast.node;
import Semantic.*;
import Utils.TypeValue;

import java.util.ArrayList;

public class FieldNode implements Node{
	private final String id;
	private final Node type;
	private final Node exp;
	private STentry entry;
	private int currentNL;

	/**
	 * Constructor
	 */
	public FieldNode(String id, Node type, Node exp) {
		this.id = id;
		this.type = type;
		this.exp = exp;
	}


	/**
	 * Check semantic errors for this node in a given environment
	 * @param env the environment
	 * @return the semantic errors
	 */
	public ArrayList<SemanticError> checkSemantics(GammaEnv env){

		int offset = env.decOffset(1);
		STentry entry = new STentry(env.getNestingLevel(), offset, this);

		ArrayList<SemanticError> errors = new ArrayList<>();

		if (exp != null) {
			errors.addAll(exp.checkSemantics(env));
		}

		// Check if type == null
		if(type.equals(new TypeNode(TypeValue.VOID))){
			errors.add(new SemanticError("Variable " + id + " can't have void type"));
		}

		SemanticError error=env.addDecl(id, entry);

		if(error!=null) {
			errors.add(error);
		}

		this.entry = entry;
		this.currentNL = env.getNestingLevel();

		return errors;
	}

	/**
	 * Generate code for this node
	 */
	public Node typeCheck(){
		Node varType = type;

		if(exp != null){
			Node expType = exp.typeCheck();

			if(!varType.equals(expType)){
				throw new RuntimeException("Type mismatch -> var " + id + " has type " + varType.toPrint("") + " and exp has type " + expType.toPrint(""));
			}
		}

		return type;
	}

	public SigmaEnv checkEffects(SigmaEnv env) {
		EffectEntry entry = new EffectEntry();
		env.addDecl(id, entry);

		if (exp != null) {
			entry.setTrue();
			exp.checkEffects(env);
		}

		return env;
	}

	/**
	 * Generate code for this node
	 */
	public String codeGeneration(){
		if (exp == null) {
			return "";
		}

		StringBuilder out = new StringBuilder();

		out.append("\n// Field ").append(id).append("\n");
		out.append(exp.codeGeneration());
		out.append("sw $a0 ").append(entry.getOffset()).append("($fp)").append("\n");

		return out.toString();
	}

	/**
	 * Print this node
	 */
	public String toPrint(String indent){
		String s = indent+"FieldNode\n";
		s += indent+"\tid: "+id+"\n";
		s += indent+"\ttype: "+type.toPrint(indent)+"\n";
		//s += indent+"\t exp: "+exp.toPrint(indent)+"\n";
		return s;
	}

	@Override
	public String toString() {
		return id;
	}
}
