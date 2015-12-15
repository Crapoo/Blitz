package be.ipl.blitz.usecases;

import java.util.List;

import javax.ejb.Remote;

import be.ipl.blitz.domaine.Card;

@Remote
public interface CardsUcc {

	/**
	 * Mélange les cartes du paquet de jeu.
	 */
	public void shuffleDeck();

	/**
	 * Pioche un nombre donné de cartes dans le paquet de jeu.
	 * 
	 * @param nb
	 *            Nombre de cartes à piocher.
	 * @return Liste des cartes piochées.
	 */
	public List<Card> pickCard(int nb);

	/**
	 * Replace une carte en dessous du paquet de jeu.
	 * 
	 * @param card
	 *            Carte à remettre dans le paquet.
	 */
	public void discard(Card card);

	/**
	 * Prends une carte au hasard dans les cartes du joueur donné.
	 * 
	 * @param username
	 *            Joueur à qui voler une carte.
	 * @return La carte volée au joueur.
	 */
	public Card stealCardFrom(String username);

}
