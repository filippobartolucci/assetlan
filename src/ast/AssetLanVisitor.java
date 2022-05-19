// Generated from /Users/fbartolucci/Documents/Git/assetlan/src/AssetLan.g4 by ANTLR 4.10.1
package ast;
import Parser.AssetLanParser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AssetLanParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AssetLanVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AssetLanParser#init}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInit(AssetLanParser.InitContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssetLanParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(AssetLanParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssetLanParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(AssetLanParser.FieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssetLanParser#asset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsset(AssetLanParser.AssetContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssetLanParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(AssetLanParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssetLanParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(AssetLanParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssetLanParser#aparam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAparam(AssetLanParser.AparamContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssetLanParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(AssetLanParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssetLanParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(AssetLanParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssetLanParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(AssetLanParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssetLanParser#move}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMove(AssetLanParser.MoveContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssetLanParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(AssetLanParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssetLanParser#transfer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransfer(AssetLanParser.TransferContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssetLanParser#ret}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRet(AssetLanParser.RetContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssetLanParser#ite}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIte(AssetLanParser.IteContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssetLanParser#statementseq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementseq(AssetLanParser.StatementseqContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssetLanParser#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(AssetLanParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssetLanParser#initcall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitcall(AssetLanParser.InitcallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code baseExp}
	 * labeled alternative in {@link AssetLanParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseExp(AssetLanParser.BaseExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binExp}
	 * labeled alternative in {@link AssetLanParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinExp(AssetLanParser.BinExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code derExp}
	 * labeled alternative in {@link AssetLanParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDerExp(AssetLanParser.DerExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valExp}
	 * labeled alternative in {@link AssetLanParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValExp(AssetLanParser.ValExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negExp}
	 * labeled alternative in {@link AssetLanParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegExp(AssetLanParser.NegExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolExp}
	 * labeled alternative in {@link AssetLanParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExp(AssetLanParser.BoolExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callExp}
	 * labeled alternative in {@link AssetLanParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExp(AssetLanParser.CallExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExp}
	 * labeled alternative in {@link AssetLanParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExp(AssetLanParser.NotExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssetLanParser#aexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAexp(AssetLanParser.AexpContext ctx);
}