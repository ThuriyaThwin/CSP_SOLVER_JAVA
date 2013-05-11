package csp;

import datamodels.CSPLimits;
import datamodels.Problem;

public class Solver {
	
	private static Solver solver;
	
	private Problem problemToSolve = null;
	private CSPLimits cspLimits = null;
	private StackMachine stackMachine = null;
	
	public static synchronized Solver getInstance(){
		if(solver == null)
			solver = new Solver();
		
		return solver;
	}
	
	public void setProblem(Problem problem){
		problemToSolve = new Problem(problem);
		cspLimits = problemToSolve.getCspLimits();
		
		stackMachine = StackMachine.getInstance();
		stackMachine.setValueNames(problemToSolve.getValueNames());
	}
	
	public void solveUsingBacktracking(){
		while(!problemToSolve.allValuesSet()){
			
		}
	}
	
	private boolean checkLimits(){
		boolean result = true;
		
		
		for(String limit : cspLimits.getLimits()){
			if(stackMachine.checkString(limit))
				System.out.println("TRUE " + limit);
			else{
				System.out.println("FALSE " + limit);
				return false;
			}
		}
		
		
		return result;
	}

}
