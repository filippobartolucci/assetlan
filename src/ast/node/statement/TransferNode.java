package ast.node.statement;

import Semantic.Environment;
import Semantic.STentry;
import Semantic.SemanticError;
import ast.node.Node;
import ast.node.TypeNode;

import java.util.ArrayList;

public class TransferNode implements Node {
    public String id;
    public STentry entry;

    /*
    Constructor
    */
    public TransferNode(String id) {
        this.id = id;
        this.entry = null;
    }

    /**
     * Check semantic errors for this node in a given environment
     */
    public ArrayList<SemanticError> checkSemantics(Environment env){
        ArrayList<SemanticError> errors = new ArrayList<>();
        STentry entry = env.lookup(id);
        if(entry == null){
            errors.add(new SemanticError("Asset " + id + " not declared"));
        }
        return errors;
    }

    /**
     * Generate code for this node
     */
    public Node typeCheck(){
        if (!(entry.getType().typeCheck().equals("asset"))) {
            throw new RuntimeException("Type mismatch: " + id + " is not an asset");
        }
        return new TypeNode("void");
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
        String res = indent + "TransferNode\n";
        res += indent + "\tid: " + id + "\n";
        return res;
    }
}