package iklim.society.model.instance.property;

import iklim.society.model.ModelScheme;

public class floatProperty extends PropertyInstance {
	
	private float maxValue;
	private float minValue;
	
	public floatProperty(String name, Object value) {
		super(name, ModelScheme.ValueFloatValue, (float)value);
	}

	public float getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(float maxValue) {
		this.maxValue = maxValue;
	}

	public float getMinValue() {
		return minValue;
	}

	public void setMinValue(float minValue) {
		this.minValue = minValue;
	}
	
	

}
