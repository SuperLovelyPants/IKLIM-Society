package iklim.society.model.base.rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import iklim.society.model.base.AbstractBaseModel;
import iklim.society.model.base.AbstractExecutable;
import iklim.society.model.base.utility.Precondition;
import iklim.society.model.instance.Work;
import iklim.society.model.instance.argument.Argument;

public class Rule extends AbstractExecutable{
	private LinkedList<String>					effectWorks;
	
	private HashMap<String, Parameter>			params;
	private LinkedList<RuleFactor>				factors;
	private Evaluator							evaluator;

	public Rule() {
		effectWorks = new LinkedList<String>();
		params = new HashMap<String, Parameter>();
		factors = new LinkedList<>();
	}
	
	public void putParameter(Parameter param) {
		String name = param.getName();
		params.put(name, param);
	}
	
	public void registerRuleFactor(RuleFactor r) {
		factors.add(r);
	}
	
	public void registerEffectWork(String workId) {
		effectWorks.add(workId);
		// TODO effectWork�� ���� target, worker�� ���� �������� Rule�� worker, Ȥ�� Target ��Ī�Ǵ� ���� ǥ���ؾ� ��
	}
	
	
	public Collection<String> execute(String worker, String target, Argument[] args) {
		for (RuleFactor r : factors) {
			r.evaluate(params.get(r.getTargetParameter()));
		}
		
		evaluator.evaluate(params);
		
		
		return effectWorks;
	}
	

	public LinkedList<String> getEffectWorks() {
		return effectWorks;
	}

	public void setEffectWorks(LinkedList<String> effectWorks) {
		this.effectWorks = effectWorks;
	}

	public HashMap<String, Parameter> getParams() {
		return params;
	}

	public void setParams(HashMap<String, Parameter> params) {
		this.params = params;
	}

	public LinkedList<RuleFactor> getFactors() {
		return factors;
	}

	public void setFactors(LinkedList<RuleFactor> factors) {
		this.factors = factors;
	}

	public Evaluator getEvaluator() {
		return evaluator;
	}

	public void setEvaluator(Evaluator evaluator) {
		this.evaluator = evaluator;
	}
	
}
