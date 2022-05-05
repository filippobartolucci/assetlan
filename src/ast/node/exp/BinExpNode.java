package ast.node.exp;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.Node;
import ast.node.TypeNode;

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

		// TODO: differenziare il TypeCheck di left e right in base al tipo di operazione
		// operazioni: *, /, +, -, <, <=, >, >=  ammettono interi o asset
		// 			   &&, || ammettono booleani
		//			   ==, != ammettono interi, asset o boolean

		if (!leftType.equals(rightType)){
			throw new RuntimeException("Type mismatch -> In binary expression, left exp is" + leftType.toPrint("") + "while right ext is " + rightType.toPrint(""));
		}
		return leftType;
	}
}





