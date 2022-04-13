package ast.node;

import Semantic.Environment;
import Semantic.STentry;
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
        ArrayList<SemanticError> errors = new ArrayList<>();

        STentry f_entry = env.lookup(id);
        if (f_entry == null){
            errors.add(new SemanticError("Function " + id + " is not defined"));
        }

        for (Node e : exp) {
            errors.addAll(e.checkSemantics(env));
        }

        for (Node e : aexp) {
            errors.addAll(e.checkSemantics(env));
        }

        return errors;
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

