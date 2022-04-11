package ast.node.exp.value;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.Node;
import ast.node.exp.ExpNode;

import java.util.ArrayList;

public class ValExpNode extends ExpNode {
	private int val;

	public ValExpNode(String val) {
		this.val = Integer.parseInt(val);
	}

	public ArrayList<SemanticError> checkSemantics(Environment env){
		return new ArrayList<SemanticError>();
	}

	/**
	 * Generate code for this node
	 * @param
	 * @return
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
	 *
	 * @param indent
	 * @returns
	 */
	public String toPrint(String indent) {return "\n"+indent + "Value " + val;}
}
