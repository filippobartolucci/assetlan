package ast.node;

import Semantic.*;
import java.util.ArrayList;

public interface Node {
	// Must have function foreach node in ast
	ArrayList<SemanticError> checkSemantics(GammaEnv env);
	Node typeCheck();
	SigmaEnv checkEffects(SigmaEnv env);
	String codeGeneration();
	String toPrint(String indent);
}
