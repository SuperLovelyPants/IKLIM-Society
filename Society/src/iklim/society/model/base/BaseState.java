package iklim.society.model.base;

import java.util.LinkedList;

public class BaseState extends AbstractBaseModel {
	
	private LinkedList<String> states;
	private String stateRule;
	
	public LinkedList<String> getStates() {
		return states;
	}
	
	public void setStates(LinkedList<String> states) {
		this.states = states;
	}
	
	public String getStateRule() {
		return stateRule;
	}
	
	public void setStateRule(String stateRule) {
		this.stateRule = stateRule;
	}

}
