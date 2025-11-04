package fr.ex.java;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static List<Integer> getValidCode(){
		
		Scanner scan =  new Scanner(System.in);
		
		List<Integer> codeList = new ArrayList<>();
		boolean validMarks = false;
		
		while (!validMarks) {
			System.out.println("Veuillez saisir votre code a 4 chiffre : ");
			String marks = scan.nextLine();
			String[] codeArray = marks.trim().split("\\s+");
			
			// Check if exactly 4 values were entered
	        if (codeArray.length != 4) {
	            System.out.println("You must enter exactly 4 digits!");
	            continue;
	        }
			
			codeList.clear();
			boolean allValid = true;
			
			for (String mark : codeArray) {
				try {
					int value = Integer.parseInt(mark);
					if (value >=0 && value <= 9) {
						codeList.add(value);
					} else {
						System.out.println("Invalid mark (must be between 0 and 9): " + value);
	                    allValid = false;
	                    break;
					}				
				} catch (NumberFormatException err) {
					System.out.println("Invalid input: " + mark);
	                allValid = false;
	                break;
				}
			}
			
			if (allValid && !codeList.isEmpty()) {
				validMarks = true;
			} else {
				System.out.println("Please re enter valid code!");
			}
			
		}
		return codeList;
				
	}

	public static void main(String[] args) {
		
		int mycode = 2657;
		List<Integer> myGuess = getValidCode();
		
		System.out.println("Your guess: " + myGuess);
		
		
		
		
		

	}

}
