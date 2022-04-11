package ast.node;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.exp.ExpNode;

import java.util.ArrayList;

public class IteNode implements Node {
    private ExpNode exp;
    private StatementNode if_statement;
    private StatementNode else_statement;

    /**
     * Contstructor
     * @param if_statement
     * @param else_statement
     * @param exp
     */
    public IteNode(ExpNode exp, StatementNode if_statement, StatementNode else_statement){
        this.exp = exp;
        this.if_statement = if_statement;
        this.else_statement = else_statement;
    }

    /**
     * Check semantic errors for this node in a given environment
     * @param env
     * @return errors
     */
    public ArrayList<SemanticError> checkSemantics(Environment env){
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        if (exp.checkSemantics(env) != null) {
            errors.addAll(exp.checkSemantics(env));
            if (if_statement.checkSemantics(env) != null) {
                errors.addAll(if_statement.checkSemantics(env));
            }
        }
        if (else_statement.checkSemantics(env) != null)
            errors.addAll(else_statement.checkSemantics(env));

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
        s += "\n" + indent + "\tIF:\n" + if_statement.toPrint(indent + "\t");
        s +=  indent + "\tELSE:\n" + else_statement.toPrint(indent + "\t");
        return s;
    }




}
