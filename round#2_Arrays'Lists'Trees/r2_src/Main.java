import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

/*Sample number sequences to test solutions for cases 1-4 (considering case 3 solution, each test number to be entered
 one per line. Keep 'line-by-line' entry validity convention until forcing interruption by a specific character or number input)    
-32.54 -18.2583 -11.24 -7.9102 -2 0.78 2.8125 5.75 7.154 10.07 12.742 17 23.1 28.834 31.8 40.73
-57.921 -41.28 -24.27909 -13.67 -9.62 -1.87 2.6 4 7.98 14.57 20.9 25.6 30.57 35.8 39.1926 68
*/

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		scan.useLocale(Locale.US);
		String[] strArray;
		ArrayList<Double> numList1 = new ArrayList<>();
		ArrayList<Double> numList2 = new ArrayList<>();
		Locale justLocale = Locale.getDefault();
		DecimalFormatSymbols altChar = new DecimalFormatSymbols(justLocale);
		altChar.setDecimalSeparator(',');
		altChar.setDecimalSeparator('.');				
		DecimalFormat dF = new DecimalFormat("0.#############", altChar);
		int i = 0;
			
		//case #1: merge two sorted arrays to a single structure preserving ascending order
		try {
			System.out.println("Enter two ascending number rows, each in single line, dividing inputs by space (both integer and real numbers are valid):");
			strArray = scan.nextLine().split("\\s+");
			while(i < strArray.length) {
				numList1.add(Double.parseDouble(strArray[i]));
				i++;
			}
		} catch(NumberFormatException exc) {
			System.out.println("Well, chum, an empty list doesn't make any worries to me.\nYour crash test is just a ridiculous blow. Go ahead with second row:");		
		}
		
		try {
			strArray = scan.nextLine().split("\\s+");
			i = 0;
			while(i < strArray.length) {
				numList2.add(Double.parseDouble(strArray[i]));
				i++;
			}	
		} catch(NumberFormatException exc) {
			System.out.println("Still vigil here. Although reluctantly, your empty list will be fetched too");		
		} 
		
		Optional <ArrayList<Double>> mergeResult = mergeToSorted(numList1, numList2);
		if(!mergeResult.isPresent())
			System.out.println("Are you serious? Both sequences you've input are nothing but empty lines");
		else {
			System.out.println("What do you want to do with all that stuff, really? Just humble interest, there is a merged sequence (hoping, indeed):");
			numList1 = mergeResult.get();
			for(Double num : numList1) 
				System.out.print(dF.format(num) + " ");			
		}		
		
		//case #2: implement binary search algorithm
		i = 0;
		do {
			try {
				 System.out.println("\n\nEnter an ascending number sequence dividing inputs by space (both integer and real numbers are valid):");
			     strArray = scan.nextLine().split("\\s+");					
			     while(i < strArray.length) {
					numList1.add(Double.parseDouble(strArray[i]));
					i++;
				}
			} catch(NumberFormatException exc) {
				System.out.println("Are you serious? Your input is an empty list. Let's try it next time");				
			}
		} while(numList1.isEmpty());
		
		System.out.println("Enter some number to search for it through your sequence:");
		double val = scan.nextDouble();
		Optional<Integer> output = runBinarySearch(numList1, val);
		if(!output.isPresent())
			System.out.println("Are you serious? Your input is an empty list");
		else if(output.get() == numList1.size())
			System.out.println("What a nuisance! No match of " + dF.format(val) + " was found as long as the sequence is");
		else
			System.out.println("Cheers! " + dF.format(val) + " was found at position of " + output.get());		
		
				
		//case #3.1: design array-based stack-featured data structure
		ArrayBasedStack<Double> arrayStack = new ArrayBasedStack<>(10);
		System.out.println("Is stack empty so far? " + arrayStack.isEmpty());
		System.out.println("In different lines, enter some numbers to fill stack array (enter \"0\" to end series):");
		double x = scan.nextDouble(); 
		try {
			while(true) {			
					if(x == 0)
						break;
					else {
						arrayStack.push(x);	
						System.out.println("Just pushed: " + dF.format(x));
					}
					x = scan.nextDouble();			 
			} 
				
			System.out.println("Actual number of stack elements: " + arrayStack.size());
			System.out.println("Element on top: " + dF.format(arrayStack.getOnTop()));
			System.out.println("\nStart taking elements off\n");
		} catch (FullStackException e) {
			System.out.println("Actual number of stack elements: " + arrayStack.size());
		} catch(ArrayIndexOutOfBoundsException exc) {
			System.out.println("What about entering any relevant numbers? You don't seem feeding something pushable here.");
		}	
		
		while(!arrayStack.isEmpty()) {
			System.out.println("Element on top: " + dF.format(arrayStack.getOnTop()));
			try {
				double removed = arrayStack.pop();
				System.out.println(removed + " away from stack");
				System.out.println("Actual number of stack elements: " + arrayStack.size() + "\n");
			} catch (EmptyStackException e) {
				System.out.println("Actual number of stack elements: " + arrayStack.size());
			} 		
		}
		
		System.out.println("Actual number of stack elements: " + arrayStack.size() + "\n\n");
		
		System.out.println("What would happen if anyone can no longer remember how to create an array-based stack?\n"
				+ "I gonna be sure, list to become a first solution which would save all that geek crowd in the array-less future.\n");
		//case #3.2: implement linked list-based stack-featured data structure
		ListBasedStack <Character> listStack = new ListBasedStack<>();
		System.out.println("Is stack empty so far? " + listStack.isEmpty());
		System.out.println("In different lines, enter some characters to fill stack-implementing list (enter \"/\" to end series):");
		char v = scan.next().charAt(0); 
		while(true) {			
		    if(v == '/')
				break;
			else {
				listStack.push(v);	
				System.out.println("Just pushed: " + v);
			}
			v = scan.next().charAt(0);			 
		} 
				
		System.out.println("Actual number of stack elements: " + listStack.size());
		System.out.println("Element on top: " + listStack.getOnTop());
		System.out.println("\nStart taking elements off\n");
				
		while(!listStack.isEmpty()) {
			System.out.println("Element on top: " + listStack.getOnTop());
			try {
				char removedChar = listStack.pop();
				System.out.println(removedChar + " away from stack");
				System.out.println("Actual number of stack elements: " + listStack.size() + "\n");
			} catch (EmptyStackException e) {
				System.out.println("Actual number of stack elements: " + listStack.size());
			} 		
		}
		
		System.out.println("Actual number of stack elements: " + listStack.size() + "\n\n");
		
		//case #4 (told to be a kind of nightmare): make List reverse 
		System.out.println("Are you ready to proceed? Let's enter some deal of numbers (in a single line) to initialize a reversible list: ");
		List<Double> origList = new ArrayList<>();
		try {
			strArray = scan.nextLine().split("\\s+");
			i = 0;
			while(i < strArray.length) {
				origList.add(Double.parseDouble(strArray[i]));
				i++;
			}	
		} catch(NumberFormatException exc) {
			System.out.println("Whenever you would do that, your 'fake input' cheat isn't any sort of surprise.\n"
					+ "Empty entry just fixed, next ones will be treated same way also.");	
			return;
		}  
		
		System.out.print("May you remember this row of numbers? Right, there is somewhat of your initial input: \n");
		origList.forEach(it -> System.out.print(dF.format(it) + " "));
		
		System.out.println("\nAs a reverse procedure is over, your input looks a little bit different, isn't?\n"
				+ "(Hopefully, the sequence you've fed to process doesn't consist of identic numbers)");
		revLinkedList(origList).forEach(it -> System.out.print(dF.format(it) + " "));
				
		//case #5: make a BST just to go to walk through that   
		System.out.println("\nGet calm and enter some numbers (divide them by spaces in a single line) to go walking through a "
				+ "binary search tree: ");
		BST tree = new BST(); 
		try {	
			strArray = scan.nextLine().split("\\s+");
			for(String str : strArray) {
				double elem = Double.parseDouble(str);
				tree.runInsert(elem);
			}
		} catch(NumberFormatException exc) {
			System.out.println("You're likely to be an avid cheater, please don't mind."
					+ "\nAt any rate, it doesn't cost too much here to sweep away empty inputs, fairly.");	
			return;
		}  
		System.out.println("BST contains sorted node values as follows (based on in-order traversal technique):");
		tree.walkInOrder(tree.node);
		System.out.println("\nLet's jump across BST levels rather than taking a subtrees shuttle");
		tree.walkCrossLevels();
		scan.close();
	}
	
	//case #1  
	public static Optional<ArrayList<Double>> mergeToSorted(ArrayList<Double> list1, ArrayList<Double> list2) {
		 if(list1.isEmpty() && list2.isEmpty())
			 return Optional.ofNullable(null);
		 else if(list1.isEmpty())
			 return Optional.of(list2);
		 else if(list2.isEmpty()) 
			 return Optional.of(list1);
		 
		 ArrayList<Double> resList = new ArrayList<>();
		 int i = 0, j = 0;
		 
		 while(i < list1.size() && j < list2.size()) {
			   if(list1.get(i) <= list2.get(j)) {
				   resList.add(list1.get(i));
				   i++;
			   } else {
				   resList.add(list2.get(j));
				   j++;
			   }
		 }
		 
		 if(i < list1.size()) {
			 while(i < list1.size())
				 resList.add(list1.get(i++));
		 } else if(j < list2.size()) {
			 while(j < list2.size())
				 resList.add(list2.get(j++));
		 }
		 
		 return Optional.of(resList);	 
	 }	

	 //case #2 
	 public static Optional<Integer> runBinarySearch(ArrayList<Double> list, double val) {
		if(list.size() == 0) 
			return Optional.ofNullable(null);
		else if(val > list.get(list.size() - 1) || val < list.get(0))
            return Optional.of(list.size());
				
		int left = 0;
		int right = list.size() - 1;
				
		while(left <= right) {
			int mean = (left + right) / 2;
			if(list.get(mean) == val)
				return Optional.of(mean);
			else if(list.get(mean) > val) {
				right = mean - 1;
			} else {
				left = mean + 1;
			}			
		}
				
		return Optional.of(list.size());
	 }		
	 
	 //case #4
	 public static List<Double> revLinkedList(List<Double> list) {
		 if(list.isEmpty() || list.size() == 1) 
			 return list;
		 Double head = list.remove(0);
		 revLinkedList(list);
		 list.add(head);
		 
		 return list;
	 }
}
	

