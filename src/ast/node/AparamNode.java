package ast.node;

import Semantic.Environment;
import Semantic.SemanticError;

import java.util.ArrayList;

public class AparamNode implements Node{
    private String id;

    /**
     * Empty Contructor
     */
    public AparamNode(){
        this.id = "";
    }

    /**
     * Constructor
     * @param id
     */
    public AparamNode(String id){
        this.id = id;
    }

    // Node interface to implement

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
     * @return
     */
    public String toPrint(String indent){
       return null;
    }
}
