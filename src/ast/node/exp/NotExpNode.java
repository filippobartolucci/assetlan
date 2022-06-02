package ast.node.exp;
import Utils.TypeValue;
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
		if (!type.equals(TypeValue.BOOL)) {
			throw new RuntimeException("Type mismatch -> expression type "+ type.toPrint("") +" is not bool");
		}
		return type;
	}

	public String codeGeneration() {
		return exp.codeGeneration() + "not $a0 $a0 //not\n";
	}


	@Override
	public int preEvaluation(){
		throw new RuntimeException("Cannot use not operator  in asset expression");
	}
}
