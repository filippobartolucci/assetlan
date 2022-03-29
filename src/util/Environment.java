package util;

import ast.STentry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
	Functions that must be implemented in a SymbolTable:
		1. SymTable newScope(SymTable st) extends the st with a new scope
		2. SymTable addDecl(SymTable st, String id, Type t) if there is no clash of names, adds id ⟼ t to st
		3. Type lookup(SymTable st, String id) looks for the type of id in st, if any
		4. SymTable exitScope(SymTable st) exits the current scope
 */

public class Environment implements Cloneable {
	private ArrayList<HashMap<String,STentry>>  symTable = new ArrayList<HashMap<String,STentry>>();
	private int nestingLevel;
	private int offset;

	// CONSTRUCTORS

	/**
	 * Constructor for {@code Environment}
	 */
	public Environment(ArrayList<HashMap<String,STentry>> symTable, int nestingLevel, int offset) {
		this.symTable = symTable;
		this.nestingLevel = nestingLevel;
		this.offset = offset;
	}
	/**
	 * Creates an empty environment
	 */
	public Environment() {
		this(new ArrayList<>(), -1, 0);
	}

	/**
	 * Deep copy constructor
	 */
	/*
	public Environment(Environment e) {
		this(new ArrayList<>(), e.nestingLevel, e.offset);

		for (var scope : e.symTable) {
			final Map<String, STentry> copiedScope = new HashMap<>();
			for (var id : scope.keySet()) {
				copiedScope.put(id, new STentry(scope.get(id)));
			}
			this.symTable.add(copiedScope);
		}
	} */

	// GETTER

	// return symbol table
	public ArrayList<HashMap<String, STentry>> getSymTable() {
		return symTable;
	}

	// return the current nesting level.
	public int getNestingLevel() {
		return nestingLevel;
	}

	// return the current offset level.
	public int getOffset() {
		return offset;
	}


	// METHODS
}