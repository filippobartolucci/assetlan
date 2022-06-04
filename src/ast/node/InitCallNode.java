package ast.node;

import Semantic.GammaEnv;
import Semantic.STentry;
import Semantic.SemanticError;
import Semantic.SigmaEnv;
import Utils.TypeValue;
import ast.node.exp.ExpNode;

import java.util.ArrayList;

public class InitCallNode implements Node{
    private final String id;
    ArrayList<Node> exp;
    ArrayList<Node> aexp;
    private STentry entry;

    /**
     * Constructor
     */
    public InitCallNode(String id, ArrayList<Node> expnodes, ArrayList<Node> aexpnodes) {
        this.id = id;
        this.exp = expnodes;
        this.aexp = aexpnodes;
        this.entry = null;
    }

    public InitCallNode() {
        this.id = "";
    }


    /**
     * Check semantic errors for this node in a given environment
     * @param env the environment
     * @return the semantic errors
     */
    public ArrayList<SemanticError> checkSemantics(GammaEnv env){
        ArrayList<SemanticError> errors = new ArrayList<>();

        STentry f_entry = env.lookup(id);
        if (f_entry == null){
            errors.add(new SemanticError("Function " + id + " is not defined"));
        }

        for (Node e : exp) {
            errors.addAll(e.checkSemantics(env));
        }

        for (Node e : aexp) {
            errors.addAll(e.checkSemantics(env));
        }

        this.entry = f_entry;

        return errors;
    }

    /**
     * Generate code for this node
     */
    public Node typeCheck(){
        Node function = this.entry.getEntry();
        if ((function instanceof FunctionNode f)){
            ArrayList<Node> params = f.getParams();
            if (params.size() != exp.size()){
                throw new RuntimeException("Error -> number of parameters in function call does not match the number of parameters in the function definition");
            }

            ArrayList<Node> aparams = f.getAparams();
            if (aparams.size() != aexp.size()){
                throw new RuntimeException("Error -> number of assets parameters in function call does not match the number of assets parameters in the function definition");
            }

            for (int i=0; i<exp.size(); i++){
                Node formal_parType = params.get(i).typeCheck();
                Node actual_parType = exp.get(i).typeCheck(); // this also checks type correctness in exp

                if (!formal_parType.equals(actual_parType)){
                    throw new RuntimeException("Type mismatch -> Wrong type for " + (i+1) + "-th parameter in the invocation of "+id);
                }
            }

            for (int i=0; i<aparams.size(); i++){
                Node actual_parType = aexp.get(i).typeCheck(); // this also checks type correctness in exp

                if (!(actual_parType.equals(new TypeNode(TypeValue.INT)))){
                    throw new RuntimeException("Type mismatch -> type of " + (i+1) + "-th asset parameter in function " + id + " is not a valid expression");
                }
            }
            return f.getType();
        }else{
            throw new RuntimeException("Type mismatch -> " + id + " is not a function");
        }
    }

    public SigmaEnv checkEffects(SigmaEnv env){
        // Checking effects for each expression used as actual parameter
        for (Node e : exp) {
           e.checkEffects(env);
        }

        // Same as before, but for asset expressions
        for (Node e : aexp) {
            e.checkEffects(env);
        }

        ArrayList<Boolean> actualEffects = new ArrayList<>();
        for (Node e: aexp) {
            try{
                ExpNode exp = (ExpNode) e;
                int preEval = exp.preEvaluation();
                if (preEval<0){
                    throw new RuntimeException("asset expression cannot be less than 0");
                }else{
                    actualEffects.add(preEval != 0);
                }
            }catch(RuntimeException ex){
                env.addError(new SemanticError( "Pre Evaluation in InitCall -> "+ ex.getMessage()));
                return env;
            }
        }

        if (entry.getEntry() instanceof FunctionNode f) {
            env.addFunctionCall(this.id);
            try {
                f.checkFunctionEffects(env,actualEffects);
            }catch(StackOverflowError ex){
                env.addError(new SemanticError("Cannot reach fixed point in effect analysis"));
            }
        }

        return env;
    }


    /**
     * Generate code for this node
     */
    public String codeGeneration(){
        StringBuilder out = new StringBuilder();
        out.append("push $fp\n");

        ArrayList<Node> bodyParams = this.getBodyParams();

        // Push for body params
        for (int i = bodyParams.size()-1; i>=0; i--){
            //out.append(bodyParams.get(i).codeGeneration());
            out.append("li $a0 0 \n");
            out.append("push $a0 \n");
        }

        // Push for assets
        for (int i = aexp.size()-1; i>=0; i--){
            out.append(aexp.get(i).codeGeneration());
            out.append("push $a0 \n");
        }

        // Push for vars
        for (int i = exp.size()-1; i>=0; i--){
            out.append(exp.get(i).codeGeneration());
            out.append("push $a0 \n");
        }

        out.append("mv $fp $al //put in $al actual fp\n");
        out.append("push $al\n");
        out.append("jal ").append(this.getLabel()).append(" //Initcall: jump to start of ").append(id).append("\n");

        return out.toString();

    }

    /**
     * Print this node
     */
    public String toPrint(String indent){
        StringBuilder s = new StringBuilder(indent + "InitCallNode\n");
        s.append(indent).append("\tid: ").append(id).append("\n");

        if (exp != null) {
            for (Node e : exp) {
                s.append(indent).append("\tExp: \n\t").append(e.toPrint(indent + "\t")).append("\n");
            }
        }else s.append(indent).append("\tExp: no exp\n");

        if (aexp != null) {
            for (Node e : aexp) {
                s.append(indent).append("\tAExp: \n\t ").append(e.toPrint(indent + "\t")).append("\n");
            }
        }else s.append(indent).append("\tAExp: no aexp\n");

        return s.toString();
    }

    public String getLabel(){
        Node f = entry.getEntry();
        return ((FunctionNode) f).getLabel();
    }

    public ArrayList<Node> getBodyParams(){
        Node f = entry.getEntry();
        return ((FunctionNode) f).getBodyParams();
    }


}

