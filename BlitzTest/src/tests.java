import java.util.HashSet;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import be.ipl.blitz.usecases.CardsUcc;
import be.ipl.blitz.usecases.GameUcc;
import be.ipl.blitz.usecases.UserUcc;

public class tests {
	static UserUcc userUcc;
	static GameUcc gameUcc;
	static CardsUcc cardUcc;

	public static void main(String[] args) {
		try {
			String gameName = "New Game";
			Set<String> playsersUsername = new HashSet<String>();
			playsersUsername.add("1");
			playsersUsername.add("2");
			playsersUsername.add("3");

			Context jdni = new InitialContext();

			userUcc = (UserUcc) jdni.lookup("ejb:BlitzEAR/BlitzEJB/UserUccImpl!be.ipl.blitz.usecases.UserUcc");
			gameUcc = (GameUcc) jdni.lookup("ejb:BlitzEAR/BlitzEJB/GameUccImpl!be.ipl.blitz.usecases.GameUcc");
			cardUcc = (CardsUcc) jdni.lookup("ejb:BlitzEAR/BlitzEJB/CardsUccImpl!be.ipl.blitz.usecases.CardsUcc");
		

			/**************************************************************************
			 * GAMEUCC TESTS
			 *************************************************************/
			System.out.println("\n\n\nTEST GAMEUCC\n");
			if (!gameUcc.createGame(gameName)) {
				System.err.println("Game not created");
			} else {
				System.out.println("Game created successfuly");
			}
			System.out.print("creating a second game: ");
			if (!gameUcc.createGame(gameName)) {
				System.out.println("Game not created.. OK");
			} else {
				System.err.println("Game created successfuly");
			}
			System.out.println("state of game:" + gameUcc.getState());

			gameUcc.joinGame(gameName, "em");
			gameUcc.joinGame(gameName, "mi");
			gameUcc.joinGame(gameName, "ol");

			gameUcc.startGame();

			printGameState();

			gameUcc.removePlayer("em");
			
			printGameState();
			
		/*	List<String> f = gameUcc.throwDice();

			System.out.println("dices thrown: " + f.size());
			for (String fa : f) {
				System.out.print(fa + " - ");
			}
			System.out.println();

			System.out.println("Current player removes 1 die:");
			gameUcc.deleteDice(1, gameUcc.getCurrentPlayer());
			printGameState();
			System.out.println("Current player gives 1 die to mi");
			if (!gameUcc.giveDice("mi", 1)) {
				gameUcc.giveDice("em", 1);
			}
			printGameState();

			System.out.println("Current player draws a card:");
			gameUcc.drawCard(gameUcc.getCurrentPlayer(), 1);
			printGameState();

			// NOT IMPLEMENTED YET
			 System.out.println("Current player steals card from");
			 if(!gameUcc.giveMeCards("mi")){
				 gameUcc.giveMeCards("ol");
			 }
			 printGameState();
*/
			// int effectCode=;
			// System.out.println("\n\ncards of currentPlayer:");
			// for(Card s:gameUcc.getCardsOf(gameUcc.getCurrentPlayer())){
			// System.out.println(s);
			// }
			// System.out.println("Current user discards a card of effectcode:
			// "+effectCode);
			// gameUcc.discard(gameUcc.getCurrentPlayer(), effectCode);
			// System.out.println("\n\ncards of currentPlayer:");
			// for(Card s:gameUcc.getCardsOf(gameUcc.getCurrentPlayer())){
			// System.out.println(s);
			// }
			//TODO: gerer si il y n y a plus de card dans le deck
			//TODO: verifier dans toutes les methodes si l utilisateur a bien les cartes/de pour faire ce qu il veut
			
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	static void printGameState() {
		System.out.println("\nGame state: " + gameUcc.getState());
		for (String p : gameUcc.listPlayers()) {
			System.out.print(p + ": ");
			System.out.println("\tnombre de dés: " + gameUcc.getNbDice(p));
			System.out.println("\tnombre de cartes: " + gameUcc.getCardsOf(p).size() + "\n");
		}
		System.out.println("Current player: " + gameUcc.getCurrentPlayer());
	}

}
