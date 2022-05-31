package ast.node;

import Semantic.*;

import java.util.ArrayList;

public class ParamNode implements Node {
    private final String id;
    private final Node type;
    public STentry entry;


    /**
     * Constructor
     */
    public ParamNode(String id, Node type) {
        this.id = id;
        this.type = type;
        this.entry = null;
    }

    /**
     * Check semantic errors for this node in a given environment
     * @param env the environment
     * @return the semantic errors
     */
    public ArrayList<SemanticError> checkSemantics(GammaEnv env){
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        STentry entry = new STentry(env.getNestingLevel(),-1,this);
        SemanticError error=env.addDecl(id, entry);

        // Check if type == null
        if("void".equals(((TypeNode)type).getType())){
            errors.add(new SemanticError("Variable " + id + " can't have void type"));
        }

        if(error!=null) {
            errors.add(error);
        }
        this.entry = entry;

        return errors;
    }

    /**
     * Check semantic errors for this node in a given environment
     * @param env the environment
     * @return the semantic errors
     */
    public SigmaEnv checkEffects(SigmaEnv env){
        EffectEntry entry = new EffectEntry();
        entry.setTrue();
        env.addDecl(id,entry);
        return env;
    }

    /**
     * Generate code for this node
     */
    public Node typeCheck(){
        return this.type;
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
