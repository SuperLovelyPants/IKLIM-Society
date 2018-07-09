package iklim.society.model;

import iklim.society.model.base.*;
import iklim.society.model.base.property.*;
import iklim.society.model.instance.*;
import iklim.society.model.instance.property.*;

import java.util.HashMap;
import java.util.Map.Entry;

public class ModelManager {
	private static ModelManager 			instance;
	
	private HashMap<String, AbstractBaseModel> staticBaseModel;
	
	private HashMap<String, Agent>			instanceAgentModel;
	private HashMap<String, Structure>		instanceStructureModel;
	private HashMap<String, PropertyInstance> instanceProperty;
	
	private ModelManager() {
		staticBaseModel = new HashMap<String, AbstractBaseModel>();
		
		instanceAgentModel = new HashMap<String, Agent>();
		instanceStructureModel = new HashMap<String, Structure>();
		instanceProperty = new HashMap<String, PropertyInstance>();
		
		initialize();
		
	}
	
	private void initialize() {
//		BaseAgent a = new BaseAgent();
//		a.id = "Agent";
//		a.parent = null;
//		this.addAgentModel(a);
//		
//		BaseWork w = new BaseWork();
//		w.id = "Work";
//		w.parent = null;
//		this.addWorkModel(w);
//		
//		BaseStructure s = new BaseStructure();
//		s.id = "Structure";
//		s.parent = null;
//		this.addStructureModel(s);
//		
//		BaseItem i = new BaseItem();
//		i.id = "Item";
//		i.parent = null;
//		this.addItemModel(i);
		
	}

	public static synchronized ModelManager getInstance(){
		if(instance==null){
			instance = new ModelManager();
		}
		return instance;
	}

	public void addStructureModel(BaseStructure s) {
		staticBaseModel.put(s.id, s);
	}

	public void addWorkModel(BaseWork w) {
		staticBaseModel.put(w.id, w);
	}
	
	public void addAgentModel(BaseAgent a) {
		staticBaseModel.put(a.id, a);
	}
	
	public void addItemModel(BaseItem i){
		staticBaseModel.put(i.id, i);
	}
	
	public void addStructureInstance(String id, String type) {
		Structure s = new Structure(id, type);
		instanceStructureModel.put(id, s);
	}
	
	public void addAgentInstance(String id, String type) {
		Agent a = new Agent(id, type);
		instanceAgentModel.put(id, a);
	}
	
	public void addPropertyInstance(String id, String type) {
		
	}

	public void setFacility(String child, String parent) {
		Structure parentStructure = instanceStructureModel.get(parent);
		Structure childStructure = instanceStructureModel.get(child);
		parentStructure.addFacility(childStructure);
		childStructure.setParent(parentStructure);
	}

	public void printInstance() {
		StringBuilder sb = new StringBuilder();
		sb.append("--Agent--\n");
		
		for (Entry<String, Agent> entry : instanceAgentModel.entrySet()) {
			sb.append(entry.getValue()).append("\n");
		}
		sb.append("\n--Structure--\n");
		for (Entry<String, Structure> entry : instanceStructureModel.entrySet()) {
			sb.append(entry.getValue()).append("\n");
		}
		System.out.println(sb.toString());
	}

	public AbstractModelInstance get(String key) {
		if(instanceAgentModel.containsKey(key)){
			return this.getAgent(key);
		}
		if(instanceStructureModel.containsKey(key)){
			return this.getStructure(key);
		}
		return null;
	}

	public Structure getStructure(String key) {
		return instanceStructureModel.get(key);
	}

	public Agent getAgent(String key) {
		return instanceAgentModel.get(key);
	}

	public BaseAgent getBaseAgent(String agentType) {
		return (BaseAgent)this.getBaseModel(agentType);
	}

	public BaseStructure getBaseStructure(String structureType) {
		return (BaseStructure)this.getBaseModel(structureType);
	}

	public BaseWork getBaseWork(String workType) {
		return (BaseWork)this.getBaseModel(workType);
	}
	
	public AbstractBaseModel getBaseModel(String type) {
		return staticBaseModel.get(type);
	}

	public AbstractBaseModel getItemModel(String itemType) {
		return (BaseItem)this.getBaseModel(itemType);
	}
}
