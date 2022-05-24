package Semantic;

import ast.node.AssetNode;
import ast.node.Node;

import java.util.ArrayList;
import java.util.HashMap;

public class SigmaEnv {
    private ArrayList<HashMap<String, EffectEntry>> symTable;
    private int nestingLevel;
    private ArrayList<SemanticError> errors;
    private ArrayList<Boolean> fixedPointResult = new ArrayList<>();


    public SigmaEnv() {
        this(new ArrayList<>(), -1, new ArrayList<>());
    }

    /**
     * Constructor for {@code GammaEnv}
     */
    public SigmaEnv(ArrayList<HashMap<String, EffectEntry>> symTable, int nestingLevel,ArrayList<SemanticError> errors) {
        this.symTable = symTable;
        this.nestingLevel = nestingLevel;
        this.errors = errors;
        this.fixedPointResult = new ArrayList<>();
    }

    /**
     * Deep copy constructor
     */
    public SigmaEnv(SigmaEnv e) {
        this(new ArrayList<>(), e.nestingLevel,e.errors);

        for (var scope : e.symTable) {
            final HashMap<String,EffectEntry> copiedScope = new HashMap<>();
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

    public ArrayList<Boolean> getFixedPointResult() { return this.fixedPointResult; }

    public void addError(SemanticError e) {
        this.errors.add(e);
    }
    public void addFixedPointResult(ArrayList<Boolean> l) { this.fixedPointResult = l; }

    // Environment functions
    public void newEmptyScope(){
        HashMap<String,EffectEntry> st = new HashMap<>();
        this.nestingLevel++;
        this.symTable.add(st);
    }

    public void addDecl(String id, EffectEntry entry) {
        this.symTable.get(this.nestingLevel).put(id, entry);
    }

    public EffectEntry lookup(String id){
        int nl = this.getNestingLevel();
        EffectEntry tmp;
        for(tmp = null; nl >= 0 && tmp == null; tmp = (EffectEntry) ((HashMap)this.symTable.get(nl--)).get(id)) {}
        return tmp;
    }

    // Lookup for assets
    public EffectEntry lookup(Node id){
        if (! (id instanceof AssetNode) ) throw new RuntimeException("lookup(Node id) called on non-AssetNode");
        int nl = this.getNestingLevel();
        EffectEntry tmp;
        for(tmp = null; nl >= 0 && tmp == null; tmp = (EffectEntry) ((HashMap)this.symTable.get(nl--)).get(id.toString())) {}
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
                sb.append("\t").append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
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



    public Boolean fixedPoint(ArrayList<Boolean> actualEffects, ArrayList<String> ids){
        Boolean fixedPoint = true;

        for(int i = 0; i < actualEffects.size() && fixedPoint; i++) {
            String id = ids.get(i);
            Boolean status = this.lookup(id).getStatus();

            if (!status == actualEffects.get(i)) {
                fixedPoint = false; // fixed point not reached
            }
        }

        return fixedPoint;
    }


    public ArrayList<Boolean> getEffects(ArrayList<String> ids){
        ArrayList<Boolean> effects = new ArrayList<>();

        for(int i = 0; i < ids.size(); i++) {
            String id = ids.get(i).toString();
            effects.add(this.lookup(id).getStatus());
        }

        return effects;
    }
}