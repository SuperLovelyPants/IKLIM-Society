package iklim.society.model.instance;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import iklim.society.model.ModelManager;
import iklim.society.model.base.BaseCapabilityProperty;
import iklim.society.model.instance.property.PropertyInstance;

public class AbstractCapableInstance extends AbstractMaterialInstance{

	private final HashMap<String, BaseCapabilityProperty> workerCapability;
	private final HashMap<String, BaseCapabilityProperty> targetCapability;
	
	public AbstractCapableInstance(String id, String type) {
		super(id, type);
		
		workerCapability = new HashMap<String, BaseCapabilityProperty>();
		targetCapability = new HashMap<String, BaseCapabilityProperty>();
	}

	public HashMap<String, BaseCapabilityProperty> getWorkerCapability() {
		return workerCapability;
	}

	public HashMap<String, BaseCapabilityProperty> getTargetCapability() {
		return targetCapability;
	}

	public void addWorkerCapability(String capabilityId) {
		BaseCapabilityProperty baseCapabilityProperty = ModelManager.getInstance().getCapabilityProperty(capabilityId);
		workerCapability.put(baseCapabilityProperty.getId(), baseCapabilityProperty);
	}
	
	public void addTargetCapability(String capabilityId) {
		BaseCapabilityProperty baseCapabilityProperty = ModelManager.getInstance().getCapabilityProperty(capabilityId);
		targetCapability.put(baseCapabilityProperty.getId(), baseCapabilityProperty);
	}

	public PropertySet getProperty(String name) {
		Collection<PropertySet> properties = this.getHasProperties().values();
		for (PropertySet propertySet : properties) {
			if(propertySet.getType().equals(name)) {
				return propertySet;
			}
		}
		
		return null;
	}
	
	
	
	
}
