package ast.node;

import Semantic.Environment;
import Semantic.SemanticError;

import java.util.ArrayList;

public class StatementNode implements Node {
    private StatementNode statement;

    public StatementNode() {
        this.statement = null;
    }

    public  StatementNode(StatementNode statement) {
        this.statement = statement;
    }


    @Override
    public String toPrint(String indent) {
        return null;
    }

    @Override
    public Node typeCheck() {
        return null;
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return null;
    }
}
