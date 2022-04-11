package ast.node.exp;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.Node;

import java.util.ArrayList;

public class NegExpNode extends ExpNode {
    // | '-' exp					                        #negExp
    public Node exp;

    /*Empty Constructor*/
    public NegExpNode() {
        exp = null;
    }

    /*Constructor
    @param exp
    */
    public NegExpNode(Node exp) {
        this.exp = exp;
    }

    /**
     * Check semantic errors for this node in a given environment
     * @param env
     * @return errors
     */
    public ArrayList<SemanticError> checkSemantics(Environment env){
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        return errors;
    }

    /**
     * Generate code for this node
     * @param
     * @return
     */
    public Node typeCheck(){
        return null;
    }

    /**
     * Generate code for this node
     */
    public String codeGeneration(){
        return "";
    }

    /**
     *
     * @param indent
     * @returns
     */
    public String toPrint(String indent){
        return "\n" + indent + "NegExpNode\t" + exp.toPrint(indent + "\t");

    }

}
