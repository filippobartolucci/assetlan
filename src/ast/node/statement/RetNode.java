package ast.node.statement;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.Node;
import ast.node.TypeNode;
import ast.node.exp.ExpNode;
import java.util.ArrayList;

public class RetNode implements Node {
    private final ExpNode exp;

    /*
     *Constructor
    */
    public RetNode(ExpNode exp){
        this.exp = exp;
    }

    /**
     * Check semantic errors for this node in a given environment
     */
    public ArrayList<SemanticError> checkSemantics(Environment env){
        ArrayList<SemanticError> errors = new ArrayList<>();
        if(exp != null){
            errors.addAll(exp.checkSemantics(env));
        }
        return errors;
    }

    /**
     * Generate code for this node
     */
    public Node typeCheck(){
        if (exp == null){
            return new TypeNode("void");
        }
        Node type = exp.typeCheck();
        return type;
    }

    public ArrayList<SemanticError> checkEffects() {
        return new ArrayList<>();
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
        String res = indent + "Return\n";
        if(exp != null){
            res += exp.toPrint(indent + "\t");
        }
        res +="\n";
        return res;
    }

}
