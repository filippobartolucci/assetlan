package ast.node.exp;
import ast.node.Node;

public class NotExpNode extends ExpNode {
	public NotExpNode(Node exp) {
		super(exp);
	}

	public String toPrint(String indent){
		return "\n" + indent + "NotExpNode\t" + exp.toPrint(indent + "\t");
	}

	public Node typeCheck() {
		Node type = exp.typeCheck();
		if (!type.toPrint("").equals("bool")) {
			System.err.println("Type mismatch -> NotExpNode: " + exp.toPrint("") + " is not bool");
			System.exit(1);
		}
		return type;
	}


}
