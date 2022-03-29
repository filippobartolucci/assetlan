package ErrorHandler;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class LexerErrorListener extends BaseErrorListener {
    public static final LexerErrorListener INSTANCE = new LexerErrorListener();
    private final List<Error> lexerErrors = new ArrayList<>();
    public List<Error> getLexerErrors() {
        return lexerErrors;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e){
        this.lexerErrors.add(new Error(recognizer, offendingSymbol, line, charPositionInLine, msg, e));
    }
}
