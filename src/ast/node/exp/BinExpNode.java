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


		switch (this.op) {
			case "*", "/", "+", "-", "<", "<=", ">", ">=":
				if (! ( (leftType.equals(TypeValue.INT) || (leftType.equals(TypeValue.ASSET) ) && ( (rightType.equals(TypeValue.INT) || (rightType.equals(TypeValue.ASSET)) ))))){
					throw new RuntimeException("Type mismatch -> in op " + op + " type of both expression must be int or asset");
				}
				break;
			case "&&", "||":
				if (!(leftType.equals(TypeValue.BOOL) && rightType.equals(leftType))) {
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

	@Override
	public SigmaEnv checkEffects(SigmaEnv env) {
		left.checkEffects(env);
		right.checkEffects(env);
		return env;
	}

	public String codeGeneration() {
		StringBuilder out = new StringBuilder();

		String left_generated = left.codeGeneration();
		out.append(left_generated);
		out.append("push $a0 // push e1\n");
		String right_generated = right.codeGeneration();
		out.append(right_generated);
		out.append("lw $a2 0($sp) //take e2 and $a2 take e1\n");
		out.append("pop // remove e1 from the stack to preserve stack\n");

		switch (op) {
			case "+":{
				out.append("add $a0 $a2 $a0 // a0 = t1+a0\n");

				break;
			}
			case "-": {
				out.append("sub $a0 $a2 $a0 // a0 = t1-a0\n");
				break;
			}
			case "*": {
				out.append("mult $a0 $a2 $a0 // a0 = t1+a0\n");
				break;
			}
			case "/": {
				out.append("div $a0 $a2 $a0 // a0 = t1/a0\n");
				break;
			}
			case "<=":{
				out.append("le $a0 $a2 $a0 // $a0 = $a2 <= $a0\n");
				break;
			}
			case "<":{
				out.append("lt $a0 $a2 $a0 // $a0 = $a2 < $a0\n");
				break;
			}
			case ">":{
				out.append("gt $a0 $a2 $a0 // $a0 = $a2 > $a0\n");
				break;
			}
			case ">=":{
				out.append("ge $a0 $a2 $a0 // $a0 = $a2 >= $a0\n");
				break;
			}
			case "==":{
				out.append("eq $a0 $a2 $a0 // $a0 = $a2 == $a0\n");
				break;
			}
			case "!=":{
				out.append("eq $a0 $a2 $a0 // $a0 = $a2 == $a0\n");
				out.append("not $a0 $a0 // $a0 = !$a0\n");
				break;
			}
			case "&&":{
				out.append("and $a0 $a2 $a0 // $a0 = $a2 && $a0\n");
				break;
			}
			case "||":{
				out.append("or $a0 $a2 $a0 // $a0 = $a2 || $a0\n");
				break;
			}
		}
		return out.toString();
	}

	@Override
	public int preEvaluation(){
		int leftValue = ((ExpNode) left).preEvaluation();
		int rightValue = ((ExpNode)right).preEvaluation();
		switch (op){
			case "+":
				return leftValue + rightValue;
			case "-":
				return leftValue - rightValue;
			case "*":
				return leftValue * rightValue;
			case "/":
				return leftValue / rightValue;

			default :
				throw new RuntimeException("Invalid operator for asset expression");
		}
	}
}





