package iklim.society.model.instance.property;

import iklim.society.model.ModelScheme;

public class stringProperty extends PropertyInstance {
	
	public stringProperty(String name, Object value) {
		super(name, ModelScheme.ValueStringValue, (String)value);
	}
	

}
