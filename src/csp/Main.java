package csp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import datamodels.Problem;

import utils.ProblemGenerator;
import utils.ProblemReader;

public class Main {
	
	public static void main(String[] args){
		ProblemGenerator generator = ProblemGenerator.getInstance();
		ProblemReader reader = ProblemReader.getInstance();
		StackMachine stackMachine = StackMachine.getInstance();
		
//		generator.generateHetmanProblem(8, "csp.txt");
//		generator.generateMagicSquare(4, "csp.txt");
//		Problem problem = reader.readProblem("csp.txt");
//		stackMachine.setValueNames(problem.getValueNames());
//		
//		Solver solver = Solver.getInstance();
//
//		
//		solver.setProblem(problem);
//		System.out.println("Forward Checking: ");
//		solver.solveUsingForwardChecking();
//		
//		System.out.println("");
		
//		problem = reader.readProblem("csp.txt");
//		solver.setProblem(problem);
//		System.out.println("BackTracking: ");
//		solver.solveUsingBacktracking();
//		
		
		
		System.out.println("CSP Program Interface:");
		BufferedReader br;
		
		br =  new BufferedReader(new InputStreamReader(System.in));
		System.out.println("[H] Generate Hetman Problem");
		System.out.println("[M] Generate Magic Square Problem");
		
		try {
			String line = br.readLine();
			System.out.println("Please input the N value:");
			String lalala = br.readLine();
			int n = Integer.valueOf(lalala);
			boolean isMS = false;
			switch(line){
			case "H":
				generator.generateHetmanProblem(n, "csp.txt");
				break;
			case "M":
				isMS = true;
				generator.generateMagicSquare(n, "csp.txt");
				break;
			default:
				System.out.println("Error");
				break;
			}
			
			
			// Tutaj juz rozwiazywanie
			Problem problem = null;
			if(isMS)
				problem = reader.readProblemMS("csp.txt");
			else
				problem = reader.readProblem("csp.txt");
			stackMachine.setValueNames(problem.getValueNames());
			Solver solver = Solver.getInstance();
			
			solver.setProblem(new Problem(problem));
			
			System.out.println("[F] Forward Checking");
			System.out.println("[B] Backtracking");
			
			lalala = br.readLine();
			
			switch(lalala){
			case "F":
				System.out.println("Forward Checking: ");
				solver.solveUsingForwardChecking();
				break;
			case "B":
				System.out.println("Backtracking: ");
				solver.solveUsingBacktracking();
				break;
			default:
				System.out.println("Error");
				break;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}