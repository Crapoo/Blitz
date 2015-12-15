package be.ipl.blitz.usecasesImpl;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import be.ipl.blitz.daoImpl.BlitzDaoImpl;
import be.ipl.blitz.usecases.BlitzUcc;

@Singleton
@Startup
public class BlitzUccImpl implements BlitzUcc {

	@EJB
	BlitzDaoImpl blitzDao;

	@Override
	public int getMaxPlayers() {
		return blitzDao.get().getMaxPlayers();
	}

	@Override
	public int getMinPlayers() {
		return blitzDao.get().getMinPlayers();
	}

	@Override
	public String getGoal() {
		return blitzDao.get().getGoal();
	}

	@Override
	public int getNbCardsByPlayer() {
		return blitzDao.get().getNbCardsByPlayer();
	}

}
