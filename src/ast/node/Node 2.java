package ast.node;

import Semantic.*;
import java.util.ArrayList;

public interface Node {
	/**
	 * Check semantic errors for this node in a given environment
	 * @param env the environment
	 * @return the semantic errors
	 */
	ArrayList<SemanticError> checkSemantics(GammaEnv env);
	Node typeCheck();
	SigmaEnv checkEffects(SigmaEnv env);
	String codeGeneration();
	String toPrint(String indent);
}
