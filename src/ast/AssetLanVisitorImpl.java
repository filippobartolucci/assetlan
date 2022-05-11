package ast;import Parser.AssetLanParser;import ast.node.*;import ast.node.exp.*;import ast.node.exp.value.BoolExpNode;import ast.node.exp.value.ValExpNode;import ast.node.statement.*;import java.util.ArrayList;public class AssetLanVisitorImpl extends AssetLanBaseVisitor<Node> {	@Override	public Node visitInit(AssetLanParser.InitContext ctx) {		return visitProgram(ctx.program());	}	@Override	public Node visitProgram(AssetLanParser.ProgramContext ctx) {		// program	    : field* asset* function* initcall ;		ArrayList<Node> fields = new ArrayList<>();		ArrayList<Node> assets = new ArrayList<>();		ArrayList<Node> functions = new ArrayList<>();		Node initcall;		for (AssetLanParser.FieldContext f : ctx.field()) {			fields.add(visit(f));		}		for (AssetLanParser.AssetContext a : ctx.asset()) {			assets.add(visit(a));		}		for (AssetLanParser.FunctionContext f : ctx.function()) {			functions.add(visit(f));		}		initcall = this.visit(ctx.initcall());		// ProgramNode		return new ProgramNode(fields, assets, functions, initcall);	}	@Override	public Node visitField(AssetLanParser.FieldContext ctx) {		// field       : type ID ('=' exp)? ';' ;		Node typenode = visit(ctx.type());		String id = ctx.ID().getText();		Node expnode = null;		if (ctx.exp() != null) {			expnode = visit(ctx.exp());		}		return new FieldNode(id, typenode, expnode);	}	@Override	public Node visitAsset(AssetLanParser.AssetContext ctx) {		// asset       : 'asset' ID ';' ;		return new AssetNode(ctx.ID().getText());	}	@Override	public Node visitFunction(AssetLanParser.FunctionContext ctx) {		// function    : type ID		//              '(' (param (',' param)* )? ')'		//              '[' (aparam (',' aparam)* )? ']'		//	          '{' field* statement* '}' ;		Node typenode = visit(ctx.type());		String id = ctx.ID().getText();        ArrayList<Node> params = new ArrayList<>();		ArrayList<Node> aparams = new ArrayList<>();		ArrayList<Node> body_params = new ArrayList<>();        ArrayList<Node> statements = new ArrayList<>();		ArrayList<Node> return_statement = new ArrayList<>();        for(AssetLanParser.ParamContext p: ctx.param()){            params.add(visit(p));        }        for(AssetLanParser.AparamContext a: ctx.aparam()){			aparams.add(visit(a));		}		for(AssetLanParser.FieldContext p: ctx.field()){			body_params.add(visit(p));		}        for(AssetLanParser.StatementContext s: ctx.statement()){			statements.add(visit(s));		}		return new FunctionNode(id, typenode, params, aparams, body_params, statements, return_statement);	}	@Override	public Node visitParam(AssetLanParser.ParamContext ctx) {		// param       : type ID;		return new ParamNode(ctx.ID().getText(), visit(ctx.type()));	}	@Override	public Node visitAparam(AssetLanParser.AparamContext ctx) {		return new AssetNode(ctx.ID().getText());	}	@Override	public Node visitStatement(AssetLanParser.StatementContext ctx) {		Node child = visit(ctx.getChild(0));		return new StatementNode(child);	}	@Override	public Node visitType(AssetLanParser.TypeContext ctx) {		return new TypeNode(ctx.getText());	}	@Override	public Node visitAssignment(AssetLanParser.AssignmentContext ctx) {		//assignment  : ID '=' exp ;		return new AssignmentNode(ctx.ID().getText(), visit(ctx.exp()));	}	@Override	public Node visitMove(AssetLanParser.MoveContext ctx) {		// move        : ID '-o' ID ;		return new MoveNode(ctx.ID(0).getText(), ctx.ID(1).getText());	}	@Override	public Node visitPrint(AssetLanParser.PrintContext ctx) {		// print	    : 'print' exp ;		return new PrintNode(visit(ctx.exp()));	}	@Override	public Node visitTransfer(AssetLanParser.TransferContext ctx) {		// transfer    : 'transfer' ID ;		return new TransferNode(ctx.ID().getText());	}	@Override	public Node visitRet(AssetLanParser.RetContext ctx) {		//ret	        : 'return' (exp)? ;		if (ctx.exp() != null) {			return new RetNode((ExpNode) visit(ctx.exp()));		}		return new RetNode(null);	}	@Override	public Node visitIte(AssetLanParser.IteContext ctx) {		// ite         : 'if' '(' exp ')' statement ('else' statement)? ;		Node expnode = visit(ctx.exp());		Node if_node = visit(ctx.statement(0));		Node else_node = null;		if (ctx.statement(1) != null) {			else_node = visit(ctx.statement(1));		}		return new IteNode(expnode, if_node, else_node);	}	@Override	public Node visitCall(AssetLanParser.CallContext ctx) {		// call        : ID '(' (exp (',' exp)* )? ')' '[' (ID (',' ID)* )? ']' ;		String id = ctx.ID(0).getText();		ArrayList<Node> expnodes = new ArrayList<>();		ArrayList<String> ids = new ArrayList<>();		for(AssetLanParser.ExpContext e: ctx.exp()){			expnodes.add(visit(e));		}		for (int i = 1; i < ctx.ID().size(); i++) {			ids.add(ctx.ID(i).getText());		}		return new CallNode(id, expnodes, ids);	}	@Override	public Node visitInitcall(AssetLanParser.InitcallContext ctx){		// initcall    : ID '(' (exp (',' exp)* )? ')' '[' (aexp (',' aexp)* )? ']' ;		String id = ctx.ID().getText();		ArrayList<Node> expnodes = new ArrayList<>();		ArrayList<Node> aexpnodes = new ArrayList<>();		for(AssetLanParser.ExpContext e: ctx.exp()){			expnodes.add(visit(e));		}		for(AssetLanParser.AexpContext e: ctx.aexp()){			aexpnodes.add(visit(e));		}		return new InitCallNode(id, expnodes, aexpnodes);	}	// TODO : implement visit for all ExpNodes	@Override	public Node visitBaseExp(AssetLanParser.BaseExpContext ctx) {return visit(ctx.exp());}	@Override	public Node visitAexp(AssetLanParser.AexpContext ctx) {return visit(ctx.exp());}	@Override	public Node visitBinExp(AssetLanParser.BinExpContext ctx) {		Node left = visit(ctx.exp(0));		Node right = visit(ctx.exp(1));		return new BinExpNode(left, ctx.op.getText(), right);	}	@Override	public Node visitDerExp(AssetLanParser.DerExpContext ctx) {		return new DerExpNode(ctx.ID().getText());	}	@Override	public Node visitValExp(AssetLanParser.ValExpContext ctx) {		return new ValExpNode(ctx.getText());	}	@Override	public Node visitNegExp(AssetLanParser.NegExpContext ctx) {		return new NegExpNode(visit(ctx.exp()));	}	@Override	public Node visitBoolExp(AssetLanParser.BoolExpContext ctx) {		return new BoolExpNode(ctx.getText());	}	@Override	public Node visitCallExp(AssetLanParser.CallExpContext ctx) {		return new CallExpNode(visit(ctx.call()));	}	@Override	public Node visitNotExp(AssetLanParser.NotExpContext ctx) {return new NotExpNode(visit(ctx.exp()));}}