package ast.node.exp.value;

import Semantic.GammaEnv;
import Semantic.SemanticError;
import Semantic.SigmaEnv;
import Utils.TypeValue;
import ast.node.Node;
import ast.node.TypeNode;
import ast.node.exp.ExpNode;

import java.util.ArrayList;

public class ValExpNode extends ExpNode {
	private final int val;

	public ValExpNode(String val) {
		this.val = Integer.parseInt(val);
	}

	/**
	 * Check semantic errors for this node in a given environment
	 * @param env the environment
	 * @return the semantic errors
	 */
	public ArrayList<SemanticError> checkSemantics(GammaEnv env){
		return new ArrayList<>();
	}

	/**
	 * Type check
	 */
	public Node typeCheck() {return new TypeNode(TypeValue.INT);}

	/**
	 * Generate code for this node
	 */
	public String codeGeneration(){
		return "li $a0 " + this.val + "\n";
	}

	/**
	 * Print this node
	 */
	public String toPrint(String indent) {return "\n"+indent + "Value " + val;}

	public SigmaEnv checkEffects(SigmaEnv env){
		return env;
	}

	@Override
	public int preEvaluation(){
		return this.val;
	}

}
