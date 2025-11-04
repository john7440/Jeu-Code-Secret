package fr.ex.java;
import java.util.*;

public class Main {
	
	
	public static String updateSecretCodeDisplay(List<Integer> secretCode, List<Integer> userGuess) {
		StringBuilder display = new StringBuilder();
	    List<Integer> unmatchedCode = new ArrayList<>();
	    List<Integer> unmatchedGuess = new ArrayList<>();
		
	    for (int i = 0; i < 4; i++) {
	        if (!userGuess.get(i).equals(secretCode.get(i))) {
	            unmatchedCode.add(secretCode.get(i));
	            unmatchedGuess.add(userGuess.get(i));
	        }
	    }

	    
	    for (int i = 0; i < 4; i++) {
	        int guessDigit = userGuess.get(i);
	        if (guessDigit == secretCode.get(i)) {
	            display.append(guessDigit); 
	        } else if (unmatchedCode.contains(guessDigit)) {
	            display.append("*"); 
	            unmatchedCode.remove((Integer) guessDigit);
	        } else {
	            display.append("*"); 
	        }
	    }

	    return display.toString();
	}
	
	
	
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
		boolean validInput = false;
		
		while (!validInput) {
			System.out.println("Veuillez saisir votre code a 4 chiffre (ex: 1234) : ");
			String input = scan.nextLine().trim();
			
			// Check if exactly 4 values were entered
	        if (input.length() != 4 || !input.matches("\\d{4}")) {
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
	

	}

}
