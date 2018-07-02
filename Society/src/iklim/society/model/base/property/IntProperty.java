package iklim.society.model.base.property;

import iklim.society.model.ModelScheme;

public class IntProperty extends AbstractProperty{
	private final int initValue;
	private int minValue;
	private int maxValue;
	
	public IntProperty(String name, int initValue){
		super(name,ModelScheme.ValueIntValue);
		this.initValue = initValue;
	}

	public int getInitValue() {
		return initValue;
	}

	public int getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
	
}
