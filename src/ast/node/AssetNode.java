package ast.node;


import Semantic.Environment;
import Semantic.STentry;
import Semantic.SemanticError;

import java.util.ArrayList;

public class AssetNode implements Node{
	private final String id;
	private int value;
	private STentry entry;

	public AssetNode(String id){
		this.id = id;
		this.value = 0;
		this.entry = null;
	}

	public Node typeCheck(){
		return new TypeNode("asset");
	}

	public String codeGeneration(){
		return null;
	}

	public ArrayList<SemanticError> checkSemantics(Environment env) {
		STentry entry = new STentry(env.getNestingLevel(), -1, this);
		ArrayList<SemanticError> errors;
		errors = new ArrayList<SemanticError>();

		SemanticError err = env.addDecl(id, entry);
		if (err != null) {
			errors.add(err);
		}
		this.entry = entry;

		return errors;

	}

	public ArrayList<SemanticError> checkEffects() {
		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

		//entry.setStatus(Effects.RW);

		return new ArrayList<SemanticError>();
	}

	public String toPrint(String indent) {
		String s = indent + "AssetNode\n";
		s += indent + "\tid: " + id + "\n";
		s += indent + "\ttype: " + new TypeNode("asset") + "\n";
		return s;
	}

	public void setStatus(boolean s){
		entry.setStatus(s);
	}
	public boolean getStatus(){
		return entry.getStatus();
	}

	@Override
	public String toString() {
		return id;
	}
}
