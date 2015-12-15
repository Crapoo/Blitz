package be.ipl.blitz.usecases;

import java.util.List;

import javax.ejb.Remote;

import be.ipl.blitz.domaine.Card;
import be.ipl.blitz.domaine.PlayerGame;

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
	public List<Card> drawCard(int nb);

	/**
	 * Replace une carte en dessous du paquet de jeu.
	 * 
	 * @param card
	 *            Carte à remettre dans le paquet.
	 */
	public void discard(Card card);

	/**
	 * Vole une carte dans le jeu du volé et les rajoute dans le jeu du voleur.
	 * 
	 * @param thief
	 *            Joueur qui vole la carte.
	 * @param victim
	 *            Joueur chez qui on vole la carte.
	 * @return La carte rajoutée au jeu du voleur.
	 */
	public Card stealCardFrom(PlayerGame thief, PlayerGame victim);

}
