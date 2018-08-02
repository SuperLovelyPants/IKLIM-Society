package iklim.society.node;

import java.util.LinkedList;

import visitor.FomularScriptVisitor;

public class BlockStatement extends ASTStatement {

	private LinkedList<ASTStatement> Statements;

	public BlockStatement() {
	}

	public LinkedList<ASTStatement> getStatements() {
		return Statements;
	}

	public void setStatements(LinkedList<ASTStatement> statements) {
		Statements = statements;
	}

	@Override
	public Object accept(FomularScriptVisitor v) {
		return v.visit(this);

	}
}
