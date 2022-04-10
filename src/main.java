import ErrorHandler.Error;

import Parser.AssetLanLexer;
import Parser.AssetLanParser;
import ast.AssetLanVisitorImpl;
import ast.node.Node;
import org.antlr.v4.parse.v4ParserException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ErrorHandler.*;

import java.io.*;
import java.nio.file.Paths;

import Semantic.Environment;

public class main {
    public static void main(String[] args){
        try{
            System.out.println("AssetLan Compiler... ");

            if(args.length == 0){
                System.err.println("No file provided.");
                System.exit(0);
            }

            String file = args[0];
            if(!Paths.get(file).toFile().exists()) {
                throw new FileNotFoundException("File: " + file + " not found.");
            }

            AssetLanLexer lexer = new AssetLanLexer(CharStreams.fromFileName(file));

            // Our LexerErrorListener, used to dump errors to txt
            LexerErrorListener lexerListener = new LexerErrorListener();
            lexer.addErrorListener(lexerListener);

            CommonTokenStream cts = new CommonTokenStream(lexer);
            AssetLanParser parser = new AssetLanParser(cts);

            AssetLanVisitorImpl visitor = new AssetLanVisitorImpl();
            Node ast = visitor.visitInit(parser.init());

            if (parser.getNumberOfSyntaxErrors()>0) {
                System.err.println("Syntax errors found.");
                return;
            }
            System.out.println("\nParsing successful!");

            System.out.println(ast.toPrint(""));

            // Ex1
            PrintWriter out = null;
            try {
                out = new PrintWriter("lexer_errors.txt");
                for (Error error : lexerListener.getLexerErrors()) {
                    out.write(error.getErrorMessage()+"\n");
                }

                out.flush();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            if (out != null) {out.close();}


            // Ex2
            Environment env = new Environment();
            //ArrayList<SemanticError> s_errors = ast.checkSemantics(env);

            /*
            if(s_errors.size() > 0){
                for (SemanticError s_error : s_errors) {
                    System.out.println(s_error.toString());
                }
            }*/


        }catch (Exception exc) {
            System.err.println(exc.getMessage());
            System.exit(2);
        }
    }

}

