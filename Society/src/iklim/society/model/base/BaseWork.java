package iklim.society.model.base;

import iklim.society.model.rule.Rule;

import java.util.ArrayList;
import java.util.HashMap;

public class BaseWork extends AbstractBaseModel {
	public String worker;
	public String target;
	private String rule;
	
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	
	
}
