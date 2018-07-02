package iklim.society.model.instance;

import iklim.society.model.ModelManager;
import iklim.society.model.base.AbstractBaseModel;

public class Item extends AbstractModelInstance{
	private final String					itemType;

	public Item(String id, String itemType) {
		super(id, "Item");
		this.itemType = itemType;
		AbstractBaseModel baseModel = ModelManager.getInstance().getItemModel(itemType);
		this.initializeProperties(baseModel);
	}

}
