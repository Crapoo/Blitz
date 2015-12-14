package be.ipl.blitz.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.ipl.blitz.domaine.Game.State;
import be.ipl.blitz.usecases.GameUcc;
import be.ipl.blitz.utils.Util;

@WebServlet("/create-game.html")
public class CreateGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private GameUcc gameUcc;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getNamedDispatcher("index.html").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String gameName = request.getParameter("create-game-name");

		try {
			Util.checkString(gameName);
		} catch (NullPointerException | IllegalArgumentException e) {
			// TODO Do something
		}
		
		final ServletContext context = getServletContext();

		synchronized (context) {
			/*if (gameUcc.createGame(gameName)) {
				
			} else {
				
			}*/
			//State gameState = gameUcc.getState();
			//context.setAttribute("game-status", gameState);
			context.setAttribute("game-status", State.IN_PROGRESS);
		}
		getServletContext().getNamedDispatcher("index.html").forward(request, response);
	}

}
