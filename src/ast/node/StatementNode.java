package ast.node;

import Semantic.Environment;
import Semantic.SemanticError;

import java.util.ArrayList;

public class StatementNode implements Node {
    private Node statement;

    public StatementNode() {
        this.statement = null;
    }

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
