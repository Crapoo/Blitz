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
		// TODO: protect against evil sql/xss injections

		String nickname = request.getParameter("signin-nickname");
		String password = request.getParameter("signin-password");

		try {
			Util.checkString(nickname);
			Util.checkString(password);
			try {
				if (userUcc.login(nickname, password) != null) {
					login(nickname, request, response);
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

		getServletContext().setAttribute("status", "signin-error");
		getServletContext().setAttribute("error-message", errorMessage);
		request.getRequestDispatcher("login.html").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "index.html");
	}

	private void login(String nickname, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		synchronized (session) {
			session.setAttribute("nickname", nickname);
			session.setAttribute("connected", true);
		}
		response.sendRedirect(request.getContextPath() + "index.html");
	}
}
