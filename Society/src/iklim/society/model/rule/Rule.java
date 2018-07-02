package iklim.society.model.rule;

import java.util.HashMap;

public class Rule {
	private String id;
	private HashMap<String, Integer> constantValues;
	private Evaluate value;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public HashMap<String, Integer> getConstantValues() {
		return constantValues;
	}
	public void setConstantValues(HashMap<String, Integer> constantValues) {
		this.constantValues = constantValues;
	}
	public Evaluate getValue() {
		return value;
	}
	public void setValue(Evaluate value) {
		this.value = value;
	}
	
}
