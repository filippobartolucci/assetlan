package ast.node;

import Semantic.Environment;
import Semantic.SemanticError;

import java.util.ArrayList;

public class ParamNode implements Node {
    private String id;
    private Node type;

    /**
     * Constructor
     *
     * @param id
     * @param type
     */
    public ParamNode(String id, Node type) {
        this.id = id;
        this.type = type;
    }
    /**
     * Check semantic errors for this node in a given environment
     * @param env
     * @return errors
     */
    public ArrayList<SemanticError> checkSemantics(Environment env){
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
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
        String s = indent+"FieldNode\n";
        s += indent+"\tid: "+id+"\n";
        s += indent+"\ttype: "+type.toPrint(indent)+"\n";
        //s += indent+"\tvalue: "+value.toPrint(indent)+"\n";
        return s;
    }




}
