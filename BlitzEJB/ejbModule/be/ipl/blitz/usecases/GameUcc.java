package be.ipl.blitz.usecases;

import java.util.List;

import javax.ejb.Remote;

import be.ipl.blitz.domaine.Card;
import be.ipl.blitz.domaine.Game;
import be.ipl.blitz.domaine.Game.State;

@Remote
public interface GameUcc {

	/* Constantes de jeu */
	/** Retourne le nombre maximum de joueurs. */
	int getMaxPlayers();

	/** Retourne le nombre minimum de joueurs. */
	int getMinPlayers();

	/** Retourne le but du jeu. */
	String getGoal();

	/** Retourne le nombre initial de cartes par joueurs. */
	int getNbCardsByPlayer();

	/** Retourne le nombre initial de dé par joueur. */
	int getDicePerPlayer();

	/* Gestion de la partie */
	/**
	 * Crée un jeu avec le nom donnée et la date courante comme date de début.
	 * 
	 * @param gameName
	 *            Nom de la partie à créer.
	 * @return Vrai si la partie a pu être créée.
	 */
	boolean createGame(String gameName);

	/**
	 * Ajoute la joueur désigné par son nom dans la partie donnée.
	 * 
	 * @param gameName
	 *            Nom de la partie à rejoindre.
	 * @param username
	 *            Nom du joueur.
	 * @return True si le joueur a pu rejoindre la partie.
	 */
	boolean joinGame(String gameName, String username);

	/**
	 * Démarre la partie avec les joueurs préalablement ajoutés.
	 * 
	 * @return True si la partie a pu être lancée.
	 */
	boolean startGame();

	/**
	 * Annule la partie précédemment lancée.
	 */
	void cancelGame();

	/**
	 * Cloture la partie.
	 * 
	 * @return le pseudo du vainqueur
	 */
	void endGame();

	/**
	 * Récupère le dernière partie lancée.
	 * 
	 * @return La dernière partie lancée.
	 */
	Game getCurrentGame();

	/**
	 * Récupère l'état de la dernière partie lancée.
	 * 
	 * @return Etat de la partie ou null si aucune partie n'a été lancée jusqu'à
	 *         maintenant.
	 */
	State getState();

	/** Récupère la liste de toutes les parties jouées. */
	List<Game> getAllGames();

	/* Gestion des joueurs */
	/**
	 * Récupère les noms de tous les joueurs participant à la partie courante.
	 * 
	 * @return Liste des noms de tous les joueurs.
	 */
	List<String> listPlayers();

	/**
	 * Récupère le joueur courant.
	 * 
	 * @return Le joueur courant.
	 */
	String getCurrentPlayer();

	/**
	 * Change le joueur courant par le joueur devant joueur après.
	 * 
	 * @return Le nouveau joueur courant.
	 */
	String nextPlayer();

	/**
	 * Retire un joueur de la partie
	 * 
	 * @param pseudo
	 */
	void removePlayer(String pseudo);

	/** Retourne le nom du gagnant de la partie finie. */
	String getWinner();

	/* Actions du jeu */
	/**
	 * Lance les dés du joueur courant.
	 * 
	 * @return Set de Face correspondant au résultat du lancer.
	 */
	List<String> throwDice();

	/**
	 * Supprime un certain nombre de dé du joueur donné. Si ce nombre est
	 * supérieur au nombre de dés du joueur, supprime tous les dés.
	 * 
	 * @param numero
	 *            Nombre de dé à supprimer.
	 * @param username
	 *            Nom du joueur à qui supprimer les dés.
	 * @return True si les dés ont pu être supprimés.
	 */
	boolean deleteDice(int numero, String username);

	/**
	 * Tire une carte dans la pioche
	 * 
	 * @return une liste des cartes a tirer, null si il n'y a pas de cartes dans
	 *         la pioche
	 */
	List<Card> drawCard(String username, int num);

	/**
	 * Récupère les cartes pour le joueur passé en paramètre
	 * 
	 * @param pseudo
	 * 
	 * @return La liste des cartes de ce joueur
	 */
	List<Card> getCardsOf(String pseudo);

	/**
	 * Le joueur src donne num cartes au hasard au joueur courrant
	 */
	boolean giveMeCards(String src);

	/**
	 * change la direction du jeu
	 */
	void changeDirection();

	/**
	 * Supprime une carte du deck du joueur
	 * 
	 * @param username
	 * @param effectCode
	 *            code effect de la carte
	 * @return true si ca a reussit, faux si le joueur ne possede pas la carte.
	 */
	boolean discard(String username, int effectCode);

	/**
	 * Donne un dé du joueur courant au joueur passé em parametre.
	 * 
	 * @param username
	 * @param num
	 *            nombre de dé a donner
	 * @return true si reussi, false sinon
	 */
	boolean giveDice(String username, int num);

	/**
	 * Récupere le nombre dés d'un utilisateur
	 * 
	 * @param username
	 * @return le nombre de dé
	 */
	int getNbDice(String username);

	/**
	 * the username only keeps num cards
	 * 
	 * @param username
	 * @param num
	 */
	void keepRandomCards(String username, int num);

	/**
	 * Enregistre le fait qu'un joueur devra passer son tour.
	 * 
	 * @param username
	 *            Nom du joueur à qui passer le tour.
	 */
	void skipTurn(String username);

	/**
	 * Permet au joueur courant de rejouer à la fin de son tour.
	 */
	void replay();
}
