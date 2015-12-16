package be.ipl.blitz.usecases;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import be.ipl.blitz.domaine.Card;
import be.ipl.blitz.domaine.Face;
import be.ipl.blitz.domaine.Game;
import be.ipl.blitz.domaine.Game.State;
import be.ipl.blitz.domaine.User;

@Remote
public interface GameUcc {

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
	 * Crée un jeu avec le nom donnée et la date courante comme date de début.
	 * 
	 * @param gameName
	 *            Nom de la partie à créer.
	 * @return Vrai si la partie a pu être créée.
	 */
	boolean createGame(String gameName);

	/**
	 * Récupère l'état de la dernière partie lancée.
	 * 
	 * @return Etat de la partie ou null si aucune partie n'a été lancée jusqu'à
	 *         maintenant.
	 */
	State getState();

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
	 * Lance les dés du joueur courant.
	 * 
	 * @return Set de Face correspondant au résultat du lancer.
	 */
	Set<Face> throwDice();

	/**
	 * Récupère le dernière partie lancée.
	 * 
	 * @return La dernière partie lancée.
	 */
	Game getCurrentGame();

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
	 * Change le joueur courant par le joueur devant joueur après.
	 * 
	 * @return Le nouveau joueur courant.
	 */
	User nextPlayer();

	/**
	 * Récupère le nom du gagnant de la dernière partie.
	 * 
	 * @return Le nom du gagnant.
	 */
	String getWinner();

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
	 * Tire une carte dans la pioche
	 * 
	 * @return une liste des cartes a tirer, null si il n'y a pas de cartes dans
	 *         la pioche
	 */
	List<Card> drawCard(String username,int num);

	int getMaxPlayers();

	int getMinPlayers();

	String getGoal();

	int getNbCardsByPlayer();

	int getDicePerPlayer();
	
	/**
	 * Récupère les cartes pour le joueur passé en paramètre
	 * 
	 * @param pseudo
	 * 
	 * @return La liste des cartes de ce joueur
	 */
	List<Card> getCardsOf(String pseudo);
	/**
	 * Donne une liste de cartes a un joueur
	 * @return La nouvelle liste de cartes du joueur
	 */
	List<Card> giveCardsTo(String pseudo, List<Card> cards);
	
	/**
	 * Supprime une carte du deck du joueur
	 * 
	 * @param username
	 * @param effectCode code effect de la carte
	 * @return true si ca a reussit, faux si le joueur ne possede pas la carte.
	 */
	boolean discard(String username, int effectCode);
	
	/**
	 * Donne un dé a un joueur
	 * @param username
	 * @param num nombre de dé a donner
	 */
	void giveDice(String username, int num);

	/**
	 * Récupere le nombre dés d'un utilisateur
	 * @param username
	 * @return le nombre de dé
	 */
	int getNbDice(String username);
	
	
}
