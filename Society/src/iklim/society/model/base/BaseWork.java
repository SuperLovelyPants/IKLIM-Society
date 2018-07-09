package iklim.society.model.base;

import java.util.Collection;
import java.util.LinkedList;

import iklim.society.model.base.rule.Rule;
import iklim.society.model.instance.argument.Argument;

public class BaseWork extends AbstractExecutable {
	
	private LinkedList<Rule> ruleSet;
	

	public BaseWork() {
		ruleSet = new LinkedList<>();
	}
	
	public LinkedList<Rule> getRuleSet() {
		return ruleSet;
	}
	@Override
	public Collection<String> execute(String worker, String target, Argument ...args){
		LinkedList<String> tempwork = new LinkedList<String>();
		for (Rule rule : ruleSet) {
			Collection<String> effectWorks = rule.execute(worker, target, args);
			tempwork.addAll(effectWorks);
		}
		return tempwork;
	}

}