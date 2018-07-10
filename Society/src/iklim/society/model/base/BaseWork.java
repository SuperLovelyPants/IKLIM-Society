package iklim.society.model.base;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import iklim.society.model.ModelManager;
import iklim.society.model.base.rule.Rule;
import iklim.society.model.instance.argument.Argument;
import iklim.society.model.instance.argument.IndividualArgument;

public class BaseWork extends AbstractExecutable {
	
	private LinkedList<String> ruleSet;

	public void setRuleSet(LinkedList<String> ruleSet) {
		this.ruleSet = ruleSet;
	}

	public BaseWork() {
		ruleSet = new LinkedList<>();
	}
	
	public LinkedList<String> getRuleSet() {
		return ruleSet;
	}
	@Override
	public Collection<String> execute(String worker, String target, Argument ...args){
		LinkedList<String> tempwork = new LinkedList<String>();
		for (String ruleName : ruleSet) {
			Rule rule = ModelManager.getInstance().getRule(ruleName);
			Collection<String> effectWorks = rule.execute(worker, target, args);
			tempwork.addAll(effectWorks);
		}
		return tempwork;
	}

}