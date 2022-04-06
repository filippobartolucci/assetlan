package ast.node.exp;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.Node;

import java.util.ArrayList;

public class BaseExpNode {
    private ExpNode exp;

    /*Empty Constructor*/
    public BaseExpNode(){
        exp = null;
    }

    /*Constructor
    * @param exp
    * */
    public BaseExpNode(ExpNode exp){
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
        return null;
    }

}
