package fr.ex.java;
import java.util.*;

public class Main {
	
	/**
     * Updates the masked secret code based on the user's guess
     * Correct digits in the correct position are shown, others are masked with '*'
     *
     * @param secretCode the secret 4-digit code to guess
     * @param userGuess the user's current guess
     * @return a masked string showing correct digits and masking others
     */
	public static String updateSecretCodeDisplay(List<Integer> secretCode, List<Integer> userGuess) {
		StringBuilder display = new StringBuilder();
	    List<Integer> unmatchedCode = new ArrayList<>();
	    List<Integer> unmatchedGuess = new ArrayList<>();
		
	    // Used to identify unmatched digits
	    for (int i = 0; i < 4; i++) {
	        if (!userGuess.get(i).equals(secretCode.get(i))) {
	            unmatchedCode.add(secretCode.get(i));
	            unmatchedGuess.add(userGuess.get(i));
	        }
	    }

	 // Build the masked display
	    for (int i = 0; i < 4; i++) {
	        int guessDigit = userGuess.get(i);
	        if (guessDigit == secretCode.get(i)) {
	        	// Correct and well placed
	            display.append(guessDigit); 
	        } else if (unmatchedCode.contains(guessDigit)) {
	        	// Misplaced
	            display.append("*"); 
	            unmatchedCode.remove((Integer) guessDigit);
	        } else {
	        	//Incorrect
	            display.append("*"); 
	        }
	    }

	    return display.toString();
	}
	
	
	/**
     * Converts a list of digits into a single string ([1,2,3,4] → "1234")
     * 
     * @param code the list of digits
     * @return the formatted string representation of the code
     */
	public static String formatCode(List<Integer> code) {
		
		StringBuilder formatedCode = new StringBuilder();
		for (Integer digit : code) {
			formatedCode.append(digit);
			
		}
		return formatedCode.toString();
	}
	
	/**
     * Provides feedback on the user's guess compared to the secret code
     * Prints which digits are correct, misplaced, or incorrect
     *
     * @param secretCode the secret code to guess
     * @param userGuess the user's current guess
     */
	public static void codeFeedback(List<Integer> secretCode, List<Integer> userGuess) {
		
	    List<Integer> unmatchedCode = new ArrayList<>();
	    List<Integer> unmatchedGuess = new ArrayList<>();
	    Set<Integer> alreadyReported = new HashSet<>();

	    // Checking the position
	    for (int i = 0; i < 4; i++) {
	        if (!userGuess.get(i).equals(secretCode.get(i))) {
	            unmatchedCode.add(secretCode.get(i));
	            unmatchedGuess.add(userGuess.get(i));
	            
	        }
	    }

	    // We check misplaced elements
	    for (int guessDigit : unmatchedGuess) {
	        if (unmatchedCode.contains(guessDigit)) {
	            System.out.println(guessDigit + " est correct mais mal placé! ");
	            unmatchedCode.remove((Integer) guessDigit);
	            alreadyReported.add(guessDigit);
	        }
	    }

	    // We check correct elements
	    for (int i = 0; i < 4; i++) {
	        if (userGuess.get(i).equals(secretCode.get(i))) {
	            System.out.println(userGuess.get(i) + " est correct ! ");
	            alreadyReported.add(userGuess.get(i));
	        }
	    }
	    
	      // Report incorrect digits
	    for (int digit : userGuess) {
	        if (!alreadyReported.contains(digit)) {
	            System.out.println(digit + " est incorrect !");
	        }
	    }
	}
	
	/**
     * Generates a random 4-digit code using digits from 0 to 9
     *
     * @return a list of 4 random digits
     */
	public static List<Integer> generateRandomCode(){
		
		Random rand = new Random();
		List<Integer> code = new ArrayList<>();
		
		for (int i = 0; i <4 ; i++) {
			int digit = rand.nextInt(10);
			code.add(digit);
		}
		
		return code;
	}
	
	// First we verify that input exist and it takes only 
	// 4 digits otherwise it return false
	private static boolean isValidInput(String input) {
	    return input != null && input.matches("\\d{4}");
	}
	
	/**
     * Prompts the user to enter a valid 4 digit code
     * Validates input format and check if digits are between 0 and 9
     *
     * @return a list of 4 digits entered by the user
     */
	public static List<Integer> getValidCode(Scanner scan){
		
		List<Integer> codeList = new ArrayList<>();
		boolean validInput = false;
		
		while (!validInput) {
			System.out.println("Veuillez saisir votre code a 4 chiffre (ex: 1234) : ");
			String input = scan.nextLine().trim();
			
			// Check if exactly 4 values were entered
	        if (!isValidInput(input)) {
	            System.out.println("Vous devez entrer exactement 4 chiffres (de 0 a 9)!");
	            continue;
	        }
			
	        codeList.clear();
	        for (char c : input.toCharArray()) {
	        	codeList.add(Character.getNumericValue(c));
	        }
			
	        validInput = true;
		}
		
		return codeList;
				
	}

	/**
     * Main game loop, it generates a secret code and allows the user up to 10 attempts to guess it
     * Provides feedback after each guess and ends when the code is guessed or attempts run out
     *
     * @param args command-line arguments (not used)
     */
	public static void main(String[] args) {
		Scanner scan =  new Scanner(System.in);
		
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
        	List<Integer> userGuess = getValidCode(scan);
        	
        	if (userGuess.equals(secretCode)) {
        		System.out.println("Félicitations! Vous avez deviné le code secret: " + formatCode(secretCode) +" en " + actualTry + " essais.");
        		System.out.println("Affichage masqué : " + updateSecretCodeDisplay(secretCode, userGuess));
                found = true;
        	} else {
        		codeFeedback(secretCode, userGuess);
        		System.out.println("Affichage masqué : " + updateSecretCodeDisplay(secretCode, userGuess));
                actualTry++;
        	}
        }
        
        if (!found) {
        	System.out.println("Vous avez perdu! Le code a trouver était: " + formatCode(secretCode));
        }
        scan.close();
	}

}
