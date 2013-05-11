package datamodels;

public class Value {
	
	private String content;
	private ValueLimits limits = new ValueLimits();;
	private boolean isSet;
	private String value = "null";
	
	private int takenLimitId = -1;
	
	public Value(Value value){
		this.content = new String(value.content);
		this.limits = new ValueLimits(value.limits);
		this.isSet = new Boolean(value.isSet);
		this.value = new String(value.value);
		
		this.takenLimitId = new Integer(value.takenLimitId);
	}
	
	public Value(String content){
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ValueLimits getLimits() {
		return limits;
	}

	public void setLimits(ValueLimits limits) {
		this.limits = limits;
	}
	
	public void addLimit(String limit){
		limits.addLimit(limit);
	}

	public boolean isSet() {
		return isSet;
	}

	public void setSet(boolean isSet) {
		this.isSet = isSet;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public boolean setNextValue(){
		++takenLimitId;
		
		if(takenLimitId < limits.getLimits().size()){
			value = limits.getLimits().get(takenLimitId);
			isSet = true;
			return true;
		} else {
			--takenLimitId;
			return false;
		}
	}
	
	public boolean setPreviousValue(){
		--takenLimitId;
		
		if(takenLimitId < 0){
			takenLimitId = 0;
			return false;
		} else {
			value = limits.getLimits().get(takenLimitId);
			isSet = true;
			return true;
		}
	}
	
	public void clearValue(){
		isSet = false;
		value = "null";
		takenLimitId = -1;
	}

}
