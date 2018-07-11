package iklim.society.model;

import java.util.Collection;
import java.util.HashMap;
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
import iklim.society.model.instance.Inventory;
import iklim.society.model.instance.Item;
import iklim.society.model.instance.PropertySet;
import iklim.society.model.instance.Structure;
import iklim.society.model.instance.property.PropertyInstance;

public class ModelManager {
	private static ModelManager 					instance;
	
	private HashMap<String, Agent>					instanceAgent;
	private HashMap<String, Structure>				instanceStructure;
	private HashMap<String, PropertySet> 			instancePropertySet;
	private HashMap<String, Item>					instanceItem;
	private HashMap<String, Inventory>				instanceInventory;
	
	private HashMap<String, BasePropertySet>		modelPropertySet;
	private HashMap<String, BaseCapabilityProperty>	modelCapabilityProperty;
	private HashMap<String, BaseState>				modelState;
	private HashMap<String, BaseWork>				modelWork;
	private HashMap<String, Rule>					modelRule;
	private HashMap<String, BaseItem>				modelItem;
	private HashMap<String, BaseStructure>			modelStructure;
	private HashMap<String, BaseAgent>				modelAgent;
	
	private ModelManager() {
		
		instanceAgent = new HashMap<String, Agent>();
		instanceStructure = new HashMap<String, Structure>();
		instancePropertySet = new HashMap<String, PropertySet>();
		instanceItem = new HashMap<String, Item>();
		instanceInventory = new HashMap<String, Inventory>();
		
		modelPropertySet = new HashMap<String, BasePropertySet>();
		modelCapabilityProperty = new HashMap<String, BaseCapabilityProperty>();
		modelState = new HashMap<String, BaseState>();
		modelWork = new HashMap<String, BaseWork>();
		modelRule = new HashMap<String, Rule>();
		modelItem = new HashMap<String, BaseItem>();
		modelStructure = new HashMap<String, BaseStructure>();
		modelAgent = new HashMap<String, BaseAgent>();
		
		initialize();
		
	}

	public static synchronized ModelManager getInstance(){
		if(instance==null){
			instance = new ModelManager();
		}
		return instance;
	}
	
	private void initialize() {
		BaseAgent a = new BaseAgent();
		a.setId("Agent");
		a.setParent(null);
		this.addAgentModel(a);
		
		BaseWork w = new BaseWork();
		w.setId("Work");
		w.setParent(null);
		this.addWorkModel(w);
		
		BaseStructure s = new BaseStructure();
		s.setId("Structure");
		s.setParent(null);
		this.addStructureModel(s);
		
		BaseItem i = new BaseItem();
		i.setId("Item");
		i.setParent(null);
		this.addItemModel(i);	
	}

	
	public void addStructureModel(BaseStructure s) {
		modelStructure.put(s.getId(), s);
	}

	public void addWorkModel(BaseWork w) {
		modelWork.put(w.getId(), w);
	}
	
	public void addAgentModel(BaseAgent a) {
		modelAgent.put(a.getId(), a);
	}
	
	public void addItemModel(BaseItem i){
		modelItem.put(i.getId(), i);
	}
	
	public void addPropertySetModel(BasePropertySet ps) {
		modelPropertySet.put(ps.getId(), ps);
	}

	public void addStateModel(BaseState s) {
		modelState.put(s.getId(), s);
	}

	public void addCapabilityPropertyModel(BaseCapabilityProperty cp) {
		modelCapabilityProperty.put(cp.getId(), cp);
	}

	public void addRuleModel(Rule r) {
		modelRule.put(r.getId(), r);
	}

	public Rule getRule(String ruleId) {
		return modelRule.get(ruleId);
	}
	
	public BaseWork getWork(String workId) {
		return modelWork.get(workId);
	}
	
	
	public Collection<BaseWork> getWorks() {
		return modelWork.values();
	}
	
	public Collection<Rule> getRules() {
		return modelRule.values();
	}
	
	
	//getters & setters------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public HashMap<String, Agent> getInstanceAgent() {
		return instanceAgent;
	}

	public void setInstanceAgent(HashMap<String, Agent> instanceAgent) {
		this.instanceAgent = instanceAgent;
	}

	public HashMap<String, Structure> getInstanceStructure() {
		return instanceStructure;
	}

	public void setInstanceStructure(HashMap<String, Structure> instanceStructure) {
		this.instanceStructure = instanceStructure;
	}

	public HashMap<String, PropertySet> getInstancePropertySet() {
		return instancePropertySet;
	}

	public void setInstancePropertySet(HashMap<String, PropertySet> instancePropertySet) {
		this.instancePropertySet = instancePropertySet;
	}

	public HashMap<String, Item> getInstanceItem() {
		return instanceItem;
	}

	public void setInstanceItem(HashMap<String, Item> instanceItem) {
		this.instanceItem = instanceItem;
	}

	public HashMap<String, Inventory> getInstanceInventory() {
		return instanceInventory;
	}

	public void setInstanceInventory(HashMap<String, Inventory> instanceInventory) {
		this.instanceInventory = instanceInventory;
	}

	public HashMap<String, BasePropertySet> getModelPropertySet() {
		return modelPropertySet;
	}

	public void setModelPropertySet(HashMap<String, BasePropertySet> modelPropertySet) {
		this.modelPropertySet = modelPropertySet;
	}

	public HashMap<String, BaseCapabilityProperty> getModelCapabilityProperty() {
		return modelCapabilityProperty;
	}

	public void setModelCapabilityProperty(HashMap<String, BaseCapabilityProperty> modelCapabilityProperty) {
		this.modelCapabilityProperty = modelCapabilityProperty;
	}

	public HashMap<String, BaseState> getModelState() {
		return modelState;
	}

	public void setModelState(HashMap<String, BaseState> modelState) {
		this.modelState = modelState;
	}

	public HashMap<String, BaseWork> getModelWork() {
		return modelWork;
	}

	public void setModelWork(HashMap<String, BaseWork> modelWork) {
		this.modelWork = modelWork;
	}

	public HashMap<String, Rule> getModelRule() {
		return modelRule;
	}

	public void setModelRule(HashMap<String, Rule> modelRule) {
		this.modelRule = modelRule;
	}

	public HashMap<String, BaseItem> getModelItem() {
		return modelItem;
	}

	public void setModelItem(HashMap<String, BaseItem> modelItem) {
		this.modelItem = modelItem;
	}

	public HashMap<String, BaseStructure> getModelStructure() {
		return modelStructure;
	}

	public void setModelStructure(HashMap<String, BaseStructure> modelStructure) {
		this.modelStructure = modelStructure;
	}

	public HashMap<String, BaseAgent> getModelAgent() {
		return modelAgent;
	}

	public void setModelAgent(HashMap<String, BaseAgent> modelAgent) {
		this.modelAgent = modelAgent;
	}
	
}
