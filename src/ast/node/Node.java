package ast.node;

import Semantic.*;
import java.util.ArrayList;

public interface Node {
	ArrayList<SemanticError> checkSemantics(GammaEnv env);
	Node typeCheck();
	SigmaEnv checkEffects(SigmaEnv env);
	String codeGeneration();
	String toPrint(String indent);
}
