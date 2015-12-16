package be.ipl.blitz.daoImpl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.ipl.blitz.domaine.Game;
import be.ipl.blitz.domaine.PlayerGame;
import be.ipl.blitz.domaine.PlayerGamePK;
import be.ipl.blitz.domaine.User;
import be.ipl.blitz.utils.Util;

@SuppressWarnings("serial")
@Stateless
@LocalBean
public class PlayerGameDaoImpl extends DaoImpl<PlayerGamePK, PlayerGame> {

	public PlayerGameDaoImpl() {
		super(PlayerGame.class);
	}

	@Override
	public PlayerGame findById(PlayerGamePK id) {
		Util.checkObject(id);
		String queryString = "SELECT pg FROM PLAYERS_GAME pg WHERE pg.userId = ?1 AND pg.gameId = ?2";
		return search(queryString, id.getUserId(), id.getGameId());
	}
	
	public PlayerGame findByUserAndGame(User u, Game g) {
		Util.checkObject(u);
		String queryString = "SELECT pg FROM PLAYERS_GAME pg WHERE pg.userId = ?1 AND pg.gameId= ?2";
		return search(queryString, u.getId(), g.getId());
	}	

}
