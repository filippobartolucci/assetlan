package ast.node;

import Semantic.Environment;
import Semantic.STentry;
import Semantic.SemanticError;

import java.util.ArrayList;

public class ParamNode implements Node {
    private String id;
    private Node type;

    /**
     * Constructor
     */
    public ParamNode(String id, Node type) {
        this.id = id;
        this.type = type;
    }
    /**
     * Check semantic errors for this node in a given environment
     */
    public ArrayList<SemanticError> checkSemantics(Environment env){
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
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
        String s = indent+"FieldNode\n";
        s += indent+"\tid: "+id+"\n";
        s += indent+"\ttype: "+type.toPrint(indent)+"\n";
        //s += indent+"\tvalue: "+value.toPrint(indent)+"\n";
        return s;
    }




}
