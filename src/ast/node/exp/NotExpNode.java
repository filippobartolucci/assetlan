package ast.node.exp;

import ast.node.Node;

public class NotExpNode extends ExpNode {
	public NotExpNode(Node exp) {
		super(exp);
	}

	@Override
	public String toPrint(String indent) {
		return "\n"+indent+"NotExp"+this.exp.toPrint(indent+"");
	}

}
