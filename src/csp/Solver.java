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
		
		int answersFound = 0;
		
		while(!problemToSolve.allValuesSet()){
			
			if(!changeValues())
				break;

			boolean checkLimits = true;
			
			while(!(checkLimits = checkLimits())){
				if(!problemToSolve.setNextValue())
					break;
			}
			
			if(!checkLimits){
				problemToSolve.goOneLevelUp();
			}
			else{
				problemToSolve.goOneLevelDown();
			}
			
			if(problemToSolve.allValuesSet() && checkLimits){
				++answersFound;
//				problemToSolve.printValues();
				
				while(problemToSolve.allValuesSet()){
					if(changeValues()){
						if(problemToSolve.allValuesSet() && checkLimits()){
							++answersFound;
//							problemToSolve.printValues();
						}
					}
					else
						break;
				}
			}
			
		}
		long totalTime = (System.nanoTime() - startTime)/1000000;
		System.out.println("Found "+ answersFound +" in: " + totalTime + " ms");
	}
	
	private boolean changeValues(){
		boolean result = true;
		while(!problemToSolve.setNextValue()){
			if(!problemToSolve.goOneLevelUp()){
				result = false;
				break;
			}
		}
		
		return result;
	}
	
	private boolean checkLimits(){
		boolean result = true;
		
		
		for(String limit : cspLimits.getLimits()){
			if(!stackMachine.checkString(limit))
				return false;
		}
		
		
		return result;
	}

}
