package iklim.society.model.instance;

import iklim.society.model.ModelManager;
import iklim.society.model.base.BaseWork;

public class Work extends AbstractModelInstance{
	private final String workType;
	private final String worker;
	private final String target;
	
	public Work(String id, String workType, String worker, String target) {
		super(id, "Work");
		this.workType = workType;
		this.worker = worker;
		this.target = target;
	}

	public String getWorkType() {
		return workType;
	}

	public String getWorker() {
		return worker;
	}

	public String getTarget() {
		return target;
	}
	
	
}
