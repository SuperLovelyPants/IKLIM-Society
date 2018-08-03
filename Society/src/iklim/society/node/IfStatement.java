package iklim.society.node;

import iklim.society.visitor.FomularScriptVisitor;

public class IfStatement extends ASTStatement {

	private ASTExpression condition;
	private ASTStatement thenStatement;
	private ASTStatement elseStatement;

	public IfStatement(ASTExpression cond, ASTStatement then, ASTStatement els) {
		this.condition = cond;
		this.thenStatement = then;
		this.elseStatement = els;
	}
	
	public IfStatement(ASTExpression cond, ASTStatement then) {
		this.condition = cond;
		this.thenStatement = then;
		this.elseStatement = null;
	}


	public ASTExpression getCondition() {
		return condition;
	}

	public void setCondition(ASTExpression condition) {
		this.condition = condition;
	}

	public ASTStatement getThenStatement() {
		return thenStatement;
	}

	public void setThenStatement(ASTStatement thenStatement) {
		this.thenStatement = thenStatement;
	}

	public ASTStatement getElseStatement() {
		return elseStatement;
	}

	public void setElseStatement(ASTStatement elseStatment) {
		this.elseStatement = elseStatment;
	}

	@Override
	public Object accept(FomularScriptVisitor v) {
		return v.visit(this);

	}

}
