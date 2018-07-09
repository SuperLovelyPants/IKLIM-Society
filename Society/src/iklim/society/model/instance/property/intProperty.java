package iklim.society.model.instance.property;

import iklim.society.model.ModelScheme;

public class intProperty extends PropertyInstance {

	private int maxValue;
	private int minValue;
	
	public intProperty(String name, Object value) {
		super(name, ModelScheme.ValueIntValue, (int)value);
	}

	public Object getValue() {
		return (int)super.getValue();
	}
	
	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public int getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}
	
	

}
