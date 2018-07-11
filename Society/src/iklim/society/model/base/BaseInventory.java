package iklim.society.model.base;

import java.util.HashMap;

public class BaseInventory extends AbstractBaseModel {

	private int invenSize;
	private HashMap<String, BaseItem> contents;
	
	public int getInvenSize() {
		return invenSize;
	}
	public void setInvenSize(int invenSize) {
		this.invenSize = invenSize;
	}
	public HashMap<String, BaseItem> getContents() {
		return contents;
	}
	public void setContents(HashMap<String, BaseItem> contents) {
		this.contents = contents;
	}
	
	
}
