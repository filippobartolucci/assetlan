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
	public String toPrint(String indent) {
		String res = indent + "BinExpNode\t";
		res += left.toPrint(indent + "\t\t");
		res += "\n" + indent + "\t\tOP: " + op + "\t";
		res += right.toPrint(indent + "\t\t");
		return res;
	}

	public Node typeCheck() {
		Node leftType = left.typeCheck();
		Node rightType = right.typeCheck();


		/* Asset and int can be used in the same expression
		if (!leftType.equals(rightType)) {
			throw new RuntimeException("Type mismatch -> In binary expression, left exp is" + leftType.toPrint("") + "while right ext is " + rightType.toPrint(""));
		}*/

		switch (this.op) {
			case "*", "/", "+", "-", "<", "<=", ">", ">=":
				if (! ( (leftType.equals("int") || (leftType.equals("asset") ) && ( (rightType.equals("int") || (rightType.equals("asset")) ))))){
					throw new RuntimeException("Type mismatch -> in op " + op + " type of both expression must be int or asset");
				}
				break;
			case "&&", "||":
				if (!(leftType.equals("boolean") && rightType.equals(leftType))) {
					throw new RuntimeException("Type mismatch -> in op " + op + " type of both expression must be a boolean");
				}
				return new TypeNode("bool");
			case "==", "!=":
				if (!(leftType.equals(rightType)) ) {
					throw new RuntimeException("Type mismatch -> in op " + op + " type of both expression must be the same");
				}
				return new TypeNode("bool");
		}
		return new TypeNode("int");
	}

	@Override
	public ArrayList<SemanticError> checkEffects() {
		ArrayList<SemanticError> errors = new ArrayList<>();
		errors.addAll(left.checkEffects());
		errors.addAll(right.checkEffects());
		return errors;
	}

	public String codeGeneration() {
		StringBuilder out = new StringBuilder();
		out.append(left.codeGeneration());
		out.append("push $a0\n");
		out.append(right.codeGeneration());
		out.append("lw $t0 $sp\n");

		switch (op) {
			case "+":
				out.append("add $t0 $a0 $a0\n");
				break;
			case "-":
				out.append("sub $t0 $a0 $a0\n");
				break;
			case "/":
				out.append("div $t0 $a0 $a0\n");
				break;
			case "*":
				out.append("mult $t0 $a0 $a0\n");
				break;
			case "<":
				// $a0 < $t0
				out.append("less $t0 $a0 $a0\n");
				break;
			case "<=":
				// $a0 <= $t0
				out.append("leq $t0 $a0 $a0\n");
				break;
			case ">":
				// $t0 < $a0
				out.append("less $a0 $t0 $a0\n");
				break;
			case ">=":
				// $t0 <= $a0
				out.append("leq $a0 $t0 $a0\n");
				break;
			case "==":
				// $a0 == $t0
				out.append("eq $t0 $a0 $a0\n");
				break;
			case "!=":
				// $a0 == $t0
				out.append("neq $t0 $a0 $a0\n");
				break;
			case "&&":
				// $a0 && $t0
				out.append("and $t0 $a0 $a0\n");
				break;
			case "||":
				// $a0 || $t0
				out.append("or $t0 $a0 $a0\n");
				break;
			default:
		}
		out.append("pop\n");
		return out.toString();
	}
}





