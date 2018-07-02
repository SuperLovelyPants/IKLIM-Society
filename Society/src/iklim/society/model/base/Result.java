package iklim.society.model.base;

public class Result {
	
	private String targetValue;
	private String evaluate;
	
	public String getTargetValue() {
		return targetValue;
	}
	public void setTargetValue(String targetValue) {
		this.targetValue = targetValue;
	}
	public String getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	
	@Override
	public String toString() {
		return "{ targetValue: " + targetValue + ", evlauate: " + evaluate + " }";
	}
	

}
