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


		/* Asset and int can be used in the same expression
		if (!leftType.equals(rightType)) {
			throw new RuntimeException("Type mismatch -> In binary expression, left exp is" + leftType.toPrint("") + "while right ext is " + rightType.toPrint(""));
		}*/

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
		StringBuilder codeGenerated = new StringBuilder();

		//codeGenerated.append("//Start codegen of ").append(left.getClass().getName()).append(op).append(right.getClass().getName()).append("\n");
		/**
		 * Code generation for lhs and rhs to push them on the stack
		 */
		String left_generated = left.codeGeneration();
		codeGenerated.append(left_generated);

		codeGenerated.append("push $a0 // push e1\n");
		String right_generated = right.codeGeneration();

		codeGenerated.append(right_generated);

		codeGenerated.append("lw $a2 0($sp) //take e2 and $a2 take e1\n");
		codeGenerated.append("pop // remove e1 from the stack to preserve stack\n");

		/**
		 * $a2(=e1) operation $a0(=e2)
		 */

		switch (op) {
			case "+":{
				codeGenerated.append("add $a0 $a2 $a0 // a0 = t1+a0\n");

				break;
			}
			case "-": {
				codeGenerated.append("sub $a0 $a2 $a0 // a0 = t1-a0\n");
				break;
			}
			case "*": {
				codeGenerated.append("mult $a0 $a2 $a0 // a0 = t1+a0\n");
				break;
			}
			case "/": {
				codeGenerated.append("div $a0 $a2 $a0 // a0 = t1/a0\n");
				break;
			}
			/*
			 * le
			 * lt
			 * gt
			 * ge
			 * eq
			 * */
			case "<=":{
				codeGenerated.append("le $a0 $a2 $a0 // $a0 = $a2 <= $a0\n");
				break;
			}
			case "<":{
				codeGenerated.append("lt $a0 $a2 $a0 // $a0 = $a2 < $a0\n");
				break;
			}
			case ">":{
				codeGenerated.append("gt $a0 $a2 $a0 // $a0 = $a2 > $a0\n");
				break;
			}
			case ">=":{
				codeGenerated.append("ge $a0 $a2 $a0 // $a0 = $a2 >= $a0\n");
				break;
			}
			case "==":{
				codeGenerated.append("eq $a0 $a2 $a0 // $a0 = $a2 == $a0\n");
				break;
			}
			case "!=":{
				codeGenerated.append("eq $a0 $a2 $a0 // $a0 = $a2 == $a0\n");
				codeGenerated.append("not $a0 $a0 // $a0 = !$a0\n");
				break;
			}
			case "&&":{
				codeGenerated.append("and $a0 $a2 $a0 // $a0 = $a2 && $a0\n");
				//codeGenerated.append("mult $a0 $a2 $a0 // $a0 = $a2 && $a0 aka $a0 = $a2 * $a0\n");
				break;
			}

			case "||":{
				codeGenerated.append("or $a0 $a2 $a0 // $a0 = $a2 || $a0\n");
				break;
			}

			/**
			 * Case of == and != to implement on boolean expression
			 */
		}
		return codeGenerated.toString();
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





