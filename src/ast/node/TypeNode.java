package ast.node;

import Semantic.GammaEnv;
import Semantic.SemanticError;
import Semantic.SigmaEnv;
import Utils.TypeValue;

import java.util.ArrayList;

public class TypeNode implements Node{
    private final TypeValue type;

    /*
     * Constructor
    */
    public TypeNode(String type){

        switch (type) {
            case "int" -> this.type = TypeValue.INT;
            case "bool" -> this.type = TypeValue.BOOL;
            case "asset" -> this.type = TypeValue.ASSET;
            default -> this.type = TypeValue.VOID;
        }
    }

    public TypeNode(TypeValue typeValue) {
        this.type = typeValue;
    }


    /*
     * Getters
    */
    public TypeValue getType(){
        return this.type;
    }

    /**
     * Check semantic errors for this node in a given environment
     * @param env the environment
     * @return the semantic errors
     */
    public ArrayList<SemanticError> checkSemantics(GammaEnv env){
        return new ArrayList<>();
    }

    public SigmaEnv checkEffects(SigmaEnv env){
        return env;
    }

    /**
     * Generate code for this node
     */
    public Node typeCheck(){
        return this;
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
        return type.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TypeNode other){
            return this.type.equals(other.type);
        }

        return false;
    }

    @Override
    public String toString(){
        return type.toString();
    }

}
