package be.ipl.blitz.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.ipl.blitz.usecases.GameUcc;

@WebServlet("/join-lobby.html")
public class JoinLobbyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GameUcc gameUcc;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final ServletContext context = getServletContext();
		synchronized (context) {
			// TODO Add security - Przemek
			gameUcc.joinGame((String) context.getAttribute("game-name"),
					(String) request.getSession().getAttribute("username"));
		}

		response.sendRedirect(request.getServletPath() + "/lobby.html");
	}

}
