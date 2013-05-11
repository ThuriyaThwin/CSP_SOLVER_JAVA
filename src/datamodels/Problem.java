package datamodels;

import java.util.LinkedList;
import java.util.Random;

public class Problem {

	private LinkedList<Value> values = new LinkedList<Value>();
	private CSPLimits cspLimits;
	
	private int lastSetValue = -1;
	
	public Problem(){
		
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
			if(!value.isSet())
				return false;
		
		
		return true;
	}
	
	public boolean goOneLevelDown(){
		boolean result = true;
		
		if(lastSetValue < values.size() -1){
			++lastSetValue;
		} else {
			result = false;
		}
		
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
		
		return result;
	}
}
