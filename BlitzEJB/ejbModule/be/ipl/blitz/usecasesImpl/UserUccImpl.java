package be.ipl.blitz.usecasesImpl;

import java.util.Arrays;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import be.ipl.blitz.daoImpl.UserDaoImpl;
import be.ipl.blitz.domaine.User;
import be.ipl.blitz.usecases.UserUcc;
import be.ipl.blitz.utils.PasswordTools;
import be.ipl.blitz.utils.Util;

@Singleton
@Startup
public class UserUccImpl implements UserUcc {

	@EJB
	private UserDaoImpl dao;

	public boolean saveUser(String username, String pwd) throws Exception {
		if (dao.findByName(username) != null) {
			return false;
		}
		User u = new User(username, pwd);
		dao.save(u);
		return true;
	}

	@Override
	public User login(String username, String pwd) throws Exception {
		Util.checkString(username);
		Util.checkString(pwd);
		User u = dao.findByName(username);
		byte[] encryptedAttemptedPassword = PasswordTools.hash(pwd, u.getSalt());
		if (Arrays.equals(u.getPwd(), encryptedAttemptedPassword)) {
			return u;
		} else {
			return null;
		}
	}

	@Override
	public User findByName(String nickname) {
		return dao.findByName(nickname);
	}
}
