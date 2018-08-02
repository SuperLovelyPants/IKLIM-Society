package iklim.society.node;

import visitor.FomularScriptVisitor;

public class IntValue extends ASTValue {
	private int value;

	public IntValue(int value) {
		
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public Object accept(FomularScriptVisitor v) {
		return v.visit(this);
	}

}
