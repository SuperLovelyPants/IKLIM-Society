package iklim.society.node;

import visitor.FomularScriptVisitor;

public class ThisVariable extends BaseVariable{

	public ThisVariable(Variable var) {
		super("this", var);
	}
	@Override
	public Object accept(FomularScriptVisitor v) {
		
		return v.visit(this);
	}

}
