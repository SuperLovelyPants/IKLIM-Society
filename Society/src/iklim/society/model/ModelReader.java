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
import iklim.society.model.base.rule.BaseArgument;
import iklim.society.model.base.rule.Rule;
import iklim.society.model.base.utility.Precondition;
import iklim.society.model.instance.Agent;
import iklim.society.model.instance.Inventory;
import iklim.society.model.instance.Item;
import iklim.society.model.instance.PropertySet;
import iklim.society.model.instance.Structure;
import iklim.society.model.instance.argument.Argument;
import iklim.society.model.instance.argument.IndividualArgument;

public class ModelReader {

	public static ModelManager parseModel(String path) {
		ModelManager manager = ModelManager.getInstance();
		File f = new File(path);
		JsonParser parser = new JsonParser();

		HashMap<String, LinkedList<String>> ruleEffectWorkBuffer = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<BaseArgument>> workArgumentBuffer = new HashMap<String, LinkedList<BaseArgument>>();

		try {
			JsonElement e = parser.parse(new FileReader(f));
			JsonObject root = e.getAsJsonObject();

			buildBaseModel(root.get(ModelScheme.ObjectBaseModel).getAsJsonArray(), manager);

			buildPropertySet(root.get(ModelScheme.ObjectPropertySet).getAsJsonArray(), manager);
			buildState(root.get(ModelScheme.ObjectState).getAsJsonArray(), manager);
			buildCapabilityProperty(root.get(ModelScheme.ObjectCapabilityProperty).getAsJsonArray(), manager);
			buildRule(root.get(ModelScheme.ObjectRule).getAsJsonArray(), manager, workArgumentBuffer);
			buildWork(root.get(ModelScheme.ObjectWork).getAsJsonArray(), manager, ruleEffectWorkBuffer);
			buildItem(root.get(ModelScheme.ObjectItem).getAsJsonArray(), manager);
			buildStructure(root.get(ModelScheme.ObjectStructure).getAsJsonArray(), manager);

			effectWorkCheck(manager, ruleEffectWorkBuffer);
			argumentCheck(manager, workArgumentBuffer);

			buildInstance(root.get(ModelScheme.ObjectInstance).getAsJsonObject(), manager);

		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return manager;
	}

	private static void buildBaseModel(JsonArray jaBaseModel, ModelManager manager) {
		// TODO
	}

	private static void buildPropertySet(JsonArray jaPropertySet, ModelManager manager) {
		for (JsonElement propertySetElement : jaPropertySet) {
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

	private static void buildState(JsonArray jaState, ModelManager manager) {
		for (JsonElement stateElement : jaState) {
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

	private static void buildCapabilityProperty(JsonArray jaCapabilityProperty, ModelManager manager) {
		for (JsonElement capabilityPropertyElement : jaCapabilityProperty) {
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
		JsonArray capabilityPropertiesJsonArray = capabilityPropertyObject.get(ModelScheme.PropertyCapable)
				.getAsJsonArray();
		for (JsonElement capabilityPropertyJsonElement : capabilityPropertiesJsonArray) {
			capabilityProperties.add(capabilityPropertyJsonElement.getAsString());
		}
		bcp.setCapabilityProperties(capabilityProperties);

		return bcp;
	}

	private static void buildRule(JsonArray jaRule, ModelManager manager,
			HashMap<String, LinkedList<BaseArgument>> argumentBuffer) {
		for (JsonElement ruleElement : jaRule) {
			JsonObject ruleObject = ruleElement.getAsJsonObject();
			manager.addRuleModel(buildRuleModel(ruleObject, argumentBuffer));
		}
	}

	private static Rule buildRuleModel(JsonObject ruleObject,
			HashMap<String, LinkedList<BaseArgument>> argumentBuffer) {
		Rule rule = new Rule();
		rule.setId(ruleObject.get(ModelScheme.PropertyID).getAsString());
		rule.setName(ruleObject.get(ModelScheme.PropertyName).getAsString());
		rule.setParent(ruleObject.get(ModelScheme.PropertyParentType).getAsString());
		// rule.setEvaluator(ruleObject.get(ModelScheme.PropertyEvaluate).getAsString());
		rule.setTargetValue(ruleObject.get(ModelScheme.PropertyTargetValue).getAsString());

		JsonArray arguments = ruleObject.get(ModelScheme.PropertyArgument).getAsJsonArray();
		LinkedList<BaseArgument> argumentList = new LinkedList<BaseArgument>();
		for (JsonElement argumentElement : arguments) {
			JsonObject arguementObject = argumentElement.getAsJsonObject();
			BaseArgument argument = new BaseArgument(arguementObject.get(ModelScheme.PropertyName).getAsString(),
					arguementObject.get(ModelScheme.PropertyType).getAsString());
			argumentList.add(argument);
		}

		argumentBuffer.put(rule.getId(), argumentList);

		return rule;
	}

	private static void buildWork(JsonArray jaWork, ModelManager manager,
			HashMap<String, LinkedList<String>> effectWorkBuffer) {
		for (JsonElement workElement : jaWork) {
			JsonObject workObject = workElement.getAsJsonObject();
			manager.addWorkModel(buildBaseWorkModel(workObject, effectWorkBuffer));
		}
	}

	private static BaseWork buildBaseWorkModel(JsonObject workObject,
			HashMap<String, LinkedList<String>> effectWorkBuffer) {
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
			LinkedList<String> triggerList = effectWorkBuffer.get(trigger);
			if (triggerList == null) {
				triggerList = new LinkedList<String>();
				triggerList.add(bw.getId());
				effectWorkBuffer.put(trigger, triggerList);
			} else {
				triggerList.add(bw.getId());
			}
		}

		return bw;

	}

	private static void buildItem(JsonArray jaItem, ModelManager manager) {
		for (JsonElement itemElement : jaItem) {
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

	private static void buildStructure(JsonArray jaStructure, ModelManager manager) {
		for (JsonElement structureElement : jaStructure) {
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

	private static void effectWorkCheck(ModelManager manager, HashMap<String, LinkedList<String>> effectWorkBuffer) {
		for (Rule rule : manager.getRules()) {
			rule.setEffectWorks(effectWorkBuffer.get(rule.getTargetValue()));
		}
	}

	private static void argumentCheck(ModelManager manager, HashMap<String, LinkedList<BaseArgument>> argumentBuffer) {
		for (BaseWork work : manager.getWorks()) {
			HashMap<String, BaseArgument> argumentList = new HashMap<String, BaseArgument>();
			LinkedList<String> ruleList = work.getRuleSet();
			for (String ruleName : ruleList) {
				for (BaseArgument ba : argumentBuffer.get(ruleName)) {
					argumentList.put(ba.getName(), ba);
				}
			}
			work.setArgument(argumentList);
		}
	}

	private static void buildInstance(JsonObject joInstance, ModelManager manager) {
		buildAgentInstance(joInstance.get(ModelScheme.ObjectAgent).getAsJsonArray(), manager);
		buildStructureInstance(joInstance.get(ModelScheme.ObjectStructure).getAsJsonArray(), manager);
		buildItemInstance(joInstance.get(ModelScheme.ObjectInstance).getAsJsonArray(), manager);
		buildInventoryInstance(joInstance.get(ModelScheme.ObjectItem).getAsJsonArray(), manager);
		buildPropertySetInstance(joInstance.get(ModelScheme.ObjectPropertySet).getAsJsonArray(), manager);
	}
	
	private static void buildAgentInstance(JsonArray jaAgent, ModelManager manager) {
		for (JsonElement agentElement : jaAgent) {
			JsonObject agentObject = agentElement.getAsJsonObject();
			String id = agentObject.get(ModelScheme.PropertyID).getAsString();
			String type = agentObject.get(ModelScheme.PropertyType).getAsString();
			Agent agent = new Agent(id, type);
			manager.getInstanceAgent().put(id, agent);
		}
	}	
	
	private static void buildStructureInstance(JsonArray jaStructure, ModelManager manager) {
		for (JsonElement structureElement : jaStructure) {
			JsonObject structureObject = structureElement.getAsJsonObject();
			String id = structureObject.get(ModelScheme.PropertyID).getAsString();
			String type = structureObject.get(ModelScheme.PropertyType).getAsString();
			Structure structure = new Structure(id, type);
			manager.getInstanceStructure().put(id, structure);
		}
	}	
	
	private static void buildItemInstance(JsonArray itemArray, ModelManager manager) {
		for (JsonElement itemElement : itemArray) {
			JsonObject itemObject = itemElement.getAsJsonObject();
			String id = itemObject.get(ModelScheme.PropertyID).getAsString();
			String type = itemObject.get(ModelScheme.PropertyType).getAsString();
			Item item = new Item(id, type);
			manager.getInstanceItem().put(id, item);
		}
	}	
	
	private static void buildInventoryInstance(JsonArray inventoryArray, ModelManager manager) {
		for (JsonElement inventoryElement : inventoryArray) {
			JsonObject invnetoryObject = inventoryElement.getAsJsonObject();
			String id = invnetoryObject.get(ModelScheme.PropertyID).getAsString();
			String type = invnetoryObject.get(ModelScheme.PropertyType).getAsString();
			Inventory invnetory = new Inventory(id, type);
			manager.getInstanceInventory().put(id, invnetory);
		}
	}	
	
	private static void buildPropertySetInstance(JsonArray propertySetArray, ModelManager manager) {
		for (JsonElement propertySetElement : propertySetArray) {
			JsonObject propertySetObject = propertySetElement.getAsJsonObject();
			String id = propertySetObject.get(ModelScheme.PropertyID).getAsString();
			String type = propertySetObject.get(ModelScheme.PropertyType).getAsString();
			PropertySet propertySet = new PropertySet(id, type);
			manager.getInstancePropertySet().put(id, propertySet);
		}
	}

}
