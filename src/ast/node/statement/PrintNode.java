package ast.node.statement;

import Semantic.GammaEnv;
import Semantic.SemanticError;
import Semantic.SigmaEnv;
import Utils.TypeValue;
import ast.node.Node;
import ast.node.TypeNode;

import java.util.ArrayList;

public class PrintNode implements Node {
	private final Node exp;

	/*Constructor*/
	public PrintNode(Node exp) {
		this.exp = exp;
	}

	/**
	 * Check semantic errors for this node in a given environment
	 * @param env the environment
	 * @return the semantic errors
	 */
	public ArrayList<SemanticError> checkSemantics(GammaEnv env){
		return exp.checkSemantics(env);
	}

	/**
	 * Generate code for this node
	 * @return Void Type
	 */
	public Node typeCheck(){
		exp.typeCheck();
		return new TypeNode(TypeValue.VOID);
	}

	public SigmaEnv checkEffects(SigmaEnv env) {
		return exp.checkEffects(env);
	}

	/**
	 * Generate code for this node
	 */
	public String codeGeneration(){
		return this.exp.codeGeneration() + "print $a0\n";
	}

	/**
	 *
	 * @param indent
	 * @returns
	 */
	public String toPrint(String indent){
		return indent + "PrintNode\n" +
				indent + "  exp:" +
				exp.toPrint(indent + "\t") +
				"\n";
	}


}
