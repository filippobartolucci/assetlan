package ast.node;

import Semantic.Environment;
import Semantic.STentry;
import Semantic.SemanticError;
import ast.node.exp.ExpNode;

import java.util.ArrayList;

public class AssignmentNode implements Node {
    private String id;
    private Node exp;

    /**
     * Construtor
     * @param id
     * @param exp
     */
    public AssignmentNode(String id, Node exp) {
        this.id = id;
        this.exp=exp;
    }

    /**
     * Check semantic errors for this node in a given environment
     * @param env
     * @return errors
     */
    public ArrayList<SemanticError> checkSemantics(Environment env){
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        STentry entry = env.lookup(id);
        if(entry == null){
            errors.add(new SemanticError("Undeclared variable: " + id));
        }
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
        String res = indent + "AssignmentNode\n";
        res += indent + "\tid: " + id + "\n";
        res += indent + "\tExp: " + exp.toPrint(indent + "\t\t") + "\n";
        return res;
    }

}
