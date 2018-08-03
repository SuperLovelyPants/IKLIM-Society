package iklim.society.node;

import iklim.society.visitor.FomularScriptVisitor;

public abstract class AST {
	
	public abstract Object accept(FomularScriptVisitor v);
}
