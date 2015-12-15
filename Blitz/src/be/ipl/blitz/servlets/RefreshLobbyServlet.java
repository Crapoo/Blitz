package be.ipl.blitz.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.ipl.blitz.usecases.GameUcc;

@WebServlet("/refresh-lobby.html")
public class RefreshLobbyServlet extends HttpServlet {
	@EJB
	private GameUcc gameUcc;

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JsonObjectBuilder oBuilder = Json.createObjectBuilder();
		JsonArrayBuilder aBuilder = Json.createArrayBuilder();

		for (String player : gameUcc.listPlayers()) {
			aBuilder.add(player);
		}

		oBuilder.add("players-list", aBuilder);
		oBuilder.add("players-count", gameUcc.listPlayers().size());
		response.getWriter().print(oBuilder.build().toString());
	}

}
