package ast.node;

import Semantic.Environment;
import Semantic.SemanticError;

import java.util.ArrayList;

public class StatementNode implements Node {
    private final Node statement;

    public  StatementNode(Node statement) {
        this.statement = statement;
    }


    @Override
    public String toPrint(String indent) {
        String s = indent + "\tStatementNode\n";
        if (statement != null) {
            s += statement.toPrint(indent+"\t\t");
        }
        return s;
    }

    @Override
    public Node typeCheck() {
        return statement.typeCheck();
    }

    @Override
    public String codeGeneration() {
        return this.statement.codeGeneration();
    }

    @Override
    public ArrayList<SemanticError> checkEffects() {
        return this.statement.checkEffects();
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> errors = new ArrayList<>();

        if (statement != null) {
            errors.addAll(statement.checkSemantics(env));
        }
        return errors;
    }

    public Node getChild() {
        return statement;
    }
}
