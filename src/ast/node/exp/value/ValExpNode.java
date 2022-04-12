package ast.node.exp.value;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.Node;
import ast.node.exp.ExpNode;

import java.util.ArrayList;

public class ValExpNode extends ExpNode {
	private final int val;

	public ValExpNode(String val) {
		this.val = Integer.parseInt(val);
	}

	/**
	 * Check semantic errors for this node in a given environment
	 */
	public ArrayList<SemanticError> checkSemantics(Environment env){
		return new ArrayList<>();
	}

	/**
	 * Generate code for this node
	 */
	public Node typeCheck(){
		return null;
	}

	/**
	 * Generate code for this node
	 */
	public String codeGeneration(){
		return "";
	}

	/**
	 * Print this node
	 */
	public String toPrint(String indent) {return "\n"+indent + "Value " + val;}
}
