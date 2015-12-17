package be.ipl.blitz.game;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.ipl.blitz.domaine.Card;
import be.ipl.blitz.domaine.Game.State;
import be.ipl.blitz.usecases.GameUcc;

@WebServlet("/update-game.html")
public class UpdateGame extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GameUcc gameUcc;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currentPlayer = gameUcc.getCurrentPlayer();
		String myUsername = (String) request.getSession().getAttribute("username");
		List<String> players = gameUcc.listPlayers();
		String winner = gameUcc.getWinner();

		JsonObjectBuilder oBuilder = Json.createObjectBuilder();
		response.setContentType("application/json");

		if (!winner.isEmpty()) {
			oBuilder.add("hasWon", gameUcc.getWinner().equals(myUsername) ? true : false);
			String json = oBuilder.build().toString();
			System.out.println("P&!W NULL");
			System.out.println(json);
			response.getWriter().println(json);
			return;
		}

		if (players.size() <= 1) {
			gameUcc.endGame();
			oBuilder.add("", "");
			String json = oBuilder.build().toString();
			System.out.println("END GAME NULL");
			System.out.println(json);
			response.getWriter().println(json);
			return;
		} else {
			// TODO Store the current player in the context to minimise DB
			// access
			// and only update it on each end of turn - François

			List<Card> myCards = gameUcc.getCardsOf(myUsername);

			JsonArrayBuilder listBuilder = Json.createArrayBuilder();
			JsonObjectBuilder mBuilder = Json.createObjectBuilder();
			JsonArrayBuilder cBuilder = Json.createArrayBuilder();

			for (String player : players) {
				if (!player.equals(myUsername)) {
					JsonObjectBuilder playerBuilder = Json.createObjectBuilder();
					playerBuilder.add("username", player);

					int nbDice = gameUcc.getNbDice(player);

					if (nbDice == 0) {
						gameUcc.endGame();
					}

					playerBuilder.add("nbDice", nbDice);
					System.out.println(gameUcc.getCardsOf(player) + " - QSDFFFFFFFFFFFFFFFFFFFFFFF");
					playerBuilder.add("nbCards", gameUcc.getCardsOf(player).size());
					listBuilder.add(playerBuilder.build());
				}
			}

			oBuilder.add("players", listBuilder.build());

			if (currentPlayer.equals(myUsername)) {
				mBuilder.add("myTurn", true);
			} else {
				mBuilder.add("myTurn", false);
			}

			for (Card card : myCards) {
				cBuilder.add(card.toJson());
			}

			oBuilder.add("currentPlayer", gameUcc.getCurrentPlayer());
			oBuilder.add("myCards", cBuilder.build());
			oBuilder.add("myDice", gameUcc.getNbDice(myUsername));

		}

		String json = oBuilder.build().toString();
		response.getWriter().println(json);
	}
}
