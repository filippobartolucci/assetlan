package ast.node;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.exp.ExpNode;

import java.util.ArrayList;

public class IteNode implements Node {
    private final ExpNode exp;
    private final StatementNode if_statement;
    private final StatementNode else_statement;

    /**
     * Constructor
     */
    public IteNode(ExpNode exp, StatementNode if_statement, StatementNode else_statement){
        this.exp = exp;
        this.if_statement = if_statement;
        this.else_statement = else_statement;
    }

    /**
     * Check semantic errors for this node in a given environment
     * @return ArrayList<SemanticError>
     */
    public ArrayList<SemanticError> checkSemantics(Environment env){
        ArrayList<SemanticError> errors = new ArrayList<>();
        if (exp.checkSemantics(env) != null) {
            errors.addAll(exp.checkSemantics(env));
        }

        if (if_statement.checkSemantics(env) != null) {
            errors.addAll(if_statement.checkSemantics(env));
        }

        if (else_statement.checkSemantics(env) != null)
            errors.addAll(else_statement.checkSemantics(env));

        return errors;
    }


    public Node typeCheck(){
        return null;
    }

    public String codeGeneration(){
        return "";
    }

    public String toPrint(String indent){
        String s = indent + "IteNode\n";
        s += indent + "\tCONDITION:" + exp.toPrint(indent + "\t\t");
        s += "\n" + indent + "\tIF:\n" + if_statement.toPrint(indent + "\t");
        s +=  indent + "\tELSE:\n" + else_statement.toPrint(indent + "\t");
        return s;
    }




}
