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
	private static final long serialVersionUID = 1L;

	@EJB
	private UserUcc userUcc;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String connectionButton = request.getParameter("connection");

		// TODO How to store the session ?
		// TODO: protect against evil sql/xss injections

		// if (connectionButton == null) {
		String nickname = request.getParameter("signin-nickname");
		String password = request.getParameter("signin-password");

		try {
			Util.checkString(nickname);
			Util.checkString(password);
			try {
				if (userUcc.login(nickname, password)) {
					login(nickname, request, response);
					return;
				} else {
					getServletContext().setAttribute("status", "signin-error");
					getServletContext().setAttribute("error-message", "Mauvais Pseudo ou Mot-de-passe");
				}
			} catch (Exception e) {
				getServletContext().setAttribute("status", "signin-error");
				getServletContext().setAttribute("error-message", "Erreur de connection");
			}
		} catch (NullPointerException | IllegalArgumentException e) {
			getServletContext().setAttribute("status", "signin-error");
			getServletContext().setAttribute("error-message", "Veuillez remplir les champs correctement");
		}

		// }
		request.getRequestDispatcher("login.html").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getNamedDispatcher("index.html").forward(req, resp);
	}

	private void login(String nickname, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		synchronized (session) {
			session.setAttribute("nickname", nickname);
			session.setAttribute("connected", true);
		}
		getServletContext().getNamedDispatcher("index.html").forward(req, resp);
	}
}
