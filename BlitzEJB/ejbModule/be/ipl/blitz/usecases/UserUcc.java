package be.ipl.blitz.usecases;

import javax.ejb.Remote;

import be.ipl.blitz.domaine.User;

@Remote
public interface UserUcc {
	/**
	 * Enregistre un nouvel utilisateur dans la base de données. Génère un sel
	 * et encrypte le mot de passe donné.
	 * 
	 * @param userName
	 *            Nom de l'utilisateur à enregistrer.
	 * @param pwd
	 *            Mot de passe à encrypter.
	 * @return False si le nom d'utilisateur est déjà utilisé. True sinon.
	 * @throws Exception
	 *             Si l'algorithme de cryptage n'a pas pu être trouvé.
	 */
	boolean saveUser(String userName, String pwd) throws Exception;

	/**
	 * Connecte l'utilisateur en utilisant le nom et la mot de passe donnés.
	 * 
	 * @param username
	 *            Nom de l'utilisateur voulant se connecter.
	 * @param pwd
	 *            Tentative de mot de passe.
	 * @return L'utilisateur connecté si réussi, null sinon.
	 * @throws Exception
	 *             Si l'algorithme de cryptage n'a pas pu être trouvé.
	 */
	User login(String username, String pwd) throws Exception;

	/**
	 * Récupère un utilisateur de la base de données selon le nom.
	 * 
	 * @param username
	 *            Nom de l'utilisateur à récupérer.
	 * @return L'utilisateur de la base de données s'il existe, null sinon.
	 */
	User findByName(String username);

}
