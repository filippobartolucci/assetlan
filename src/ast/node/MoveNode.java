package ast.node;

import Semantic.Environment;
import Semantic.STentry;
import Semantic.SemanticError;

import java.util.ArrayList;

public class MoveNode implements Node {
    private final String id1;
    private final String id2;

    /**
     * Constructor
     */
    public MoveNode(String id1, String id2){
        this.id1 = id1;
        this.id2 = id2;
    }
    /**
     * Check semantic errors for this node in a given environment
     */
    public ArrayList<SemanticError> checkSemantics(Environment env){
        ArrayList<SemanticError> errors = new ArrayList<>();

        STentry entry = env.lookup(id1);
        if(entry == null){
            errors.add(new SemanticError("Undeclared variable: " + id1));
        }

        entry = env.lookup(id2);
        if(entry == null){
            errors.add(new SemanticError("Undeclared variable: " + id2));
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
        return indent + "\tMOVE " + id1 + " -o " + id2 + "\n";
    }

}
