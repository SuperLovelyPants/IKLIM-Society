package iklim.society.model.instance;

import java.util.HashMap;
import java.util.LinkedList;

public class CapableInstance extends AbstractModelInstance{

	private final LinkedList<String> workerCapability;
	private final LinkedList<String> targetCapability;
	
	public CapableInstance(String id, String type) {
		super(id, type);
		workerCapability = new LinkedList<String>();
		targetCapability = new LinkedList<String>();
	}

	public LinkedList<String> getWorkerCapability() {
		return workerCapability;
	}

	public LinkedList<String> getTargetCapability() {
		return targetCapability;
	}
	
	public void addWorkerCapablility(String wc){
		//TODO
	}
	
	public void removeWorkerCapability(String wc) {
		//TODO
	}
	
	public void addTargetCapability(String tc) {
		//TODO
	}
	
	public void removeTargetCapability(String tc) {
		//TODO
	}
	
	public void makeWork() {
		//TODO
	}
	
	
}
