package ast.node.exp;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.Node;

import java.util.ArrayList;

public class NegExpNode extends ExpNode {
    // | '-' exp					                        #negExp
    public Node exp;

    /*Constructor
    */
    public NegExpNode(Node exp) {
        this.exp = exp;
    }

    /**
     * Check semantic errors for this node in a given environment
     */
    public ArrayList<SemanticError> checkSemantics(Environment env){
        return exp.checkSemantics(env);
    }

    /**
     * Generate code for this node
     */
    public Node typeCheck(){
        return exp.typeCheck();
    }

    /**
     * Generate code for this node
     */
    public String codeGeneration(){
        return "";
    }

    /**
     * Print this node
     */
    public String toPrint(String indent){
        return "\n" + indent + "NegExpNode\t" + exp.toPrint(indent + "\t");

    }

}
