package iklim.society.node;

import iklim.society.visitor.FomularScriptVisitor;

public class AssignmentStatement extends ASTStatement {
	private Variable left;
	private ASTExpression right;

	public AssignmentStatement(String operator, Variable left, ASTExpression right) {
		
		this.left = left;
		if(operator.equals("=")) {
			this.right = right;
		}else if(operator.contains("*=")) {
			right = new OperationExpression("*", left, right);
		}else if(operator.contains("/=")) {
			right = new OperationExpression("/", left, right);
		}else if(operator.contains("%=")) {
			right = new OperationExpression("%", left, right);
		}else if(operator.contains("+=")) {
			right = new OperationExpression("+", left, right);
		}else if(operator.contains("-=")) {
			right = new OperationExpression("-", left, right);
		}
	}

	public Variable getLeft() {
		return left;
	}

	public void setLeft(Variable left) {
		this.left = left;
	}

	public ASTExpression getRight() {
		return right;
	}

	public void setRight(ASTExpression right) {
		this.right = right;
	}

	@Override
	public Object accept(FomularScriptVisitor v) {
		return v.visit(this);
	}

}
