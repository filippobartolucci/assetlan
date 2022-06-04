package ast.node.exp;

import Semantic.GammaEnv;
import Semantic.SemanticError;
import Utils.TypeValue;
import ast.node.Node;
import ast.node.TypeNode;

import java.util.ArrayList;

public class NegExpNode extends ExpNode {
    // | '-' exp					                        #negExp
    /*Constructor
     */
    public NegExpNode(Node exp) {
        super(exp);
    }

    /**
     * Check semantic errors for this node in a given environment
     */
    public ArrayList<SemanticError> checkSemantics(GammaEnv env){
        return exp.checkSemantics(env);
    }

    public Node typeCheck() {
        Node expType = exp.typeCheck();
        if( ! ( expType.equals(new TypeNode(TypeValue.INT)) || expType.equals(new TypeNode(TypeValue.ASSET)) ) ) {
            throw new RuntimeException("Type mismatch -> the type of the expression is not int or asset");
        }
        return expType;
    }

    /**
     * Generate code for this node
     */
    public String codeGeneration(){
        return exp.codeGeneration() + "multi $a0 $a0 -1 //negate\n";
    }

    /**
     * Print this node
     */
    public String toPrint(String indent){
        return "\n" + indent + "NegExpNode\t" + exp.toPrint(indent + "\t");
    }

    @Override
    public int preEvaluation(){
        return -(exp).preEvaluation();
    }

}
