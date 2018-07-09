package iklim.society.model.base;

import java.util.HashMap;

public class BaseInventory extends AbstractBaseModel {

	private int invenSize;
	private HashMap<String, BaseItem> items;
	
	public int getInvenSize() {
		return invenSize;
	}
	public void setInvenSize(int invenSize) {
		this.invenSize = invenSize;
	}
	public HashMap<String, BaseItem> getItems() {
		return items;
	}
	public void setItems(HashMap<String, BaseItem> items) {
		this.items = items;
	}
	
	
}
