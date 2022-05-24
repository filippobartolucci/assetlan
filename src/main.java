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


public class main {
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

            AssetLanLexer lexer = new AssetLanLexer(CharStreams.fromFileName(file));
            CommonTokenStream cts = new CommonTokenStream(lexer);
            AssetLanParser parser = new AssetLanParser(cts);

            AssetLanVisitorImpl visitor = new AssetLanVisitorImpl();
            Node ast = visitor.visitInit(parser.init());

            if (parser.getNumberOfSyntaxErrors()>0) {
                System.err.println("\n" + parser.getNumberOfSyntaxErrors() + " Syntax errors found -> Compilation failed.");
                // PARSER ERROR EXIT CODE
                System.exit(ExitCode.PARSER_ERROR.ordinal());
            }

            System.out.println("Parsing successful!\n\nSemantic analysis...");

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

            Node program_type = ast.typeCheck();;
            System.out.println("Type checking successful!\n\nProgram type is: " + program_type + "\n\nChecking effects...");


            /* Prova max SigmaEnv

            SigmaEnv env1 = new SigmaEnv();
            SigmaEnv env2 = new SigmaEnv();

            env1.newEmptyScope();
            env2.newEmptyScope();

            env1.addDecl("x",new EffectEntry());
            env2.addDecl("x",new EffectEntry());

            env1.addDecl("y",new EffectEntry());
            env2.addDecl("y",new EffectEntry());

            env1.newEmptyScope();
            env2.newEmptyScope();

            env1.addDecl("z",new EffectEntry());
            env2.addDecl("z",new EffectEntry());

            env1.addDecl("w",new EffectEntry());
            env2.addDecl("w",new EffectEntry());

            env1.lookup("x").setTrue();
            env2.lookup("y").setTrue();

            env1.lookup("z").setTrue();
            env2.lookup("w").setTrue();

            System.out.println("Env1\n" + env1.toPrint());
            System.out.println("\nEnv2\n" + env2.toPrint());

            env1.max(env2);
            System.out.println("\nEnv1 Max\n" + env1.toPrint());

            */



            SigmaEnv s_env = new SigmaEnv();
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
            System.out.println("Code generation successful!\n\nBytecode:\n" + bytecode);


            System.exit(ExitCode.SUCCESS.ordinal());


        }catch (Exception exc) {
            System.err.println(exc.getMessage());
            System.exit(ExitCode.RUNTIME_ERROR.ordinal());
        }
    }
}

