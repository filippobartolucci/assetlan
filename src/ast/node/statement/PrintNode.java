package ast.node.statement;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.Node;
import ast.node.TypeNode;
import ast.node.exp.BaseExpNode;
import ast.node.exp.ExpNode;

import java.util.ArrayList;

public class PrintNode implements Node {
	private Node exp;

	/*Constructor*/
	public PrintNode(Node exp) {
		this.exp = exp;
	}

	/**
	 * Check semantic errors for this node in a given environment
	 * @param env
	 * @return errors
	 */
	public ArrayList<SemanticError> checkSemantics(Environment env){
		return exp.checkSemantics(env);
	}

	/**
	 * Generate code for this node
	 * @param
	 * @return
	 */
	public Node typeCheck(){
		exp.typeCheck();
		return new TypeNode("void");
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
	public String toPrint(String indent){
		StringBuilder sb = new StringBuilder();
		sb.append(indent + "PrintNode\n");
		sb.append(indent + "  exp:");
		sb.append(exp.toPrint(indent + "\t"));
		sb.append("\n");
		return sb.toString();
	}


}
