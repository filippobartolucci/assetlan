package ast.node.exp;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.Node;

import java.util.ArrayList;

public class BaseExpNode extends ExpNode{
    private Node exp;
    public BaseExpNode(Node exp){
        this.exp = exp;
    }


    /*Constructor
    * @param exp
    * */
    public BaseExpNode(ExpNode exp){super(exp);}
}
