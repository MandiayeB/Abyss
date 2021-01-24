package org.abyss.javafxview;

public class Game {
	
	Player player1;
	Player player2;
	Player currentPlayer;
	
	public Game(Player player1, Player player2) {
	
		this.player1 = player1;
		this.player2 = player2;
		this.currentPlayer = player1;
	}
	
	public void changeTurn() {
		if (player1.equals(currentPlayer)) {
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
		}
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}	
	
}
