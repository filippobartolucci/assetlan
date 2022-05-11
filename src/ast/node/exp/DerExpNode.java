package ast.node.exp;

import Semantic.Environment;
import Semantic.STentry;
import Semantic.SemanticError;
import ast.node.Node;
import java.util.ArrayList;

public class DerExpNode extends ExpNode {
    // | ID						                        #derExp
    private final String id;
    private STentry entry;

    /*Constructor
    */
    public DerExpNode(String id) {
        super();
        this.id = id;
        this.entry = null;
    }

    /**
     * Check semantic errors for this node in a given environment
     */
    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env){
        ArrayList<SemanticError> errors = new ArrayList<>();
        STentry entry = env.lookup(id);
        if(entry == null){
            errors.add(new SemanticError("Variable " + id + " not declared"));
        }
        this.entry = entry;

        return errors;
    }

    /**
     * Generate code for this node
     */
    public String toPrint(String indent){
        String s = "\n" + indent + "DerExpNode\n";
        s += indent + "\t id: " + id;
        return s;
    }

    public Node typeCheck(){
        return this.entry.getType().typeCheck();
    }

    public ArrayList<SemanticError> checkEffects(){

        ArrayList<SemanticError> errors = new ArrayList<>();

        if (!entry.getStatus()){
            errors.add(new SemanticError("Variable " + id + " is not initialized and cannot be used in a rhs exp"));
        }

        return errors;
    }

}
