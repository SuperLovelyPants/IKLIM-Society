package iklim.society.node;

import iklim.society.visitor.FomularScriptVisitor;

public class TargetVariable extends BaseVariable{
	
	public TargetVariable(Variable var) {
		super("Target", var);
	}
	
	@Override
	public Object accept(FomularScriptVisitor v) {
		return v.visit(this);
	}
	

}
