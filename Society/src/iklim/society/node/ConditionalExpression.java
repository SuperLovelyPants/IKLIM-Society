package iklim.society.node;

import iklim.society.visitor.FomularScriptVisitor;

public class ConditionalExpression extends ASTExpression {

	private BooleanExpression conditionExpression;
	private ASTExpression trueExpression;
	private ASTExpression falseExpression;

	public ConditionalExpression(BooleanExpression condition, ASTExpression trueE, ASTExpression falseE) {
		this.conditionExpression = condition;
		this.trueExpression = trueE;
		this.falseExpression = falseE;
	}
	
	public BooleanExpression getConditionExpression() {
		return conditionExpression;
	}

	public void setConditionExpression(BooleanExpression conditionExpression) {
		this.conditionExpression = conditionExpression;
	}

	public ASTExpression getTrueExpression() {
		return trueExpression;
	}

	public void setTrueExpression(ASTExpression trueExpression) {
		this.trueExpression = trueExpression;
	}

	public ASTExpression getFalseExpression() {
		return falseExpression;
	}

	public void setFalseExpression(ASTExpression falseExpression) {
		this.falseExpression = falseExpression;
	}

	@Override
	public Object accept(FomularScriptVisitor v) {
		return v.visit(this);

	}

}
