package be.ipl.blitz.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login.html")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nickname = req.getParameter("nickname");
		String password = req.getParameter("password");
		String passwordRepeat = req.getParameter("passwordRepeat");

		if (nickname.isEmpty() || password.isEmpty() || passwordRepeat.isEmpty()) {

		}

		HttpSession session = req.getSession();
		synchronized (session) {
			session.setAttribute("name", nickname);
			session.setAttribute("connected", true);
		}
		
		getServletContext().getNamedDispatcher("index.html").forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getNamedDispatcher("login.html").forward(req, resp);
	}
}
