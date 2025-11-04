package fr.ex.java;
import java.util.*;

public class Main {
	
	
	public static String formatCode(List<Integer> code) {
		
		StringBuilder formatedCode = new StringBuilder();
		for (Integer digit : code) {
			formatedCode.append(digit);
			
		}
		return formatedCode.toString();
	}
	
	public static void codeFeedback(List<Integer> secretCode, List<Integer> userGuess) {
		
	    List<Integer> unmatchedCode = new ArrayList<>();
	    List<Integer> unmatchedGuess = new ArrayList<>();
	    Set<Integer> alreadyReported = new HashSet<>();

	    // check la position
	    for (int i = 0; i < 4; i++) {
	        if (!userGuess.get(i).equals(secretCode.get(i))) {
	            unmatchedCode.add(secretCode.get(i));
	            unmatchedGuess.add(userGuess.get(i));
	            
	        }
	    }

	    // check les élément mal placé
	    for (int guessDigit : unmatchedGuess) {
	        if (unmatchedCode.contains(guessDigit)) {
	            System.out.println(guessDigit + " est correct mais mal placé! ");
	            unmatchedCode.remove((Integer) guessDigit);
	            alreadyReported.add(guessDigit);
	        }
	    }

	    // les éléme,nt corrects
	    for (int i = 0; i < 4; i++) {
	        if (userGuess.get(i).equals(secretCode.get(i))) {
	            System.out.println(userGuess.get(i) + " est correct ! ");
	            alreadyReported.add(userGuess.get(i));
	        }
	    }
	    
	    for (int digit : userGuess) {
	        if (!alreadyReported.contains(digit)) {
	            System.out.println(digit + " est incorrect !");
	        }
	    }
	}

	
	
	// This method generate 4 random digits (0-9) to make the code to guess
	public static List<Integer> generateRandomCode(){
		
		Random rand = new Random();
		List<Integer> code = new ArrayList<>();
		
		for (int i = 0; i <4 ; i++) {
			int digit = rand.nextInt(10);
			code.add(digit);
		}
		
		return code;
	}
	
	// This method take user input and check their validity
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
					System.out.println("Error: " + err +"\n");
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
		
		List<Integer> secretCode = generateRandomCode();
		int maxTry = 10;
		int actualTry = 0;
		boolean found = false;
		
		System.out.print("Le code a deviner: ");
		for (int i = 0; i < secretCode.size(); i++) {
            System.out.print("* ");
        }
        System.out.println();
		
        while (actualTry < maxTry && !found) {
        
        	System.out.println("\nEssai n°" + actualTry +" (" + (maxTry - actualTry) + " essais restant)");
        	List<Integer> userGuess = getValidCode();
        	
        	if (userGuess.equals(secretCode)) {
        		System.out.println("Félicitations! Vous avez deviné le code secret: " + formatCode(secretCode) +" en " + actualTry + " essais.");
                found = true;
        	} else {
        		codeFeedback(secretCode, userGuess);
                actualTry++;
        	}
        }
        
        if (!found) {
        	System.out.println("Vous avez perdu! Le code a trouver était: " + formatCode(secretCode));
        }
	

	}

}
