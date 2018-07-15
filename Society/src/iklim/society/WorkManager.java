package iklim.society;

import java.util.Collection;
import java.util.HashMap;

import iklim.society.model.instance.Work;

public class WorkManager {
	
	private HashMap<String, Work>			cyclicWork;
	
	public WorkManager() {
		cyclicWork = new HashMap<String, Work>();
	} 
	
	
	public void putCyclicWork(Work w) {
		cyclicWork.put(w.getId(), w);
	}
	
	public void putAllCyclicWork(Collection<Work> works) {
		for (Work work : works) {
			this.putCyclicWork(work);
		}
	}
	
	
	public Work getCyclicWorkByID(String id) {
		return cyclicWork.get(id);
	}
	
	public Collection<Work>	getAllCyclicWork(){
		return cyclicWork.values();
	}
	
	
	
	

}
