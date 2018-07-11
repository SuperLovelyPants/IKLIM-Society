package iklim.society.model.instance;

import java.util.Collection;
import java.util.HashMap;

import iklim.society.model.ModelScheme;
import iklim.society.model.instance.property.PropertyInstance;

public class PropertySet extends AbstractModelInstance{

	private HashMap<String, PropertyInstance> properties; 
	
	public PropertySet(String id, String type) {
		super(id, type);
		this.properties = new HashMap<String, PropertyInstance>();
	}

	public void addProperty(PropertyInstance pi) {
		this.properties.put(pi.getName(), pi);
	}
	
	public Collection<PropertyInstance> getAllProperties() {
		return properties.values();
	}
	
	public PropertyInstance getProperty(String name) {
		return this.properties.get(name);
	}

}
