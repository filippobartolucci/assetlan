package ast.node;


import Semantic.*;
import Utils.TypeValue;

import java.util.ArrayList;

public class AssetNode implements Node{
	private final String id;
	private final int value;
	private STentry entry;

	public AssetNode(String id){
		this.id = id;
		this.value = 0;
		this.entry = null;
	}

	public Node typeCheck(){
		return new TypeNode(TypeValue.ASSET);
	}

	public String codeGeneration(){
		StringBuilder out = new StringBuilder();

		out.append("li $a0 0\n");
		out.append("push $a0 \n");

		return out.toString();
	}

	/**
	 * Check semantic errors for this node in a given environment
	 * @param env the environment
	 * @return the semantic errors
	 */
	public ArrayList<SemanticError> checkSemantics(GammaEnv env) {
		STentry entry = new STentry(env.getNestingLevel(), env.decOffset(1), this);
		ArrayList<SemanticError> errors;
		errors = new ArrayList<SemanticError>();

		SemanticError err = env.addDecl(id, entry);
		if (err != null) {
			errors.add(err);
		}
		this.entry = entry;

		return errors;

	}

	public SigmaEnv checkEffects(SigmaEnv env) {
		EffectEntry entry = new EffectEntry();
		env.addDecl(id, entry);

		return env;
	}

	public String toPrint(String indent) {
		String s = indent + "AssetNode\n";
		s += indent + "\tid: " + id + "\n";
		s += indent + "\ttype: " + new TypeNode("asset") + "\n";
		return s;
	}

	@Override
	public String toString() {
		return id;
	}
}
