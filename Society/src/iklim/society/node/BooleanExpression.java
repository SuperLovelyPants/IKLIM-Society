package iklim.society.node;

import iklim.society.visitor.FomularScriptVisitor;

public class BooleanExpression extends ASTExpression {

	private ASTExpression left;
	private ASTExpression right;
	private String operator;

	public BooleanExpression(String operator, ASTExpression left, ASTExpression right) {
		
		
		this.operator = operator;
		this.left = left;
		this.right = right;
	}

	public BooleanExpression() {
	}

	public ASTExpression getLeft() {
		return left;
	}

	public void setLeft(ASTExpression left) {
		this.left = left;
	}

	public ASTExpression getRight() {
		return right;
	}

	public void setRight(ASTExpression right) {
		this.right = right;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Override
	public Object accept(FomularScriptVisitor v) {
		return v.visit(this);
	}

}
