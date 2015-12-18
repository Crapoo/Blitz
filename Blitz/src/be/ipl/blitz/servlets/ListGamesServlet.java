package be.ipl.blitz.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.ipl.blitz.domaine.Game;
import be.ipl.blitz.usecases.GameUcc;

/**
 * Servlet implementation class ListGamesServlet
 */
@WebServlet("/list-games.html")
public class ListGamesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GameUcc gameUcc;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		JsonObjectBuilder oBuilder = Json.createObjectBuilder();

		System.out.println("Games : " + gameUcc.getAllGames().size());

		int i = 0;
		for (Game game : gameUcc.getAllGames()) {
			JsonObjectBuilder oBuilderGame = Json.createObjectBuilder();
			oBuilderGame.add("name", game.getName());
			oBuilderGame.add("startDate", new SimpleDateFormat().format(game.getStartDate()));
			oBuilderGame.add("winner", game.getWinner() == null ? "annulée" : game.getWinner());
			oBuilder.add("" + i++, oBuilderGame.build());
		}

		JsonObject json = oBuilder.build();

		response.getWriter().print(json.toString());
	}
}
