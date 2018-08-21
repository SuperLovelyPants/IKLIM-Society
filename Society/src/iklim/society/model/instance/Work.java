package iklim.society.model.instance;

import java.util.Collection;

import iklim.society.model.ModelManager;
import iklim.society.model.base.BaseWork;

public class Work extends AbstractModelInstance{
	private final BaseWork workType;
	private final AbstractCapableInstance worker;
	private final AbstractCapableInstance target;
	
	public Work(String id, String workType, String worker, String target) {
		super(id, "Work");
		
		ModelManager manager = ModelManager.getInstance();
		
		this.workType = manager.getWork(workType);
		this.worker = (AbstractCapableInstance)manager.getInstance(worker);
		this.target = (AbstractCapableInstance)manager.getInstance(target);
	}

	public BaseWork getWorkType() {
		return workType;
	}

	public AbstractCapableInstance getWorker() {
		return worker;
	}

	public AbstractCapableInstance getTarget() {
		return target;
	}

	public Collection<Work> execute() {
		
		return null;
	}
	
}
