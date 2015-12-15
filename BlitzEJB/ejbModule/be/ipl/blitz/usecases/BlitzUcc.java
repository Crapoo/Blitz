package be.ipl.blitz.usecases;

import javax.ejb.Remote;

@Remote
public interface BlitzUcc {
	/**
	 * Récupère le nombre maximum de joueurs dans une partie.
	 * 
	 * @return Le nombre maximum de joueurs.
	 */
	int getMaxPlayers();

	/**
	 * Récupère le nombre minimum de joueurs dans une partie.
	 * 
	 * @return Le nombre minimum de joueurs.
	 */
	int getMinPlayers();

	/**
	 * Récupère le but du jeu.
	 * 
	 * @return Le but du jeu.
	 */
	String getGoal();

	/**
	 * Récupère le nombre de cartes par joueur.
	 * 
	 * @return Le nombre de cartes par joueur.
	 */
	int getNbCardsByPlayer();
}
