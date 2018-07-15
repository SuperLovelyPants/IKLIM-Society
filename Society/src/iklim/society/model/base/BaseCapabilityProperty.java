package iklim.society.model.base;

import java.util.HashMap;
import java.util.LinkedList;

import iklim.society.model.ModelManager;

public class BaseCapabilityProperty extends AbstractBaseModel{

	private HashMap<String, BaseWork> capable;
	
	public BaseCapabilityProperty() {
		this.capable = new HashMap<String, BaseWork>();
	}

	public HashMap<String, BaseWork> getCapabilityProperties() {
		return capable;
	}

	public void addCapable(String workId) {
		ModelManager manager = ModelManager.getInstance();
		BaseWork baseWork = manager.getWork(workId);
		this.capable.put(baseWork.getId(), baseWork);
	}
	
	
}
