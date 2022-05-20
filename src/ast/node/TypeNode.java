package ast.node;

import Semantic.GammaEnv;
import Semantic.SemanticError;
import Semantic.SigmaEnv;
import Utils.TypeValue;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static Utils.TypeValue.INT;

public class TypeNode implements Node{
    private final TypeValue type;

    /*
     * Constructor
    */
    public TypeNode(String type){

        if (type.equals("int")){
            this.type = TypeValue.INT;
        }
        else if (type.equals("bool")){
            this.type = TypeValue.BOOL;
        }
        else if (type.equals("asset")){
            this.type = TypeValue.ASSET;
        }else {
            this.type = TypeValue.VOID;
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
        if (obj instanceof TypeNode){
            TypeNode other = (TypeNode) obj;
            if (this.toString().equals(other.toString())){
                return true;
            }
        }
        if (obj instanceof TypeValue){
            if(this.type.equals(obj)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return type.toString();
    }

}
