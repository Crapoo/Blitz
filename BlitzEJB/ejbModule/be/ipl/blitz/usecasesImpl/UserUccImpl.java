package be.ipl.blitz.usecasesImpl;

import java.util.Arrays;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import be.ipl.blitz.daoImpl.UserDaoImpl;
import be.ipl.blitz.domaine.User;
import be.ipl.blitz.usecases.UserUcc;
import be.ipl.blitz.utils.PasswordTools;
import be.ipl.blitz.utils.Util;

@Stateless
public class UserUccImpl implements UserUcc{
	
	@EJB
	private UserDaoImpl dao;

	public User saveUser(String username,String pwd) throws Exception {
		Util.checkString(username);
		Util.checkString(pwd);
		User u=new User(username, pwd);		
		dao.save(u);
		return u;
	}

	@Override
	public Boolean login(String username, String pwd) throws Exception {
		Util.checkString(username);
		Util.checkString(pwd);
		User u= dao.findByName(username);
		byte[] encryptedAttemptedPassword = PasswordTools.hash(pwd, u.getSalt());
		return Arrays.equals(u.getPwd(), encryptedAttemptedPassword);
	}
}
