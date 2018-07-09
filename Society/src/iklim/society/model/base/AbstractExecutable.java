package iklim.society.model.base;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import iklim.society.model.base.rule.BaseArgument;
import iklim.society.model.base.rule.Rule;
import iklim.society.model.base.utility.Precondition;
import iklim.society.model.instance.Work;
import iklim.society.model.instance.argument.Argument;

public abstract class AbstractExecutable extends AbstractBaseModel {
	
	private LinkedList<Precondition> precondition;
	private HashMap<String,BaseArgument> argument;
	

	public AbstractExecutable() {
		precondition = new LinkedList<Precondition>();
		argument = new HashMap<String, BaseArgument>();
	}
	
	public LinkedList<Precondition> getPrecondition() {
		return precondition;
	}
	
	public void setPrecondition(LinkedList<Precondition> precondition) {
		this.precondition = precondition;
	}

	public void appendArgument(BaseArgument arg) {
		String name = arg.getName();
		argument.put(name, arg);
	}
	
	public HashMap<String, BaseArgument> getArgument() {
		return argument;
	}

	public void setArgument(HashMap<String, BaseArgument> argument) {
		this.argument = argument;
	}

	public boolean isExecutable(String worker, String target, Argument ...args) {
		for(Argument a : args) {
			if(!argument.containsKey(a.getName())) {
				return false;
			}else {
				BaseArgument ba = argument.get(a.getName());
				if(ba.getType() != a.getType()) {
					return false;
				}
			}
		}
		
		for (Precondition p : precondition) {
			if (!p.isValid(worker, target, args)) {
				return false;
			}
		}
		return true;
		
	}
	
	public abstract Collection<String> execute(String worker, String target, Argument ...args);
}
