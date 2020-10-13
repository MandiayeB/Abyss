package Game;

import java.util.Random;

public class Game {

	private String hand[] = new String[6];
	private String deck[] = {"Salut","jhvblj","uuu","dssssssssss"};
	
	
	///////////////////////////////////////////////////////////////// 
	
	private void melangerTableau(String t[]) {
	    for (int i = 0; i < t.length; i++) {
	        int r = (int)  (Math.random()*(t.length-0));
	        String tmp = t[i];
	        t[i] = t[r]; // Je remplace la carte "i" par la carte "r"
	        t[r] = tmp;
	    }
	}														
	
	//////////////////////////////////////////////////////////////////
	
	public void deck() {
		
		melangerTableau(deck);
		for (int i = 0; i < deck.length; i++) { 
		    System.out.print(deck[i]+ ","); 
		    
		}
		System.out.println("");
		System.out.println("---------------------------");
	}
	
	public void pioche() {

		for (int i = 0; i < hand.length; i++) { // Pioche jusqu'a que la main est pleine
			try {
				if (hand[i] == null) {
					hand[i] = deck[i];	// pioche la premiere carte du deck
					System.out.println("Je pioche ma "+ (i+1) + "eme carte, et j'ai eu le cette carte " + hand[i]);

				}
			} catch (ArrayIndexOutOfBoundsException e) { // Exeception du main plein.
				System.out.println("------------------------");
				System.out.println("Votre main est pleine.");
				System.out.println("------------------------");
				break;

			}
		}
	}
	
	public void jouer() {
		for(int i = 0; i < 4; i++) {
			System.out.println("joue ca : " + hand[i]);
		}
	}
	
	public String[] gethand() {
		return hand;
	}

	public void sethand(String[] hand) {
		this.hand = hand;
	}


}
