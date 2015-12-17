package be.ipl.blitz.daoImpl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.ipl.blitz.domaine.PlayerGame;
import be.ipl.blitz.domaine.PlayerGamePK;
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
		return find(id);
	}

	public PlayerGame loadCards(PlayerGame pg) {
		pg = findById(pg.getPk());
		pg.getCards().size();
		return pg;
	}

}
