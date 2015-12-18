package be.ipl.blitz.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

	private boolean startGame = false;

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JsonObjectBuilder oBuilder = Json.createObjectBuilder();
		JsonArrayBuilder aBuilder = Json.createArrayBuilder();
		List<String> players = gameUcc.listPlayers();

		if (players.size() == 1) {
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {

				@Override
				public void run() {
					System.out.println("en effet, joueurs : " + gameUcc.listPlayers().size() + "/" + gameUcc.getMinPlayers());
					startGame = gameUcc.listPlayers().size() >= gameUcc.getMinPlayers();
				}
			};
			timer.schedule(task, 30000);
		}
		for (String player : players) {
			aBuilder.add(player);
		}

		oBuilder.add("playersList", aBuilder);
		oBuilder.add("playersCount", players.size());
		oBuilder.add("minPlayers", gameUcc.getMinPlayers());
		oBuilder.add("startGame", startGame);
		response.getWriter().print(oBuilder.build().toString());
	}

}
