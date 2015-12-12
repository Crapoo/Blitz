package be.ipl.blitz.usecases;

import javax.ejb.Remote;

import be.ipl.blitz.domaine.User;

@Remote
public interface UserUcc {
	User saveUser(String userName, String pwd) throws Exception;
	Boolean login(String login, String pwd) throws Exception;	
}
