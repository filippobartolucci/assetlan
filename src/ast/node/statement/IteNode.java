package ast.node.statement;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.Node;
import ast.node.StatementNode;
import ast.node.TypeNode;
import ast.node.exp.ExpNode;

import java.util.ArrayList;

public class IteNode implements Node {
    private final Node exp;
    private ArrayList<Node> thenb;
    private ArrayList<Node> elseb;

    /**
     * Constructor
     */
    public IteNode(Node exp, ArrayList<Node> thenb, ArrayList<Node> elseb){
        this.exp = exp;
        this.thenb = thenb;
        this.elseb = elseb;
    }

    /**
     * Check semantic errors for this node in a given environment
     * @return ArrayList<SemanticError>
     */
    public ArrayList<SemanticError> checkSemantics(Environment env){
        ArrayList<SemanticError> errors = new ArrayList<>();

        if (exp != null) {
            errors.addAll(exp.checkSemantics(env));
        }

        for (Node n : thenb) {
            errors.addAll(n.checkSemantics(env));
        }

        for (Node n: elseb) {
            errors.addAll(n.checkSemantics(env));
        }

        return errors;
    }


    public Node typeCheck() {
        if (!exp.typeCheck().equals("bool")) {
            throw new RuntimeException("Type mismatch -> Condition of If statement must be of type bool");
        }

        // Type for then branch
        Node thenb_type = new TypeNode("void");

        // for each statement in then branch
        for (Node s : thenb) {
            Node n = ((StatementNode) s).getChild(); // getting the type of the statement
            // Looking for return statement
            if (n instanceof RetNode) {
                // return statement is found, checking its type
                if (thenb_type.equals("void")) { // if true -> then branch type never updated
                    thenb_type = n.typeCheck(); // updating then branch type
                } else if (!thenb_type.equals(n.typeCheck())) { // if another return is found, checking if types are the same
                    throw new RuntimeException("Type mismatch -> Return type of If statement must be the same");
                }
            } else if (n instanceof IteNode) {
                // another if is found, if it has a type, it must be equal to this node type
                if (thenb_type.equals("void")) {
                    thenb_type = n.typeCheck();
                } else if (!thenb_type.equals(n.typeCheck())) {
                    throw new RuntimeException("Type mismatch -> If statement must have the same type");
                }
            }
        }

        Node elseb_type = new TypeNode("void");
        for (Node s : elseb) {
            Node n = ((StatementNode) s).getChild();
            // Looking for return statement
            if (n instanceof RetNode) {
                // return statement is found, adding its type to if
                if (elseb_type.equals("void")) {
                    elseb_type = n.typeCheck();
                } else if (!elseb_type.equals(n.typeCheck())) {
                    // if another return is found, checking if types are the same
                    throw new RuntimeException("Type mismatch -> Return type of If statement must be the same");
                }
            } else if (n instanceof IteNode) {
                // another if is found, if it has a type, it must be equal to this node type
                if (elseb_type.equals("void")) {
                    elseb_type = n.typeCheck();
                } else if (!elseb_type.equals(n.typeCheck())) {
                    throw new RuntimeException("Type mismatch -> If statement must have the same type");
                }
            }
        }

        if (elseb.size() == 0) {
            return thenb_type == null ? new TypeNode("void") : thenb_type;
        }

        if (!thenb_type.equals(thenb_type)) {
            new RuntimeException("Type mismatch -> If statement must have the same type");
        }

        return thenb_type;
    }

    public String codeGeneration(){
        return "";
    }

    public ArrayList<SemanticError> checkEffects() {
        ArrayList<SemanticError> errors = new ArrayList<>();
        errors.addAll(exp.checkEffects());
        //errors.addAll(if_statement.checkEffects());

        //if (else_statement != null) {
           // errors.addAll(else_statement.checkEffects());
        //}

        return errors;
    }

    public String toPrint(String indent){
        String s = indent + "IteNode\n";
        s += indent + "\tCONDITION:" + exp.toPrint(indent + "\t\t");
        //s += "\n" + indent + "\tIF:\n" + thenb.toPrint(indent + "\t");
        if (elseb!=null){
           // s +=  indent + "\tELSE:\n" + elseb.toPrint(indent + "\t");
        }
        return s;
    }



}



