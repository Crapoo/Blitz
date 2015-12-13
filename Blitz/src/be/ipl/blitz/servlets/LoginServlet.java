package be.ipl.blitz.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.ipl.blitz.usecases.UserUcc;
import be.ipl.blitz.usecasesImpl.UserUccImpl;

@WebServlet("/login.html")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nickname = req.getParameter("signup-nickname");
		String password = req.getParameter("signup-password");
		String passwordRepeat = req.getParameter("signup-repeat-password");

		// TODO Needs a veeery big refactoring
		String nextPage = "login.html";

		if (!nickname.isEmpty() && !password.isEmpty() && !passwordRepeat.isEmpty()) {
			UserUcc userUcc = new UserUccImpl();
			if (password.equals(passwordRepeat)) {
				try {
					userUcc.saveUser(nickname, password);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			HttpSession session = req.getSession();
			synchronized (session) {
				session.setAttribute("name", nickname);
				session.setAttribute("connected", true);
			}
			nextPage = "index.html";
		}
		getServletContext().getNamedDispatcher(nextPage).forward(req, resp);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getNamedDispatcher("login.html").forward(req, resp);
	}
}
