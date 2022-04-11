import ErrorHandler.Error;

import Parser.AssetLanLexer;
import Parser.AssetLanParser;
import Semantic.SemanticError;
import ast.AssetLanVisitorImpl;
import ast.node.Node;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ErrorHandler.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;

import Semantic.Environment;

public class main {
    public static void main(String[] args){
        System.out.println("\nAssetLan Compiler.");
        try{
            if(args.length == 0){
                System.err.println("No file provided.");
                System.exit(0);
            }// Check if file is provided

            String file = args[0];
            if(!Paths.get(file).toFile().exists()) {
                throw new FileNotFoundException("File: " + file + " not found.");
            }// File found, continue with lexer and parser...
            System.out.println("File: \"" + file + "\" found.\nParsing...");

            AssetLanLexer lexer = new AssetLanLexer(CharStreams.fromFileName(file));

            // Our LexerErrorListener, used later to dump errors to txt
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
            System.out.println("Parsing successful!");

            // Ex1
            PrintWriter out = null;
            try {
                out = new PrintWriter("lexer_errors.txt");
                for (Error error : lexerListener.getLexerErrors()) out.write(error.getErrorMessage()+"\n");
                out.flush(); // Flush the output, save the file
            } catch (FileNotFoundException ex) {ex.printStackTrace();}
            if (out != null) out.close();

            // Ex2
            Environment env = new Environment();

            ArrayList<SemanticError> s_errors = new ArrayList<>();
            try {
                s_errors = ast.checkSemantics(env);
            } catch (RuntimeException e) {
                System.err.println(e.getMessage());
            }

            if(s_errors.size() > 0){
                for (SemanticError s_error : s_errors) {
                    System.out.println(s_error.toString());
                }
            }


        }catch (Exception exc) {
            System.err.println(exc.getMessage());
            System.exit(2);
        }
    }

}

