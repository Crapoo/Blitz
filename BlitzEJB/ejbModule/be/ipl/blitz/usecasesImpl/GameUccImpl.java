package be.ipl.blitz.usecasesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import be.ipl.blitz.daoImpl.GameDaoImpl;
import be.ipl.blitz.daoImpl.PlayerGameDaoImpl;
import be.ipl.blitz.daoImpl.UserDaoImpl;
import be.ipl.blitz.domaine.Card;
import be.ipl.blitz.domaine.Face;
import be.ipl.blitz.domaine.Game;
import be.ipl.blitz.domaine.Game.State;
import be.ipl.blitz.domaine.PlayerGame;
import be.ipl.blitz.domaine.PlayerGamePK;
import be.ipl.blitz.domaine.User;
import be.ipl.blitz.usecases.CardsUcc;
import be.ipl.blitz.usecases.GameUcc;
import be.ipl.blitz.usecases.UserUcc;
import be.ipl.blitz.utils.Util;

@Singleton
@Startup
public class GameUccImpl implements GameUcc {
	private final int INITIAL_CARDS_PER_PLAYER = 3;

	private Game game;
	@EJB
	private GameDaoImpl gameDao;
	@EJB
	private UserDaoImpl userDao;
	@EJB
	private PlayerGameDaoImpl playerGameDao;

	@EJB
	private UserUcc userUcc;
	@EJB
	private CardsUcc cardsUcc;

	static int maxPlayers;
	static int minPlayers;
	static String goal;
	static int dicePerPlayer;
	static int nbCardsByPlayer;

	public GameUccImpl() {
	}

	public boolean joinGame(String gameName, String username) {
		Util.checkString(username);
		Util.checkString(gameName);
		if (game == null) {
			return false;
		}
		State gameState = game.getState();
		if (gameState == State.IN_PROGRESS || gameState == State.OVER) {
			return false;
		}
		User player = userUcc.findByName(username);

		PlayerGame playerGame = game.addPlayer(player);
		if (playerGame == null) {
			return false;
		}
		playerGameDao.save(playerGame);
		playerGameDao.update(playerGame);
		gameDao.update(game);
		return true;
	}

	@Override
	@Lock(LockType.READ)
	public List<String> listPlayers() {
		if (game == null) {
			return null;
		}
		List<PlayerGame> playerGames = game.getUsers();

		List<String> pseudos = new ArrayList<String>();

		for (PlayerGame pl : playerGames) {
			pl = playerGameDao.reload(new PlayerGamePK(pl.getUserId(), game.getId()));
			pseudos.add(pl.getUser().getName());
		}
		return pseudos;
	}

	@Override
	public boolean startGame() {
		if (game == null || game.getState() != State.INITIAL) {
			return false;
		}
		game = gameDao.findById(game.getId());
		if (game.startGame()) {
			if (dealCards(game, game.getUsers())) {
				gameDao.update(game);
				return true;
			}
		}
		return false;
	}

	private boolean dealCards(Game game, List<PlayerGame> gp) {
		for (PlayerGame player : gp) {
			List<Card> cards = drawCard(INITIAL_CARDS_PER_PLAYER);
			if (cards == null) {
				return false;
			}
			player.setCards(cards);
			playerGameDao.update(player);
		}
		return true;
	}

	public String getCurrentPlayer() {
		if (game == null) {
			return null;
		}
		User u = game.getCurrentUser();
		if (u == null) {
			return null;
		}
		return u.toString();
	}

	@Override
	public Set<Face> throwDice() {
		if (game == null)
			return null;
		game = gameDao.findById(game.getId());
		return game.throwDice();
	}

	@Override
	public boolean deleteDice(int num, String username) {
		Util.checkPositiveOrZero(num);
		Util.checkString(username);
		if (game == null) {
			return false;
		}
		game = gameDao.reload(game.getId());
		PlayerGame pg=playerGameDao.findByUserAndGame(userDao.findByName(username),game);
		if(game.deleteDice(num, pg)){
			playerGameDao.update(pg);
			return true;
		}
		return false;
	}

	@Override
	public User nextPlayer() {
		if (game == null) {
			return null;
		}
		game = gameDao.findById(game.getId());
		return game.nextPlayer();
	}

	@Override
	public String getWinner() {
		if (game == null) {
			return null;
		}
		game = gameDao.findById(game.getId());
		User u = game.getWinner();
		if (u == null)
			return null;
		return u.getName();
	}

	@Override
	public void cancelGame() {
		if (game != null) {
			game.cancel();
		}
	}

	@Override
	public boolean createGame(String gameName) {
		Util.checkString(gameName);
		if (game != null) {
			return false;
		}

		this.game = new Game(gameName);
		game = gameDao.save(game);

		cardsUcc.shuffleDeck();
		return true;
	}

	@Override
	@Lock(LockType.READ)
	public State getState() {
		if (game == null) {
			return null;
		}
		return game.getState();
	}

	@Override
	public Game getCurrentGame() {
		return this.game;
	}

	@Override
	public List<Card> drawCard(int num) {
		Util.checkPositiveOrZero(num);
		return giveCardsTo(game.getCurrentUser().getName(), cardsUcc.drawCard(num));
	}

	@Override
	public int getMaxPlayers() {
		return maxPlayers;
	}

	public static void setMaxPlayers(int maxPlayers) {
		GameUccImpl.maxPlayers = maxPlayers;
	}

	@Override
	public int getMinPlayers() {
		return minPlayers;
	}

	public static void setMinPlayers(int minPlayers) {
		GameUccImpl.minPlayers = minPlayers;
	}

	@Override
	public String getGoal() {
		return goal;
	}

	public static void setGoal(String goal) {
		GameUccImpl.goal = goal;
	}

	@Override
	public int getDicePerPlayer() {
		return dicePerPlayer;
	}

	public static void setDicePerPlayer(int dicePerPlayer) {
		GameUccImpl.dicePerPlayer = dicePerPlayer;
	}

	public static void setNbCardsByPlayer(int nbCardsByPlayer) {
		GameUccImpl.nbCardsByPlayer = nbCardsByPlayer;
	}

	@Override
	public int getNbCardsByPlayer() {
		return nbCardsByPlayer;
	}

	@Override
	public List<Card> getCardsOf(String username) {
		return playerGameDao.findByUserAndGame(userDao.findByName(username), game).getCards();
	}

	@Override
	public List<Card> giveCardsTo(String username, List<Card> cards) {
		PlayerGame p = playerGameDao.findByUserAndGame(userDao.findByName(username), game);
		for (Card c : cards) {
			p.addCard(c);
		}
		playerGameDao.update(p);
		return p.getCards();
	}

}
