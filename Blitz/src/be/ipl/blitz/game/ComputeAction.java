package be.ipl.blitz.game;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.ipl.blitz.usecases.GameUcc;

@WebServlet("/compute-action.html")
public class ComputeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private GameUcc gameUcc;
	
	// TODO Implement all the action in compute-action.js

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int actionCode = (int) request.getAttribute("action-code");
		String username = (String) request.getSession().getAttribute("username");
		
		switch (actionCode) {
		case 1:
			gameUcc.deleteDice(1, username);
			break;
		case 2:
			gameUcc.changeDirection();
			break;
		case 3:
			gameUcc.deleteDice(2, username);
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			gameUcc.drawCard(username, 3);
			break;
		case 8:
			
			break;
		case 9:
			break;
		case 10:
			break;
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
