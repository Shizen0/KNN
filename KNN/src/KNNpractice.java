

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class KNNpractice {

	public static void main(String[] args) {
		
		ArrayList<Iris> irises = new ArrayList<Iris>();
		
		try {
			Scanner inFile = new Scanner(new File("iris.data"));
			
			while(inFile.hasNextLine()) {
				String line = inFile.nextLine();
				String[] pieces = line.split(",");
					
				irises.add(new Iris(Float.parseFloat(pieces[0]), Float.parseFloat(pieces[1]),
									Float.parseFloat(pieces[2]), Float.parseFloat(pieces[3]),
									pieces[4]));
				
			}
		} catch (FileNotFoundException e) {}
		
		Scanner scan = new Scanner(System.in);
		float sl,sw,pl,pw;
		sl = scan.nextFloat();
		sw = scan.nextFloat();
		pl = scan.nextFloat();
		pw = scan.nextFloat();
		Iris input = new Iris(sl, sw, pl, pw, "NA");
				
		for(Iris i: irises)
			i.distance(input);
		
		Collections.sort(irises);
		
		Map<String, Integer> irisCss = new HashMap<String, Integer>();
		for(int i = 0; i < 70; i++) {
			if(irisCss.containsKey(irises.get(i).cs))
				irisCss.put(irises.get(i).cs, 
					irisCss.get(irises.get(i).cs)+1);
			else irisCss.put(irises.get(i).cs, 1);
		}
		
		System.out.println(irisCss);
	}
	
} 

class Iris implements Comparable<Iris>{
	float pl, pw, sl, sw;
	String cs;
	double distance;
	
	Iris(float sl, float sw, float pl, float pw, String cs) {
		this.sl = sl;
		this.sw = sw;
		this.pl = pl;
		this.pw = pw;
		this.cs = cs;
	}
	
	public double distance(Iris input) {
		distance = Math.sqrt(Math.pow(this.sl - input.sl, 2) + Math.pow(this.sw - input.sw, 2) +
							 Math.pow(this.pl - input.pl, 2) + Math.pow(this.pw - input.pw, 2));
		return distance;
	}

	@Override
	public int compareTo(Iris iris) {
		if(this.distance > iris.distance)
			return 1;
		else if(this.distance < iris.distance)
			return -1;
		return 0;
	}
}