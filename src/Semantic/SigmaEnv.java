package Semantic;

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

    /**
     * return symbol table
     */
    public ArrayList<HashMap<String, EffectEntry>> getSymTable() {
        return symTable;
    }

    /**
     * return the current nesting level.
     */
    public int getNestingLevel() {
        return nestingLevel;
    }


    /**
     * return list of errors
     */
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

    /**
     * Type lookup(SymTable st, String id) looks for the type of id in st, if any
     * @return stentry of id
     */
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

}