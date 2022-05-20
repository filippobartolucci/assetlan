package ast.node.exp;
import Semantic.GammaEnv;
import Semantic.SemanticError;
import Semantic.SigmaEnv;
import ast.node.Node;

import java.util.ArrayList;

public class ExpNode implements Node {
    ExpNode exp;

    public ExpNode(Node exp) {
        this.exp = (ExpNode) exp;
    }

    public ExpNode() {
        this.exp = null;
    }

    public ArrayList<SemanticError> checkSemantics(GammaEnv env) {
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

    public SigmaEnv checkEffects(SigmaEnv env) {
        exp.checkEffects(env);
        return env;
    }

    public int preEvaluation(){ return exp.preEvaluation(); }

}
