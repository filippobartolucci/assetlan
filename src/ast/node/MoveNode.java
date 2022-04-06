package ast.node;

import Semantic.Environment;
import Semantic.SemanticError;

import java.util.ArrayList;

public class MoveNode implements Node {
    private String id1;
    private String id2;

    /**
     * Empty constructor
     */
    public MoveNode(){
        this.id1 = "";
        this.id2 = "";
    }

    /**
     * Constructor
     * @param id1
     * @param id2
     */
    public MoveNode(String id1, String id2){
        this.id1 = id1;
        this.id2 = id2;
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
