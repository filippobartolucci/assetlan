package ast.node;

import ast.node.exp.BaseExpNode;

public class FieldNode implements Node{
	private String id;
	private TypeNode type;
	private BaseExpNode value;

	public FieldNode(String id, TypeNode type, BaseExpNode value) {
		this.id = id;
		this.type = type;
		this.value = value;
	}
}
