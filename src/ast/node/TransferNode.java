package ast.node;

import Semantic.Environment;
import Semantic.STentry;
import Semantic.SemanticError;

import java.util.ArrayList;

public class TransferNode implements Node {
    public String id;

    /*
    Empty constructor
    */
    public TransferNode() {
        id = "";
    }

    /*
    Constructor
    */
    public TransferNode(String id) {
        this.id = id;
    }

    /**
     * Check semantic errors for this node in a given environment
     * @param env
     * @return errors
     */
    public ArrayList<SemanticError> checkSemantics(Environment env){
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        STentry entry = env.lookup(id);
        if(entry == null){
            errors.add(new SemanticError("Undeclared variable: " + id));
        }
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
        String res = indent + "TransferNode\n";
        res += indent + "\tid: " + id + "\n";
        return res;
    }
}
