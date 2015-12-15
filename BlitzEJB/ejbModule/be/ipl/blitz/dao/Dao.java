package be.ipl.blitz.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<K, E> extends Serializable {
	/**
	 * Recherche une entité de type E dans la base de données, selon son id.
	 * 
	 * @param id
	 *            Identifiant de l'entité dans la base de données.
	 * @return l'entité E correspondant à l'id ou null si l'entité n'existe pas.
	 */
	E findById(K id);

	/**
	 * Ajoute l'entité de type E dans la base de données.
	 * 
	 * @param entity
	 *            Entité à sauver dans la base de données.
	 * @return L'entité, remplie avec les paramètres ajoutés par la base de
	 *         données.
	 */
	E save(E entity);

	/**
	 * Met à jour les données de la base de données selon le contenu de l'entité
	 * locale.
	 * 
	 * @param entity
	 *            Entité à mettre à jour dans la base de données.
	 * @return L'entité mise à jour dans la base de données.
	 */
	E update(E entity);

	/**
	 * Met à jour la copie locale selon le contenu de la base de données.
	 * 
	 * @param id
	 *            Identifiant de l'entité dans la base de données.
	 * @return L'entité mise à jour selone le contenu de la base de données.
	 */
	E reload(K id);

	/**
	 * Supprime une entité de la base de données.
	 * 
	 * @param id
	 *            Identifiant de l'entité à supprimer.
	 */
	void delete(K id);

	/**
	 * Liste toutes les entités de la table correspondante dans la base de
	 * données.
	 * 
	 * @return Liste des entités.
	 */
	List<E> list();
}
