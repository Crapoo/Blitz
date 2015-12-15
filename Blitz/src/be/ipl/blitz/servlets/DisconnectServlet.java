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

/**
 * Servlet implementation class DeconnectionServlet
 */
@WebServlet("/disconnect.html")
public class DisconnectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
    private GameUcc gUcc;
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		if (session != null)
			session.invalidate();
		//request.
		response.sendRedirect("login.html");

	}

}
