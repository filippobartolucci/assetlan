package ast.node.statement;

import Semantic.GammaEnv;
import Semantic.STentry;
import Semantic.SemanticError;
import Semantic.SigmaEnv;
import Utils.TypeValue;
import ast.node.FunctionNode;
import ast.node.Node;


import java.util.ArrayList;

public class CallNode implements Node {
    private final String  id;
    private final ArrayList<Node> expressions; // formal parameters
    private final ArrayList<String> ids;  // assets parameters
    private final ArrayList<STentry> assets; // assets entry in the symbol table
    private STentry entry; // entry in the symbol table

    /**
     * Constructor
     */
    public CallNode(String id, ArrayList<Node> expressions, ArrayList<String> ids){
        this.id = id;
        this.expressions = expressions;
        this.ids = ids;
        this.assets = new ArrayList<>();
        this.entry = null;
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

        for(Node e : expressions){
            errors.addAll(e.checkSemantics(env));
        }
        for (String id : ids){
            STentry entry = env.lookup(id);
            if (entry == null){
                errors.add(new SemanticError("Asset " + id + " is not declared"));
            }
            assets.add(entry);
        }
        this.entry = f_entry;

        return errors;
    }

    /**
     * Generate code for this node
     */
    public Node typeCheck(){
        Node symbol = this.entry.getEntry();

        if ((symbol instanceof FunctionNode f)){

            ArrayList<Node> params = f.getParams();
            if (params.size() != expressions.size()){
                throw new RuntimeException("Error -> number of parameters in function call does not match the number of parameters in the function definition");
            }

            ArrayList<Node> aparams = f.getAparams();
            if (aparams.size() != ids.size()){
                throw new RuntimeException("Error -> number of assets parameters in function call does not match the number of assets parameters in the function definition");
            }

            for (int i=0; i<params.size(); i++){
                Node formal_parType = params.get(i).typeCheck();
                Node actual_parType = expressions.get(i).typeCheck(); // this also checks type correctness in exp

                if (!formal_parType.equals(actual_parType)){
                    throw new RuntimeException("Type mismatch -> Wrong type for " + (i+1) + "-th parameter in the invocation of " + id);
                }
            }

            for (int i=0; i<aparams.size(); i++){
                Node actual_parType = assets.get(i).getEntry();

                if (!actual_parType.typeCheck().equals(TypeValue.ASSET)){
                    throw new RuntimeException("Type mismatch -> type of asset parameter " + ids.get(i) + " in function " + id + " is not an asset");
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
     *
     */
    public String toPrint(String indent){
        StringBuilder s = new StringBuilder(indent + "CallNode: " + id + "\n");
        for(Node e : expressions){
            s.append(e.toPrint(indent + "\t"));
            s.append("\n");
        }
        s.append(indent).append("\tAssets:\n");
        for (String id : ids){
            s.append(indent).append("\t\t").append(id);
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    public SigmaEnv checkEffects(SigmaEnv env) {
        // Checking effects for each expression used as actual parameter
        for (Node e : expressions) {
            e.checkEffects(env);
        }

        FunctionNode called_function = (FunctionNode)this.entry.getEntry();

        // Moving values of assets in the function call
        //      def :f()[asset a]{}
        //      call :f()[b];
        //      -> value of b is moved to a
        ArrayList<Boolean> actualEffects = new ArrayList<>();
        for (int i=0; i<assets.size(); i++){
            Node actual = assets.get(i).getEntry(); // Actual asset parameter

            actualEffects.add(env.lookup(actual).getStatus());
            env.lookup(actual).setFalse();
        }

        if (env.isRecursive(this.id)){
            Boolean fixedPoint = env.fixedPoint(actualEffects,ids);

            if (!fixedPoint){
                // Fixed point not reached...
                called_function.checkFunctionEffects(env,actualEffects);

                // After fixed point, updating effects after function call...
                actualEffects = env.getFixedPointResult();
                for(int i = 0; i < assets.size(); i++) {
                    Node a = assets.get(i).getEntry();
                    if(actualEffects.get(i)) {
                        env.lookup(a).setTrue();
                    }else{
                        env.lookup(a).setFalse();
                    }
                }
            }else{
                // Fixed Point!
                env.addFixedPointResult(env.getEffects(ids)); // Updating effects...
            }
        }else{
            called_function.checkFunctionEffects(env,actualEffects);
        }


        return env;

    }

    public String getId() {
        return id;
    }
}
