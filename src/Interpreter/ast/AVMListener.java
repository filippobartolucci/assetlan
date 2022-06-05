// Generated from /Users/fbartolucci/Documents/Git/assetlan/src/Interpreter/Lexer/AVM.g4 by ANTLR 4.10.1
package Interpreter.ast;

import Interpreter.Parser.AVMParser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AVMParser}.
 */
public interface AVMListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterAssembly(AVMParser.AssemblyContext ctx);
	/**
	 * Exit a parse tree produced by {@link AVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitAssembly(AVMParser.AssemblyContext ctx);
	/**
	 * Enter a parse tree produced by {@link AVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(AVMParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(AVMParser.InstructionContext ctx);
}