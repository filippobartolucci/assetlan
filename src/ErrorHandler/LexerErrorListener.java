package ErrorHandler;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import java.io.PrintWriter;


public class LexerErrorListener extends BaseErrorListener {
    public static LexerErrorListener INSTANCE = new LexerErrorListener();
    private List<Error> lexerErrors = new ArrayList<>();
    public List<Error> getLexerErrors() {
        return lexerErrors;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e){
        lexerErrors.add(new Error(recognizer, offendingSymbol, line, charPositionInLine, msg, e));
    }
}
