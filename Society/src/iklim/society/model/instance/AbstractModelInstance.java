package iklim.society.model.instance;

import iklim.society.model.ModelManager;
import iklim.society.model.ModelScheme;
import iklim.society.model.base.AbstractBaseModel;
import iklim.society.model.instance.property.PropertyInstance;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class AbstractModelInstance {
	
	private final String id;
	private final String type;
	
	public AbstractModelInstance(String id, String type) {
		this.id = id;
		this.type = type;
	}
	
	public boolean isType(String type) {
		if(this.type.equals(type)) {
			return true;
		} 
		
		return ModelManager.getInstance().getBaseModel(this.type).isType(type);
	}
	
	
	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public AbstractBaseModel getBaseModel() {
		return ModelManager.getInstance().getBaseModel(type);
	}

}
