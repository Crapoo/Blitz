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
import be.ipl.blitz.utils.Util;

@WebServlet("/login.html")
public class LoginServlet extends HttpServlet {
	private UserUcc userUcc;

	@Override
	public void init() throws ServletException {
		super.init();
		userUcc = new UserUccImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String connectionButton = req.getParameter("connection");

		// TODO Needs a veeery big refactoring
		// TODO Find a way to alert the user if there was an error

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
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (NullPointerException | IllegalArgumentException e) {
				doGet(req, resp);
				return;
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (NullPointerException | IllegalArgumentException e) {
				doGet(req, resp);
				return;
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
