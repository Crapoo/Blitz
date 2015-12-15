package be.ipl.blitz.usecases;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import be.ipl.blitz.domaine.Face;
import be.ipl.blitz.domaine.Game;
import be.ipl.blitz.domaine.Game.State;

@Remote
public interface GameUcc {

	boolean joinGame(String gameName, String pseudo);
	boolean createGame(String gameName);
	State getState();
	boolean startGame();
	boolean isOver();
	void cancelGame();
	Set<Face> throwDice();
	Game getCurrentGame();
	boolean deleteDie(int numero);
	boolean nextPlayer();
	String winner();
	List<String> listPlayers();
	String getCurrentPlayer();
}
