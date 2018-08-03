package iklim.society.node;

import iklim.society.visitor.FomularScriptVisitor;

public class BooleanValue extends ASTValue{
	private boolean	value;
	
	public BooleanValue(boolean value) {
		
		this.value = value;
	}
	

	public boolean getValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}


	@Override
	public Object accept(FomularScriptVisitor v) {
		return v.visit(this);
	}

}
