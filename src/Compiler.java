
import Parser.AssetLanLexer;
import Parser.AssetLanParser;
import Semantic.SemanticError;
import ast.AssetLanVisitorImpl;
import ast.node.Node;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import Semantic.*;
import Utils.*;
import Interpreter.*;


public class Compiler {
    public static void main(String[] args){
        System.out.println("\nAssetLan Compiler" );
        try{
            if(args.length == 0){
                System.err.println("No file provided.");
                System.exit(0);
            }// Check if file is provided

            String file = args[0];
            if(!Paths.get(file).toFile().exists()) throw new FileNotFoundException("File: " + file + " not found.");

            // File found, continue with lexer and parser...
            System.out.println("File: \"" + file + "\" found.\n\nParsing...");

            // Lexer and parser
            AssetLanLexer lexer = new AssetLanLexer(CharStreams.fromFileName(file));
            CommonTokenStream cts = new CommonTokenStream(lexer);
            AssetLanParser parser = new AssetLanParser(cts);

            // Visiting the parse tree...
            AssetLanVisitorImpl visitor = new AssetLanVisitorImpl();
            Node ast = visitor.visitInit(parser.init());

            if (parser.getNumberOfSyntaxErrors()>0) {
                System.err.println("\n" + parser.getNumberOfSyntaxErrors() + " Syntax errors found -> Compilation failed.");
                System.exit(ExitCode.PARSER_ERROR.ordinal());
            }
            System.out.println("Parsing successful!\n\nSemantic analysis...");

            // Check errors for scope and undeclared variables
            GammaEnv env = new GammaEnv();
            ArrayList<SemanticError> s_errors;
            s_errors = ast.checkSemantics(env);

            if(s_errors.size() > 0){
                for (SemanticError s_error : s_errors) {
                    System.err.println(s_error.toString());
                }
                System.err.println("\n" + s_errors.size() + " Semantic errors found -> Compilation failed.");
                System.exit(ExitCode.SEMANTIC_ERROR.ordinal());
            }
            System.out.println("Semantic analysis successful!\n\nType checking...");

            Node program_type = ast.typeCheck();
            System.out.println("Type checking successful!\n\nProgram type is: " + program_type + "\n\nChecking effects...");

            SigmaEnv s_env = new SigmaEnv(); // Used to check effects
            ast.checkEffects(s_env);
            s_errors = s_env.getErrors();
            if(s_errors.size() > 0){
                for (SemanticError s_error : s_errors) {
                    System.err.println(s_error.toString());
                }
                System.err.println("\n" + s_errors.size() + " Effect errors found -> Compilation failed.");
                System.exit(ExitCode.SEMANTIC_ERROR.ordinal());
            }
            System.out.println("Effects analysis successful! -> Liquidity is respected.\n\nCode generation...");

            String bytecode = ast.codeGeneration();
            System.out.println("Code generation successful!\n\nLaunching interpreter...\n");

            Interpreter.run(bytecode);
            System.exit(ExitCode.SUCCESS.ordinal());

        }catch (Exception exc) {
            System.err.println(exc.getMessage());
            System.exit(ExitCode.RUNTIME_ERROR.ordinal());
        }
    }
}

