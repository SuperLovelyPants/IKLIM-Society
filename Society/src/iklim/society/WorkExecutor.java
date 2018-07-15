package iklim.society;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import iklim.society.model.instance.Work;

public class WorkExecutor {
	private LinkedList<Work>		works;
	
	public void excute() {
		while(!works.isEmpty()) {
			Work w = works.poll();
			Collection<Work> effectWorks = w.execute();
			if(effectWorks!=null && !effectWorks.isEmpty()) {
				works.addAll(effectWorks);
			}
		}
	}
	
	public void addAllWork(Collection<Work> works) {
		this.works.addAll(works);
	}
	
	public void addWork(Work w) {
		this.works.add(w);
	}
}
