package iklim.society.model.base;

import iklim.society.model.ModelManager;
import iklim.society.model.base.property.AbstractProperty;

import java.util.ArrayList;

public class AbstractBaseModel {
	public String id;
	public String name;
	public String description;
	
	public ArrayList<AbstractProperty> properties;
	
	public String parent;

	public boolean isType(String type) {
		if(type.equals(this.id)) {
			return true;
		}
		if(parent !=null) {
			return ModelManager.getInstance().getBaseModel(parent).isType(type);
		}
		return false;
	}
	
	
}
