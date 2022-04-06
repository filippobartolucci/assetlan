package ast.node;

public class StatementNode implements Node {
    private StatementNode statement;

    public StatementNode() {
        this.statement = null;
    }

    public  StatementNode(StatementNode statement) {
        this.statement = statement;
    }


}
