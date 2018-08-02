package iklim.society;

import java.util.Collection;
import java.util.HashMap;

import iklim.society.model.ModelManager;
import iklim.society.model.ModelReader;
import iklim.society.model.instance.Work;

public class Core {
	private ModelManager 		model;
	private WorkExecutor		executor;
	private WorkManager			works;
	
	public Core() {
		works = new WorkManager();
		model = ModelReader.parseModel("./model/BaseModel.mdl");
		
		Collection<Work> cyclicWork = model.getCyclicWorks();
		works.putAllCyclicWork(cyclicWork);
		
		
		start();
	}

	

	private void start() {
		
		while(true) {
			addCyclicWork();
			doWork();
			
			
		}
		
		
		
		
		
		
//		while(true){
//			testWork();
//			model.printInstance();
//			
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		
		
	}

	private void doWork() {
		executor.excute();
		
	}



	private void addCyclicWork() {
		executor.addAllWork(works.getAllCyclicWork());
	}
	
	public void addWork(Work w) {
		executor.addWork(w);
	}



//	private void testWork() {
//		Work w = new Work("work001","GrowthWork","farmer001", "farmland001");
//		performWork(w);
//		
//	}
//
//
//
//	private void performWork(Work w) {
//		
//		BaseWork bw = model.getBaseWork(w.getWorkType());
//		
//		AbstractModelInstance worker = model.get(w.getWorker());
//		if(!isMatched(bw.worker, worker)){
//			System.out.println("worker type is mismatched");
//		}
//		
//		AbstractModelInstance target = model.get(w.getTarget());
//		if(!isMatched(bw.target, target)){
//			System.out.println("target type is mismatched");
//		}
//		
//		BaseRule rule = model.getBaseRule(bw.getRule());
//		
//		if(!conditionSadisfied(rule, worker, target)){
//			System.out.println("Condition not Sadisfied");
//		} 
//		
//		System.out.println(rule.getArgument());
//		System.out.println(rule.getResultSet());
//		
//		for(Result result : rule.getResultSet()) {
//			String[] s = result.getTargetValue().split("/");
//			if(s[0].equals("target")) {
//				PropertyInstance prop = target.getProperty(s[1]);
//				System.out.println("프로퍼티 growth 출력: " + prop);
//			} else if(s[0].equals("worker")) {
//				PropertyInstance prop = worker.getProperty(s[1]);
//				System.out.println("프로퍼티 WorkForce 출력: " + prop);
//			}
//		}
//		
//		
//		for(PostCondition con : bw.postCondition){
//			String[] s = con.type.split("/");
//			if(s[0].equals("worker")){
//				PropertyInstance prop = worker.getProperty(s[1]);
//				if(ModelScheme.ValueIntValue.equals(prop.getType())){
//					IntPropertyInstance ip = (IntPropertyInstance)prop;
//					ip.setValue(ip.getValue()+con.value);
//				}
//			}
//		}
//		
//		float changeValue = 0;
//		
//		if('0' <= bw.result.charAt(0) && '9' >= bw.result.charAt(0)){
//			changeValue = Float.valueOf(bw.result);
//		}else{
//			
//			if(bw.rules.containsKey(bw.result)){
//				changeValue = processExpression(bw.rules.get(bw.result), worker, target);
//				
//			}
//		}
//		
//		String[] s = bw.targetValue.split("/");
//		if(s[0].equals("target")){
//			PropertyInstance prop = target.getProperty(s[1]);
//			if(ModelScheme.ValueFloatValue.equals(prop.getType())){
//				FloatPropertyInstance ip = (FloatPropertyInstance)prop;
//				ip.setValue(ip.getValue()+changeValue); 
//			}
//		}
//		
//		
//	}
//
//
//
//	private boolean conditionSadisfied(BaseRule br,
//			AbstractModelInstance worker, AbstractModelInstance target) {
//		for (PreCondition con : bw.preCondition) {
//			String[] s = con.type.split("/");
//			if(s[0].equals("worker")){
//				PropertyInstance prop = worker.getProperty(s[1]);
//				System.out.println(s[1]);
//				if(ModelScheme.ValueIntValue.equals(prop.getType())){
//					IntPropertyInstance ip = (IntPropertyInstance)prop;
//					if(ip.getValue()<con.value){
//						return false;
//					}
//				}
//				
//			}
//		}
//		System.out.println("컨디션 정보 : " + br.getCondition());
//		System.out.println("현재 컨디션은 무조건 만족됨");
//		
//		return true;
//	}
//
//
//
//	private float processExpression(Rule r, AbstractModelInstance worker,	AbstractModelInstance target) {
//		if(r.getValue().getMultiplier().size()>0){
//			float expressionResult = 1;
//			for (Multiplier mul : r.getValue().getMultiplier()) {
//				expressionResult*=processMultiplier(r, mul, worker, target);
//			}
//			return expressionResult;
//		}
//		return 0;
//	}
//
//
//
//	private float processMultiplier(Rule r, Multiplier mul,
//			AbstractModelInstance worker, AbstractModelInstance target) {
//		if(mul.getAdders().size()>0){
//			float value = 0;
//			for(AddValues add : mul.getAdders()){
//				value += processAdder(r, add, worker, target);
//			}
//			return value;
//		}
//		return 0;
//	}
//
//
//
//	private float processAdder(Rule r, AddValues add,
//			AbstractModelInstance worker, AbstractModelInstance target) {
//		float value = 0;
//		if('0' <= add.getValue().charAt(0) && '9' >= add.getValue().charAt(0)){
//			value += Float.valueOf(add.getValue());
//		}else{
//			String[] s = add.getValue().split("/");
//			if(s[0].equals("worker")){
//				PropertyInstance prop = worker.getProperty(s[1]);
//				if(ModelScheme.ValueIntValue.equals(prop.getType())){
//					IntPropertyInstance ip = (IntPropertyInstance)prop;
//					value+=ip.getValue();
//				}
//			}else if(s[0].equals("this")){
//				value+=r.getConstantValues().get(s[1]).intValue();
//			}
//		}
//		return value;
//	}
//
//
//
//	private boolean isMatched(String type, AbstractModelInstance target) {
//		if(type.equals(target.getType())) return true;
//		if(target instanceof Agent){
//			Agent s = (Agent)target;
//			if(type == s.getAgentType()){
//				return true;
//			}
//			
//		}
//		if(target instanceof Structure){
//			Structure s = (Structure)target;
//			if(type.equals(s.getStructureType())){
//				return true;
//			}
//			
//		}
//		
//		return false;
//	}






	public static void main(String[] args) {
		new Core();
	}
}
