package ast.node.exp;
import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.Node;

import java.util.ArrayList;

public class ExpNode implements Node {
    private Node exp;

    public ExpNode() {
        this.exp = null;
    }

    public ExpNode(Node exp) {
        this.exp = exp;
    }

    public String toPrint(String indent) {
        return null;
    }

    public Node typeCheck() {
        return null;
    }

    public String codeGeneration() {
        return null;
    }

    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> semanticErrors = new ArrayList<>();
        if (exp != null) {
            //semanticErrors.addAll(exp.checkSemantics());
        }
        return semanticErrors;
    }

}
