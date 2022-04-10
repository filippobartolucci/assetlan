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
     * Contstructor
     * @param id
     * @param expnodes
     * @param aexpnodes
     */
    public InitCallNode(String id, ArrayList<Node> expnodes, ArrayList<Node> aexpnodes) {
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
        String s = indent + "InitCallNode\n";
        s += indent + "\tid: " + id + "\n";
        s += indent + "\texp: " + exp + "\n";
        s += indent + "\taexp: " + aexp + "\n";
        return s;
    }


}

