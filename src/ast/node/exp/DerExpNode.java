package ast.node.exp;

import Semantic.Environment;
import Semantic.STentry;
import Semantic.SemanticError;
import ast.node.Node;
import Semantic.Effects;

import java.util.ArrayList;

public class DerExpNode extends ExpNode {
    // | ID						                        #derExp
    private final String id;

    /*Constructor
    */
    public DerExpNode(String id) {
        super();
        this.id = id;
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

    public Node typeCheck(Environment env){
        STentry entry = env.lookup(id);
        return entry.getType().typeCheck(env);
    }

    public ArrayList<SemanticError> checkEffects(Environment env){
        STentry entry = env.lookup(id);
        // entry can't be null because it was checked in checkSemantics

        ArrayList<SemanticError> errors = new ArrayList<>();

        switch (entry.getStatus()){
            case BOTTOM :
                errors.add(new SemanticError( id + " is not initialized"));
                break;

            case D:
                errors.add(new SemanticError( id + " status is error"));
                break;
            case TOP:
                errors.add(new SemanticError( id + " is not in a consistent state"));
                break;
            default:
                break;
        }

        return errors;
    }

}
