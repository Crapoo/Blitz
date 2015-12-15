package be.ipl.blitz.usecases;

import javax.ejb.Remote;

import be.ipl.blitz.domaine.User;

@Remote
public interface UserUcc {
	boolean saveUser(String userName, String pwd) throws Exception;

	User login(String login, String pwd) throws Exception;

	User findByName(String nickname);

}
