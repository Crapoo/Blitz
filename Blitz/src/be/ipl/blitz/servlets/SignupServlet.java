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

@WebServlet("/signup.html")
public class SignupServlet extends HttpServlet {
	private String errorMessage;
	private static final long serialVersionUID = 1L;

	@EJB
	private UserUcc userUcc;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("signup-username");
		String password = request.getParameter("signup-password");
		String passwordRepeat = request.getParameter("signup-repeat-password");

		try {
			Util.checkString(username);
			Util.checkString(password);
			Util.checkString(passwordRepeat);
			if (password.equals(passwordRepeat)) {
				try {
					if (!userUcc.saveUser(username, password)) {
						throw new Exception();
					}
					login(username, request, response);
					return;
				} catch (Exception e) {
					errorMessage = "Erreur lors de la cr&eacute;ation d'un compte";
				}
			}
		} catch (NullPointerException | IllegalArgumentException e) {
			errorMessage = "Erreur lors de la cr&eacute;ation d'un compte";
		}

		getServletContext().setAttribute("status", "signup-error");
		getServletContext().setAttribute("error-message", errorMessage);
		getServletContext().getNamedDispatcher("login.html").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath() + "/index.html");
	}

	private void login(String nickname, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		synchronized (session) {
			session.setAttribute("username", nickname);
			session.setAttribute("connected", true);
		}
		resp.sendRedirect(req.getContextPath() + "/index.html");
	}

}
