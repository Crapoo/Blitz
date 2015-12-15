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

/**
 * Servlet implementation class RefreshLobbyServlet
 */
@WebServlet("/refresh-lobby.html")
public class RefreshLobbyServlet extends HttpServlet {
	@EJB
	private GameUcc gameUcc;
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final ServletContext context = getServletContext();
		context.setAttribute("player-list", gameUcc.listPlayers());
	}

}
