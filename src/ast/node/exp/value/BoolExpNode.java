package ast.node.exp.value;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.Node;
import ast.node.exp.ExpNode;
import java.util.ArrayList;

public class BoolExpNode extends ExpNode {
    //| BOOL                    #boolExp
    public Boolean bool;

    /*
     * Constructor
    */
    public BoolExpNode(Boolean bool) {
        this.bool = bool;
    }

    public BoolExpNode(String text) {
        this.bool = Boolean.parseBoolean(text);
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
    public String toPrint(String indent) {return "\n"+indent + "Bool " + bool;}
}

