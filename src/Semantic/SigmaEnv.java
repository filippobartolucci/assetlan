package Semantic;

import ast.node.AssetNode;
import ast.node.Node;

import java.util.ArrayList;
import java.util.HashMap;

public class SigmaEnv implements Cloneable {
    private ArrayList<HashMap<String, EffectEntry>> symTable;
    private int nestingLevel;
    private ArrayList<SemanticError> errors;


    public SigmaEnv() {
        this(new ArrayList<HashMap<String, EffectEntry>>(), -1,new ArrayList<SemanticError>());
    }

    /**
     * Constructor for {@code GammaEnv}
     */
    public SigmaEnv(ArrayList<HashMap<String, EffectEntry>> symTable, int nestingLevel,ArrayList<SemanticError> errors) {
        this.symTable = symTable;
        this.nestingLevel = nestingLevel;
        this.errors = errors;
    }

    /**
     * Deep copy constructor
     */
    public SigmaEnv(SigmaEnv e) {
        this(new ArrayList<>(), e.nestingLevel,e.errors);

        for (var scope : e.symTable) {
            final HashMap<String,EffectEntry> copiedScope = new HashMap<String,EffectEntry>();
            for (var id : scope.keySet()) {
                copiedScope.put(id, new EffectEntry(scope.get(id)));
            }
            this.symTable.add(copiedScope);
        }
    }

    // GETTER
    public int getNestingLevel() {
        return nestingLevel;
    }


    public ArrayList<SemanticError> getErrors() { return this.errors; }

    public void addError(SemanticError e) {
        this.errors.add(e);
    }

    // Envinronment functions

    public void newEmptyScope(){
        HashMap<String,EffectEntry> st = new HashMap<String,EffectEntry>();
        this.nestingLevel++;
        this.symTable.add(st);
    }

    public SemanticError addDecl(String id, EffectEntry entry) {
        this.symTable.get(this.nestingLevel).put(id, entry);
        return null;
    }

    public EffectEntry lookup(String id){
        int nl = this.getNestingLevel();
        EffectEntry tmp;
        for(tmp = null; nl >= 0 && tmp == null; tmp = (EffectEntry) ((HashMap)this.symTable.get(nl--)).get(id)) {}
        return tmp;
    }

    public void exitScope(){
        this.symTable.remove(this.nestingLevel);
        this.nestingLevel--;
    }

    public String toPrint(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.symTable.size(); i++) {
            sb.append("Scope ").append(i).append(":\n");
            for (var entry : this.symTable.get(i).entrySet()) {
                sb.append("\t" + entry.getKey() + " -> " + entry.getValue()+"\n");
            }
        }
        return sb.toString();
    }


    public void max(SigmaEnv env){
        if (env.symTable.size() != this.symTable.size()){
            throw new RuntimeException("Cannot call max on STs with different size");
        }

        for (int i = 0; i < this.symTable.size(); i++){
            for (var entry: this.symTable.get(i).entrySet()){
                entry.getValue().max(env.lookup(entry.getKey()));
            }
        }
    }

    public boolean fixedPoint(ArrayList<Node> assets, ArrayList<Boolean> effects){
        if (assets.size() != effects.size()){
            throw new RuntimeException(" fixedPoint: assets and effects must have the same size");
        }

        for (int i = 0; i < assets.size(); i++){
            if (effects.get(i) != this.lookup(assets.get(i).toString()).getStatus()) return false;
        }

        return true;
    }

}