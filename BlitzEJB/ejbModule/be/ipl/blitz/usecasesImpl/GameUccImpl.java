package be.ipl.blitz.usecasesImpl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;

import be.ipl.blitz.daoImpl.GameDaoImpl;
import be.ipl.blitz.daoImpl.UserDaoImpl;
import be.ipl.blitz.domaine.Die;
import be.ipl.blitz.domaine.Game;
import be.ipl.blitz.usecases.GameUcc;

@Stateless
public class GameUccImpl implements GameUcc{
	private Game game;
	@EJB
	private GameDaoImpl gameDao;
	@EJB
	private UserDaoImpl userDao;

	public GameUccImpl() {
		
	}
	@PostConstruct
	public void postconstruct() {
		System.out.println("GestionPartieImpl created");
	}
	
	@PreDestroy
	public void predestroy() {
		System.out.println("GestionPartieImpl destroyed");
	}
	
	@Override
	public boolean joinGame(String gameName,String pseudo) {
	/*	if (game != null && game.getEtat() == Etat.EN_COURS)
			return false;
		if (game == null || game.getEtat() == Etat.FINIE) {
			return false;
		}
		game=new Game(name);
		User player = userDao.search(pseudo);
		
		return game.ajouterJoueur(player);*/
		return false;
	}

	@Override
	@Lock(LockType.READ)
	public List<String> listPlayers() {/*
		if (game == null)
			return null;
		List<User> users = game.getPlayers();
		List<String> pseudos = new ArrayList<String>();
		for (User u : users)
			pseudos.add(u.getName());
		return pseudos;*/
		return null;
	}

	@Override
	public boolean startGame() {/*
		if (game == null || game.getEtat() != Etat.INITIAL)
			return false;
		game = gameDao.findById(game.getId());
		return game.commencerPartie();*/
		return false;
	}

	@Override
	public String currentPlayer() {
		/*if (game == null)
			return null;
		if (game.getJoueurCourant() == null)
			return null;
		return game.getJoueurCourant().getPseudo();*/ return null;
	}

	@Override
	public List<Die> getPlayableDice() {/*
		if (game == null)
			return null;
		game = gameDao.findById(game.getId());
		List<Die> list = new ArrayList<Die>();
		liste.addAll(game.getPlayableDice());
		return list;*/
		return null;
	}

	@Override
	public int throwDice() {/*
		if (game == null)
			return 0;
		game = gameDao.findById(game.getId());
		return game.throwDice();*/
		return 0;
	}

	@Override
	public boolean deleteDie(int num) {/*
		if (game == null)
			return false;
		game = partieDao.findById(game.getId());
		boolean result = game.deleteDie(num);
		return result;*/
		return false;
	}

	@Override
	public int myScore() {/*
		if (game == null)
			return 0;
		game = gameDao.findById(game.getId());
		return game.getPoints(game.getJoueurCourant());*/
		return 0;
	}

	@Override
	public int score(String pseudo) {/*
		if (game == null)
			return 0;
		game = gameDao.findById(game.getId());
		return game.getPoints(game.getJoueur(pseudo));*/
		return 0;
	}

	@Override
	public boolean nextPlayer() {/*
		if (game== null)
			return false;
		game = gameDao.findById(game.getId());
		return game.commencerTourSuivant();*/
		return false;
	}

	@Override
	public String winner() {/*
	if (game == null)
			return null;
		game = gameDao.findById(game.getId());
		User u = game.estVainqueur();
		if (u == null)
			return null;
		return u.getName();*/
		return null;
	}

	@Override
	public boolean isOver() {/*
	if (game == null)
			return true;
		game = gameDao.findById(game.getId());
		return game.getEtat() == Etat.FINIE;*/
		return false;
	}

	@Override
	public void cancelGame() {/*
		if (game != null)
			game.annuler();*/		
	}
	
	
}
