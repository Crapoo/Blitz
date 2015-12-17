package be.ipl.blitz.game;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.ipl.blitz.usecases.GameUcc;

@WebServlet("/forfeit.html")
public class Forfeit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GameUcc gameUcc;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String) request.getSession().getAttribute("username");

		final ServletContext ctx = getServletContext();

		synchronized (ctx) {
			ctx.setAttribute("players-count", (int) ctx.getAttribute("players-count") - 1);
		}
		gameUcc.removePlayer(username);

		/*
		 * if (gameUcc.getWinner().isEmpty()) { }
		 */

		response.sendRedirect("index.html");
	}

}
