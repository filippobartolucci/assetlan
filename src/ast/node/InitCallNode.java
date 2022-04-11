package ast.node;

import Semantic.Environment;
import Semantic.SemanticError;
import java.util.ArrayList;

public class InitCallNode implements Node{
    private final String id;
    ArrayList<Node> exp;
    ArrayList<Node> aexp;

    /**
     * Contstructor
     */
    public InitCallNode(String id, ArrayList<Node> expnodes, ArrayList<Node> aexpnodes) {
        this.id = id;
        this.exp = expnodes;
        this.aexp = aexpnodes;
    }

    /**
     * Check semantic errors for this node in a given environment
     */
    public ArrayList<SemanticError> checkSemantics(Environment env){
        ArrayList<SemanticError> res = new ArrayList<>();
        if(env.lookup(id) == null){
            res.add(new SemanticError("Function " + id + " is not declared"));
        }

        for (Node e : exp) {
            res.addAll(e.checkSemantics(env));
        }

        for (Node e : aexp) {
            res.addAll(e.checkSemantics(env));
        }

        return new ArrayList<>();
    }

    /**
     * Generate code for this node
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
     * Print this node
     */
    public String toPrint(String indent){
        StringBuilder s = new StringBuilder(indent + "InitCallNode\n");
        s.append(indent).append("\tid: ").append(id).append("\n");

        if (exp != null) {
            for (Node e : exp) {
                s.append(indent).append("\tExp: \n\t").append(e.toPrint(indent + "\t")).append("\n");
            }
        }else s.append(indent).append("\tExp: no exp\n");

        if (aexp != null) {
            for (Node e : aexp) {
                s.append(indent).append("\tAExp: \n\t ").append(e.toPrint(indent + "\t")).append("\n");
            }
        }else s.append(indent).append("\tAExp: no aexp\n");

        return s.toString();
    }


}

