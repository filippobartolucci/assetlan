package ast.node.exp;

import Semantic.Environment;
import Semantic.SemanticError;
import ast.node.Node;

import java.util.ArrayList;

public class BaseExpNode extends ExpNode{
    /*Empty Constructor*/
    public BaseExpNode(){super(null);}

    /*Constructor
    * @param exp
    * */
    public BaseExpNode(ExpNode exp){super(exp);}
}
