package ast.node.statement;

import Semantic.Environment;
import Semantic.STentry;
import Semantic.SemanticError;
import ast.node.Node;

import java.util.ArrayList;

public class AssignmentNode implements Node {
    private final String id;
    private final Node exp;
    private STentry symbol;

    /**
     * Construtor
     */
    public AssignmentNode(String id, Node exp) {
        this.id = id;
        this.exp = exp;
        this.symbol = null;
    }

    /**
     * Check semantic errors for this node in a given environment
     */
    public ArrayList<SemanticError> checkSemantics(Environment env){
        ArrayList<SemanticError> errors = new ArrayList<>();
        symbol = env.lookup(id);
        if(symbol == null){
            errors.add(new SemanticError("Variable " + id + " not declared"));
        }


        errors.addAll(exp.checkSemantics(env));
        return errors;
    }

    /**
     * Generate code for this node
     */
    public Node typeCheck(){
        Node var = symbol.getType();

        Node varType = var.typeCheck();
        Node expType = exp.typeCheck();

        if (!varType.equals(expType)){
            throw new RuntimeException("Type mismatch -> var " + id + " is " + varType + " and exp is " + expType);
        }

        return varType;
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
        String res = indent + "AssignmentNode\n";
        res += indent + "\tid: " + id + "\n";
        res += indent + "\tExp: " + exp.toPrint(indent + "\t\t") + "\n";
        return res;
    }

}
