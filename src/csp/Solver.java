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
		stackMachine.setProblem(problemToSolve);
	}
	
	public void solveUsingBacktracking(){
		long startTime = System.nanoTime();
		System.out.println("Started solving");
		boolean stop = false;
		while(!problemToSolve.allValuesSet()){
			
			while(!problemToSolve.setNextValue()){
				if(!problemToSolve.goOneLevelUp()){
					stop = true;
					break;
				}
			}
			
			if(stop)
				break;

			boolean checkLimits = true;
			
			while(!(checkLimits = stackMachine.checkLimits(cspLimits))){
				if(!problemToSolve.setNextValue())
					break;
			}
			
			if(!checkLimits){
				problemToSolve.goOneLevelUp();
			}
			else{
				problemToSolve.goOneLevelDown();
			}
			
//			problemToSolve.printValues();
		}
		long totalTime = (System.nanoTime() - startTime)/1000000;
		System.out.println("Found in: " + totalTime + " ms");
		problemToSolve.printValues();
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
