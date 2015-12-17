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
			String gameName = (String) context.getAttribute("game-name");

/* TODO : Matt - Peut être mieux de faire ainsi? Si pas d epartie ou impossible de la joindre, on renvoie vers l'index, avec une erreur ou quoi
            if (gameName == null || !gameUcc.joinGame(gameName, (String) request.getSession().getAttribute("username"))) {
                // Renvoyer vers l'index?
            } else {
                response.sendRedirect(request.getContextPath() + "/lobby.html");
            }
*/
			if (gameName != null) {
				if (gameUcc.joinGame(gameName, (String) request.getSession().getAttribute("username"))) {
					response.sendRedirect(request.getContextPath() + "/lobby.html");
				}
			}
		}

	}

}
