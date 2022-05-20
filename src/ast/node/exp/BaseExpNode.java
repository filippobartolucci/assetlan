package ast.node.exp;

import ast.node.Node;

public class BaseExpNode extends ExpNode{
    private Node exp;

    /*
     * Constructor
    */
    public BaseExpNode(ExpNode exp){super(exp);}
}
