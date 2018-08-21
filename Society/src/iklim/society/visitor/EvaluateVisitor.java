package iklim.society.visitor;

import java.util.LinkedList;

import iklim.society.model.base.rule.Rule;
import iklim.society.model.instance.AbstractCapableInstance;
import iklim.society.model.instance.AbstractModelInstance;
import iklim.society.model.instance.Agent;
import iklim.society.model.instance.PropertySet;
import iklim.society.model.instance.property.PropertyInstance;
import iklim.society.model.instance.property.IntProperty;
import iklim.society.node.*;

public class EvaluateVisitor implements FomularScriptVisitor {

	private static EvaluateVisitor evaluateVisitorInstance;

	private AbstractCapableInstance worker;
	private AbstractCapableInstance target;
	private Rule rule;
	private Object currentVariable;

	public static synchronized EvaluateVisitor getInstance() {
		if (evaluateVisitorInstance == null) {
			evaluateVisitorInstance = new EvaluateVisitor();
		}
		return evaluateVisitorInstance;
	}

	public AbstractCapableInstance getWorker() {
		return worker;
	}

	public void setWorker(AbstractCapableInstance worker) {
		this.worker = worker;
	}

	public AbstractCapableInstance getTarget() {
		return target;
	}

	public void setTarget(AbstractCapableInstance target) {
		this.target = target;
	}

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
		System.out.println("assign : " + assignmentStatement.getLeft().getName() + " "
				+ assignmentStatement.getRight().accept(this));
		// setVariableValue(assignmentStatement.getLeft().getName(),
		// assignmentStatement.getRight().accept(this));
		return null;
	}

	@Override
	public Object visit(IncrementStatement incrementStatement) {
		if (incrementStatement.getSign()) {
			// setVariableValue(incrementStatement.getVar(),
			// getVariableValue(incrementStatement.getVar()) + 1);
		} else {
			// setVariableValue(incrementStatement.getVar(),
			// getVariableValue(incrementStatement.getVar()) - 1);
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
			if ((int) booleanExpression.getLeft().accept(this) < (int) booleanExpression.getRight().accept(this)) {
				return true;
			} else {
				return false;
			}
		case ">":
			if ((int) booleanExpression.getLeft().accept(this) > (int) booleanExpression.getRight().accept(this)) {
				return true;
			} else {
				return false;
			}
		case "<=":
			if ((int) booleanExpression.getLeft().accept(this) <= (int) booleanExpression.getRight().accept(this)) {
				return true;
			} else {
				return false;
			}
		case ">=":
			if ((int) booleanExpression.getLeft().accept(this) >= (int) booleanExpression.getRight().accept(this)) {
				return true;
			} else {
				return false;
			}
		}
		return booleanExpression.getLeft().accept(this);
	}

	@Override
	public Object visit(MinusExpression minusExpression) {
		return (int) minusExpression.getExpression().accept(this) * -1;
	}

	@Override
	public Object visit(NotExpression notExpression) {
		return !(boolean) notExpression.getExpression().accept(this);
	}

	@Override
	public Object visit(Variable var) {

		PropertyInstance property = null;

		if (currentVariable instanceof PropertySet) {
			property = ((PropertySet) currentVariable).getProperty(var.getName());
		}

		if (property != null) {
			System.out.println(property.getValue());
			return property.getValue();
		} else {
			return null;
		}
	}

	@Override
	public Object visit(BaseVariable baseVar) {

		if (baseVar.getName().equals("targetValue")) {
			return rule.getTargetValue().accept(this);
		}
		
		if (baseVar.getName().equals("worker")) {
			currentVariable = worker;
		} else if (baseVar.getName().equals("target")) {
			currentVariable = target;
		} else if (baseVar.getName().equals("this")) {
			currentVariable = rule;
		}  else if (currentVariable instanceof AbstractCapableInstance) {
			currentVariable = ((AbstractCapableInstance) currentVariable).getProperty(baseVar.getName());
		} else if (currentVariable instanceof Rule) {
			currentVariable = ((Rule) currentVariable).getArgument().get(baseVar.getName());
		} else if (currentVariable instanceof PropertySet) {
			currentVariable = ((PropertySet) currentVariable).getProperty(baseVar.getName());
		}

		return baseVar.getVar().accept(this);
	}

	@Override
	public Object visit(WorkerVariable workerVar) {
		currentVariable = this.worker;
		workerVar.getVar().accept(this);
		return null;
	}

	@Override
	public Object visit(TargetVariable targetVar) {
		currentVariable = this.target;
		targetVar.getVar().accept(this);
		return null;
	}

	@Override
	public Object visit(ThisVariable thisVar) {
		currentVariable = this.rule;
		thisVar.getVar().accept(this);
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
