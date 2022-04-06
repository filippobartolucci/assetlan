package ast.node;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.exp.ExpNode;

import java.util.ArrayList;

public class IteNode implements Node {
   private StatementNode statement2;
   private StatementNode statement1;
   private ExpNode exp;

    /**
     * Empty constructor
     */
    public IteNode(){
        this.statement1 = null;
        this.statement2 = null;
        this.exp = null;

    }
    /**
     * Contstructor
     * @param statement1
     * @param statement2
     * @param exp
     */
    public IteNode(StatementNode statement1, StatementNode statement2, ExpNode exp){
        this.statement1 = statement1;
        this.statement2 = statement2;
        this.exp = exp;
    }

    /**
     * Check semantic errors for this node in a given environment
     * @param env
     * @return errors
     */
    public ArrayList<SemanticError> checkSemantics(Environment env){
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        return errors;
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
    public String toPrint(String indent){
        return null;
    }




}
