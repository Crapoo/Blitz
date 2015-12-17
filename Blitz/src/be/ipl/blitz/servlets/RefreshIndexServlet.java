package be.ipl.blitz.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.ipl.blitz.domaine.Game.State;
import be.ipl.blitz.usecases.GameUcc;

@WebServlet("/refresh-index.html")
public class RefreshIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GameUcc gameUcc;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		JsonObjectBuilder oBuilder = Json.createObjectBuilder();

		List<String> playersList = gameUcc.listPlayers();

		if (playersList == null) {
			oBuilder.add("playersCount", 0);
		} else {
			oBuilder.add("playersCount", gameUcc.listPlayers().size());
		}
		State state = gameUcc.getState();
		if (state != null) {
			oBuilder.add("gameState", gameUcc.getState().toString());
		} else {
			oBuilder.add("gameState", "OVER");
		}
		JsonObject json = oBuilder.build();
		
		response.getWriter().print(json.toString());
	}

}
