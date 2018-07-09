package iklim.society.model.base;

import java.util.LinkedList;

public class AbstractCapable extends AbstractBaseModel{
	private LinkedList<String> workerCapable;
	private LinkedList<String> targetCapable;
	
	public LinkedList<String> getWorkerCapable() {
		return workerCapable;
	}
	
	public void setWorkerCapable(LinkedList<String> workerCapable) {
		this.workerCapable = workerCapable;
	}
	
	public LinkedList<String> getTargetCapable() {
		return targetCapable;
	}
	
	public void setTargetCapable(LinkedList<String> targetCapable) {
		this.targetCapable = targetCapable;
	}
	
	
	
}
