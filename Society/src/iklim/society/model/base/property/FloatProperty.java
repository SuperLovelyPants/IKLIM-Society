package iklim.society.model.base.property;

import iklim.society.model.ModelScheme;

public class FloatProperty extends AbstractProperty{
	private final float initValue;
	private float minValue;
	private float maxValue;
	public FloatProperty(String name, float initValue){
		super(name,ModelScheme.ValueFloatValue);
		this.initValue = initValue;
	}

	public float getInitValue() {
		return initValue;
	}

	public float getMinValue() {
		return minValue;
	}

	public void setMinValue(float minValue) {
		this.minValue = minValue;
	}

	public float getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(float maxValue) {
		this.maxValue = maxValue;
	}
	
	
}
