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

	private final Node left;
	private final String op;
	private final Node right;

	public BinExpNode(Node left, String op, Node right) {
		super();
		this.left = left;
		this.op = op;
		this.right = right;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> errors = new ArrayList<>();
		errors.addAll(left.checkSemantics(env));
		errors.addAll(right.checkSemantics(env));
		return errors;
	}

	@Override
	public String toPrint(String indent){
		String res = indent + "BinExpNode\t";
		res += left.toPrint(indent+"\t\t");
		res += "\n" + indent + "\t\tOP: " + op + "\t";
		res += right.toPrint(indent+"\t\t");
		return res;
	}

	public Node typeCheck() {
		Node leftType = left.typeCheck();
		Node rightType = right.typeCheck();


		if (!leftType.equals(rightType)){
			throw new RuntimeException("Type mismatch -> In binary expression, left exp is" + leftType.toPrint("") + "while right ext is " + rightType.toPrint(""));
		}

		switch (this.op){
			case "*","/","+","-","<","<=",">",">=":
				if ( !( (leftType.equals("int") || (leftType.equals("asset") ) && rightType.equals(leftType)))){
					throw new RuntimeException("Type mismatch -> in op " + op + " type of both expression must be an int");
				}
				break;
			case "&&","||":
				if (!(leftType.equals("boolean") && rightType.equals(leftType))){
					throw new RuntimeException("Type mismatch -> in op " + op + " type of both expression must be a boolean");
				}
		}

		return leftType;
	}

	@Override
	public ArrayList<SemanticError> checkEffects() {
		ArrayList<SemanticError> errors = new ArrayList<>();
		errors.addAll(left.checkEffects());
		errors.addAll(right.checkEffects());
		return errors;
	}
}





