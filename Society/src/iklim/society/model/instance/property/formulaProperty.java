package iklim.society.model.instance.property;

import iklim.society.model.ModelScheme;

public class formulaProperty extends PropertyInstance{

	public formulaProperty(String name, Object value) {
		super(name, ModelScheme.ValueFormulaValue, (String)value);
	}
	
}
