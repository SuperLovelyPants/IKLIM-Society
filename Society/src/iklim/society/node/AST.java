package iklim.society.node;

import visitor.FomularScriptVisitor;

public abstract class AST {
	
	public abstract Object accept(FomularScriptVisitor v);
}
