package ast.node;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.exp.BaseExpNode;

import java.util.ArrayList;

public class InitCallNode implements Node{
    private String id;
    ArrayList<BaseExpNode> exp;
    ArrayList<BaseExpNode> aexp;

    /**
     * Empty constructor
     */
    public InitCallNode(){
        this.id = "";
        this.exp = new ArrayList<BaseExpNode>();
        this.aexp = new ArrayList<BaseExpNode>();
    }
    /**
     * Contstructor
     * @param id
     * @param exp
     */
    public InitCallNode(String id, ArrayList<BaseExpNode> exp, ArrayList<BaseExpNode> aexp){
        this.id = id;
        this.exp = exp;
        this.aexp = aexp;
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

