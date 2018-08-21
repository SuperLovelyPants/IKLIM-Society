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
	
	private HashMap<String, AbstractModelInstance>	instanceAll;
	
	private HashMap<String, BasePropertySet>		modelPropertySet;
	private HashMap<String, BaseCapabilityProperty>	modelCapabilityProperty;
	private HashMap<String, BaseState>				modelState;
	private HashMap<String, BaseWork>				modelWork;
	private HashMap<String, Rule>					modelRule;
	private HashMap<String, BaseItem>				modelItem;
	private HashMap<String, BaseStructure>			modelStructure;
	private HashMap<String, BaseAgent>				modelAgent;
	
	private HashMap<String, AbstractBaseModel>		modelAll;
	
	private ModelManager() {
		
		instanceAgent = new HashMap<String, Agent>();
		instanceStructure = new HashMap<String, Structure>();
		instancePropertySet = new HashMap<String, PropertySet>();
		instanceItem = new HashMap<String, Item>();
		instanceInventory = new HashMap<String, Inventory>();
		
		instanceAll = new HashMap<String, AbstractModelInstance>();
		
		modelPropertySet = new HashMap<String, BasePropertySet>();
		modelCapabilityProperty = new HashMap<String, BaseCapabilityProperty>();
		modelState = new HashMap<String, BaseState>();
		modelWork = new HashMap<String, BaseWork>();
		modelRule = new HashMap<String, Rule>();
		modelItem = new HashMap<String, BaseItem>();
		modelStructure = new HashMap<String, BaseStructure>();
		modelAgent = new HashMap<String, BaseAgent>();
		
		modelAll = new HashMap<String, AbstractBaseModel>();
		
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
	
	public void addAgentInstance(Agent agent) {
		instanceAgent.put(agent.getId(), agent);
		instanceAll.put(agent.getId(), agent);
	}
	
	public void addStructureInstance(Structure structure) {
		instanceStructure.put(structure.getId(), structure);
		instanceAll.put(structure.getId(), structure);
	}
	
	public void addPropertySetInstance(PropertySet propertySet) {
		instancePropertySet.put(propertySet.getId(), propertySet);
		instanceAll.put(propertySet.getId(), propertySet);
	}
	
	public void addItemInstance(Item item) {
		instanceItem.put(item.getId(), item);
		instanceAll.put(item.getId(), item);
	}
	
	public void addInventoryInstance(Inventory inventory) {
		instanceInventory.put(inventory.getId(), inventory);
		instanceAll.put(inventory.getId(), inventory);
	}
	
	public AbstractBaseModel getBaseModel(String key) {
		return modelAll.get(key);
	}
	
	public void addStructureModel(BaseStructure s) {
		modelStructure.put(s.getId(), s);
		modelAll.put(s.getId(), s);
	}

	public void addWorkModel(BaseWork w) {
		modelWork.put(w.getId(), w);
		modelAll.put(w.getId(), w);
	}
	
	public void addAgentModel(BaseAgent a) {
		modelAgent.put(a.getId(), a);
		modelAll.put(a.getId(), a);
	}
	
	public void addItemModel(BaseItem i){
		modelItem.put(i.getId(), i);
		modelAll.put(i.getId(), i);
	}
	
	public void addPropertySetModel(BasePropertySet ps) {
		modelPropertySet.put(ps.getId(), ps);
		modelAll.put(ps.getId(), ps);
	}

	public void addStateModel(BaseState s) {
		modelState.put(s.getId(), s);
		modelAll.put(s.getId(), s);
	}

	public void addCapabilityPropertyModel(BaseCapabilityProperty cp) {
		modelCapabilityProperty.put(cp.getId(), cp);
		modelAll.put(cp.getId(), cp);
	}

	public void addRuleModel(Rule r) {
		modelRule.put(r.getId(), r);
		modelAll.put(r.getId(), r);
	}

	public Structure getStructure(String structureId) {
		return instanceStructure.get(structureId);
	}
	
	public Rule getRule(String ruleId) {
		return modelRule.get(ruleId);
	}
	
	public BaseWork getWork(String workId) {
		return modelWork.get(workId);
	}
	
	public BaseState getState(String stateId) {
		return modelState.get(stateId);
	}
	
	public BaseCapabilityProperty getCapabilityProperty(String capabilityId) {
		return modelCapabilityProperty.get(capabilityId);
	}
	
	public PropertySet getPropertyInstance(String propertyId) {
		return instancePropertySet.get(propertyId);
	}
	
	public Collection<BaseWork> getWorks() {
		return modelWork.values();
	}
	
	public Collection<Rule> getRules() {
		return modelRule.values();
	}
	
	public Inventory getInventoryInstance(String inventoryId) {
		return instanceInventory.get(inventoryId);
	}
	
	public AbstractModelInstance getInstance(String instanceId) {
		return instanceAll.get(instanceId);
	}
	
	public String getInstanceType(String instanceId) {
		return instanceAll.get(instanceId).getType();
	}
	
}
