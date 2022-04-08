package ast.node;

import Semantic.*;
import java.util.ArrayList;

public interface Node {
	String toPrint(String indent);
	Node typeCheck();
	String codeGeneration();
	ArrayList<SemanticError> checkSemantics(Environment env);
}
