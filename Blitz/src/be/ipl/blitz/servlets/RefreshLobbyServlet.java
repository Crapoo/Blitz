package be.ipl.blitz.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.ipl.blitz.usecases.GameUcc;

/**
 * Servlet implementation class RefreshLobbyServlet
 */
@WebServlet("/refresh-lobby.html")
public class RefreshLobbyServlet extends HttpServlet {
	@EJB
	private GameUcc gameUcc;
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*final ServletContext context = getServletContext();
		context.setAttribute("player-list", gameUcc.listPlayers());
		context.setAttribute("players-count", gameUcc.listPlayers().size());*/
		
		JsonObjectBuilder oBuilder = Json.createObjectBuilder();
		JsonArrayBuilder aBuilder = Json.createArrayBuilder();
		
		for (String player : gameUcc.listPlayers()) {
			aBuilder.add(player);
		}
		
		oBuilder.add("players-list", aBuilder.build());
		oBuilder.add("payers-count", gameUcc.listPlayers().size());
		oBuilder.build();
		// TODO Do something with the json - Przemek
	}

}
