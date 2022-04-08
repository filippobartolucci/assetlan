package ast.node;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.exp.BaseExpNode;
import ast.node.exp.ExpNode;

import java.util.ArrayList;

public class PrintNode implements Node {
    private BaseExpNode exp;

    /*Empty Constructor*/
    public PrintNode() {
        exp = null;
    }

    /*Constructor*/
    public PrintNode(BaseExpNode exp) {
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
