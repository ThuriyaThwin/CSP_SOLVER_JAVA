package datamodels;

import java.util.LinkedList;

public class ValueLimits {
	
	private LinkedList<String> limits = new LinkedList<String>();
	
	public ValueLimits(){
		
	}
	
	public ValueLimits(ValueLimits valueLimits){
		this.limits = new LinkedList<String>(valueLimits.limits);
	}
	
	public LinkedList<String> getLimits(){
		return limits;
	}
	
	public void addLimit(String limit){
		limits.add(limit);
	}
	
	public void setLimits(LinkedList<String> limits){
		this.limits = limits;
	}
	
	

}
