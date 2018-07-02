package iklim.society.model.instance;

import iklim.society.model.ModelManager;
import iklim.society.model.base.BaseAgent;
import iklim.society.model.instance.property.PropertyInstance;

import java.util.ArrayList;
import java.util.Map.Entry;

public class Agent extends AbstractModelInstance {

	private String myWork;
	private final String agentType;

	private int emptyInven;
	private ArrayList<String> inventory;

	public Agent(String id, String agentType) {
		super(id, "Agent");
		this.agentType = agentType;
		BaseAgent ba = ModelManager.getInstance().getBaseAgent(agentType);
		emptyInven = ba.invenSize;
		this.initializeProperties(ba);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[').append(agentType).append(']');
		sb.append(this.getId()).append('\n');

		sb.append("inven : ").append(emptyInven);
		sb.append('\n');

		if (this.getPropertyNum() > 0) {
			for (PropertyInstance pi : this.getProperties()) {
				sb.append(pi.toString()).append('\n');
			}

		}
		return sb.toString();
	}

	public String getMyWork() {
		return myWork;
	}

	public void setMyWork(String myWork) {
		this.myWork = myWork;
	}

	public int getEmptyInven() {
		return emptyInven;
	}

	public void setEmptyInven(int emptyInven) {
		this.emptyInven = emptyInven;
	}

	public ArrayList<String> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<String> inventory) {
		this.inventory = inventory;
	}

	public String getAgentType() {
		return agentType;
	}

}
