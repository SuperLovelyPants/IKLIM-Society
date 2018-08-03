package iklim.society.node;

import iklim.society.visitor.FomularScriptVisitor;

public class NotExpression extends ASTExpression {

	ASTExpression expression;
	
	public NotExpression(ASTExpression expression) {
		this.expression = expression;
	}
	
	public ASTExpression getExpression() {
		return expression;
	}

	public void setExpression(ASTExpression expression) {
		this.expression = expression;
	}

	@Override
	public Object accept(FomularScriptVisitor v) {
		return v.visit(this);
	}

}
