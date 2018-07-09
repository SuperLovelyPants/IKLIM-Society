package iklim.society.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import iklim.society.model.base.AbstractBaseModel;
import iklim.society.model.base.BaseAgent;
import iklim.society.model.base.BaseCapabilityProperty;
import iklim.society.model.base.BaseItem;
import iklim.society.model.base.BasePropertySet;
import iklim.society.model.base.BaseState;
import iklim.society.model.base.BaseStructure;
import iklim.society.model.base.BaseWork;
import iklim.society.model.base.rule.Rule;
import iklim.society.model.instance.AbstractModelInstance;
import iklim.society.model.instance.Agent;
import iklim.society.model.instance.Structure;
import iklim.society.model.instance.property.PropertyInstance;

public class ModelManager {
	private static ModelManager 			instance;
	
	private HashMap<String, AbstractBaseModel> staticBaseModel;
	
	private HashMap<String, Agent>			instanceAgentModel;
	private HashMap<String, Structure>		instanceStructureModel;
	private HashMap<String, PropertyInstance> instanceProperty;
	
	private HashMap<String, Rule> ruleModel;
	private HashMap<String, LinkedList<String>> ruleEffectWorkManager;
	
	private ModelManager() {
		staticBaseModel = new HashMap<String, AbstractBaseModel>();
		
		instanceAgentModel = new HashMap<String, Agent>();
		instanceStructureModel = new HashMap<String, Structure>();
		instanceProperty = new HashMap<String, PropertyInstance>();
		
		initialize();
		
	}
	
	
	
	public HashMap<String, LinkedList<String>> getRuleEffectWorkManager() {
		return ruleEffectWorkManager;
	}

	public void setRuleEffectWorkManager(HashMap<String, LinkedList<String>> ruleEffectWorkManager) {
		this.ruleEffectWorkManager = ruleEffectWorkManager;
	}

	public HashMap<String, Rule> getRuleModel() {
		return ruleModel;
	}

	public void setRuleModel(HashMap<String, Rule> ruleModel) {
		this.ruleModel = ruleModel;
	}
	
	public Collection<Rule> getRules() {
		return ruleModel.values();
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
		staticBaseModel.put(s.getId(), s);
	}

	public void addWorkModel(BaseWork w) {
		staticBaseModel.put(w.getId(), w);
	}
	
	public void addAgentModel(BaseAgent a) {
		staticBaseModel.put(a.getId(), a);
	}
	
	public void addItemModel(BaseItem i){
		staticBaseModel.put(i.getId(), i);
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

	public void addPropertySetModel(BasePropertySet buildPropertySetModel) {
		// TODO Auto-generated method stub
		
	}

	public void addStateModel(BaseState buildStateModel) {
		// TODO Auto-generated method stub
		
	}

	public void addCapabilityPropertyModel(BaseCapabilityProperty buildCapabilityPropertyModel) {
		// TODO Auto-generated method stub
		
	}

	public void addRuleModel(Rule buildRuleModel) {
		// TODO Auto-generated method stub
		
	}
}
