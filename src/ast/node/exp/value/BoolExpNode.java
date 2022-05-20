package ast.node.exp.value;

import Semantic.GammaEnv;
import Semantic.SemanticError;
import Semantic.SigmaEnv;
import Utils.TypeValue;
import ast.node.Node;
import ast.node.TypeNode;
import ast.node.exp.ExpNode;
import java.util.ArrayList;

public class BoolExpNode extends ExpNode {
    //| BOOL                    #boolExp
    public boolean bool;

    /*
     * Constructor
    */
    public BoolExpNode(boolean bool) {
        this.bool = bool;
    }

    public BoolExpNode(String text) {
        this.bool = Boolean.parseBoolean(text);
    }

	/**
     * Check semantic errors for this node in a given environment
     */
    public SigmaEnv checkSemantics(SigmaEnv env){
        return env;
    }

    /**
     * Generate code for this node
     */
    public Node typeCheck(){
        return new TypeNode(TypeValue.BOOL);
    }

    public SigmaEnv checkEffects(SigmaEnv env){
        return env;
    }

    /**
     * Generate code for this node
     */
    public String codeGeneration(){
        return "li $a0 "+(this.bool?1:0)+"\n";
    }

    /**
     * Print this node
     */
    public String toPrint(String indent) {
        return "\n"+indent + "Bool " + bool;
    }

    @Override
    public int preEvaluation(){
        throw new RuntimeException("Cannot use boolean value in asset expression");
    }
}

