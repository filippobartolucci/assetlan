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
    private String id;
    private STentry entry;


    /*
    Constructor
    */
    public TransferNode(String id) {
        this.id = id;
        this.entry = null;
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
        return "";
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
