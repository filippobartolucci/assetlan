package ast.node;


import Semantic.*;
import Utils.TypeValue;

import java.util.ArrayList;

public class AssetNode implements Node{
	private final String id;

	public AssetNode(String id){
		this.id = id;
	}

	public Node typeCheck(){
		return new TypeNode(TypeValue.ASSET);
	}

	public String codeGeneration(){
		return "push 0\n";
	}

	/**
	 * Check semantic errors for this node in a given environment
	 * @param env the environment
	 * @return the semantic errors
	 */
	public ArrayList<SemanticError> checkSemantics(GammaEnv env) {
		STentry entry = new STentry(env.getNestingLevel(), env.decOffset(1), this);
		ArrayList<SemanticError> errors;
		errors = new ArrayList<>();

		SemanticError err = env.addDecl(id, entry);
		if (err != null) {
			errors.add(err);
		}

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
