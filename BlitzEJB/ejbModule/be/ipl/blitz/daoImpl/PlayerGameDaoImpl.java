package be.ipl.blitz.daoImpl;

import be.ipl.blitz.domaine.PlayerGame;
import be.ipl.blitz.domaine.PlayerGamePK;
import be.ipl.blitz.utils.Util;

public class PlayerGameDaoImpl extends DaoImpl<PlayerGamePK, PlayerGame>{

	public PlayerGameDaoImpl() {
		super(PlayerGame.class);
	}

	@Override
	public PlayerGame findById(PlayerGamePK id) {
		Util.checkObject(id);
		String queryString = "SELECT pg FROM PLAYERS_GAME pg WHERE pg.player = ?1 AND pg.game= ?2";
		return search(queryString, id.getPlayer(), id.getGame());
	}

}
