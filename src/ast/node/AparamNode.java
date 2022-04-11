package ast.node;

import Semantic.Environment;
import Semantic.STentry;
import Semantic.SemanticError;
import java.util.ArrayList;

public class AparamNode implements Node{
    private final String id;

    /**
     * Constructor
     */
    public AparamNode(String id){
        this.id = id;
    }

    /**
     * Check semantic errors for this node in a given environment
     * @return errors
     */
    public ArrayList<SemanticError> checkSemantics(Environment env){
        ArrayList<SemanticError> errors = new ArrayList<>();
        STentry entry = new STentry(env.getNestingLevel(),-1,this);

        SemanticError error=env.addDecl(id, entry);
        if(error!=null) {
            errors.add(error);
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
     * Print this node
     */
    public String toPrint(String indent){
        return "\n" + indent + "AparamNode"+ this.id +"\"n";
    }
}
