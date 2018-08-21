package iklim.society.model.base.utility;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import iklim.society.Core;
import iklim.society.model.instance.argument.Argument;
import iklim.society.node.BlockStatement;
import iklim.society.parser.MyNewGrammar;
import iklim.society.visitor.EvaluateVisitor;

public class Precondition {

	private BlockStatement condition;
	
	public boolean isValid(String worker, String target) {
		EvaluateVisitor visitor = EvaluateVisitor.getInstance();
		return (boolean)this.condition.accept(visitor);
	}

	public boolean isValid(String worker, String target, Argument[] args) {
		EvaluateVisitor visitor = EvaluateVisitor.getInstance();
		return (boolean)this.condition.accept(visitor);
	}

	public void setCondition(String condition) {
		
		BlockStatement block = null;
		
		InputStream stream = new ByteArrayInputStream(condition.getBytes(StandardCharsets.UTF_8));
		if(Core.parser == null) {
			Core.parser = new MyNewGrammar(stream);
		} else {
			Core.parser.ReInit(stream);
		}
		
		try {
			block = (BlockStatement)MyNewGrammar.Component();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} catch (Error e) {
			System.out.println(e.getMessage());
		}
		
		this.condition = block;
	}
	
	public void evaluate(EvaluateVisitor v) {
		condition.accept(v);
	}
	
	
	 

}
