package ast.node;

import Semantic.Environment;
import Semantic.SemanticError;

import java.util.ArrayList;

public class InitCallNode implements Node{
    private String id;
    ArrayList<Node> exp;

    /**
     * Empty constructor
     */
    public InitCallNode(){
        this.id = "";
        this.exp = new ArrayList<Node>();
    }
    /**
     * Contstructor
     * @param id
     * @param exp
     */
    public InitCallNode(String id, ArrayList<Node> exp){
        this.id = id;
        this.exp = new ArrayList<Node>();
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

