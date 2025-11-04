package fr.ex.java;
import java.util.*;

public class Main {
	
	public static List<Integer> generateRandomCode(){
		
		Random rand = new Random();
		List<Integer> code = new ArrayList<>();
		
		for (int i = 0; i <4 ; i++) {
			int digit = rand.nextInt(10);
			code.add(digit);
		}
		
		return code;
	}
	
	
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
	            System.out.println("Vous devez entrer exactement 4 chiffres (de 0 a 9)!");
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
						System.out.println("Entrée invalide : " + value + "\n");
	                    allValid = false;
	                    break;
					}				
				} catch (NumberFormatException err) {
					System.out.println("Entrée invalide : " + mark + "\n");
	                allValid = false;
	                break;
				}
			}
			
			if (allValid && !codeList.isEmpty()) {
				validMarks = true;
			} else {
				System.out.println("Veuillez entre 4 chiffres: ");
			}
			
		}
		return codeList;
				
	}

	public static void main(String[] args) {
		
		List<Integer> mycode = generateRandomCode();
		
		System.out.print("Le code a deviner: ");
		for (int i = 0; i < mycode.size(); i++) {
            System.out.print("* ");
        }
        System.out.println();
		
		
		List<Integer> myGuess = getValidCode();
		
		System.out.println("Votre essai actuel: " + myGuess);
		
	
		

	}
	
	

}
