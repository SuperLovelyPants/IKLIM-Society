package iklim.society.model.instance;

import iklim.society.model.ModelManager;
import iklim.society.model.ModelScheme;
import iklim.society.model.base.AbstractBaseModel;
import iklim.society.model.base.property.AbstractProperty;
import iklim.society.model.base.property.FloatProperty;
import iklim.society.model.base.property.IntProperty;
import iklim.society.model.instance.property.FloatPropertyInstance;
import iklim.society.model.instance.property.IntPropertyInstance;
import iklim.society.model.instance.property.PropertyInstance;

import java.util.Collection;
import java.util.HashMap;

public abstract class AbstractModelInstance {
	private final String id;
	private final String type;
	private final HashMap<String, PropertyInstance>		properties;
	
	public AbstractModelInstance(String id, String type) {
		this.id = id;
		this.type =type;
		properties = new HashMap<String, PropertyInstance>();
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
		return this.properties.size();
	}
	
	public Collection<PropertyInstance> getProperties(){
		return properties.values();
	}
	
	

	public PropertyInstance getProperty(String key) {
		return properties.get(key);
		
	}
	protected void initializeProperties(AbstractBaseModel baseModel){
		for(AbstractProperty a : baseModel.properties){
			switch(a.getType()){
			case ModelScheme.ValueFloatValue:
				FloatProperty fp = (FloatProperty)a;
				FloatPropertyInstance fpi = new FloatPropertyInstance(fp.getName(), fp.getInitValue());
				fpi.setMaxValue(fp.getMaxValue());
				fpi.setMinValue(fp.getMinValue());
				this.properties.put(fpi.getName(), fpi);
				break;
			case ModelScheme.ValueIntValue:
				IntProperty ip = (IntProperty)a;
				IntPropertyInstance ipi = new IntPropertyInstance(ip.getName(), ip.getInitValue());
				ipi.setMaxValue(ip.getMaxValue());
				ipi.setMinValue(ip.getMinValue());
				this.properties.put(ipi.getName(), ipi);
			}
		}
		
		
	}
	
}
