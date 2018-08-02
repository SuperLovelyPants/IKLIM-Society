package iklim.society.node;

import visitor.FomularScriptVisitor;

public class BaseVariable extends Variable {
	private Variable	var;
	
	public BaseVariable(String name, Variable var) {
		super(name);
		this.var = var;
	}
	
	public BaseVariable(String name) {
		super(name);
		this.var = null;
	}
	
	public Variable getVar() {
		return var;
	}
	public void setVar(Variable var) {
		this.var = var;
	}
	
	@Override
	public Object accept(FomularScriptVisitor v) {
		
		return v.visit(this);
	}

	
}
