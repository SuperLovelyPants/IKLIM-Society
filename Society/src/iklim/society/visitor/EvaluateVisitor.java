package iklim.society.visitor;

import java.util.LinkedList;

import iklim.society.node.*;

public class EvaluateVisitor implements FomularScriptVisitor {

	@Override
	public Object visit(BlockStatement blockStatement) {
		LinkedList<ASTStatement> states = blockStatement.getStatements();

		for (int i = 0; i < states.size(); i++) {
			states.get(i).accept(this);
		}

		return null;
	}

	@Override
	public Object visit(IfStatement ifStatement) {

		if ((boolean) ifStatement.getCondition().accept(this) == true) {
			ifStatement.getThenStatement().accept(this);
		} else {
			if (ifStatement.getElseStatement() != null) {
				ifStatement.getElseStatement().accept(this);
			}
		}

		return null;
	}

	@Override
	public Object visit(AssignmentStatement assignmentStatement) {
		System.out.println("assign : " + assignmentStatement.getLeft().getName() + " " + assignmentStatement.getRight().accept(this));
		//setVariableValue(assignmentStatement.getLeft().getName(), assignmentStatement.getRight().accept(this));
		return null;
	}

	@Override
	public Object visit(IncrementStatement incrementStatement) {
		if (incrementStatement.getSign()) {
			//setVariableValue(incrementStatement.getVar(), getVariableValue(incrementStatement.getVar()) + 1);
		} else {
			//setVariableValue(incrementStatement.getVar(), getVariableValue(incrementStatement.getVar()) - 1);
		}
		return null;
	}

	@Override
	public Object visit(OperationExpression operationExpression) {

		switch (operationExpression.getOperator()) {
		case "+":
			return ((int) operationExpression.getLeft().accept(this)
					+ (int) operationExpression.getRight().accept(this));
		case "-":
			return ((int) operationExpression.getLeft().accept(this)
					- (int) operationExpression.getRight().accept(this));
		case "*":
			return ((int) operationExpression.getLeft().accept(this)
					* (int) operationExpression.getRight().accept(this));
		case "/":
			return ((int) operationExpression.getLeft().accept(this)
					/ (int) operationExpression.getRight().accept(this));
		case "%":
			return ((int) operationExpression.getLeft().accept(this)
					% (int) operationExpression.getRight().accept(this));
		}

		return operationExpression.getLeft().accept(this);
	}

	@Override
	public Object visit(ConditionalExpression conditionalExpression) {

		if ((boolean) conditionalExpression.getConditionExpression().accept(this)) {
			conditionalExpression.getTrueExpression().accept(this);
		} else {
			conditionalExpression.getFalseExpression().accept(this);
		}

		return null;
	}

	@Override
	public Object visit(BooleanExpression booleanExpression) {
		switch (booleanExpression.getOperator()) {
		case "||":
			if ((boolean) booleanExpression.getLeft().accept(this)
					|| (boolean) booleanExpression.getRight().accept(this)) {
				return true;
			} else {
				return false;
			}
		case "&&":
			if ((boolean) booleanExpression.getLeft().accept(this)
					&& (boolean) booleanExpression.getRight().accept(this)) {
				return true;
			} else {
				return false;
			}
		case "==":

			if (booleanExpression.getLeft().accept(this) == booleanExpression.getRight().accept(this)) {
				return true;
			} else {
				return false;
			}

			// if(booleanExpression.getLeft() instanceof OperationExpression ||
			// booleanExpression.getLeft() instanceof Variable ||
			// booleanExpression.getLeft() instanceof IntValue) {
			// if((int)booleanExpression.getLeft().accept(this) ==
			// (int)booleanExpression.getRight().accept(this)) {
			// return true;
			// } else {
			// return false;
			// }
			// } else if(booleanExpression.getLeft() instanceof BooleanExpression ||
			// booleanExpression.getLeft() instanceof BooleanValue) {
			// if((boolean)booleanExpression.getLeft().accept(this) ==
			// (boolean)booleanExpression.getRight().accept(this)) {
			// return true;
			// } else {
			// return false;
			// }
			// }
		case "!=":
			if (booleanExpression.getLeft().accept(this) != booleanExpression.getRight().accept(this)) {
				return true;
			} else {
				return false;
			}
		case "<":
			if ((int)booleanExpression.getLeft().accept(this) < (int)booleanExpression.getRight().accept(this)) {
				return true;
			} else {
				return false;
			}
		case ">":
			if ((int)booleanExpression.getLeft().accept(this) > (int)booleanExpression.getRight().accept(this)) {
				return true;
			} else {
				return false;
			}
		case "<=":
			if ((int)booleanExpression.getLeft().accept(this) <= (int)booleanExpression.getRight().accept(this)) {
				return true;
			} else {
				return false;
			}
		case ">=":
			if ((int)booleanExpression.getLeft().accept(this) >= (int)booleanExpression.getRight().accept(this)) {
				return true;
			} else {
				return false;
			}
		}
		return booleanExpression.getLeft().accept(this);
	}

	@Override
	public Object visit(MinusExpression minusExpression) {
		return (int)minusExpression.getExpression().accept(this) *-1;
	}

	@Override
	public Object visit(NotExpression notExpression) {
		return !(boolean)notExpression.getExpression().accept(this);
	}

	@Override
	public Object visit(Variable var) {
		//TODO
		return null;
	}

	@Override
	public Object visit(BaseVariable baseVar) {
		//TODO
		return null;
	}

	@Override
	public Object visit(WorkerVariable workerVar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(TargetVariable targetVar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ThisVariable thisVar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(BooleanValue booleanValue) {
		return booleanValue.getValue();
	}

	@Override
	public Object visit(IntValue intValue) {
		return intValue.getValue();
	}

	@Override
	public Object visit(StringValue stringValue) {
		return stringValue.getValue();
	}

}
