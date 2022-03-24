import Parser.AssetLanLexer;
import Parser.AssetLanParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ErrorHandler.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class main {
    public static void main(String[] args){
        try{

            /*
            System.out.println(" - AssetLan Compiler ");

            if(args.length == 0){
                System.err.println("No file to compile & run provided.");
                System.exit(0);
            }

            String file = args[0];
            if(!Paths.get(file).toFile().exists()) {
                throw new FileNotFoundException("File: " + file + " not found.");
            }
            */

            AssetLanLexer lexer = new AssetLanLexer(CharStreams.fromString("if..@à# = ; f] "));
            //AssetLanLexer lexer = new AssetLanLexer(CharStreams.fromFileName("Test/test.plan"));

            LexerErrorListener lexerListener = new LexerErrorListener();
            lexer.removeErrorListeners();
            lexer.addErrorListener(lexerListener);

            CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
            AssetLanParser parser = new AssetLanParser(commonTokenStream);

            // SyntaxErrorListener parserListener = new SyntaxErrorListener();
            // parser.removeErrorListeners();
            // parser.addErrorListener(parserListener);
            parser.program();

            // Ex1
            try {
                PrintWriter out = new PrintWriter("lexer_errors.txt");
                for (SyntaxError i: lexerListener.getLexerErrors()) {
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

