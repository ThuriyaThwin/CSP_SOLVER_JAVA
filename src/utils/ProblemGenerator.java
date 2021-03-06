package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ProblemGenerator {

	private static ProblemGenerator generator;

	public static synchronized ProblemGenerator getInstance() {
		if (generator == null)
			generator = new ProblemGenerator();

		return generator;
	}
	
	public String generateMagicSquare(int n, String fileName){
		String filePath = fileName;
		BufferedWriter bw = null;
		
		try{
			
			bw = new BufferedWriter(new FileWriter(filePath));
			
			for(int i = 0; i<n*n; i++){
				bw.write("x" + (i + 1) +" ");
			}
			
			bw.newLine();
			
			for (int i = 0; i < n*n; i++) {
				for (int j = 0; j < n*n; j++) {
					bw.write((j + 1) + " ");
				}
				bw.newLine();
			}
			
			for (int i = 0; i < n*n; i++)
				bw.write("x" + (i + 1) + " ");

			bw.write(n*n + " rozne");
			bw.newLine();
			
			int s = (n * (n*n +1))/2;
			
			for(int i=0; i<n; i++){
				
				for(int j=1; j<n+1; j++){
					bw.write("x"+(i*n+j)+" ");
				}
				
				for(int j=1; j<n; j++){
					bw.write("+ ");
				}
				
				bw.write(s+" =");
				
				bw.newLine();
			}
			
			for(int i=1; i<n+1; i++){
				
				for(int j=0; j<n; j++){
					bw.write("x"+(j*n+i)+" ");
				}
				
				for(int j=1; j<n; j++){
					bw.write("+ ");
				}
				
				bw.write(s+" =");
				
				bw.newLine();
			}
			
			
			
			
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.flush();
					bw.close();
				}
			} catch (IOException e) {
				System.out.println("Exception: " + e);
			}

		}
		
		return filePath;
	}

	public String generateHetmanProblem(int n, String fileName) {
		String filePath = fileName;
		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter(filePath));

			for (int i = 0; i < n; i++)
				bw.write("x" + (i + 1) + " ");

			bw.newLine();

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					bw.write((j + 1) + " ");
				}
				bw.newLine();
			}

			for (int i = 0; i < n; i++)
				bw.write("x" + (i + 1) + " ");

			bw.write(n + " rozne");
			bw.newLine();

			for (int i = 0; i < n - 1; i++) {
				for (int j = i + 1; j < n; j++) {
					bw.write("x" + (i + 1) + " x" + (j + 1) + " - || " + ((j + 1)-(i + 1))  + " <>");
					if(i != n)
						bw.newLine();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.flush();
					bw.close();
				}
			} catch (IOException e) {
				System.out.println("Exception: " + e);
			}

		}

		return filePath;
	}

}
