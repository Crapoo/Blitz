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
		List<String> players = gameUcc.listPlayers();

		String myUsername = (String) request.getServletContext().getAttribute("username");
		List<Card> myCards = gameUcc.getCardsOf(myUsername);

		JsonObjectBuilder oBuilder = Json.createObjectBuilder();
		JsonArrayBuilder listBuilder = Json.createArrayBuilder();
		JsonObjectBuilder mBuilder = Json.createObjectBuilder();
		JsonArrayBuilder cBuilder = Json.createArrayBuilder();

		for (String player : players) {
			if (!player.equals(myUsername)) {
				JsonObjectBuilder playerBuilder = Json.createObjectBuilder();
				playerBuilder.add("username", player);
				playerBuilder.add("nbDice", gameUcc.getNbDice(player));
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

		oBuilder.add("myCards", cBuilder.build());
		oBuilder.add("myDice", gameUcc.getNbDice(myUsername));

		response.setContentType("application/json");

		String json = oBuilder.build().toString();
		response.getWriter().println(json);
	}

}
