package ast.node.statement;

import Semantic.Environment;
import Semantic.STentry;
import Semantic.SemanticError;
import ast.node.FunctionNode;
import ast.node.Node;


import java.util.ArrayList;

public class CallNode implements Node {
    private final String  id;
    private final ArrayList<Node> expressions; // formal parameters
    private final ArrayList<String> ids;  // assets parameters
    private final ArrayList<STentry> assets; // assets entry in the symbol table

    /**
     * Constructor
     */
    public CallNode(String id, ArrayList<Node> expressions, ArrayList<String> ids){
        this.id = id;
        this.expressions = expressions;
        this.ids = ids;
        this.assets = new ArrayList<STentry>();
    }

    /**
     * Check semantic errors for this node in a given environment
     */
    public ArrayList<SemanticError> checkSemantics(Environment env){
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

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

        return errors;
    }

    /**
     * Generate code for this node
     */
    public Node typeCheck(Environment env){
        STentry f_entry = env.lookup(id);
        Node symbol = f_entry.getType();

        if ((symbol instanceof FunctionNode)){
            FunctionNode f = (FunctionNode) symbol;

            ArrayList<Node> params = f.getParams();
            if (params.size() != expressions.size()){
                throw new RuntimeException("Error -> number of parameters in function call does not match the number of parameters in the function definition");
            }

            ArrayList<Node> aparams = f.getAparams();
            if (aparams.size() != ids.size()){
                throw new RuntimeException("Error -> number of assets parameters in function call does not match the number of assets parameters in the function definition");
            }

            for (int i=0; i<params.size(); i++){
                Node formal_parType = params.get(i).typeCheck(env);
                Node actual_parType = expressions.get(i).typeCheck(env); // this also checks type correctness in exp

                if (!formal_parType.equals(actual_parType)){
                    throw new RuntimeException("Type mismatch -> Wrong type for " + (i+1) + "-th parameter in the invocation of " + id);
                }
            }

            for (int i=0; i<aparams.size(); i++){
                Node actual_parType = assets.get(i).getType();

                if (!actual_parType.typeCheck(env).equals("asset")){
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

    // TODO: da scrivere
    public ArrayList<SemanticError> checkEffects(Environment env) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        return errors;
    }

}
