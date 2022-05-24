package ast.node.exp;

import Semantic.GammaEnv;
import Semantic.STentry;
import Semantic.SemanticError;
import Semantic.SigmaEnv;
import Utils.TypeValue;
import ast.node.Node;
import ast.node.TypeNode;

import java.util.ArrayList;

public class DerExpNode extends ExpNode implements Node{
    // | ID						                        #derExp
    private final String id;
    private STentry entry;

    private int currentNL;

    /*Constructor
    */
    public DerExpNode(String id) {
        super();
        this.id = id;
        this.entry = null;
        this.currentNL = 0;
    }

    /**
     * Check semantic errors for this node in a given environment
     * @param env the environment
     * @return the semantic errors
     */
    @Override
    public ArrayList<SemanticError> checkSemantics(GammaEnv env){
        ArrayList<SemanticError> errors = new ArrayList<>();
        STentry entry = env.lookup(id);
        if(entry == null){
            errors.add(new SemanticError("Variable " + id + " not declared"));
        }
        this.entry = entry;
        this.currentNL = env.getNestingLevel();

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
        Node type = entry.getEntry().typeCheck();
        if (type.equals(TypeValue.ASSET)){
            type = new TypeNode("int");
        }
        return type;
    }

    public SigmaEnv checkEffects(SigmaEnv env){
        if (this.entry.getEntry().typeCheck().equals(new TypeNode(TypeValue.INT))){
            if (!env.lookup(id).getStatus()){
                env.addError(new SemanticError("Variable " + id + " is not initialized and cannot be used in a rhs exp"));
            }
        }
        return env;
    }

    public String codeGeneration() {
        StringBuilder out = new StringBuilder();

        for (int i = this.entry.getNestinglevel(); i > this.entry.getNestinglevel(); i--)
            out.append("lw $al $al\n");

        int offsetWithAL = entry.getOffset();
        out.append("lw $a0 ").append(offsetWithAL).append("($al) ; loads in $a0 the value in ").append(id).append("\n");

        return out.toString();
    }

    @Override
    public int preEvaluation(){
        throw new RuntimeException("Cannot use var in asset expression");
    }

}
