package ast.node;

import Semantic.Environment;
import Semantic.STentry;
import Semantic.SemanticError;

import java.util.ArrayList;

public class AssetNode implements Node{
	private String id = null;

	public AssetNode(String id){
		this.id = id;
	}

	public Node typeCheck(){
		return null;
	}

	public String codeGeneration(){
		return null;
	}

	public ArrayList<SemanticError> checkSemantics(Environment env) {

		STentry entry = new STentry(env.getNestingLevel(), -1, this);
		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

		if (env.addDecl(id, entry) != null) {
			errors.add(new SemanticError("Error: Variable " + id + " already declared"));
		}
		return errors;
	}

	public String toPrint(String indent) {
		String s = indent + "AssetNode\n";
		s += indent + "\tid: " + id + "\n";
		return s;
	}
}
