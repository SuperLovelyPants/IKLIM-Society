package iklim.society.node;

import iklim.society.visitor.FomularScriptVisitor;

public class OperationExpression extends ASTExpression{
	
	private ASTExpression left;
	private ASTExpression right;
	private String operator;
	
	public OperationExpression(String operator, ASTExpression left, ASTExpression right) {
		this.operator = operator;
		this.left = left;
		this.right = right;
	}
	
	public OperationExpression() {
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
