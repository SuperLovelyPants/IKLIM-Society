package iklim.society.model.instance;

import iklim.society.model.ModelManager;
import iklim.society.model.ModelScheme;
import iklim.society.model.base.BaseStructure;
import iklim.society.model.base.property.AbstractProperty;
import iklim.society.model.base.property.FloatProperty;
import iklim.society.model.instance.property.FloatPropertyInstance;
import iklim.society.model.instance.property.IntPropertyInstance;
import iklim.society.model.instance.property.PropertyInstance;

import java.util.ArrayList;
import java.util.Map.Entry;

public class Structure extends AbstractModelInstance{
	private final String structureType;
	private int emptyField;
	private int emptyInventory;
	
	private Structure parent;
	private ArrayList<Structure> facilities;
	
	public Structure(String id, String structureType) {
		super(id, "Structure");
		this.structureType = structureType;
		facilities = new ArrayList<Structure>();
		BaseStructure bs = ModelManager.getInstance().getBaseStructure(structureType);
		this.emptyField = bs.fieldSize;
		this.emptyInventory = bs.invenSize;
		this.initializeProperties(bs);
	}
	
	public String getStructureType() {
		return structureType;
	}

	public int getEmptyField() {
		return emptyField;
	}

	public int getEmptyInventory() {
		return emptyInventory;
	}

	public Structure getParent() {
		return parent;
	}

	public void setParent(Structure parent) {
		this.parent = parent;
	}

	public ArrayList<Structure> getFacilities() {
		return facilities;
	}

	public void setFacilities(ArrayList<Structure> facilities) {
		this.facilities = facilities;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[').append(structureType).append(']');
		sb.append(this.getId()).append('\n');
		
		String parent = "world";
		if(this.parent!= null){
			parent = this.parent.getId();
		}
		sb.append("parent : ").append(parent);
		
		sb.append("   facility : ");
		if(facilities.size()!=0){
			for (Structure structure : facilities) {
				sb.append(structure.getId()).append(' ');
			}
		}else{
			sb.append("none");
		}
		sb.append('\n');
		
		sb.append("field : ").append(emptyField);
		
		sb.append("   inven : ").append(emptyInventory);
		sb.append('\n');
		if(this.getPropertyNum()>0){
			for (PropertyInstance pi : this.getProperties()) {
				sb.append(pi.toString()).append('\n');
			}
			
		}
		
		return sb.toString();
	}

	public void addFacility(Structure childStructure) {
		this.facilities.add(childStructure);
		BaseStructure bs = ModelManager.getInstance().getBaseStructure(childStructure.getStructureType());
		this.emptyField -= bs.area;
		
	}
}
