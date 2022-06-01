package ast.node.statement;


import Semantic.GammaEnv;
import Semantic.STentry;
import Semantic.SemanticError;
import Semantic.SigmaEnv;
import Utils.TypeValue;
import ast.node.Node;
import ast.node.TypeNode;

import java.util.ArrayList;

public class AssignmentNode implements Node {
    private final String id; // LHS
    private final Node exp; // RHS
    private STentry entry; // Var STentry
    private int currentNL;

    /**
     * Construtor
     */
    public AssignmentNode(String id, Node exp) {
        this.id = id;
        this.exp = exp;
        this.entry = null;
        this.currentNL = 0;
    }

    /**
     * Check semantic errors for this node in a given environment
     * @param env the environment
     * @return the semantic errors
     */
    public ArrayList<SemanticError> checkSemantics(GammaEnv env){
        ArrayList<SemanticError> errors = new ArrayList<>();
        STentry symbol = env.lookup(id);
        if(symbol == null) {
            errors.add(new SemanticError("Variable " + id + " not declared"));
        }
        errors.addAll(exp.checkSemantics(env));
        this.entry= symbol;

        this.currentNL = env.getNestingLevel();

        return errors;
    }

    /**
     * Generate code for this node
     */
    public Node typeCheck(){
        Node var = this.entry.getEntry();

        Node varType = var.typeCheck();

        if(varType.equals(TypeValue.ASSET)){
            throw new RuntimeException("Asset " + id + " cannot be used lhs");
        }

        Node expType = exp.typeCheck();

        if (!varType.equals(expType)){
            throw new RuntimeException("Type mismatch -> var " + id + " is " + varType + " and exp is " + expType);
        }

        return new TypeNode(TypeValue.VOID);
    }

    public SigmaEnv checkEffects(SigmaEnv env){
        exp.checkEffects(env);
        // Variables in AssetLan cannot be deleted
        env.lookup(id).setTrue();
        return env;
    }

    /**
     * Generate code for this node
     */
    public String codeGeneration(){

        /*
        * cgen(\Gamma, id = exp)
        *       cgen(\Gamma, exp)
        *       lw $al 0($fp)
        *       sw $a0 Offset($al)
        * */
        StringBuilder out = new StringBuilder();


        out.append(exp.codeGeneration());
        out.append("lw $al 0($fp) //loads in $al value of $fp");
        out.append("lw $al 0($al)\n".repeat(Math.max(0, this.currentNL - this.entry.getNestinglevel())));

        int offsetWithAL = entry.getOffset();
        out.append("sw $a0 ").append(offsetWithAL).append("($al) ; //loads in $a0 the value in ").append(id).append("\n");

        return out.toString();
    }

    /**
     * Print this node
     */
    public String toPrint(String indent){
        String res = indent + "AssignmentNode\n";
        res += indent + "\tid: " + id + "\n";
        res += indent + "\tExp: " + exp.toPrint(indent + "\t\t") + "\n";
        return res;
    }

}
