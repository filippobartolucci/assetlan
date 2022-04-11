package ast.node.exp.value;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.Node;
import ast.node.exp.ExpNode;
import java.util.ArrayList;

public class BoolExpNode extends ExpNode {
    //| BOOL                    #boolExp
    public Boolean bool;

    /* Empty Constructor */
    public BoolExpNode() {
        this.bool = false;
    }

    /* Constructor
    * @param bool
    */
    public BoolExpNode(Boolean bool) {
        this.bool = bool;
    }

    public BoolExpNode(String text) {
        this.bool = Boolean.parseBoolean(text);
    }

	/**
     * Check semantic errors for this node in a given environment
     * @param env
     * @return errors
     */
    public ArrayList<SemanticError> checkSemantics(Environment env){
        return new ArrayList<SemanticError>();
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
     * @returns
     */
    public String toPrint(String indent) {return "\n"+indent + "Bool " + bool;}
}

