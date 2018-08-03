package iklim.society.visitor;

import iklim.society.node.*;


public interface FomularScriptVisitor {
	
	public Object visit(BlockStatement blockStatement);
	public Object visit(IfStatement ifStatement);
	public Object visit(AssignmentStatement assignmentStatement);
	public Object visit(IncrementStatement incrementStatement);
	
	public Object visit(OperationExpression operationExpression);
	public Object visit(ConditionalExpression conditionalExpression);
	public Object visit(BooleanExpression booleanExpression);
	public Object visit(MinusExpression minusExpression);
	public Object visit(NotExpression notExpression);
	
	public Object visit(Variable var);
	public Object visit(BaseVariable baseVar);
	public Object visit(WorkerVariable workerVar);
	public Object visit(TargetVariable targetVar);
	public Object visit(ThisVariable thisVar);

	public Object visit(BooleanValue BooleanValue);
	public Object visit(IntValue intValue);
	public Object visit(StringValue stringValue);
	
	

	

}
