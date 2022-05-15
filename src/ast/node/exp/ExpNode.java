package ast.node.exp;
import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.Node;

import java.util.ArrayList;

public class ExpNode implements Node {
    Node exp;

    public ExpNode(Node exp) {
        this.exp = exp;
    }

    public ExpNode() {
        this.exp = null;
    }

    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> errors = new ArrayList<>();
        if (exp != null) {
            errors.addAll(exp.checkSemantics(env));
        }
        return errors;
    }

    public Node typeCheck() {
        return exp.typeCheck();
    }

    public String codeGeneration() {
        return this.exp.codeGeneration();
    }

    public String toPrint(String indent) {
        String s = indent + "ExpNode\n";
        if (exp != null) {
            s += exp.toPrint(indent + "  ");
        }
        return s;
    }

    public ArrayList<SemanticError> checkEffects() {return this.exp.checkEffects();}



}
