package iklim.society.model.instance.property;

import iklim.society.model.ModelScheme;

public class FloatPropertyInstance extends PropertyInstance {
	private float					value;
	private float					minValue;
	private float					maxValue;

	public FloatPropertyInstance(String name, float value) {
		super(name, ModelScheme.ValueFloatValue);
		this.value = value;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getName()).append(" : ").append(value);
		return sb.toString();
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		if(value>maxValue){
			value = maxValue;
		}else if(value<minValue){
			value = minValue;
		}
		this.value = value;
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
