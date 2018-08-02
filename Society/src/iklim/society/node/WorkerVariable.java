package iklim.society.node;

import visitor.FomularScriptVisitor;

public class WorkerVariable extends BaseVariable {
	
	public WorkerVariable(Variable var) {
		super("Worker", var);
	}
	
	@Override
	public Object accept(FomularScriptVisitor v) {
		return v.visit(this);
	}
}
