package ast.node.exp;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.CallNode;
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
     */
    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env){
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

}
