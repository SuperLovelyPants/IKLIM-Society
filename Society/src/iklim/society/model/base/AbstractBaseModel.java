package iklim.society.model.base;

import iklim.society.model.ModelManager;

import java.util.ArrayList;
import java.util.LinkedList;

public class AbstractBaseModel {
	
	private String id;
	private String name;
	//private String description;
	private String parent;
	
	private LinkedList<String> hasProperty;
	private LinkedList<String> initialProperty;
	
	private LinkedList<String> state;

	public boolean isType(String type) {
		if(type.equals(this.id)) {
			return true;
		}
		if(parent !=null) {
			return ModelManager.getInstance().getBaseModel(parent).isType(type);
		}
		return false;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public LinkedList<String> getHasProperty() {
		return hasProperty;
	}

	public void setHasProperty(LinkedList<String> hasProperty) {
		this.hasProperty = hasProperty;
	}

	public LinkedList<String> getInitialProperty() {
		return initialProperty;
	}

	public void setInitialProperty(LinkedList<String> initialProperty) {
		this.initialProperty = initialProperty;
	}

	public LinkedList<String> getState() {
		return state;
	}

	public void setState(LinkedList<String> state) {
		this.state = state;
	}

}
