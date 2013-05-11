package datamodels;

import java.util.LinkedList;

public class CSPLimits {

	private LinkedList<String> limits = new LinkedList<String>();
	
	public CSPLimits(){
		
	}
	
	public CSPLimits(CSPLimits cspLimits){
		this.limits = new LinkedList<String>(cspLimits.limits);
	}
	
	public void addLimit(String limit){
		limits.add(limit);
	}

	public LinkedList<String> getLimits() {
		return limits;
	}

	public void setLimits(LinkedList<String> limits) {
		this.limits = limits;
	}
	
}
