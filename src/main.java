import Parser.AssetLanLexer;
import Parser.AssetLanParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import SyntaxErrorHandler.*;

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

            AssetLanLexer lexer = new AssetLanLexer(CharStreams.fromString("int f = ; f] "));
            //AssetLanLexer lexer = new AssetLanLexer(CharStreams.fromFileName("Test/test.plan"));
            CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
            AssetLanParser parser = new AssetLanParser(commonTokenStream);
            SyntaxErrorListener listener = new SyntaxErrorListener();
            parser.removeErrorListeners();
            parser.addErrorListener(listener);
            parser.program();

        }catch (Exception exc) {
            System.err.println(exc.getMessage());
            System.exit(2);
        }
    }

}

