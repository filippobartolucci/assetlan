package ast.node.statement;

import Semantic.GammaEnv;
import Semantic.STentry;
import Semantic.SemanticError;
import Semantic.SigmaEnv;
import Utils.TypeValue;
import ast.node.Node;
import ast.node.TypeNode;

import java.util.ArrayList;

public class MoveNode implements Node {
    private final String id1;
    private final String id2;
    private STentry entry1;
    private STentry entry2;


    /**
     * Constructor
     */
    public MoveNode(String id1, String id2){
        this.id1 = id1;
        this.id2 = id2;
        this.entry1 = null;
        this.entry2 = null;
    }
    /**
     * Check semantic errors for this node in a given environment
     */
    public ArrayList<SemanticError> checkSemantics(GammaEnv env){
        ArrayList<SemanticError> errors = new ArrayList<>();

        STentry entry1 = env.lookup(id1);
        if(entry1 == null){
            errors.add(new SemanticError("Undeclared asset: " + id1));
        }
        this.entry1 = entry1;

        STentry entry2 = env.lookup(id2);
        if(entry2 == null){
            errors.add(new SemanticError("Undeclared asset: " + id2));
        }
        this.entry2 = entry2;

        return errors;
    }

    /**
     * Generate code for this node
     */
    public Node typeCheck(){
        if (!entry1.getEntry().typeCheck().equals(TypeValue.ASSET)) {
            throw new RuntimeException("Type mismatch: " + id1 + " is not an asset");
        }

        if (!entry2.getEntry().typeCheck().equals(TypeValue.ASSET)){
            throw new RuntimeException("Type mismatch: " + id2 + " is not an asset");
        }

        return new TypeNode(TypeValue.VOID);
    }

    public SigmaEnv checkEffects(SigmaEnv env){
        env.lookup(this.id1).setFalse(); // Asset1 -> empty (0)
        env.lookup(this.id2).setTrue(); // Asset2 -> not empty (1)

        return env;
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
