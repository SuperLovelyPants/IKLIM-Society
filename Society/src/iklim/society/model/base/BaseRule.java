package iklim.society.model.base;

import java.util.ArrayList;

public class BaseRule extends AbstractBaseModel{

	private ArrayList<String> Argument;
	private String condition;
	private ArrayList<Result> resultSet;
	
	public ArrayList<String> getArgument() {
		return Argument;
	}
	public void setArgument(ArrayList<String> argument) {
		Argument = argument;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public ArrayList<Result> getResultSet() {
		return resultSet;
	}
	public void setResultSet(ArrayList<Result> resultSet) {
		this.resultSet = resultSet;
	}
}
