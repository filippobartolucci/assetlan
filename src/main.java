import ErrorHandler.Error;

import Parser.AssetLanLexer;
import Parser.AssetLanParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ErrorHandler.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Paths;

public class main {
    public static void main(String[] args){
        try{
            System.out.println(" - AssetLan Compiler ");

            if(args.length == 0){
                System.err.println("No file to compile & run provided.");
                System.exit(0);
            }

            String file = args[0];
            if(!Paths.get(file).toFile().exists()) {
                throw new FileNotFoundException("File: " + file + " not found.");
            }

            AssetLanLexer lexer = new AssetLanLexer(CharStreams.fromFileName(file));
            //AssetLanLexer lexer = new AssetLanLexer(CharStreams.fromString("int x = . ; f()[] "));

            LexerErrorListener lexerListener = new LexerErrorListener();
            lexer.removeErrorListeners();
            lexer.addErrorListener(lexerListener);


            // SymbolTable st = new SymbolTable();
            // st.insert("x","5");
            // st.insert("x","Integer");
            // st.printAttributes(); //stampa tutti gli elementi della hash
            // //AssetLanLexer lexer = new AssetLanLexer(CharStreams.fromString("int f = ; f] "));
            // AssetLanLexer lexer = new AssetLanLexer(CharStreams.fromFileName("Test/test2"));

            CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
            AssetLanParser parser = new AssetLanParser(commonTokenStream);

            // SyntaxErrorListener parserListener = new SyntaxErrorListener();

            // parser.addErrorListener(parserListener);
            AssetLanParser.ProgramContext tree = parser.program();
            ParseTreeWalker walker = new ParseTreeWalker();
            System.out.println(tree);

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

