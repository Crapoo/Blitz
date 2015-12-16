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

@WebServlet("/initialise-board.html")
public class InitialiseBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GameUcc gameUcc;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*-
		 * Template JSON
		 * {
		 * 		currentPlayer : String,
		 * 		nbCards : int,
		 * 		nbDice : int,
		 * 		myCards : [CardId, CardId, CardId]
		 * 		
		 * }
		 */

		String username = (String) getServletContext().getAttribute("nickname");

		JsonObjectBuilder oBuilder = Json.createObjectBuilder();
		JsonArrayBuilder aBuilder = Json.createArrayBuilder();

		oBuilder.add("currentPlayer", gameUcc.getCurrentPlayer());
		oBuilder.add("nbCards", gameUcc.getNbCardsByPlayer());
		oBuilder.add("nbDice", gameUcc.getDicePerPlayer());

		List<String> players = gameUcc.listPlayers();
		for (String player : players) {
			// On ne s'intéresse qu'au carte du joueur connecté
			if (player.equals(username)) {
				for (Card card : gameUcc.getCardsOf(username)) {
					aBuilder.add(card.getId());
				}
				break;
			}
		}

		oBuilder.add("myCards", aBuilder.build());
		response.setContentType("application/json");
		
		System.out.println(oBuilder.build().toString());
		response.getWriter().print(oBuilder.build().toString());
	}

}
