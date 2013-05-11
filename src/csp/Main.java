package csp;

import utils.ProblemGenerator;
import utils.ProblemReader;
import datamodels.CSPLimits;
import datamodels.Problem;

public class Main {
	
	public static void main(String[] args){
		ProblemGenerator generator = ProblemGenerator.getInstance();
		ProblemReader reader = ProblemReader.getInstance();
		StackMachine stackMachine = StackMachine.getInstance();
		
		generator.generateHetmanProblem(7, "csp.txt");
		Problem problem = reader.readProblem("csp.txt");
		stackMachine.setValueNames(problem.getValueNames());
		
		System.out.println(problem);
		
		
		System.out.println("Checking at first run with empty values");
		stackMachine.setProblem(problem);
		
		CSPLimits limits = problem.getCspLimits();
		
		for(String limit : limits.getLimits()){
			if(stackMachine.checkString(limit))
				System.out.println("TRUE " + limit);
			else
				System.out.println("FALSE " + limit);
		}
		
		System.out.println("\nRandomizing \n");
		
		problem.randomizeValues();
		
		System.out.println("");
		
		
		System.out.println("");
		int i = 0;
		int done = 0;
		
		while(done<28){
			problem.randomizeValues();
			for(String limit : limits.getLimits()){
				

				if(stackMachine.checkString(limit)){
					System.out.println("TRUE " + limit);
					++done;
				}
				else{
					System.out.println("FALSE " + limit);
					done = 0;
					break;
				}
				
			}
		}

		
		problem.printValues();
		
	}

}