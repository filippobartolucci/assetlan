package ast;

import Parser.AssetLanParser;
import ast.node.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class AssetLanVisitorImpl implements AssetLanVisitor<Node> {

	@Override
	public InitNode visitInit(AssetLanParser.InitContext ctx) {
		return null;
	}

	@Override
	public Node visitProgram(AssetLanParser.ProgramContext ctx) {
		return null;
	}

	@Override
	public Node visitField(AssetLanParser.FieldContext ctx) {
		return null;
	}

	@Override
	public Node visitAsset(AssetLanParser.AssetContext ctx) {
		return null;
	}

	@Override
	public Node visitFunction(AssetLanParser.FunctionContext ctx) {
		return null;
	}

	@Override
	public Node visitParam(AssetLanParser.ParamContext ctx) {
		return null;
	}

	@Override
	public Node visitAparam(AssetLanParser.AparamContext ctx) {
		return null;
	}

	@Override
	public Node visitStatement(AssetLanParser.StatementContext ctx) {
		return null;
	}

	@Override
	public Node visitType(AssetLanParser.TypeContext ctx) {
		return null;
	}

	@Override
	public Node visitAssignment(AssetLanParser.AssignmentContext ctx) {
		return null;
	}

	@Override
	public Node visitMove(AssetLanParser.MoveContext ctx) {
		return null;
	}

	@Override
	public Node visitPrint(AssetLanParser.PrintContext ctx) {
		return null;
	}

	@Override
	public Node visitTransfer(AssetLanParser.TransferContext ctx) {
		return null;
	}

	@Override
	public Node visitRet(AssetLanParser.RetContext ctx) {
		return null;
	}

	@Override
	public Node visitIte(AssetLanParser.IteContext ctx) {
		return null;
	}

	@Override
	public Node visitCall(AssetLanParser.CallContext ctx) {
		return null;
	}

	@Override
	public Node visitInitcall(AssetLanParser.InitcallContext ctx) {
		return null;
	}

	@Override
	public Node visitBaseExp(AssetLanParser.BaseExpContext ctx) {
		return null;
	}

	@Override
	public Node visitBinExp(AssetLanParser.BinExpContext ctx) {
		return null;
	}

	@Override
	public Node visitDerExp(AssetLanParser.DerExpContext ctx) {
		return null;
	}

	@Override
	public Node visitValExp(AssetLanParser.ValExpContext ctx) {
		return null;
	}

	@Override
	public Node visitNegExp(AssetLanParser.NegExpContext ctx) {
		return null;
	}

	@Override
	public Node visitBoolExp(AssetLanParser.BoolExpContext ctx) {
		return null;
	}

	@Override
	public Node visitCallExp(AssetLanParser.CallExpContext ctx) {
		return null;
	}

	@Override
	public Node visitNotExp(AssetLanParser.NotExpContext ctx) {
		return null;
	}

	@Override
	public Node visit(ParseTree parseTree) {
		return null;
	}

	@Override
	public Node visitChildren(RuleNode ruleNode) {
		return null;
	}

	@Override
	public Node visitTerminal(TerminalNode terminalNode) {
		return null;
	}

	@Override
	public Node visitErrorNode(ErrorNode errorNode) {
		return null;
	}
}
