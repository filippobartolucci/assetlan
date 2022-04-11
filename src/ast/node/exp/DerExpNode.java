package ast.node.exp;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.Node;

import java.util.ArrayList;

public class DerExpNode extends ExpNode {
    // | ID						                        #derExp
    private String id;

    /*Constructor
    * @param id
    */
    public DerExpNode(String id) {
        super();
        this.id = id;
    }

    /**
     * Check semantic errors for this node in a given environment
     * @param env
     * @return errors
     */
    public ArrayList<SemanticError> checkSemantics(Environment env){return null;}

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
        String s = indent + "DerExpNode";
        s += " id: " + id;
        return s;
    }

}
