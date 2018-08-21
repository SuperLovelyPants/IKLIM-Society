package iklim.society.model.instance.property;

import iklim.society.model.ModelScheme;
import iklim.society.model.instance.AbstractModelInstance;

public class InstanceProperty extends PropertyInstance{
	
	public InstanceProperty(String name, Object value) {
		super(name, ModelScheme.ValueInstance, (AbstractModelInstance)value);
	}

}
