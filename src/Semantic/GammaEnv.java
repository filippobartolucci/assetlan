package Semantic;

import ast.node.FunctionNode;

import java.util.ArrayList;
import java.util.HashMap;

/*
	Functions that must be implemented in a SymbolTable:
		1. SymTable newScope(SymTable st) extends the st with a new scope
		2. SymTable addDecl(SymTable st, String id, Type t) if there is no clash of names, adds id ⟼ t to st
		3. Type lookup(SymTable st, String id) looks for the type of id in st, if any
		4. SymTable exitScope(SymTable st) exits the current scope
 */

public class GammaEnv {
	private final ArrayList<HashMap<String,STentry>>  symTable;
	private int nestingLevel;
	private int offset;

	private FunctionNode lastFunction = null;

	// CONSTRUCTORS

	/**
	 * Creates an empty environment
	 */
	public GammaEnv() {
		this(new ArrayList<>(), -1, 0);
	}

	/**
	 * Constructor for {@code GammaEnv}
	 */
	public GammaEnv(ArrayList<HashMap<String,STentry>> symTable, int nestingLevel, int offset) {
		this.symTable = symTable;
		this.nestingLevel = nestingLevel;
		this.offset = offset;
	}

	/**
	 * Deep copy constructor
	 */
	public GammaEnv(GammaEnv e) {
		this(new ArrayList<>(), e.nestingLevel, e.offset);

		for (var scope : e.symTable) {
			final HashMap<String,STentry> copiedScope = new HashMap<>();
			for (var id : scope.keySet()) {
				copiedScope.put(id, new STentry(scope.get(id)));
			}
			this.symTable.add(copiedScope);
		}
		this.lastFunction = e.lastFunction;
	}

	// GETTER

	/**
	 * return the current nesting level.
	 */
	public int getNestingLevel() {
		return nestingLevel;
	}

	/**
	 * Return the actual value of the offset and the decrement it by the size of the type
	 * @return Actual value of the offset
	 */
	public int decOffset(int s) {
		int tmp = this.offset;
		this.offset += s;
		return tmp;
	}

	// METHODS
	/**
	 * void newEmptyScope(SymTable st) extends the st with a new empty scope
	 */
	public void newEmptyScope(){
		HashMap<String,STentry> st = new HashMap<>();
		this.nestingLevel++;
		this.offset = 0;
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
	 * @return STentry of id
	 */
	public STentry lookup(String id){
		int nl = this.getNestingLevel();
		STentry tmp;
		for(tmp = null; nl >= 0 && tmp == null; tmp = (STentry)((HashMap<?, ?>)this.symTable.get(nl--)).get(id)) {
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

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.symTable.size(); i++) {
			sb.append("Scope ").append(i).append(":");
			for (var entry : this.symTable.get(i).entrySet()) {
				sb.append(entry.getKey()).append(" -> ").append(entry.getValue());
			}
		}
		return sb.toString();
	}

	public void setLastFunction(FunctionNode n) {
		this.lastFunction = n;
	}

	public FunctionNode getLastFunction() {
		return this.lastFunction;
	}
}
