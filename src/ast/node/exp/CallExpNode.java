package ast.node.exp;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.CallNode;
import ast.node.Node;

import java.util.ArrayList;

public class CallExpNode extends ExpNode {
    // | call                                              #callExp
    private Node call;

    /*Empty Constructor
    * @param call
    */
    public CallExpNode() {
        call = null;
    }

    /* Constructor */
    public CallExpNode(Node call) {
        this.call = call;
    }


    /**
     * Check semantic errors for this node in a given environment
     * @param env
     * @return errors
     */
    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env){return null;}

    /**
     * Generate code for this node
     * @param
     * @return
     */
    @Override
    public Node typeCheck(){
        return null;
    }

    /**
     * Generate code for this node
     */
    @Override
    public String codeGeneration(){
        return "";
    }

    /**
     *
     * @param indent
     * @returns
     */
    @Override
    public String toPrint(String indent){
        return null;
    }

}
