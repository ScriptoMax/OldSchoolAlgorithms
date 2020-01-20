import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MainStuff {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int var1, var2, var3;
		String [] inputs;
		ArrayList <Integer> numInputs = new ArrayList<>();
		ArrayList <Integer> numInputsCopy = new ArrayList<>();
		
		
		System.out.println("#1: enter 3 space-divided integer numbers to find a median of them:");
		inputs = scan.nextLine().split("\\s"); 
		
		var1 = Integer.parseInt(inputs[0]);
		var2 = Integer.parseInt(inputs[1]);
		var3 = Integer.parseInt(inputs[2]);
		
		//#1 stuff entry 
		int medianRes = threeNumMedian(var1, var2, var3);		
		System.out.println("A median found is: " + medianRes + "\n");
		
		System.out.println("#2: enter a number series in a single line:");
		inputs = scan.nextLine().split("\\s"); 
		for(int i = 0; i < inputs.length; i++) {
			numInputs.add(Integer.parseInt(inputs[i])); 
		}
		
		//#2 stuff entry 
		System.out.println("Enter a number to fetch away:");
		var1 = scan.nextInt();
		
		Iterator< Integer> it = numInputs.iterator(); 
		while(it.hasNext())
				numInputsCopy.add(it.next());		
		
		String removeRes = removeElem(numInputs, var1); 
		System.out.println("Your input sequence after complete removal of `" + var1 
				+ "`:\n" + removeRes + "\n");
		
		//#3 stuff entry 
		System.out.println("#3: enter any integer number to get reversed one: ");
		int reverseRes = reverseNum(scan.nextInt(), 0);
		System.out.println("Your number gets reversed to: " + reverseRes + "\n");
		
		//#4 stuff entry 
		System.out.println("#4: your 2nd case sequence to be reused. It contains elements:");
		for(int a : numInputsCopy) {
			System.out.print(a + " "); 
		}
		int pivotRes = findPivot(numInputsCopy);
		System.out.println("\nPivot element (different from max much the same as to minimum one): " + pivotRes + "\n");        
        
        //#5 stuff entry
		System.out.println("#5: enter two positive numbers - one to set a base and other to be applied as a power value");
		var1 = scan.nextInt();
		var2 = scan.nextInt();
		long powerRes = calcPow(var1, var2);
		System.out.println(var1 + " raised to power of " + var2 + " is: " + powerRes);
		
		scan.close();
        
	}
	
	// Case 1: find a median in three number input 
	public static int threeNumMedian(int a, int b, int c) {
		int x, y, s;
		
		x = a - b;
		y = b - c;
		s = a - c;
		
		if(x * y > 0)
			return b;
		
		if(x * s > 0)
			return c;
		else
			return a;		
	}
	
	// Case 2: remove each occurrence of a specific number from an array input
	public static String removeElem(ArrayList <Integer> nums, int toRemove) {
		int x = 0; 
		int removeCounter = 0;
		
		for(int i = 0; i < nums.size(); i++) {
			if(nums.get(i) != toRemove) {
				nums.set(x++, nums.get(i));
			} else 
				removeCounter++;			
		}
		
		for(int i = removeCounter; i > 0; i--) {
			nums.remove(nums.size() - i);
		}
				
		String resultString = "";
		Iterator<Integer> iterator = nums.iterator(); 
		
		while (iterator.hasNext()) {
			resultString += Integer.toString(iterator.next());
			resultString += " ";
		}
		
		return resultString;
	}
	
	// Case 3: make `reverse` operation over a positive number
	public static int reverseNum(int a, int res) {
		if(a == 0)
			return res;
		else {
			res = res * 10 + a % 10;
			return reverseNum(a/10 , res);
		}
	}
	
	/*
	Case 4: search for an array element (calling it "pivot" means nothing but a purely personal preference) 
	which is of the same or much the same difference to array maximum number as to minimum one 
	*/   
	public static int findPivot(ArrayList <Integer> nums) {
		if(nums.size() < 3) {
			System.out.println("To initialize a pivot element search, you have to enter three or more numbers as a sequence");
			return -1;
		} else if(nums.size() == 3) {
			threeNumMedian(nums.get(0), nums.get(1), nums.get(2));
		}
		 
		int max = nums.get(0) > nums.get(1) ? nums.get(0) : nums.get(1);
		int min = max == nums.get(0) ? nums.get(1) : nums.get(0);
		int pivot = nums.get(0);
		int avg, pivotDiff;  
		int i = 2;
		
		while(i < nums.size()) {
			int num = nums.get(i);
			if(num > max) 
			    max = num;
			else if(num < min) 
			    min = num;	
			i++;
		}
		
		avg = (max + min) / 2;
		pivotDiff = Math.abs(pivot - avg);
		
		i = 1;
		while(i < nums.size()) {
			int num = nums.get(i);
			if(Math.abs(num - avg) < pivotDiff) {
				pivotDiff = Math.abs(num - avg);
			    pivot = num;
			}
			i++;
		}		
		return pivot;
	}
	
	//case 5: raise a positive number (first argument) to specific power (second argument)
	public static long calcPow(int a, int b) {
	     if(b == 0)
	    	 return 1;
	     else if(b == 1)
	    	 return a;
	     else 
	    	 return a * calcPow(a, b - 1);			
	}	
}
