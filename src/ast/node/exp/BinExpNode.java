package ast.node.exp;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.Node;

import java.util.ArrayList;

public class BinExpNode extends ExpNode {
	/*
	| left=exp op=('*' | '/')               right=exp   #binExp
	| left=exp op=('+' | '-')               right=exp   #binExp
	| left=exp op=('<' | '<=' | '>' | '>=') right=exp   #binExp
	| left=exp op=('=='| '!=')              right=exp   #binExp
	| left=exp op='&&'                      right=exp   #binExp
	| left=exp op='||'                      right=exp   #binExp
	*/

	private Node left;
	private String op;
	private Node right;

	public BinExpNode() {
		this.left = null;
		this.op = null;
		this.right = null;
	}

	public BinExpNode(Node left, String op, Node right) {
		this.left = left;
		this.op = op;
		this.right = right;
	}

	public String toPrint(String indent){
		String res = indent + "BinExpNode\n\t";
		res += left.toPrint(indent + "\t");
		res += "\n" + indent + "\t\tOP: " + op + "\n\t";
		res += right.toPrint(indent + "\t");
		return res;
	}
}





