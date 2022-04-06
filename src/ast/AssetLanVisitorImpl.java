package ast;

import Parser.AssetLanParser;
import ast.node.*;
import ast.node.exp.ExpNode;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AssetLanVisitorImpl implements AssetLanVisitor<Node> {

	@Override
	public Node visitInit(AssetLanParser.InitContext ctx) {
		return visitProgram(ctx.program());
	}

	@Override
	public Node visitProgram(AssetLanParser.ProgramContext ctx) {
		// program	    : field* asset* function* initcall ;
		ArrayList<Node> fields = new ArrayList<Node>();
		ArrayList<Node> assets = new ArrayList<Node>();
		ArrayList<Node> functions = new ArrayList<Node>();
		Node initcall;

		for(AssetLanParser.FieldContext f: ctx.field()){
			fields.add(visitField(f));
		}

		for(AssetLanParser.AssetContext a: ctx.asset()){
			assets.add(visitAsset(a));
		}

		for(AssetLanParser.FunctionContext f: ctx.function()) {
			functions.add(visitFunction(f));
		}

		initcall = this.visitInitcall(ctx.initcall());

		// Creo il nodo ProgramNode
		Node programnode = new ProgramNode(fields, assets, functions, initcall);

		return null;
	}

	@Override
	public Node visitField(AssetLanParser.FieldContext ctx) {
		// field       : type ID ('=' exp)? ';' ;
		TypeNode typenode = (TypeNode) visitType(ctx.type());
		String id = ctx.ID().getText();
		ExpNode expnode = (ExpNode) visit(ctx.exp());
		return new FieldNode(id, typenode, expnode);
	}

	@Override
	public Node visitAsset(AssetLanParser.AssetContext ctx) {
		// asset       : 'asset' ID ';' ;
		return new AssetNode(ctx.ID().getText());
	}

	@Override
	public Node visitFunction(AssetLanParser.FunctionContext ctx) {
		// function    : (type | 'void') ID
		//              '(' (param (',' param)* )? ')'
		//              '[' (aparam (',' aparam)* )? ']'
		//	          '{' param* statement* '}' ;
		Node typenode = visitType(ctx.type());
		String id = ctx.ID().getText();
        ArrayList<Node> params = new ArrayList<Node>();
		ArrayList<Node> aparams = new ArrayList<Node>();
		ArrayList<Node> body_params = new ArrayList<Node>();
        ArrayList<Node> statements = new ArrayList<Node>();

        for(AssetLanParser.ParamContext p: ctx.param()){
			System.out.println(p.getText());
            //params.add(visitParam(p));
        }



		/*
        for(AssetLanParser.AparamContext a: ctx.aparam()){
			aparams.add(visitAparam(a));
		}

		for(AssetLanParser.ParamContext p: ctx.param()){
            body_params.add(visitParam(p));
        }

        for(AssetLanParser.StatementContext s: ctx.statement()){
			statements.add(visitStatement(s));
		}

		return new FunctionNode(id, typenode, params, aparams, body_params, statements);*/
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
	public Node visitInitcall(AssetLanParser.InitcallContext ctx){
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
