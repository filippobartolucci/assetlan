package Semantic;

import java.util.ArrayList;
import java.util.HashMap;

/*
	Functions that must be implemented in a SymbolTable:
		1. SymTable newScope(SymTable st) extends the st with a new scope
		2. SymTable addDecl(SymTable st, String id, Type t) if there is no clash of names, adds id ⟼ t to st
		3. Type lookup(SymTable st, String id) looks for the type of id in st, if any
		4. SymTable exitScope(SymTable st) exits the current scope
 */

public class Environment implements Cloneable {
	private ArrayList<HashMap<String,STentry>>  symTable;
	private int nestingLevel;
	private int offset;

	// CONSTRUCTORS

	/**
	 * Creates an empty environment
	 */
	public Environment() {
		this(new ArrayList<HashMap<String,STentry>>(), -1, 0);
	}

	/**
	 * Constructor for {@code Environment}
	 */
	public Environment(ArrayList<HashMap<String,STentry>> symTable, int nestingLevel, int offset) {
		this.symTable = symTable;
		this.nestingLevel = nestingLevel;
		this.offset = offset;
	}

	/**
	 * Deep copy constructor
	 */
	public Environment(Environment e) {
		this(new ArrayList<>(), e.nestingLevel, e.offset);

		for (var scope : e.symTable) {
			final HashMap<String,STentry> copiedScope = new HashMap<String,STentry>();
			for (var id : scope.keySet()) {
				copiedScope.put(id, new STentry(scope.get(id)));
			}
			this.symTable.add(copiedScope);
		}
	}

	// GETTER

	/**
	 * return symbol table
	 */
	public ArrayList<HashMap<String, STentry>> getSymTable() {
		return symTable;
	}

	/**
	 * return the current nesting level.
	 */
	public int getNestingLevel() {
		return nestingLevel;
	}

	/**
	 * return the current offset level.
	 */
	public int getOffset() {
		return offset;
	}


	// METHODS

	/**
	 * void newScope(SymTable st) extends the st with a new scope
	 */
	public void newScope(HashMap<String, STentry> st){
		this.nestingLevel++;
		this.symTable.add(st);
	}

	/**
	 * void newEmptyScope(SymTable st) extends the st with a new empty scope
	 */
	public void newEmptyScope(){
		HashMap<String,STentry> st = new HashMap<String,STentry>();
		this.nestingLevel++;
		this.symTable.add(st);
	}

	/**
	 * SymTable addDecl(SymTable st, String id, Type t) if there is no clash of names, adds id ⟼ t to st
	 */
	public SemanticError addDecl(String id, STentry entry) {
		if (this.symTable.get(this.nestingLevel).containsKey(id)) {
			return new SemanticError("Redeclaration of " + id);
		}
		this.symTable.get(this.nestingLevel).put(id, entry);
		return null;
	}

	/**
	 * Type lookup(SymTable st, String id) looks for the type of id in st, if any
	 */
	public STentry lookup(String id){
		int nl = this.getNestingLevel();
		STentry tmp;
		for(tmp = null; nl >= 0 && tmp == null; tmp = (STentry)((HashMap)this.symTable.get(nl--)).get(id)) {
			// lookup
		}
		return tmp;
	}

	/**
	 * void exitScope(SymTable st) exits the current scope
	 */
	public void exitScope(){
		this.symTable.remove(this.nestingLevel);
		this.nestingLevel--;
	}



}
