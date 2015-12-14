package be.ipl.blitz.daoImpl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.ipl.blitz.domaine.User;
import be.ipl.blitz.utils.Util;

@SuppressWarnings("serial")
@Stateless
@LocalBean
public class UserDaoImpl extends DaoImpl<Integer, User>{

	public UserDaoImpl() {
		super(User.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public User findById(Integer id) {
		Util.checkPositiveOrZero(id);
		String queryString = "SELECT u FROM User u WHERE u.id= ?1";
		return search(queryString, id);
	}
	
	public User findByName(String s){
		//TODO:javier injectionsql
		Util.checkString(s);
		String queryString = "SELECT u FROM User u WHERE u.name = ?1";
		return search(queryString, s);
	}
	
}
