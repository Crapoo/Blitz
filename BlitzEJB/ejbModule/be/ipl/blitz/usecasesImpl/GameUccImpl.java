package be.ipl.blitz.usecasesImpl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import be.ipl.blitz.daoImpl.GameDaoImpl;
import be.ipl.blitz.daoImpl.UserDaoImpl;
import be.ipl.blitz.domaine.Die;
import be.ipl.blitz.domaine.Game;
import be.ipl.blitz.domaine.User;
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
	public boolean joinGame(String pseudo) {
		if (game != null && game.getEtat() == Etat.EN_COURS)
			return false;
		if (game == null || game.getEtat() == Etat.FINIE) {
			game = new Game("partie" + num);
			game = gameDao.save(game);
		}

		game = gameDao.findById(game.getId());

		User joueur = userDao.search(pseudo);
		if (joueur == null) {
			joueur = new User(pseudo);
			joueur = joueurDao.enregistrer(joueur);
		}
		return game.ajouterJoueur(joueur);
	}

	@Override
	public List<String> listPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean startGame() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String currentPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Die> listPlayedDices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int throwDices() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteDice(int numero) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int myScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int score(String pseudo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean nextPlayer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String winner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void cancelGame() {
		// TODO Auto-generated method stub
		
	}
	
	
}
