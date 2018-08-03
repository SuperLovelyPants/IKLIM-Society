package iklim.society.node;

import iklim.society.visitor.FomularScriptVisitor;

public class Variable extends ASTExpression {
	private String name;
	
	public Variable(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Object accept(FomularScriptVisitor v) {
		return v.visit(this);
	}

}
