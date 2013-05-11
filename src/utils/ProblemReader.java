package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import datamodels.CSPLimits;
import datamodels.Problem;
import datamodels.Value;

public class ProblemReader {
	
	private static ProblemReader reader;
	
	public static synchronized ProblemReader getInstance(){
		if (reader == null)
			reader = new ProblemReader();
		
		return reader;
	}
	
	
	public Problem readProblem(String fileName){
		Problem problem = new Problem();
		
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(fileName));
			
			String line = br.readLine();
			
			String[] values = line.split(" ");
			
			System.out.println(" PROBLEM READER " + line);
			
			for(String s: values){
				Value value = new Value(s);
				problem.addValue(value);
			}
			
			int n = problem.getValues().size();
			
			for(int i = 0; i < n; i++){
				line = br.readLine();
				
				String[] valueLimits = line.split(" ");

				Value value = problem.getValueAt(i);
				
				for(String s: valueLimits){
					
					value.addLimit(s);
					
				}
			}
			
			CSPLimits cspLimits = new CSPLimits();
			while((line = br.readLine()) != null){
				cspLimits.addLimit(line);
			}
			
			problem.setCspLimits(cspLimits);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return problem;
	}

}
