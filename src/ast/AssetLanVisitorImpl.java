package ast;

import Parser.AssetLanParser;
import ast.node.*;
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
		//System.out.println("visitProgram");


		// Va implementata la visit() generica per tutti i nodi
		// non ho ancora capito bene come funziona, ho messo gli screen di quello che mi ha detto Niccolò


		// ctx contiene tutte le produzioni della regola program presente nella grammatica
		// ctx.field() son tutti i nodi field presenti presenti nell'albero
		// ctx.asset() son tutti i nodi asset presenti nell'albero
		// ctx.function() son tutti i nodi function presenti nell'albero
		// eccetera

		// L'obbiettivo delle visita è avere tutti i nodi dell'albero salvati
		// in oggetti nodi che hanno tutte le informazioni necessarie in base al tipo di nodo

		// TODO
		// - Implementare per ogni nodo le funzioni presenti nell'interfaccia Node.java (al momento è commentato per evitare errori di esecuzione)
		// - Implementare la visita per ogni nodo dell'albero:
		// 		- Per ogni nodo visitato dobbiamo salvare le informazioni dei nodi che contiene (seguendo la grammatica)
		//      - es. program: field* asset* function* initcall ;
		//				- array per nodi field (perché c'è field*)
		//				- array per nodi asset (perché c'è asset*)
		//				- array per nodi function (perché c'è function*)
		//				- array per nodi initcall (perché c'è initcall)

		// Liste per salvare i nodi
		ArrayList<Node> fields = new ArrayList<Node>();
		ArrayList<Node> assets = new ArrayList<Node>();
		ArrayList<Node> functions = new ArrayList<Node>();

		Node initcallnode = new InitCallNode();

		for(AssetLanParser.FieldContext f: ctx.field()){
			// System.out.println(f.ID().getText());
			// System.out.println(f.type().getText());

			// La visitField deve restituire un nodo in cui sono salvate type, ID ed exp
			fields.add(visitField(f));
		}

		for(AssetLanParser.AssetContext a: ctx.asset()){
			// La visitAsset deve restituire un nodo in cui è salvato l'ID dell'asset
			assets.add(visitAsset(a));
		}

		for(AssetLanParser.FunctionContext f: ctx.function()) {
			// Da scrivere scrivere il codice per la creazione dei nodi Funzione
			// Farlo in una funzione visitFunction() e mettere i nodi che restitutisce in un arraylist
			// tipo così for each f in function{functions.add(visitFunction(f));}


			// Link alle Balugani Industries https://github.com/LBindustries/SimpLanPlus-Interpreter/blob/main/src/ast/SimpLanPlusVisitorImpl.java
			// non troppo utile, ha copiato abbastanza il codice di Cosimo.

			// altro link per reference su come fare
			// https://github.com/jjocram/SimpLanPlus/blob/main/src/main/java/it/azzalinferrati/ast/SimpLanPlusVisitorImpl.java

			// Ci tengo a dire che copilot mi ha aiutato a scrivere questi commenti.

		}

		initcallnode = this.visitInitcall(ctx.initcall());

		// Creo il nodo ProgramNode
		Node programnode = new ProgramNode(fields, assets, functions, initcallnode);

		return null;
	}

	@Override
	public Node visitField(AssetLanParser.FieldContext ctx) {
		// Node fieldnode = new FieldNode(ctx.ID(), ctx.type(), ctx.exp());

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
