package ast.node.exp;

import Semantic.GammaEnv;
import Semantic.SemanticError;
import Utils.TypeValue;
import ast.node.Node;

import java.lang.reflect.Type;
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
     * @param env the environment
     * @return the semantic errors
     */
    public ArrayList<SemanticError> checkSemantics(GammaEnv env){
        return exp.checkSemantics(env);
    }

    /**
     * Generate code for this node
     */
    public String codeGeneration(){
        return exp.codeGeneration() + "multi $a0 $a0 -1\n";
    }

    public Node typeCheck() {
        Node expType = exp.typeCheck();
        if( ! ( expType.equals(TypeValue.INT) || expType.equals(TypeValue.ASSET) ) ) {
            throw new RuntimeException("Type mismatch -> the type of the expression is not int or asset");
        }
        return expType;
    }

    /**
     * Print this node
     */
    public String toPrint(String indent){
        return "\n" + indent + "NegExpNode\t" + exp.toPrint(indent + "\t");
    }

    @Override
    public int preEvaluation(){
        return -exp.preEvaluation();
    }

}
