package be.ipl.blitz.daoImpl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.ipl.blitz.domaine.Game;
import be.ipl.blitz.utils.Util;

@SuppressWarnings("serial")
@Stateless
@LocalBean
public class GameDaoImpl extends DaoImpl<Integer, Game> {

	public GameDaoImpl() {
		super(Game.class);
	}

	@Override
	public Game findById(Integer id) {
		Util.checkPositiveOrZero(id);
		String queryString = "SELECT g FROM Game g WHERE g.id = ?1";
		return search(queryString, id);
	}
	
	public List<Game> getAll(){
		String queryString = "SELECT g FROM Game g";
		return list(queryString);
	}
	
	public Game loadUsers(Game g) {
		g = findById(g.getId());
		g.getUsers().size();
		return g;
	}

}
