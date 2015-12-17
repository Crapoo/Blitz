package be.ipl.blitz.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.ipl.blitz.usecases.UserUcc;
import be.ipl.blitz.utils.Util;

@WebServlet("/signin.html")
public class SigninServlet extends HttpServlet {
	private String errorMessage;
	private static final long serialVersionUID = 1L;

	@EJB
	private UserUcc userUcc;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("signin-username");
		String password = request.getParameter("signin-password");

		try {
			Util.checkString(username);
			Util.checkString(password);
			try {
				if (userUcc.login(username, password) != null) {
					login(username, request, response);
					return;
				} else {
					errorMessage = "Mauvais Pseudo ou Mot-de-passe";
				}
			} catch (Exception e) {
				errorMessage = "Erreur de connection";
			}
		} catch (NullPointerException | IllegalArgumentException e) {
			errorMessage = "Erreur de connection";
		}

		request.setAttribute("status", "signin-error");
		request.setAttribute("error-message", errorMessage);
		request.getRequestDispatcher("login.html").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/index.html");
	}

	private void login(String nickname, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		synchronized (session) {
			session.setAttribute("username", nickname);
			session.setAttribute("connected", true);
		}
		response.sendRedirect(request.getContextPath() + "/index.html");
	}
}
