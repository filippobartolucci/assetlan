package ast.node;

import Semantic.Environment;
import Semantic.STentry;
import Semantic.SemanticError;
import java.util.ArrayList;

public class InitCallNode implements Node{
    private final String id;
    private STentry symbol;
    ArrayList<Node> exp;
    ArrayList<Node> aexp;

    /**
     * Contstructor
     */
    public InitCallNode(String id, ArrayList<Node> expnodes, ArrayList<Node> aexpnodes) {
        this.id = id;
        this.symbol = null;
        this.exp = expnodes;
        this.aexp = aexpnodes;
    }

    /**
     * Check semantic errors for this node in a given environment
     */
    public ArrayList<SemanticError> checkSemantics(Environment env){
        ArrayList<SemanticError> errors = new ArrayList<>();

        STentry f_entry = env.lookup(id);
        if (f_entry == null){
            errors.add(new SemanticError("Function " + id + " is not defined"));
        }
        this.symbol = f_entry;

        for (Node e : exp) {
            errors.addAll(e.checkSemantics(env));
        }

        for (Node e : aexp) {
            errors.addAll(e.checkSemantics(env));
        }

        return errors;
    }

    /**
     * Generate code for this node
     */
    public Node typeCheck(){
        if ((symbol.getType() instanceof FunctionNode)){
            FunctionNode f = (FunctionNode) this.symbol.getType();

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

                if (!actual_parType.equals("asset")){
                    throw new RuntimeException("Type mismatch -> type of " + (i+1) + "-th asset parameter in function " + id + " is not an asset");
                }
            }
            return f.getType();
        }else{
            throw new RuntimeException("Type mismatch -> " + id + " is not a function");
        }
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


}

