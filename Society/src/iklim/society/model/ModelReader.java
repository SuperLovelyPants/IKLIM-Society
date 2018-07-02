package iklim.society.model;

import iklim.society.model.base.BaseAgent;
import iklim.society.model.base.BaseRule;
import iklim.society.model.base.BaseStructure;
import iklim.society.model.base.BaseWork;
import iklim.society.model.base.Result;
import iklim.society.model.base.property.AbstractProperty;
import iklim.society.model.base.property.FloatProperty;
import iklim.society.model.base.property.IntProperty;
import iklim.society.model.instance.Agent;
import iklim.society.model.instance.Structure;
import iklim.society.model.rule.AddValues;
import iklim.society.model.rule.Evaluate;
import iklim.society.model.rule.Multiplier;
import iklim.society.model.rule.Rule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import static iklim.society.model.ModelScheme.*;

public class ModelReader {
	

	



	public static ModelManager parseModel(String path){
		ModelManager manager = ModelManager.getInstance();
		File f = new File(path);
		JsonParser parser = new JsonParser();
		try {
			JsonElement e = parser.parse(new FileReader(f));
			JsonObject root = e.getAsJsonObject();
			buildAgentModel(root.get(ObjectAgent).getAsJsonArray(), manager);
			buildStructureModel(root.get(ObjectStructure).getAsJsonArray(), manager);
			buildWorkModel(root.get(ObjectWork).getAsJsonArray(), manager);
			buildRuleModel(root.get(ObjectRule).getAsJsonArray(), manager);
			buildItemModel(root.get(ObjectItem).getAsJsonArray(), manager);
			
			
			buildInstanceModel(root.get(ObjectStartCondition).getAsJsonObject(), manager);
			
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return manager;
	}


	private static void buildItemModel(JsonArray asJsonArray,
			ModelManager manager) {
		// TODO Auto-generated method stub
		
	}


	private static void buildInstanceModel(JsonObject startConditionObject,	ModelManager manager) {
		for(JsonElement agentElement : startConditionObject.get(ObjectAgent).getAsJsonArray()){
			buildAgentInstance(agentElement.getAsJsonObject());
		}
		for(JsonElement structureElement : startConditionObject.get(ObjectStructure).getAsJsonArray()){
			buildStructureInstance(structureElement.getAsJsonObject());
		}
		for(JsonElement facilitySetElement : startConditionObject.get(ObjectFacilitySet).getAsJsonArray()){
			String child = facilitySetElement.getAsJsonObject().get(PropertyID).getAsString();
			String parent = facilitySetElement.getAsJsonObject().get(PropertyTo).getAsString();
			manager.setFacility(child, parent);
		}
	}

	private static void buildStructureInstance(JsonObject asJsonObject) {
		String id= asJsonObject.get(PropertyID).getAsString();
		String type = asJsonObject.get(PropertyType).getAsString();
		ModelManager.getInstance().addStructureInstance(id, type);
	}

	private static void buildAgentInstance(JsonObject asJsonObject) {
		String id= asJsonObject.get(PropertyID).getAsString();
		String type = asJsonObject.get(PropertyType).getAsString();
		ModelManager.getInstance().addAgentInstance(id, type);
	}

	private static void buildAgentModel(JsonArray agents, ModelManager manager) {
		for (JsonElement jsonElement : agents) {
			JsonObject agent = jsonElement.getAsJsonObject();
			manager.addAgentModel(buildIndividualAgentModelObject(agent));
		}
	}

	private static BaseAgent buildIndividualAgentModelObject(JsonObject agent) {
		BaseAgent s = new BaseAgent();
		s.id = agent.get(PropertyID).getAsString();
		s.name = agent.get(PropertyName).getAsString();
		s.description = agent.get(PropertyDescription).getAsString();
		s.invenSize = agent.get(PropertyInventorySize).getAsInt();
		s.properties = getModelProperty(agent);
		s.parent = agent.get(PropertyParentType).getAsString();
		return s;
	}

	private static void buildWorkModel(JsonArray works, ModelManager manager) {
		for (JsonElement jsonElement : works) {
			JsonObject work = jsonElement.getAsJsonObject();
			manager.addWorkModel(buildIndividualWorkModelObject(work));
		}
	}

	private static BaseWork buildIndividualWorkModelObject(JsonObject work) {
		BaseWork w = new BaseWork();
		w.id = work.get(PropertyID).getAsString();
		w.name = work.get(PropertyName).getAsString();
		w.worker = work.get(PropertyWorker).getAsString();
		w.target = work.get(PropertyTarget).getAsString();
		w.setRule(work.get(PropertyRule).getAsString());
		
		return w;
	}
	
	private static void buildRuleModel(JsonArray rules, ModelManager manager) {
		for(JsonElement jsonElement : rules) {
			JsonObject rule = jsonElement.getAsJsonObject();
			manager.addRuleModel(buildIndivisualRuleModelObject(rule));
		}
	}
	
	private static BaseRule buildIndivisualRuleModelObject(JsonObject rule) {
		BaseRule r = new BaseRule();
		r.id = rule.get(PropertyID).getAsString();
		//r.name = rule.get(PropertyName).getAsString();
		JsonArray array  = rule.get(PropertyArgument).getAsJsonArray();
		ArrayList<String> argument = new ArrayList<String>();
		for(JsonElement jsonElement : array) {
			argument.add(jsonElement.getAsString());
		}
		r.setArgument(argument);
		r.setCondition(rule.get(PropertyCondition).getAsString());
		array = rule.get(PropertyResultSet).getAsJsonArray();
		ArrayList<Result> result = new ArrayList<Result>();
		for(JsonElement jsonElement : array) {
			JsonObject resultJsonObject = jsonElement.getAsJsonObject();
			Result resultObject = new Result();
			resultObject.setTargetValue(resultJsonObject.get(PropertyTargetValue).getAsString());
			resultObject.setEvaluate(resultJsonObject.get(PropertyEvaluate).getAsString());
			result.add(resultObject);
		}
		r.setResultSet(result);
		
		return r;
	}

	private static void buildStructureModel(JsonArray structures, ModelManager manager) {
		for (JsonElement jsonElement : structures) {
			JsonObject structure = jsonElement.getAsJsonObject();
			manager.addStructureModel(buildIndividualStructureModelObject(structure));
		}
	}

	private static BaseStructure buildIndividualStructureModelObject(JsonObject structure) {
		BaseStructure s = new BaseStructure();
		s.id = structure.get(PropertyID).getAsString();
		s.name = structure.get(PropertyName).getAsString();
		s.description = structure.get(PropertyDescription).getAsString();
		s.area = structure.get(PropertyArea).getAsInt();
		s.workID = structure.get(PropertyWork).getAsString();
		s.invenSize = structure.get(PropertyInventorySize).getAsInt();
		s.fieldSize = structure.get(PropertyFieldSize).getAsInt();
		if(s.fieldSize!=0){
			JsonArray buildable = structure.get(PropertyBuildable).getAsJsonArray();
			ArrayList<String> buildableIDs = new ArrayList<String>();
			for (JsonElement buildableID : buildable) {
				String bid = buildableID.getAsString();
				buildableIDs.add(bid);
			}
			s.buildable = buildableIDs;
		}
		s.properties = getModelProperty(structure);
		s.parent = structure.get(PropertyParentType).getAsString();
		
		return s;
	}

	private static ArrayList<AbstractProperty> getModelProperty(JsonObject entity) {
		ArrayList<AbstractProperty> properties = new ArrayList<AbstractProperty>();
		if(!entity.has(ObjectProperties))
			return properties;
		for(JsonElement e :entity.get(ObjectProperties).getAsJsonArray()){
			JsonObject property = e.getAsJsonObject();
			String name = property.get(PropertyName).getAsString();
			if(property.get(PropertyType).getAsString().equals(ValueFloatValue)){
				float initValue = property.get(PropertyInitValue).getAsFloat();
				FloatProperty f = new FloatProperty(name, initValue);
				f.setMinValue(property.get(PropertyMinValue).getAsFloat());
				f.setMaxValue(property.get(PropertyMaxValue).getAsFloat());
				properties.add(f);
				continue;
			}
			if(property.get(PropertyType).getAsString().equals(ValueIntValue)){
				int initValue = property.get(PropertyInitValue).getAsInt();
				IntProperty f = new IntProperty(name, initValue);
				f.setMinValue(property.get(PropertyMinValue).getAsInt());
				f.setMaxValue(property.get(PropertyMaxValue).getAsInt());
				properties.add(f);
				continue;
			}
		}
		return properties;
	}
	
}
