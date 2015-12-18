package be.ipl.blitz.usecasesImpl;

import java.util.ArrayList;
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
import be.ipl.blitz.domaine.User;
import be.ipl.blitz.usecases.CardsUcc;
import be.ipl.blitz.usecases.GameUcc;
import be.ipl.blitz.usecases.UserUcc;
import be.ipl.blitz.utils.Util;

@Singleton
@Startup
public class GameUccImpl implements GameUcc {

	static int maxPlayers;
	static int minPlayers;
	static String goal;
	static int dicePerPlayer;

	static int nbCardsByPlayer;
	static List<Face> faces;

	public static void setDicePerPlayer(int dicePerPlayer) {
		Util.checkPositive(dicePerPlayer);
		GameUccImpl.dicePerPlayer = dicePerPlayer;
	}

	public static void setFaces(List<Face> value) {
		Util.checkObject(value);
		faces = value;
	}

	public static void setGoal(String goal) {
		Util.checkString(goal);
		GameUccImpl.goal = goal;
	}

	public static void setMaxPlayers(int maxPlayers) {
		Util.checkPositive(maxPlayers);
		GameUccImpl.maxPlayers = maxPlayers;
	}

	public static void setMinPlayers(int minPlayers) {
		Util.checkPositive(minPlayers);
		GameUccImpl.minPlayers = minPlayers;
	}

	public static void setNbCardsByPlayer(int nbCardsByPlayer) {
		Util.checkPositive(nbCardsByPlayer);
		GameUccImpl.nbCardsByPlayer = nbCardsByPlayer;
	}

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

	boolean replay = false;

	public GameUccImpl() {
	}

	/* Constantes de jeu */
	@Override
	public int getMaxPlayers() {
		return maxPlayers;
	}

	@Override
	public int getMinPlayers() {
		return minPlayers;
	}

	@Override
	public String getGoal() {
		return goal;
	}

	@Override
	public int getNbCardsByPlayer() {
		return nbCardsByPlayer;
	}

	@Override
	public int getDicePerPlayer() {
		return dicePerPlayer;
	}

	/* Gestion de la partie */
	@Override
	public boolean createGame(String gameName) {
		Util.checkString(gameName);
		if (game != null && !game.getState().equals(State.OVER)) {
			return false;
		}
		game = new Game(gameName);
		game = gameDao.save(game);
		cardsUcc.shuffleDeck();

		return true;
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
		gameDao.update(game);
		return true;
	}

	@Override
	public boolean startGame() {
		if (game == null || game.getUsers().size() < 2) {
			return false;
		}
		if (game.startGame()) {
			cardsUcc.shuffleDeck();
			giveInitalDice(game.getUsers());

			if (dealCards(game.getUsers())) {
				gameDao.update(game);
				return true;
			}
		}
		return false;
	}

	@Override
	public void cancelGame() {
		if (game != null) {
			game.cancel();
			game = null;
		}
	}

	@Override
	public void endGame() {
		if (game == null) {
			return;
		}
		game.endGame();
		gameDao.update(game);
	}

	@Override
	@Lock(LockType.READ)
	public State getState() {
		if (game == null) {
			return State.OVER;
		}
		return game.getState();
	}

	/* Gestion des joueurs */

	@Override
	@Lock(LockType.READ)
	public List<String> listPlayers() {
		if (game == null) {
			return new ArrayList<>();
		}
		List<PlayerGame> playerGames = game.getUsers();

		List<String> usernames = new ArrayList<String>();

		for (PlayerGame pl : playerGames) {

			usernames.add(pl.getUser().getName());
		}
		return usernames;
	}

	@Override
	public String getCurrentPlayer() {
		if (game == null) {
			return "";
		}
		int index = game.getCurrentUser() % game.getUsers().size();
		User u = game.getUsers().get(index).getUser();
		return u.getName();
	}

	@Override
	public String nextPlayer() {
		if (game == null) {
			return "";
		}
		if (replay) {
			replay = false;
			return getCurrentPlayer();
		} else {
			PlayerGame next = game.nextPlayer();
			next = playerGameDao.reload(next.getPk());
			return next.getUser().getName();
		}
	}

