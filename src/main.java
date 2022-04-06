import ErrorHandler.Error;

import Parser.AssetLanLexer;
import Parser.AssetLanParser;
import ast.AssetLanVisitorImpl;
import ast.node.InitNode;
import ast.node.Node;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ErrorHandler.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Paths;

import Semantic.Environment;

public class main {
    public static void main(String[] args){
        try{
            System.out.println("AssetLan Compiler ");
            /*
            if(args.length == 0){
                System.err.println("No file provided.");
                System.exit(0);
            }

            String file = args[0];
            if(!Paths.get(file).toFile().exists()) {
                throw new FileNotFoundException("File: " + file + " not found.");
            }*/

            //AssetLanLexer lexer = new AssetLanLexer(CharStreams.fromFileName(file));
            AssetLanLexer lexer = new AssetLanLexer(CharStreams.fromFileName("./Test/test1"));

            // Ex1
            LexerErrorListener lexerListener = new LexerErrorListener();
            lexer.removeErrorListeners();
            lexer.addErrorListener(lexerListener);

            CommonTokenStream cts = new CommonTokenStream(lexer);
            AssetLanParser parser = new AssetLanParser(cts);
            parser.removeErrorListeners();

            // Ex2
            AssetLanVisitorImpl visitor = new AssetLanVisitorImpl();

            Node ast = visitor.visitInit(parser.init());

            Environment env = new Environment();
            //ArrayList<SemanticError> s_errors = ast.checkSemantics(env);

            /*
            if(s_errors.size() > 0){
                for (SemanticError s_error : s_errors) {
                    System.out.println(s_error.toString());
                }
            }*/

            // Ex1
            try {
                PrintWriter out = new PrintWriter("lexer_errors.txt");
                for (Error i: lexerListener.getLexerErrors()) {
                    out.println(i.getMessage());
                }
                out.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }


        }catch (Exception exc) {
            System.err.println(exc.getMessage());
            System.exit(2);
        }
    }

}

