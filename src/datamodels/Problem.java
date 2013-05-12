package datamodels;

import java.util.LinkedList;
import java.util.Random;

import csp.StackMachine;

public class Problem {

	private LinkedList<Value> values = new LinkedList<Value>();
	private CSPLimits cspLimits;
	private StackMachine stackMachine = new StackMachine();
	
	private boolean stackMachineChecked;
	
	private int lastSetValue = 0;
	
	public Problem(){
		
	}
	
	public boolean forwardCheck(){
		boolean result = true;
		
		if(!stackMachineChecked){
			stackMachine = new StackMachine();
			stackMachine.setProblem(this);
			stackMachine.setValueNames(getValueNames());
			stackMachineChecked = true;
		}
		
		
		for(int i = lastSetValue+1; i<values.size(); i++){
			Value valueToCheck = values.get(i);
			
			int limitsSize = valueToCheck.getLimits().getLimits().size();
			
			for(int j = 0; j<limitsSize; j++){
				valueToCheck.setLimit(j);
				if(!stackMachine.checkLimits(cspLimits)){
					valueToCheck.blockLimit(j);
				}
			}
			
			if(!valueToCheck.hasFreeLimits()){
				valueToCheck.clearValue();
//				System.out.println("FALSE");
				return false;
			}
			
			valueToCheck.clearValue();
		}
		
		return result;
	}
	
	public Problem(Problem problem){
		this.values = new LinkedList<Value>(problem.values);
		this.cspLimits = new CSPLimits(problem.cspLimits);
	}

	public LinkedList<Value> getValues() {
		return values;
	}

	public void setValues(LinkedList<Value> values) {
		this.values = values;
	}

	public CSPLimits getCspLimits() {
		return cspLimits;
	}

	public void setCspLimits(CSPLimits cspLimits) {
		this.cspLimits = cspLimits;
	}

	public void addValue(Value value) {
		values.add(value);
	}

	public Value getValueAt(int i) {
		return values.get(i);
	}

	public void setValueAt(int i, Value value) {
		values.set(i, value);
	}

	public String toString() {

		String result = "";

		result += "CSP Problem with \n " + values.size() + " values \n"	+ cspLimits.getLimits().size() + " limits";
		

		return result;

	}
	
	public String getValueByName(String name){
		name = name.substring(1);
		int id = Integer.valueOf(name) - 1;
		
		return values.get(id).getValue();
	}
	
	public LinkedList<String> getValueNames(){
		LinkedList<String> valueNames = new LinkedList<String>();
		
		for(Value v : values)
			valueNames.add(v.getContent());
		
		return valueNames;
	}
	
	public void randomizeValues(){
		int size = values.size();
		Random r = new Random();
		
		for(Value value : values){
			int randomValue = r.nextInt(size);
			value.setValue(String.valueOf(randomValue));
		}
	}
	
	public void printValues(){
		for(Value v : values){
			System.out.print(v.getContent()+": " + v.getValue()+" ");
		}
		System.out.println("");
	}
	
	public boolean allValuesSet(){
		for(Value value : values)
			if(!value.isSet()){
//				System.out.println("allValuesSet " + false);
				return false;
			}
		
//		System.out.println("allValuesSet " + true);
		return true;
	}
	
	public boolean goOneLevelDown(){
		boolean result = true;
		
		if(lastSetValue < values.size() -1){
			++lastSetValue;
		} else {
			result = false;
		}
		
//		System.out.println("goOneLevelDown " + result);
		return result;
	}
	
	public boolean goOneLevelUp(){
		boolean result = true;
		
		if(lastSetValue > 0){
			Value value = values.get(lastSetValue);
			value.clearValue();
			
			--lastSetValue;
		} else {
			result = false;
		}
//		System.out.println("goOneLevelUp " + result);
		
		return result;
	}
	
	public boolean setNextValue(){
		boolean result = true;
		
		Value value = values.get(lastSetValue);
		result = value.setNextValue();
//		System.out.println("setNextValue " + result);
		return result;
	}
	
	public boolean setNextValueForward(){
		boolean result = true;
		
		Value value = values.get(lastSetValue);
		result = value.setNextValueForward();
		
		return result;
	}
	
	public boolean setPreviousValue(){
		boolean result = true;
		
		Value value = values.get(lastSetValue);
		result = value.setPreviousValue();
		
		return result;
	}
	
	public void resetValues(){
		lastSetValue = 0;
		for(Value value : values){
			value.clearValue();
		}
	}
	
}
