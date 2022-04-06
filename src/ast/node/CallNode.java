package ast.node;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.exp.ExpNode;

import java.util.ArrayList;

public class CallNode {
    private String id;
    private ArrayList<Node> expressions;

    /**
     * Empty constructor
     */
    public CallNode(){
        this.id = "";
        this.expressions = new ArrayList<Node>();
    }

    /**
     * Contstructor
     * @param id
     * @param expressions
     */
    public CallNode(String id, ArrayList<Node> expressions){
        this.id = id;
        this.expressions = new ArrayList<Node>();
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
