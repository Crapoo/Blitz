package be.ipl.blitz.usecasesImpl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import be.ipl.blitz.daoImpl.PlayerGameDaoImpl;
import be.ipl.blitz.domaine.Face;
import be.ipl.blitz.usecases.GameUcc;
import be.ipl.blitz.usecases.UserUcc;

public class TestGameUcc {
	static GameUcc gameUcc;
	static UserUcc userUcc;
	static PlayerGameDaoImpl playerGameDao;
	String gameName="new game";
	String em="em";
	String ol="ol";
	String mi="mi";
	
	@BeforeClass
	public static void setUp() throws Exception {
		Context jdni = new InitialContext();
		gameUcc = (GameUcc) jdni.lookup("ejb:BlitzEAR/BlitzEJB/GameUccImpl!be.ipl.blitz.usecases.GameUcc");
		userUcc = (UserUcc) jdni.lookup("ejb:BlitzEAR/BlitzEJB/UserUccImpl!be.ipl.blitz.usecases.UserUcc");
		playerGameDao = (PlayerGameDaoImpl) jdni.lookup("ejb:BlitzEAR/BlitzEJB/playerDaoImpl!be.ipl.blitz.daoImpl.PlayerDaoImpl");
	}

	@Test
	public void testCreateGame() {
		assertTrue(gameUcc.createGame(gameName));
	}

	/**
	 * test tries to create two games at the same time
	 */
	@Test
	public void testCreateGame2() {
		gameUcc.createGame(gameName);
		assertFalse(gameUcc.createGame(gameName));
	}
	
	@Test
	public void testJoinGame() {
		assertTrue(gameUcc.joinGame(gameName, em));
		assertTrue(gameUcc.joinGame(gameName, mi));
		assertTrue(gameUcc.joinGame(gameName, ol));
	}
	
	//TODO : test if error when adding more than 6 players

	@Test
	public void testListPlayers() {
		List<String> l=new ArrayList<String>();
		l.add(em);l.add(mi);l.add(ol);
		assertTrue(gameUcc.listPlayers().equals(l));
	}

	@Test
	public void testStartGame() {
		assertTrue(gameUcc.startGame());
	}

	@Test
	public void testGetCurrentPlayer() {
		assertNotNull(gameUcc.getCurrentPlayer());
		assertFalse(gameUcc.getCurrentPlayer().trim().equals(""));
	}

	@Test
	public void testThrowDice() {
		Set<Face> l = new HashSet<>();
		l=gameUcc.throwDice();
		assertNotNull(l);
		//TODO test if number of faces in l == nb of dices of currentPlayer
	}

	@Test
	public void testDeleteDice() {
		fail("Not yet implemented");
	}

	@Test
	public void testNextPlayer() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWinner() {
		fail("Not yet implemented");
	}

	@Test
	public void testCancelGame() {
		fail("Not yet implemented");
	}



	@Test
	public void testGetState() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurrentGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testDrawCard() {
		fail("Not yet implemented");
	}

}
