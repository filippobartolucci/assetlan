package ErrorHandler;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.List;

public class SyntaxErrorListener extends BaseErrorListener {
    public static SyntaxErrorListener INSTANCE = new SyntaxErrorListener();
    private List<Error> syntaxErrors = new ArrayList<>();
    public List<Error> getSyntaxErrors() {
        return syntaxErrors;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e){
        syntaxErrors.add(new Error(recognizer, offendingSymbol, line, charPositionInLine, msg, e));
    }
}
