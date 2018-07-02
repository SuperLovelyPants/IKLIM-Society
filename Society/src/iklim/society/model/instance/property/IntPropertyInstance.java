package iklim.society.model.instance.property;

import iklim.society.model.ModelScheme;

public class IntPropertyInstance extends PropertyInstance{
	private int					value;
	private int					minValue;
	private int					maxValue;

	public IntPropertyInstance(String name, int value) {
		super(name, ModelScheme.ValueIntValue);
		this.value = value;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getName()).append(" : ").append(value);
		return sb.toString();
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		if(value>maxValue){
			value = maxValue;
		}else if(value<minValue){
			value = minValue;
		}
		this.value = value;
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
