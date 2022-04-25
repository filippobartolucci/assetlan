package ast.node;

import Semantic.Environment;
import Semantic.STentry;
import Semantic.SemanticError;


import java.util.ArrayList;

public class CallNode implements Node{
    private final String  id;
    private final ArrayList<Node> expressions;
    private final ArrayList<String> ids;

    /**
     * Constructor
     */
    public CallNode(String id, ArrayList<Node> expressions, ArrayList<String> ids){
        this.id = id;
        this.expressions = expressions;
        this.ids = ids;
    }

    /**
     * Check semantic errors for this node in a given environment
     */
    public ArrayList<SemanticError> checkSemantics(Environment env){
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        STentry f_entry = env.lookup(id);
        if (f_entry == null){
            errors.add(new SemanticError("Function " + id + " is not defined"));
        }

        for(Node e : expressions){
            errors.addAll(e.checkSemantics(env));
        }
        for (String id : ids){
            STentry entry = env.lookup(id);
            if (entry == null){
                errors.add(new SemanticError("Asset " + id + " is not declared"));
            }
        }
        return errors;
    }

    /**
     * Generate code for this node
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
     */
    public String toPrint(String indent){
        StringBuilder s = new StringBuilder(indent + "CallNode: " + id + "\n");
        for(Node e : expressions){
            s.append(e.toPrint(indent + "\t"));
            s.append("\n");
        }
        s.append(indent).append("\tAssets:\n");
        for (String id : ids){
            s.append(indent).append("\t\t").append(id);
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }


}
