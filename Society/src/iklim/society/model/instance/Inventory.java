package iklim.society.model.instance;

import java.util.HashMap;

public class Inventory extends AbstractModelInstance {

	private int Size;
	private HashMap<String, Item> contents;
	
	public Inventory(String id, String type) {
		super(id, type);
	}

	public int getSize() {
		return Size;
	}

	public void setSize(int size) {
		Size = size;
	}

	public HashMap<String, Item> getContents() {
		return contents;
	}

	public void setContents(HashMap<String, Item> contents) {
		this.contents = contents;
	}
	
	public void putItem(Item item) {
		this.contents.put(item.getId(), item);
	}
	
	public Item getItem(String itemId) {
		return this.contents.get(itemId);
	}

}
