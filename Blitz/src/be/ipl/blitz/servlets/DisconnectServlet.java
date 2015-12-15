package be.ipl.blitz.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.ipl.blitz.usecases.GameUcc;

@WebServlet("/disconnect.html")
public class DisconnectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GameUcc gUcc;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session != null)
			session.invalidate();

		response.sendRedirect(request.getContextPath() + "/login.html");
	}

}
