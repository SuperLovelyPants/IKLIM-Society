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
	private final HashMap<String, String> hasProperties;
	
	private AbstractBaseModel baseModel;
	
	public AbstractModelInstance(String id, String type) {
		this.id = id;
		this.type = type;
		hasProperties = new HashMap<String, String>();
		this.baseModel = ModelManager.getInstance().getItemModel(type);
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

	public int getPropertyNum(){
		return this.hasProperties.size();
	}
	
	public Collection<String> getAllPropertySet(){
		return hasProperties.values();
	}
	
	public String getPropertySet(String key) {
		return hasProperties.get(key);
	}
	
	public void addProperty(String name, String pi) {
		hasProperties.put(name, pi);
	}

	public AbstractBaseModel getBaseModel() {
		return baseModel;
	}

	public void setBaseModel(AbstractBaseModel baseModel) {
		this.baseModel = baseModel;
	}
	
}
