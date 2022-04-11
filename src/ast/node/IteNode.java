package ast.node;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.exp.ExpNode;

import java.util.ArrayList;

public class IteNode implements Node {
    private ExpNode exp;
    private StatementNode statement1;
    private StatementNode statement2;

    /**
     * Contstructor
     * @param statement1
     * @param statement2
     * @param exp
     */
    public IteNode(ExpNode exp, StatementNode statement1, StatementNode statement2){
        this.exp = exp;
        this.statement1 = statement1;
        this.statement2 = statement2;
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
        String s = indent + "IteNode\n";
        s += indent + "\tCONDITION:" + exp.toPrint(indent + "\t\t");
        s += "\n" + indent + "\tIF:\n" + statement1.toPrint(indent + "\t");
        s +=  indent + "\tELSE:\n" + statement2.toPrint(indent + "\t");
        return s;
    }




}
