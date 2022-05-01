package ast.node.statement;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.Node;
import ast.node.exp.ExpNode;

import java.util.ArrayList;

public class IteNode implements Node {
    private final Node exp;
    private final Node if_statement;
    private final Node else_statement;

    /**
     * Constructor
     */
    public IteNode(Node exp, Node if_statement, Node else_statement){
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

        env.newEmptyScope();

        if (exp != null) {
            errors.addAll(exp.checkSemantics(env));
        }

        if (if_statement != null) {
            errors.addAll(if_statement.checkSemantics(env));
        }

        if (else_statement != null) {
            errors.addAll(else_statement.checkSemantics(env));
        }

        env.exitScope();

        return errors;
    }


    public Node typeCheck(){
        if (!exp.typeCheck().equals("bool")){
            throw new RuntimeException("Type mismatch -> Condition of If statement must be of type bool");
        }
        Node ifType = if_statement.typeCheck();
        if (else_statement!=null) {

            Node elseType = else_statement.typeCheck();

            if (!ifType.equals(elseType)) {
                throw new RuntimeException("Type mismatch -> If and Else statements must have the same type");
            }
        }

        return ifType;
    }

    public String codeGeneration(){
        return "";
    }

    public String toPrint(String indent){
        String s = indent + "IteNode\n";
        s += indent + "\tCONDITION:" + exp.toPrint(indent + "\t\t");
        s += "\n" + indent + "\tIF:\n" + if_statement.toPrint(indent + "\t");
        if (else_statement!=null){
            s +=  indent + "\tELSE:\n" + else_statement.toPrint(indent + "\t");
        }

        return s;
    }




}
