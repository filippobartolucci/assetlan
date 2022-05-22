package ast.node.exp;

import Semantic.GammaEnv;
import Semantic.SemanticError;
import Semantic.SigmaEnv;
import ast.node.Node;

import java.util.ArrayList;

public class CallExpNode extends ExpNode {
    // | call                                              #callExp
    private final Node call;

    /* Constructor */
    public CallExpNode(Node call) {
        this.call = call;
    }

    /**
     * Check semantic errors for this node in a given environment
     * @param env the environment
     * @return the semantic errors
     */
    @Override
    public ArrayList<SemanticError> checkSemantics(GammaEnv env){
        return call.checkSemantics(env);}

    /**
     * Generate code for this node
     */
    @Override
    public Node typeCheck(){
        return call.typeCheck();
    }

    /**
     * Generate code for this node
     */
    @Override
    public String codeGeneration(){
        return "";
    }

    /**
     * Print this node
     */
    @Override
    public String toPrint(String indent){
        String s = "";
        s += indent + "CallExpNode\n";
        s += indent + call.toPrint(indent + "\t");
        return s;
    }

    @Override
    public SigmaEnv checkEffects(SigmaEnv env) {
        call.checkEffects(env);
        return env;
    }

    @Override
    public int preEvaluation(){
        throw new RuntimeException("Cannot use function call in asset expression");
    }

}
