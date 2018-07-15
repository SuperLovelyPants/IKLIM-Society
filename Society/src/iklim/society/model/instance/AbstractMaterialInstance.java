package iklim.society.model.instance;

import java.util.HashMap;
import java.util.LinkedList;

import iklim.society.model.ModelManager;
import iklim.society.model.base.BaseState;

public class AbstractMaterialInstance extends AbstractModelInstance{
	
	private final HashMap<String, PropertySet> hasProperties;
	private final HashMap<String, BaseState> stateProperty;
	private Inventory inventory;
	
	public AbstractMaterialInstance(String id, String type) {
		super(id, type);
		this.hasProperties = new HashMap<String, PropertySet>();
		this.stateProperty = new HashMap<String, BaseState>();
	}
	
	public HashMap<String, BaseState> getStateProperty() {
		return stateProperty;
	}
	
	public void addStateProperty(String stateId) {
		BaseState state = ModelManager.getInstance().getState(stateId);
		stateProperty.put(state.getId(), state);
	}

	public HashMap<String, PropertySet> getHasProperties() {
		return hasProperties;
	}
	
	public void addPropertySet(String propertySetId) {	
		PropertySet ps = ModelManager.getInstance().getPropertyInstance(propertySetId);
		hasProperties.put(ps.getType(), ps);
	}
	
	public void setInventory(String inventoryId) {
		this.inventory = ModelManager.getInstance().getInventoryInstance(inventoryId);
	}
	
	public Inventory getInventory() {
		return this.inventory;
	}

}
