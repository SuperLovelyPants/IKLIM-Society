package iklim.society.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;

import javax.management.modelmbean.ModelMBeanInfoSupport;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import iklim.society.model.base.BaseCapabilityProperty;
import iklim.society.model.base.BaseItem;
import iklim.society.model.base.BasePropertySet;
import iklim.society.model.base.BaseState;
import iklim.society.model.base.BaseStructure;
import iklim.society.model.base.BaseWork;
import iklim.society.model.base.rule.Rule;
import iklim.society.model.base.utility.Precondition;
import iklim.society.model.instance.argument.Argument;
import iklim.society.model.instance.argument.IndividualArgument;

public class ModelReader {

	public static ModelManager parseModel(String path){//여기 스태틱 왜 붙는가?
		ModelManager manager = ModelManager.getInstance();
		File f = new File(path);
		JsonParser parser = new JsonParser();
		try {
			JsonElement e = parser.parse(new FileReader(f));
			JsonObject root = e.getAsJsonObject();
			
			buildBaseModel(root.get(ModelScheme.ObjectBaseModel).getAsJsonArray(), manager);
			
			buildPropertySet(root.get(ModelScheme.ObjectPropertySet).getAsJsonArray(), manager);
			buildState(root.get(ModelScheme.ObjectState).getAsJsonArray(), manager);
			buildCapabilityProperty(root.get(ModelScheme.ObjectCapabilityProperty).getAsJsonArray(), manager);
			buildRule(root.get(ModelScheme.ObjectRule).getAsJsonArray(), manager);
			buildWork(root.get(ModelScheme.ObjectWork).getAsJsonArray(), manager);
			buildItem(root.get(ModelScheme.ObjectItem).getAsJsonArray(), manager);
			buildStructure(root.get(ModelScheme.ObjectStructure).getAsJsonArray(), manager);
			
			effectWorkCheck(manager);
			argumentCheck(manager);
			
			buildInstance(root.get(ModelScheme.ObjectInstance).getAsJsonArray(), manager);
			
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return manager;
	}
	
	private static void buildBaseModel(JsonArray joBaseModel, ModelManager manager) {
		//TODO
	}

	private static void buildPropertySet(JsonArray joPropertySet, ModelManager manager) {
		for (JsonElement propertySetElement : joPropertySet) {
			JsonObject propertySetObject = propertySetElement.getAsJsonObject();
			manager.addPropertySetModel(buildPropertySetModel(propertySetObject));
		}
	}
	
	private static BasePropertySet buildPropertySetModel(JsonObject propertySetObject) {
		BasePropertySet bps = new BasePropertySet();
		bps.setId(propertySetObject.get(ModelScheme.PropertyID).getAsString());
		bps.setName(propertySetObject.get(ModelScheme.PropertyName).getAsString());
		bps.setParent(propertySetObject.get(ModelScheme.PropertyParentType).getAsString());
		LinkedList<String> properties = new LinkedList<String>();
		JsonArray propertiesJsonArray = propertySetObject.get(ModelScheme.PropertyProperties).getAsJsonArray();
		for (JsonElement propertyJsonElement : propertiesJsonArray) {
			properties.add(propertyJsonElement.getAsString());
		}
		bps.setProperties(properties);
		
		return bps;
	}
	
	private static void buildState(JsonArray joState, ModelManager manager) {
		for (JsonElement stateElement : joState) {
			JsonObject stateObject = stateElement.getAsJsonObject();
			manager.addStateModel(buildStateModel(stateObject));
		}
	}
	
	private static BaseState buildStateModel(JsonObject stateObject) {
		BaseState bs = new BaseState();
		bs.setId(stateObject.get(ModelScheme.PropertyID).getAsString());
		bs.setName(stateObject.get(ModelScheme.PropertyName).getAsString());
		bs.setParent(stateObject.get(ModelScheme.PropertyParentType).getAsString());
		bs.setStateRule(stateObject.get(ModelScheme.PropertyStateRule).getAsString());
		LinkedList<String> states = new LinkedList<String>();
		JsonArray statesJsonArray = stateObject.get(ModelScheme.PropertyStates).getAsJsonArray();
		for (JsonElement stateJsonElement : statesJsonArray) {
			states.add(stateJsonElement.getAsString());
		}
		bs.setStates(states);
		
		return bs;
	}
	
	private static void buildCapabilityProperty(JsonArray joCapabilityProperty, ModelManager manager) {
		for (JsonElement capabilityPropertyElement : joCapabilityProperty) {
			JsonObject capabilityPropertyObject = capabilityPropertyElement.getAsJsonObject();
			manager.addCapabilityPropertyModel(buildCapabilityPropertyModel(capabilityPropertyObject));
		}
	}
	
	private static BaseCapabilityProperty buildCapabilityPropertyModel(JsonObject capabilityPropertyObject) {
		BaseCapabilityProperty bcp = new BaseCapabilityProperty();
		bcp.setId(capabilityPropertyObject.get(ModelScheme.PropertyID).getAsString());
		bcp.setName(capabilityPropertyObject.get(ModelScheme.PropertyName).getAsString());
		bcp.setParent(capabilityPropertyObject.get(ModelScheme.PropertyParentType).getAsString());
		LinkedList<String> capabilityProperties = new LinkedList<String>();
		JsonArray capabilityPropertiesJsonArray = capabilityPropertyObject.get(ModelScheme.PropertyCapable).getAsJsonArray();
		for (JsonElement capabilityPropertyJsonElement : capabilityPropertiesJsonArray) {
			capabilityProperties.add(capabilityPropertyJsonElement.getAsString());
		}
		bcp.setCapabilityProperties(capabilityProperties);
		
		return bcp;
	}
	
	private static void buildRule(JsonArray joRule, ModelManager manager) {
		for (JsonElement ruleElement : joRule) {
			JsonObject ruleObject = ruleElement.getAsJsonObject();
			manager.addRuleModel(buildRuleModel(ruleObject, manager));
		}
	}
	
	private static Rule buildRuleModel(JsonObject ruleObject, ModelManager manager) {
		Rule rule = new Rule();
		rule.setId(ruleObject.get(ModelScheme.PropertyID).getAsString());
		rule.setName(ruleObject.get(ModelScheme.PropertyName).getAsString());
		rule.setParent(ruleObject.get(ModelScheme.PropertyParentType).getAsString());
		//rule.setEvaluator(ruleObject.get(ModelScheme.PropertyEvaluate).getAsString());
		rule.setTargetValue(ruleObject.get(ModelScheme.PropertyTargetValue).getAsString());
		
		JsonArray arguments = ruleObject.get(ModelScheme.PropertyArgument).getAsJsonArray();
		LinkedList<IndividualArgument> argumentList = new LinkedList<IndividualArgument>();
		for (JsonElement argumentElement : arguments) {
			JsonObject arguementObject = argumentElement.getAsJsonObject();
			IndividualArgument argument = new IndividualArgument();
			argument.setName(arguementObject.get(ModelScheme.PropertyName).getAsString());
			argument.setType(arguementObject.get(ModelScheme.PropertyType).getAsString());
			argumentList.add(argument);
		}
		
		manager.getWorkArgumentBuffer().put(rule.getId(), argumentList);
		
		return rule;
	}
	
	private static void buildWork(JsonArray joWork, ModelManager manager) {
		for (JsonElement workElement : joWork) {
			JsonObject workObject = workElement.getAsJsonObject();
			manager.addWorkModel(buildBaseWorkModel(workObject, manager));
		}
	}
	
	private static BaseWork buildBaseWorkModel(JsonObject workObject, ModelManager manager) {
		BaseWork bw = new BaseWork();
		bw.setId(workObject.get(ModelScheme.PropertyID).getAsString());
		bw.setName(workObject.get(ModelScheme.PropertyName).getAsString());
		bw.setParent(workObject.get(ModelScheme.PropertyParentType).getAsString());
		
		LinkedList<Precondition> preconditionList = new LinkedList<Precondition>();
		JsonArray preconditions = workObject.get(ModelScheme.PropertyPrecondition).getAsJsonArray();		
		for (JsonElement preconditionElement : preconditions) {
			Precondition precondition = new Precondition();
			precondition.setCondition(preconditionElement.getAsString());
			preconditionList.add(precondition);
		}
		bw.setPrecondition(preconditionList);
		
		LinkedList<String> ruleList = new LinkedList<String>();
		JsonArray rules = workObject.get(ModelScheme.PropertyRuleSet).getAsJsonArray();
		for (JsonElement ruleElement : rules) {
			ruleList.add(ruleElement.getAsString());
		}
		bw.setRuleSet(ruleList);
		
		JsonArray triggers = workObject.get(ModelScheme.PropertyTrigger).getAsJsonArray();
		for (JsonElement triggerElement : triggers) {
			String trigger = triggerElement.getAsString();
			LinkedList<String> triggerList = manager.getRuleEffectWorkBuffer().get(trigger);
			if(triggerList == null) {
				triggerList = new LinkedList<String>();
				triggerList.add(bw.getId());
				manager.getRuleEffectWorkBuffer().put(trigger,triggerList);
			} else {
				triggerList.add(bw.getId());
			}
		}
		
		return bw;
		
	}
	
	private static void buildItem(JsonArray joItem, ModelManager manager) {
		for (JsonElement itemElement : joItem) {
			JsonObject itemObject = itemElement.getAsJsonObject();
			manager.addItemModel(buildItemModel(itemObject));
		}
	}
	
	private static BaseItem buildItemModel(JsonObject itemObject) {
		BaseItem bi = new BaseItem();
		bi.setId(itemObject.get(ModelScheme.PropertyID).getAsString());
		bi.setName(itemObject.get(ModelScheme.PropertyName).getAsString());
		bi.setParent(itemObject.get(ModelScheme.PropertyParentType).getAsString());
		
		return bi;
	}
	
	private static void buildStructure(JsonArray joStructure, ModelManager manager) {
		for (JsonElement structureElement : joStructure) {
			JsonObject structureObject = structureElement.getAsJsonObject();
			manager.addStructureModel(buildStructureModel(structureObject));
		}	
	}
	
	private static BaseStructure buildStructureModel(JsonObject StructureObject) {
		BaseStructure bs = new BaseStructure();
		bs.setId(StructureObject.get(ModelScheme.PropertyID).getAsString());
		bs.setName(StructureObject.get(ModelScheme.PropertyName).getAsString());
		bs.setParent(StructureObject.get(ModelScheme.PropertyParentType).getAsString());
		
		LinkedList<String> buildableList = new LinkedList<>();
		JsonArray buildables = StructureObject.get(ModelScheme.PropertyBuildable).getAsJsonArray();
		for (JsonElement buildableElement : buildables) {
			buildableList.add(buildableElement.getAsString());
		}
		
		bs.setBuildable(buildableList);
		
		return bs;
	}
	
	private static void buildInstance(JsonArray joInstance, ModelManager manager) {
		
	}
	
	private static void effectWorkCheck(ModelManager manager) {
		for (Rule rule : manager.getRules()) {
			rule.setEffectWorks(manager.getRuleEffectWorkBuffer().get(rule.getTargetValue()));
		}
	}
	
	private static void argumentCheck(ModelManager manager) {
		for(BaseWork work : manager.getWorks()) {
			HashMap<String, Argument> argumentList = new HashMap<String, Argument>();
			LinkedList<String> ruleList = work.getRuleSet();
			for (String ruleName : ruleList) {
				LinkedList<Arguement> ruleArgumentList = manager.getWorkArgumentBuffer().get(ruleName);
			}
			
		}
	}
	

//	private static void buildItemModel(JsonArray asJsonArray,
//			ModelManager manager) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	private static void buildInstanceModel(JsonObject startConditionObject,	ModelManager manager) {
//		for(JsonElement agentElement : startConditionObject.get(ObjectAgent).getAsJsonArray()){
//			buildAgentInstance(agentElement.getAsJsonObject());
//		}
//		for(JsonElement structureElement : startConditionObject.get(ObjectStructure).getAsJsonArray()){
//			buildStructureInstance(structureElement.getAsJsonObject());
//		}
//		for(JsonElement facilitySetElement : startConditionObject.get(ObjectFacilitySet).getAsJsonArray()){
//			String child = facilitySetElement.getAsJsonObject().get(PropertyID).getAsString();
//			String parent = facilitySetElement.getAsJsonObject().get(PropertyTo).getAsString();
//			manager.setFacility(child, parent);
//		}
//	}
//
//	private static void buildStructureInstance(JsonObject asJsonObject) {
//		String id= asJsonObject.get(PropertyID).getAsString();
//		String type = asJsonObject.get(PropertyType).getAsString();
//		ModelManager.getInstance().addStructureInstance(id, type);
//	}
//
//	private static void buildAgentInstance(JsonObject asJsonObject) {
//		String id= asJsonObject.get(PropertyID).getAsString();
//		String type = asJsonObject.get(PropertyType).getAsString();
//		ModelManager.getInstance().addAgentInstance(id, type);
//	}
//
//	private static void buildAgentModel(JsonArray agents, ModelManager manager) {
//		for (JsonElement jsonElement : agents) {
//			JsonObject agent = jsonElement.getAsJsonObject();
//			manager.addAgentModel(buildIndividualAgentModelObject(agent));
//		}
//	}
//
//	private static BaseAgent buildIndividualAgentModelObject(JsonObject agent) {
//		BaseAgent s = new BaseAgent();
//		s.id = agent.get(PropertyID).getAsString();
//		s.name = agent.get(PropertyName).getAsString();
//		s.description = agent.get(PropertyDescription).getAsString();
//		s.invenSize = agent.get(PropertyInventorySize).getAsInt();
//		s.properties = getModelProperty(agent);
//		s.parent = agent.get(PropertyParentType).getAsString();
//		return s;
//	}
//
//	private static void buildWorkModel(JsonArray works, ModelManager manager) {
//		for (JsonElement jsonElement : works) {
//			JsonObject work = jsonElement.getAsJsonObject();
//			manager.addWorkModel(buildIndividualWorkModelObject(work));
//		}
//	}
//
//	private static BaseWork buildIndividualWorkModelObject(JsonObject work) {
//		BaseWork w = new BaseWork();
//		w.id = work.get(PropertyID).getAsString();
//		w.name = work.get(PropertyName).getAsString();
//		w.worker = work.get(PropertyWorker).getAsString();
//		w.target = work.get(PropertyTarget).getAsString();
//		w.setRule(work.get(PropertyRule).getAsString());
//		
//		return w;
//	}
//	
//	private static void buildRuleModel(JsonArray rules, ModelManager manager) {
//		for(JsonElement jsonElement : rules) {
//			JsonObject rule = jsonElement.getAsJsonObject();
//			manager.addRuleModel(buildIndivisualRuleModelObject(rule));
//		}
//	}
//	
//	private static BaseRule buildIndivisualRuleModelObject(JsonObject rule) {
//		BaseRule r = new BaseRule();
//		r.id = rule.get(PropertyID).getAsString();
//		//r.name = rule.get(PropertyName).getAsString();
//		JsonArray array  = rule.get(PropertyArgument).getAsJsonArray();
//		ArrayList<String> argument = new ArrayList<String>();
//		for(JsonElement jsonElement : array) {
//			argument.add(jsonElement.getAsString());
//		}
//		r.setArgument(argument);
//		r.setCondition(rule.get(PropertyCondition).getAsString());
//		array = rule.get(PropertyResultSet).getAsJsonArray();
//		ArrayList<Result> result = new ArrayList<Result>();
//		for(JsonElement jsonElement : array) {
//			JsonObject resultJsonObject = jsonElement.getAsJsonObject();
//			Result resultObject = new Result();
//			resultObject.setTargetValue(resultJsonObject.get(PropertyTargetValue).getAsString());
//			resultObject.setEvaluate(resultJsonObject.get(PropertyEvaluate).getAsString());
//			result.add(resultObject);
//		}
//		r.setResultSet(result);
//		
//		return r;
//	}
//
//	private static void buildStructureModel(JsonArray structures, ModelManager manager) {
//		for (JsonElement jsonElement : structures) {
//			JsonObject structure = jsonElement.getAsJsonObject();
//			manager.addStructureModel(buildIndividualStructureModelObject(structure));
//		}
//	}
//
//	private static BaseStructure buildIndividualStructureModelObject(JsonObject structure) {
//		BaseStructure s = new BaseStructure();
//		s.id = structure.get(PropertyID).getAsString();
//		s.name = structure.get(PropertyName).getAsString();
//		s.description = structure.get(PropertyDescription).getAsString();
//		s.area = structure.get(PropertyArea).getAsInt();
//		s.workID = structure.get(PropertyWork).getAsString();
//		s.invenSize = structure.get(PropertyInventorySize).getAsInt();
//		s.fieldSize = structure.get(PropertyFieldSize).getAsInt();
//		if(s.fieldSize!=0){
//			JsonArray buildable = structure.get(PropertyBuildable).getAsJsonArray();
//			ArrayList<String> buildableIDs = new ArrayList<String>();
//			for (JsonElement buildableID : buildable) {
//				String bid = buildableID.getAsString();
//				buildableIDs.add(bid);
//			}
//			s.buildable = buildableIDs;
//		}
//		s.properties = getModelProperty(structure);
//		s.parent = structure.get(PropertyParentType).getAsString();
//		
//		return s;
//	}

}
