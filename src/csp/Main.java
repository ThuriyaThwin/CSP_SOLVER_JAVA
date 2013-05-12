package csp;

import utils.ProblemGenerator;
import utils.ProblemReader;
import datamodels.Problem;

public class Main {
	
	public static void main(String[] args){
//		ProblemGenerator generator = ProblemGenerator.getInstance();
		ProblemReader reader = ProblemReader.getInstance();
		StackMachine stackMachine = StackMachine.getInstance();
		
//		generator.generateHetmanProblem(9, "csp.txt");
		Problem problem = reader.readProblem("csp.txt");
		stackMachine.setValueNames(problem.getValueNames());
		
		Solver solver = Solver.getInstance();
		solver.setProblem(problem);
		solver.solveUsingForwardChecking();
//		solver.solveUsingBacktracking();
		
	}

}