package SyntaxErrorHandler;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.Utils;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class SyntaxErrorListener extends BaseErrorListener {
    private final List<SyntaxError> syntaxErrors = new ArrayList<>();

    public SyntaxErrorListener() {}

    List<SyntaxError> getSyntaxErrors() {
        return syntaxErrors;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e) {
        syntaxErrors.add(new SyntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e));

        try {
            PrintWriter out = new PrintWriter("errors.txt");

            for (SyntaxError i: getSyntaxErrors()) {
                out.println(" - error: " + i.getMessage() + " charPosition: " + i.getCharPositionInLine());
            }
            out.close();
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        //System.out.println(" - questo errore lo fa solo mia moglie: " + msg );
    }

    @Override
    public String toString() {
        return Utils.join(syntaxErrors.iterator(), "\n");
    }
}