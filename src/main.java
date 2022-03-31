import ErrorHandler.Error;

import Parser.AssetLanLexer;
import Parser.AssetLanParser;
import Parser.AssetLanBaseVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ErrorHandler.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import ast.Node;

public class main {
    public static void main(String[] args){
        try{
            System.out.println("AssetLan Compiler ");

            if(args.length == 0){
                System.err.println("No file provided.");
                System.exit(0);
            }

            String file = args[0];
            if(!Paths.get(file).toFile().exists()) {
                throw new FileNotFoundException("File: " + file + " not found.");
            }

            AssetLanLexer lexer = new AssetLanLexer(CharStreams.fromFileName(file));

            // Ex1
            LexerErrorListener lexerListener = new LexerErrorListener();
            lexer.removeErrorListeners();
            lexer.addErrorListener(lexerListener);

            CommonTokenStream cts = new CommonTokenStream(lexer);
            AssetLanParser parser = new AssetLanParser(cts);
            parser.removeErrorListeners();

            // Ex2
            AssetLanBaseVisitor visitor = new AssetLanBaseVisitor();

            visitor.visit(parser.init());

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

