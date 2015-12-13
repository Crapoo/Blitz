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

@WebServlet("/login.html")
public class LoginServlet extends HttpServlet {
	@EJB
	private UserUcc userUcc;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String connectionButton = req.getParameter("connection");
		
		// TODO How to store the session ?

		if (connectionButton == null) {

		} else if (connectionButton.equals("signup")) {
			String nickname = req.getParameter("signup-nickname");
			String password = req.getParameter("signup-password");
			String passwordRepeat = req.getParameter("signup-repeat-password");

			try {
				Util.checkObject(nickname);
				Util.checkObject(password);
				Util.checkObject(passwordRepeat);
				Util.checkString(nickname);
				Util.checkString(password);
				Util.checkString(passwordRepeat);
				if (password.equals(passwordRepeat)) {
					try {
						userUcc.saveUser(nickname, password);
						login(nickname, req, resp);
						return;
					} catch (Exception e) {
						getServletContext().setAttribute("status", "signup-error");
						getServletContext().setAttribute("error-message", "Erreur lors de la cr&eacute;ation d'un compte");
					}
				}
			} catch (NullPointerException | IllegalArgumentException e) {
				getServletContext().setAttribute("status", "signup-error");
				getServletContext().setAttribute("error-message", "Veuillez remplir les champs correctement");
			}

		} else if (connectionButton.equals("signin")) {
			String nickname = req.getParameter("signin-nickname");
			String password = req.getParameter("signin-password");

			try {
				Util.checkObject(nickname);
				Util.checkObject(password);
				Util.checkString(nickname);
				Util.checkString(password);
				try {
					userUcc.login(nickname, password);
					login(nickname, req, resp);
					return;
				} catch (Exception e) {
					getServletContext().setAttribute("status", "signin-error");
					getServletContext().setAttribute("error-message", "Mauvais Pseudo ou Mot-de-passe");
				}
			} catch (NullPointerException | IllegalArgumentException e) {
				getServletContext().setAttribute("status", "signin-error");
				getServletContext().setAttribute("error-message", "Veuillez remplir les champs correctement");
			}

		}
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getNamedDispatcher("login.html").forward(req, resp);
	}

	private void login(String nickname, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		synchronized (session) {
			session.setAttribute("name", nickname);
			session.setAttribute("connected", true);
		}
		getServletContext().getNamedDispatcher("index.html").forward(req, resp);
	}
}
