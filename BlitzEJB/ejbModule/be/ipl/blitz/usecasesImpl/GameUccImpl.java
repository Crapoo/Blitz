package be.ipl.blitz.usecasesImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
	static List<Face> faces;

	String winner;

	private List<String> tmpFaces = Arrays.asList("c", "c", "d", "w", "w", "w");

	public GameUccImpl() {
	}

	public boolean joinGame(String gameName, String username) {
		Util.checkString(username);
		Util.checkString(gameName);
		if (game == null) {
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

		List<String> usernames = new ArrayList<String>();

		for (PlayerGame pl : playerGames) {

			usernames.add(pl.getUser().getName());
		}
		return usernames;
	}

	@Override
	public boolean startGame() {
		if (game == null || game.getUsers().isEmpty()) {
			return false;
		}
		if (game.startGame()) {
			winner = null;
			cardsUcc.shuffleDeck();
			giveInitalDice(game.getUsers());

			if (dealCards(game.getUsers())) {
				gameDao.update(game);
				return true;
			}
		}
		return false;
	}

	private void giveInitalDice(List<PlayerGame> pg) {
		Util.checkObject(pg);
		for (PlayerGame player : pg) {
			player.addDie(dicePerPlayer);
			playerGameDao.update(player);
		}
	}

	private boolean dealCards(List<PlayerGame> gp) {
		Util.checkObject(gp);
		for (PlayerGame player : gp) {
			List<Card> cards = drawCard(player.getUser().getName(), nbCardsByPlayer);
			if (cards == null) {
				return false;
			}
			player.setCards(cards);
			playerGameDao.update(player);
		}
		return true;
	}

	@Override
	public String getCurrentPlayer() {
		if (game == null) {
			return null;
		}
		int index = game.getCurrentUser() % game.getUsers().size();
		User u = game.getUsers().get(index).getUser();
		return u.getName();
	}

	private PlayerGame getPlayerGame(String username) {
		if (!listPlayers().contains(username)) {
			return null;
		}
		return playerGameDao.findById(new PlayerGamePK(userDao.findByName(username).getId(), game.getId()));
	}

	@Override
	public List<String> throwDice() {
		List<String> nFaces = new ArrayList<>();
		if (game == null)
			return null;
		Random r = new Random();
		for (int i = 0; i < getPlayerGame(getCurrentPlayer()).getNbDice(); i++) {
			nFaces.add(faces.get(r.nextInt(6)).getIdentif());
		}
		return nFaces;
	}

	@Override
	public boolean deleteDice(int num, String username) {
		Util.checkPositiveOrZero(num);
		Util.checkString(username);
		if (game == null) {
			return false;
		}
		PlayerGame pg = getPlayerGame(username);
		game.deleteDice(num, pg);
		playerGameDao.update(pg);
		return true;
	}

	@Override
	public boolean giveDice(String username, int num) {
		Util.checkPositiveOrZero(num);
		Util.checkString(username);
		if (username.equals(getCurrentPlayer())) {
			return false;
		}
		PlayerGame curr = getPlayerGame(getCurrentPlayer());
		// TODO: Que faire si nombre de dé donné>nombre de dé dispo? retourner
		// faux?
		if (curr.getNbDice() < num) {
			return false;
		}
		getPlayerGame(username).addDie(num);
		playerGameDao.update(getPlayerGame(username));
		curr.removeDie(num);
		playerGameDao.update(curr);
		return true;
	}

	@Override
	public int getNbDice(String username) {
		Util.checkString(username);
		return getPlayerGame(username).getNbDice();
	}

	@Override
	public User nextPlayer() {
		if (game == null) {
			return null;
		}

		PlayerGame next = game.nextPlayer();
		return next.getUser();
	}

	@Override
	public void cancelGame() {
		if (game != null) {
			game.cancel();
			game = null;
		}
	}

	@Override
	public boolean createGame(String gameName) {
		Util.checkString(gameName);
		game = new Game(gameName);
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
	public List<Card> drawCard(String username, int num) {
		Util.checkString(username);
		Util.checkPositiveOrZero(num);
		return giveCardsTo(username, cardsUcc.drawCard(num));
	}

	public void keepRandomCards(String username, int num) {
		Util.checkString(username);
		Util.checkPositiveOrZero(num);
		game.keepRandomCard(getPlayerGame(username), num);
	}

	@Override
	public boolean discard(String username, int effectCode) {
		Util.checkString(username);
		Util.checkPositiveOrZero(effectCode);
		PlayerGame pg = getPlayerGame(username);
		for (Card c : pg.getCards()) {
			if (c.getEffectCode() == effectCode) {
				pg.removeCard(c.getId());
				cardsUcc.discard(c);
				return true;
			}
		}
		return false;
	}

	@Override
	public int getMaxPlayers() {
		return maxPlayers;
	}

	public static void setMaxPlayers(int maxPlayers) {
		Util.checkPositive(maxPlayers);
		GameUccImpl.maxPlayers = maxPlayers;
	}

	@Override
	public int getMinPlayers() {
		return minPlayers;
	}

	public static void setMinPlayers(int minPlayers) {
		Util.checkPositive(minPlayers);
		GameUccImpl.minPlayers = minPlayers;
	}

	@Override
	public String getGoal() {
		return goal;
	}

	public static void setGoal(String goal) {
		Util.checkString(goal);
		GameUccImpl.goal = goal;
	}

	@Override
	public int getDicePerPlayer() {
		return dicePerPlayer;
	}

	public static void setDicePerPlayer(int dicePerPlayer) {
		Util.checkPositive(dicePerPlayer);
		GameUccImpl.dicePerPlayer = dicePerPlayer;
	}

	public static void setNbCardsByPlayer(int nbCardsByPlayer) {
		Util.checkPositive(nbCardsByPlayer);
		GameUccImpl.nbCardsByPlayer = nbCardsByPlayer;
	}

	@Override
	public int getNbCardsByPlayer() {
		return nbCardsByPlayer;
	}

	@Override
	public List<Card> getCardsOf(String username) {
		Util.checkString(username);
		PlayerGame p = getPlayerGame(username);
		p = playerGameDao.reload(p.getPk());
		return p.getCards();
	}

	public List<Card> giveCardsTo(String username, List<Card> cards) {
		Util.checkString(username);
		Util.checkObject(cards);
		PlayerGame p = getPlayerGame(username);
		p = playerGameDao.reload(p.getPk());
		for (Card c : cards) {
			p.addCard(c);
		}
		p = playerGameDao.update(p);
		return p.getCards();
	}

	public static void setFaces(List<Face> value) {
		Util.checkObject(value);
		faces = value;
	}

	@Override
	public boolean giveMeCards(String src) {
		Util.checkString(src);
		if (src.equals(getCurrentPlayer())) {
			return false;
		}
		cardsUcc.stealCardFrom(getPlayerGame(getCurrentPlayer()), getPlayerGame(src));
		return true;
	}

	@Override
	public void changeDirection() {
		game.changeDirection();
	}

	@Override
	public void removePlayer(String username) {
		Util.checkString(username);
		game.removePlayer(getPlayerGame(username));
	}

	@Override
	public void endGame() {
		if (game == null) {
			System.out.println("Aucun jeu lancé");
			return;
		}
		game.endGame();
		// gameDao.update(game);
		this.winner = game.getWinner();
		game = null;
	}

	public String getWinner() {
		return this.winner;
	}

	@Override
	public void skipTurn(String username) {
		game.skipTurn(getPlayerGame(username));
	}
	
	@Override
	public List<Game> getAllGames(){
		return gameDao.getAll();
	}
}
