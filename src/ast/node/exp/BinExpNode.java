package ast.node.exp;

import Semantic.GammaEnv;
import Semantic.SemanticError;
import Semantic.SigmaEnv;
import Utils.TypeValue;
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

	/*
	 * Constructor
	 */
	public BinExpNode(Node left, String op, Node right) {
		super();
		this.left = left;
		this.op = op;
		this.right = right;
	}

	/**
	 * Check semantic errors for this node in a given environment
	 * @param env the environment
	 * @return the semantic errors
	 */
	@Override
	public ArrayList<SemanticError> checkSemantics(GammaEnv env) {
		ArrayList<SemanticError> errors = new ArrayList<>();
		errors.addAll(left.checkSemantics(env));
		errors.addAll(right.checkSemantics(env));
		return errors;
	}

	public Node typeCheck() {
		Node leftType = left.typeCheck();
		Node rightType = right.typeCheck();


		switch (this.op) {
			case "*", "/", "+", "-", "<", "<=", ">", ">=":
				if (! ( (leftType.equals(new TypeNode(TypeValue.INT)) || (leftType.equals(new TypeNode(TypeValue.ASSET)) ) && ( (rightType.equals(new TypeNode(TypeValue.INT)) || (rightType.equals(new TypeNode(TypeValue.ASSET))) ))))){
					throw new RuntimeException("Type mismatch -> in op " + op + " type of both expression must be int or asset");
				}
				break;
			case "&&", "||":
				if (!(leftType.equals(new TypeNode(TypeValue.BOOL)) && rightType.equals(leftType))) {
					throw new RuntimeException("Type mismatch -> in op " + op + " type of both expression must be a boolean");
				}
				return new TypeNode(TypeValue.BOOL);
			case "==", "!=":
				if (!(leftType.equals(rightType)) ) {
					throw new RuntimeException("Type mismatch -> in op " + op + " type of both expression must be the same");
				}
				return new TypeNode(TypeValue.BOOL);
		}
		return new TypeNode(TypeValue.INT);
	}

	public String codeGeneration() {
		StringBuilder out = new StringBuilder();

		String left_generated = left.codeGeneration(); // Generating code for exp1
		out.append(left_generated);
		out.append("push $a0\n");
		String right_generated = right.codeGeneration(); // Generating code for exp2
		out.append(right_generated);
		// $a2 is used as a temporary register
		out.append("lw $a2 0($sp)\n"); // $a2 <- top
		out.append("pop\n"); // pop e1 to preserve stack

		switch (op) {
			case "+" -> out.append("add $a0 $a2 $a0 // a0 = t1+a0\n");
			case "-" -> out.append("sub $a0 $a2 $a0 // a0 = t1-a0\n");
			case "*" -> out.append("mult $a0 $a2 $a0 // a0 = t1+a0\n");
			case "/" -> out.append("div $a0 $a2 $a0 // a0 = t1/a0\n");
			case "<=" -> out.append("le $a0 $a2 $a0 // $a0 = $a2 <= $a0\n");
			case "<" -> out.append("lt $a0 $a2 $a0 // $a0 = $a2 < $a0\n");
			case ">" -> out.append("gt $a0 $a2 $a0 // $a0 = $a2 > $a0\n");
			case ">=" -> out.append("ge $a0 $a2 $a0 // $a0 = $a2 >= $a0\n");
			case "==" -> out.append("eq $a0 $a2 $a0 // $a0 = $a2 == $a0\n");
			case "!=" -> {
				out.append("eq $a0 $a2 $a0 // $a0 = $a2 == $a0\n");
				out.append("not $a0 $a0 // $a0 = !$a0\n");
			}
			case "&&" -> out.append("and $a0 $a2 $a0 // $a0 = $a2 && $a0\n");
			case "||" -> out.append("or $a0 $a2 $a0 // $a0 = $a2 || $a0\n");
		}
		return out.toString();
	}

	@Override
	public String toPrint(String indent) {
		String res = indent + "BinExpNode\t";
		res += left.toPrint(indent + "\t\t");
		res += "\n" + indent + "\t\tOP: " + op + "\t";
		res += right.toPrint(indent + "\t\t");
		return res;
	}

	@Override
	public SigmaEnv checkEffects(SigmaEnv env) {
		left.checkEffects(env);
		right.checkEffects(env);
		return env;
	}

	@Override
	public int preEvaluation(){
		int leftValue = ((ExpNode) left).preEvaluation();
		int rightValue = ((ExpNode)right).preEvaluation();
		return switch (op) {
			case "+" -> leftValue + rightValue;
			case "-" -> leftValue - rightValue;
			case "*" -> leftValue * rightValue;
			case "/" -> leftValue / rightValue;
			default -> throw new RuntimeException("Invalid operator for asset expression");
		};
	}
}





