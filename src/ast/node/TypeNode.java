package ast.node;

import Semantic.Environment;
import Semantic.SemanticError;

import java.util.ArrayList;

public class TypeNode implements Node{
    private final String type;

    /*
     * Constructor
    */
    public TypeNode(String type){
        this.type = type;
    }


    /*
     * Getters
    */
    public String getType(){
        return this.type;
    }

    /**
     * Check semantic errors for this node in a given environment
     */
    public ArrayList<SemanticError> checkSemantics(Environment env){
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
        return type;
    }

}
