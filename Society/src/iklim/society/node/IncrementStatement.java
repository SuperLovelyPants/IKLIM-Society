package iklim.society.node;

import iklim.society.visitor.FomularScriptVisitor;

public class IncrementStatement extends ASTStatement{
	private boolean sign;
	private Variable var;
	
	public IncrementStatement(String sign, Variable var) {
		
		if(sign.equals("++")) {
			this.sign = true;
		}else if(sign.equals("--")) {
			this.sign = false;
		}
		this.var = var;
	}

	@Override
	public Object accept(FomularScriptVisitor v) {
		return v.visit(this);
	}

	public boolean getSign() {
		return sign;
	}

	public void setSign(boolean sign) {
		this.sign = sign;
	}

	public Variable getVar() {
		return var;
	}

	public void setVar(Variable var) {
		this.var = var;
	}

}
