package be.ipl.blitz.usecases;

import java.util.List;

public interface GameUcc {
	boolean joinGame(String pseudo);
	List<String> listPlayers();
	boolean startGame();
	String currentPlayer();
	List<Dice> listPlayedDices();
	int throwDices();
	boolean deleteDice(int numero);
	int myScore();
	int score(String pseudo);
	boolean nextPlayer();
	String winner();
	boolean isOver();
	void cancelGame();
	//Etat getEtatPartie();
}
