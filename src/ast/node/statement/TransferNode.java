package ast.node.statement;


import Semantic.GammaEnv;
import Semantic.STentry;
import Semantic.SemanticError;
import Semantic.SigmaEnv;
import Utils.TypeValue;
import ast.node.Node;
import ast.node.TypeNode;

import java.util.ArrayList;

public class TransferNode implements Node {
    private final String id;
    private STentry entry;

    private int currentNL;


    /*
    Constructor
    */
    public TransferNode(String id) {
        this.id = id;
        this.entry = null;
        this.currentNL=0;
    }

    /**
     * Check semantic errors for this node in a given environment
     * @param env the environment
     * @return the semantic errors
     */
    public ArrayList<SemanticError> checkSemantics(GammaEnv env){
        ArrayList<SemanticError> errors = new ArrayList<>();
        STentry entry = env.lookup(id);
        if(entry == null){
            errors.add(new SemanticError("Asset " + id + " not declared"));
        }
        this.entry = entry;
        this.currentNL = env.getNestingLevel();

        return errors;
    }

    public SigmaEnv checkEffects(SigmaEnv env) {
        env.lookup(id).setFalse(); // Asset -> Empty after transfer
        return env;
    }

    /**
     * Generate code for this node
     */
    public Node typeCheck(){
        if (!(entry.getEntry().typeCheck().equals(TypeValue.ASSET))) {
            throw new RuntimeException("Type mismatch: " + id + " is not an asset");
        }
        return new TypeNode(TypeValue.VOID);
    }

    /**
     * Generate code for this node
     */
    public String codeGeneration(){
        StringBuilder out = new StringBuilder();

        // Loading in $a0 the value of asset
        out.append("mv $fp $al //put in $al actual fp\n");
        out.append("lw $al 0($al)\n".repeat(Math.max(0, this.currentNL) - this.entry.getNestinglevel()));
        int offsetWithAL = entry.getOffset();
        out.append("lw $a0 ").append(offsetWithAL).append("($al) //loads in $a0 the value in ").append(id).append("\n");

        // Transferring value to the wallet
        out.append("transfer $a0\n");

        // Emptying the asset...
        out.append("li $a0 0 // Emptying the asset...\n");
        out.append("sw $a0 ").append(offsetWithAL).append("($al)").append("\n");

        return out.toString();
    }

    /**
     * Print this node
     */
    public String toPrint(String indent){
        String res = indent + "TransferNode\n";
        res += indent + "\tid: " + id + "\n";
        return res;
    }
}
