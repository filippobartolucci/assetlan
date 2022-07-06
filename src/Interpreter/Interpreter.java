package Interpreter;

import Interpreter.Lexer.AVMLexer;
import Interpreter.Parser.AVMParser;
import Interpreter.ast.Instruction;
import Interpreter.ast.AVMVisitorImpl;
import Utils.ExitCode;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Interpreter{
    public static void run(String bytecode) throws IOException {
        saveCode(bytecode);

        AVMLexer lexerASM = new AVMLexer(CharStreams.fromString(bytecode));
        CommonTokenStream tokensASM = new CommonTokenStream(lexerASM);

        if (lexerASM.lexicalErrors>0 ){
            System.out.println("You had: "+lexerASM.lexicalErrors+" lexical errors in AVM.");
            System.exit(ExitCode.LEXER_ERROR.ordinal());
        }
        AVMParser parserASM = new AVMParser(tokensASM);

        AVMVisitorImpl visitorAVM = new AVMVisitorImpl();
        visitorAVM.visit(parserASM.assembly());

        if (parserASM.getNumberOfSyntaxErrors()>0) {
            System.out.println("You had: "+parserASM.getNumberOfSyntaxErrors()+" syntax errors in AVM.");
            System.exit(ExitCode.PARSER_ERROR.ordinal());
        }
        Instruction[] generatedCode = visitorAVM.getCode();
        AVM vm = new AVM(generatedCode);
        vm.cpu();
    }

    public static void saveCode(String bytecode) throws IOException {
        String fileAsm = "./Test/bytecode.asm";
        BufferedWriter out = new BufferedWriter(new FileWriter(fileAsm));
        out.write(bytecode);
        out.close();
    }
}
