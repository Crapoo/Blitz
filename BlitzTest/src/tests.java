import java.util.HashSet;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import be.ipl.blitz.usecases.CardsUcc;
import be.ipl.blitz.usecases.GameUcc;
import be.ipl.blitz.usecases.UserUcc;

public class tests {
	public static void main(String[] args) {
		try {
			String gameName = "New Game";
			Set<String> playsersUsername = new HashSet<String>();
			playsersUsername.add("1");
			playsersUsername.add("2");
			playsersUsername.add("3");

			Context jdni = new InitialContext();

			UserUcc userUcc = (UserUcc) jdni.lookup("ejb:BlitzEAR/BlitzEJB/UserUccImpl!be.ipl.blitz.usecases.UserUcc");
			GameUcc gameUcc = (GameUcc) jdni.lookup("ejb:BlitzEAR/BlitzEJB/GameUccImpl!be.ipl.blitz.usecases.GameUcc");
			CardsUcc cardUcc = (CardsUcc) jdni.lookup("ejb:BlitzEAR/BlitzEJB/CardsUccImpl!be.ipl.blitz.usecases.CardsUcc");
			/**********************************************************************
			 * TESTS USERUCC
			 ************************************************************************/
	/*		try {
				System.out.println("TEST USERUCC\n");
				if (userUcc.saveUser("bob", "bob")) {
					System.out.println("user saved in db");
					if (userUcc.login("bob", "bob") == null) {
						System.err.println("Probleme de login");
					}else{
						System.out.println("login OK");
					}
					if (userUcc.login("bob", "wfwqer") != null) {
						System.err.println("Probleme de login");
					}else{
						System.out.println("verif pwd OK");
					}
					if (userUcc.login("bob", "bob") == null) {
						System.err.println("on a inscrit deux user du meme pseudo");
					}else{
						System.out.println("username tjrs =! OK");
					}
					System.out.print("test user.find by name: ");
					System.out.println(userUcc.findByName("bob").getName().equals("bob"));
				} else {
					System.err.println("user could not be created");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
*/
			
			/**************************************************************************
			 * 	GAMEUCC TESTS
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
		/*	for (String pseudo : playsersUsername) {
				if (!gameUcc.joinGame(gameName, pseudo)) {
					System.err.println("player" + pseudo + " could not join game");
				}
				System.out.println("Player" + pseudo + " joined succesfully");
			}
			*/			
		
			System.out.println("\nlist of players:");
		//	System.out.println(gameUcc.listPlayers().toString());
/*			
			if(!gameUcc.startGame()){
				System.err.println("game not started");
			}else{
				System.out.println("game started: state: "+gameUcc.getState());
				System.out.println("\nlist of players:");
				System.out.println(gameUcc.listPlayers().toString());
			}
*/
			
			/*****************************************************************************
			 * TEST CARDUCC
			 *****************************************************************************/
			System.out.println("\n\nTESTS UCCCARDS\n");
			cardUcc.shuffleDeck();
			System.out.println("careffect "+cardUcc.drawCard(1).get(0).getEffect());
			//TODO:gerer si il y a plus de cartes dans le deck
			
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}
}
