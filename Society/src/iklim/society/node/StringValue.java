package iklim.society.node;

import iklim.society.visitor.FomularScriptVisitor;

public class StringValue extends ASTValue{

	private String			value;
	
	public StringValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public Object accept(FomularScriptVisitor v) {
		return v.visit(this);
	}
	
	
}
