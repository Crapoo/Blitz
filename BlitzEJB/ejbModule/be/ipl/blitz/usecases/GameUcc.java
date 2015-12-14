package be.ipl.blitz.usecases;

import java.util.List;

import be.ipl.blitz.domaine.Die;

public interface GameUcc {
	
	boolean joinGame(String gameName,String pseudo);
	boolean startGame();
	boolean isOver();
	void cancelGame();
	
	List<Die> getPlayableDice();
	int throwDice();
	boolean deleteDie(int numero);
	
	int myScore();
	int score(String pseudo);
	boolean nextPlayer();
	String winner();
	List<String> listPlayers();
	String currentPlayer();
	
	// Etat getEtatPartie();
}
