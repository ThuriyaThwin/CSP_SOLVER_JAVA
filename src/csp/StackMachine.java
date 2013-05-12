package csp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

import datamodels.CSPLimits;
import datamodels.Problem;

public class StackMachine {

	private Stack<String> stack = new Stack<String>();
	private Problem problemToCheck = null;

	private LinkedList<String> valueNames = new LinkedList<String>();

	private static StackMachine stackMachine;

	public static final String TRUE = "true";
	public static final String FALSE = "false";
	public static final String EQUAL = "=";
	public static final String NOTEQUAL = "<>";
	public static final String GREATER_THAN = ">";
	public static final String LOWER_THAN = "<";
	public static final String MODULO = "||";
	public static final String EXTRACT = "[]";
	public static final String DIFFERENT = "rozne";
	public static final String ADD = "+";
	public static final String SUBSTRACT = "-";
	public static final String MULTIPLY = "*";

	public static synchronized StackMachine getInstance() {
		if (stackMachine == null)
			stackMachine = new StackMachine();

		return stackMachine;
	}

	public void push(String s) {
		stack.push(s);
	}

	public String pop() {
		return stack.pop();
	}

	public void setProblem(Problem problem) {
		problemToCheck = problem;
	}
	
	public boolean checkLimits(CSPLimits limits){
		boolean result = true;
		
		for(String limit : limits.getLimits()){
			if(!checkString(limit)){
				result = false;
				break;
			}
		}
		
		return result;
	}

	public boolean checkString(String toCheck) {
		stack.clear();
		String[] splitted = toCheck.split(" ");

		for (String s : splitted) {
			stack.push(s);
		}

		while (stack.size() > 1) {

			if (isOperator(stack.peek()))
				handleOperator();
		}
		
//		System.out.println(Arrays.toString(splitted) + " " + stack.peek().equals(TRUE));

		return stack.pop().equals(TRUE);
	}

	private void handleOperator() {
		String operator = stack.pop();

		// System.out.println("Handling operator: " + operator);
		switch (operator) {
		case EQUAL:
			handleStackEqual();
			break;
		case NOTEQUAL:
			handleStackNotEqual();
			break;
		case SUBSTRACT:
			handleStackSubstract();
			break;
		case MODULO:
			handleStackModulo();
			break;
		case DIFFERENT:
			handleStackDifferent();
			break;
		case ADD:
			handleStackAdd();
			break;
		default:
			break;
		}
	}

	private String getValueFromStack() {
		while (isOperator(stack.peek()))
			handleOperator();
		
		String result = "null";

		String stackTop = stack.pop();

		if (valueNames.contains(stackTop)){
			result = problemToCheck.getValueByName(stackTop);
		}
		else{
			result = stackTop;
		}

		// System.out.println("getValueFromStack " + stackTop + " : " + result);

		return result;
	}

	private void handleStackEqual() {

		String first = getValueFromStack();
		String second = getValueFromStack();

		if (!stack.isEmpty())
			while (isOperator(stack.peek()))
				handleOperator();

		if (checkIsValueIsNull(first) || checkIsValueIsNull(second))
			stack.push(TRUE);
		else {
			if (first.equals(second))
				stack.push(TRUE);
			else
				stack.push(FALSE);
		}

	}

	private void handleStackNotEqual() {

		String first = getValueFromStack();
		String second = getValueFromStack();

		if (!stack.isEmpty())
			while (isOperator(stack.peek()))
				handleOperator();

		if (checkIsValueIsNull(first) || checkIsValueIsNull(second))
			stack.push(TRUE);
		else {
			if (first.equals(second))
				stack.push(FALSE);
			else
				stack.push(TRUE);
		}

	}

	private void handleStackSubstract() {

		String first = getValueFromStack();

		String second = getValueFromStack();

		if (checkIsValueIsNull(first) || checkIsValueIsNull(second))
			stack.push("null");
		else {
			int firstValue = Integer.valueOf(first);
			int secondValue = Integer.valueOf(second);

			int result = firstValue - secondValue;

			stack.push(String.valueOf(result));
		}

	}
	
	private void handleStackAdd() {
		String first = getValueFromStack();
		String second = getValueFromStack();
		
		if (!stack.isEmpty())
			while (isOperator(stack.peek()))
				handleOperator();
		
		if(checkIsValueIsNull(first) || checkIsValueIsNull(second))
			stack.push("null");
		else{
			int firstValue = Integer.valueOf(first);
			int secondValue = Integer.valueOf(second);
			
			int result = firstValue + secondValue;
			
			stack.push(String.valueOf(result));
		}
	}

	private void handleStackModulo() {

		String value = getValueFromStack();

		if (checkIsValueIsNull(value))
			stack.push("null");
		else {
			value = value.replace("-", "");
			stack.push(value);
		}

	}

	private void handleStackDifferent() {
		while (isOperator(stack.peek()))
			handleOperator();

		String stringNumberOfValues = stack.pop();
		int numberOfValues = Integer.valueOf(stringNumberOfValues);

		LinkedList<String> values = new LinkedList<String>();

		for (int i = 0; i < numberOfValues; i++) {
			String stackTop = stack.pop();

			if (!values.contains(stackTop) || checkIsValueIsNull(stackTop))
				values.add(problemToCheck.getValueByName(stackTop));
		}

		if (values.size() == numberOfValues) {
			for(int i=0; i<values.size() - 1; i++){
				String firstValue = values.get(i);
				for(int j = i+1; j<values.size(); j++){
					String secondValue = values.get(j);
					
					if(firstValue.equals(secondValue) && (!checkIsValueIsNull(firstValue) && !checkIsValueIsNull(secondValue))){
						stack.push(FALSE);
						return;
					}
				}
			}
			stack.push(TRUE);
			return;
		} else
			stack.push(FALSE);

	}

	private boolean isOperator(String string) {
		switch (string) {
		case EQUAL:
		case NOTEQUAL:
		case GREATER_THAN:
		case LOWER_THAN:
		case MODULO:
		case EXTRACT:
		case DIFFERENT:
		case ADD:
		case SUBSTRACT:
		case MULTIPLY:
			return true;
		default:
			return false;
		}
	}

	private boolean checkIsValueIsNull(String value) {
		return value.equals("null");
	}

	public void setValueNames(LinkedList<String> valueNames) {
		this.valueNames = valueNames;
	}

}
