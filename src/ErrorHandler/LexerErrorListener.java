package ErrorHandler;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.misc.Utils;

public class LexerErrorListener extends BaseErrorListener {
    public static final LexerErrorListener INSTANCE = new LexerErrorListener();
    private final List<SyntaxError> lexerErrors = new ArrayList<>();
    public List<SyntaxError> getLexerErrors() {
        return lexerErrors;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e){
        this.lexerErrors.add(new SyntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e));
    }
}
