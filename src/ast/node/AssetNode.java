package ast.node;

import Semantic.Environment;
import Semantic.SemanticError;

import java.util.ArrayList;

public class AssetNode implements Node{
	private String id = null;

	public AssetNode(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}


	public void setId(String id){
		this.id = id;
	}

	public Node typeCheck(){
		return null;
	}

	public String codeGeneration(){
		return null;
	}

	public ArrayList<SemanticError> checkSemantics(Environment env){
		return null;
	}

	public String toPrint(String indent) {
		return null;
	}
}
