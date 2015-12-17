package be.ipl.blitz.usecasesImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import be.ipl.blitz.domaine.Game.State;
import be.ipl.blitz.usecases.CardsUcc;
import be.ipl.blitz.usecases.GameUcc;
import be.ipl.blitz.usecases.UserUcc;

public class GameUccTest {

	static UserUcc userUcc;
	static GameUcc gameUcc;
	static CardsUcc cardUcc;
	String gameName = "game";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Context jdni = new InitialContext();

		userUcc = (UserUcc) jdni.lookup("ejb:BlitzEAR/BlitzEJB/UserUccImpl!be.ipl.blitz.usecases.UserUcc");
		gameUcc = (GameUcc) jdni.lookup("ejb:BlitzEAR/BlitzEJB/GameUccImpl!be.ipl.blitz.usecases.GameUcc");
		cardUcc = (CardsUcc) jdni.lookup("ejb:BlitzEAR/BlitzEJB/CardsUccImpl!be.ipl.blitz.usecases.CardsUcc");

	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
		gameUcc.endGame();
	}

	@Test
	public void testJoinGame() {
		gameUcc.createGame(gameName);
		assertTrue("Rejoindre partie impossible", gameUcc.joinGame(gameName, "em"));
		assertFalse("Rejoindre partie inexistante", gameUcc.joinGame("test", "em"));
	}

	@Test
	public void testListPlayers() {
		gameUcc.createGame(gameName);
		gameUcc.joinGame(gameName, "em");
		gameUcc.joinGame(gameName, "ol");
		gameUcc.joinGame(gameName, "mi");
		assertEquals("Nombre de joueurs incorrects", 3, gameUcc.listPlayers().size());
	}

	@Test
	public void testStartGame() {
		gameUcc.createGame(gameName);
		assertEquals("joueurs?", 0, gameUcc.listPlayers().size());
		assertFalse("Démarrage de partie sans joueur", gameUcc.startGame());
		assertEquals("Etat de la partie inexacte", State.INITIAL, gameUcc.getState());
		gameUcc.joinGame(gameName, "em");
		assertTrue("Démarrage de partie échoue", gameUcc.startGame());
		gameUcc.endGame();
		assertFalse("Démarrage de partie inexistante", gameUcc.startGame());
	}

	@Test
	public void testGetCurrentPlayer() {
		gameUcc.createGame(gameName);
		gameUcc.joinGame(gameName, "em");
		gameUcc.startGame();
		assertEquals("Joueur courant inexact", "em", gameUcc.getCurrentPlayer());
	}

	@Test
	public void testThrowDice() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteDice() {
		fail("Not yet implemented");
	}

	@Test
	public void testGiveDice() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNbDice() {
		fail("Not yet implemented");
	}

	@Test
	public void testNextPlayer() {
		gameUcc.createGame(gameName);
		gameUcc.joinGame(gameName, "em");
		gameUcc.joinGame(gameName, "ol");
		gameUcc.startGame();
		String firstPlayer = gameUcc.getCurrentPlayer();
		String nextPlayer = gameUcc.nextPlayer().getName();
		assertFalse("Next player garde le meme joueur", firstPlayer.equals(nextPlayer));
	}

	@Test
	public void testGetWinner() {
		gameUcc.createGame(gameName);
		gameUcc.joinGame(gameName, "em");
		gameUcc.joinGame(gameName, "ol");
		gameUcc.startGame();
		assertEquals("Etat incorrect", State.IN_PROGRESS, gameUcc.getState());
		gameUcc.removePlayer("ol");
		gameUcc.endGame();
		assertEquals("Mauvais gagnant", "em", gameUcc.getWinner());
	}

	@Test
	public void testCancelGame() {
		gameUcc.createGame(gameName);
		gameUcc.cancelGame();
		assertEquals("Jeu pas annulé", null, gameUcc.getState());
	}

	@Test
	public void testCreateGame() {
		assertTrue("partie pas créée", gameUcc.createGame(gameName));
		assertEquals("Partie state pas correct", State.INITIAL, gameUcc.getState());
		assertFalse("deuxieme partie cree", gameUcc.createGame(gameName));
	}

	@Test
	public void testGetState() {
		gameUcc.createGame(gameName);
		gameUcc.startGame();
		assertEquals("etat pas correct", State.INITIAL, gameUcc.getState());
	}

	@Test
	public void testDrawCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testDiscard() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCardsOf() {
		fail("Not yet implemented");
	}

	@Test
	public void testGiveCardsTo() {
		fail("Not yet implemented");
	}

	@Test
	public void testGiveMeCards() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangeDirection() {
		gameUcc.createGame(gameName);
		gameUcc.joinGame(gameName, "em");
		gameUcc.joinGame(gameName, "ol");
		gameUcc.joinGame(gameName, "mi");
		gameUcc.startGame();

		String firstPlayer = gameUcc.getCurrentPlayer();
		gameUcc.nextPlayer();
		gameUcc.changeDirection();
		gameUcc.nextPlayer();

		assertEquals("Changement de sens echoue", firstPlayer, gameUcc.getCurrentPlayer());
	}

	@Test
	public void testRemovePlayer() {
		gameUcc.createGame(gameName);
		gameUcc.joinGame(gameName, "em");
		gameUcc.joinGame(gameName, "ol");
		gameUcc.joinGame(gameName, "mi");
		gameUcc.removePlayer("ol");
		assertEquals("Nombre de joueurs incorrects", 2, gameUcc.listPlayers().size());
		gameUcc.removePlayer("pasDedans");
		assertEquals("Suppression joueur inexistant - nombre de joueurs incorrects", 2, gameUcc.listPlayers().size());
	}

	@Test
	public void testEndGame() {
		gameUcc.startGame();
		gameUcc.endGame();
		assertEquals("Impossible de finir une partie", null, gameUcc.getState());
	}

}