	@Override
	public void removePlayer(String username) {
		Util.checkString(username);
		if (listPlayers().contains(username)) {
			game.removePlayer(getPlayerGame(username));
		}
	}

	/* Actions du jeu */
	@Override
	public void changeDirection() {
		game.changeDirection();
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
	public boolean discard(String username, int effectCode) {
		Util.checkString(username);
		Util.checkPositiveOrZero(effectCode);
		PlayerGame pg = getPlayerGame(username);

		List<Card> cards = pg.getCards();
		for (int i = 0; i < cards.size(); i++) {
			Card card;
			if ((card = cards.get(i)).getEffectCode() == effectCode) {
				cardsUcc.discard(card);
				pg.removeCard(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Card> drawCard(String username, int num) {
		Util.checkString(username);
		Util.checkPositiveOrZero(num);
		return giveCardsTo(username, cardsUcc.drawCard(num));
	}

	@Override
	public List<Game> getAllGames() {
		return gameDao.getAll();
	}

	@Override
	public List<Card> getCardsOf(String username) {
		Util.checkString(username);
		PlayerGame p = getPlayerGame(username);
		return (p.getCards() == null) ? new ArrayList<Card>() : p.getCards();
	}

	@Override
	public int getNbDice(String username) {
		Util.checkString(username);
		// FIXME Przemek : getPlayerGame est null
		PlayerGame pg = getPlayerGame(username);
		if (pg == null) {
			return 0;
		}
		return pg.getNbDice();
		// return getPlayerGame(username).getNbDice();
	}

	public String getWinner() {
		if (game == null) {
			return "";
		} else {
			return game.getWinner();
		}
	}

	public List<Card> giveCardsTo(String username, List<Card> cards) {
		Util.checkString(username);
		Util.checkObject(cards);
		PlayerGame p = getPlayerGame(username);

		for (Card c : cards) {
			p.addCard(c);
		}
		return p.getCards();
	}

	@Override
	public boolean giveDice(String username, int num) {
		Util.checkPositiveOrZero(num);
		Util.checkString(username);
		if (username.equals(getCurrentPlayer())) {
			return false;
		}
		PlayerGame curr = getPlayerGame(getCurrentPlayer());
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
	public boolean giveMeCards(String player) {
		Util.checkString(player);
		if (player.equals(getCurrentPlayer())) {
			return false;
		}
		PlayerGame thief = getPlayerGame(getCurrentPlayer());
		PlayerGame victim = getPlayerGame(player);

		List<Card> victimCards = victim.getCards();
		if (victimCards.size() != 0) {
			Random r = new Random();
			int toSteal = r.nextInt(victimCards.size());
			Card card = victim.removeCard(toSteal);
			thief.addCard(card);
		}
		return true;
	}

	public void keepRandomCards(String username, int num) {
		Util.checkString(username);
		Util.checkPositiveOrZero(num);
		game.keepRandomCard(getPlayerGame(username), num);
	}

	@Override
	public void replay() {
		replay = true;
	}

	@Override
	public void skipTurn(String username) {
		game.skipTurn(getPlayerGame(username));
	}

	@Override
	public List<String> throwDice() {
		List<String> nFaces = new ArrayList<>();
		if (game == null)
			return new ArrayList<>();
		Random r = new Random();
		for (int i = 0; i < getPlayerGame(getCurrentPlayer()).getNbDice(); i++) {
			nFaces.add(faces.get(r.nextInt(6)).getIdentif());
		}
		return nFaces;
	}

	@Override
	public void limitAllToNumCards(int num) {
		for (PlayerGame pg : game.getUsers()) {
			if (!pg.getUser().getName().equals(getCurrentPlayer())) {
				game.keepRandomCard(pg, num);
			}
		}
	}

	/* Util */
	private PlayerGame getPlayerGame(String username) {
		List<PlayerGame> list = game.getUsers();
		for (PlayerGame playerGame : list) {
			if (playerGame.getUser().getName().equals(username)) {
				return playerGame;
			}
		}
		return null;
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

	private void giveInitalDice(List<PlayerGame> pg) {
		Util.checkObject(pg);
		for (PlayerGame player : pg) {
			player.addDie(dicePerPlayer);
			playerGameDao.update(player);
		}
	}

}
