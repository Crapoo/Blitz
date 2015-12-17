package be.ipl.blitz.game;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
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
		String target;
		int number;

		response.setContentType("application/json");

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
			target = (String) request.getAttribute("target");
			gameUcc.giveDice(target, 1);
			break;
		case 5:
			target = (String) request.getAttribute("target");
			gameUcc.giveMeCards(target);
			break;
		case 6:
			target = (String) request.getAttribute("target");
			gameUcc.keepRandomCards(target, 1);
			break;
		case 7:
			gameUcc.drawCard(username, 3);
			break;
		case 8:
			number = (int) request.getAttribute("number");
			for (String s : gameUcc.listPlayers()) {
				if (!s.equals(gameUcc.getCurrentPlayer())) {
					gameUcc.keepRandomCards(s, number);
				}
			}
			break;
		case 9:
			target = (String) request.getAttribute("target");
			gameUcc.skipTurn(target);
			break;
		case 10:
			// TODO:rejouer
			gameUcc.changeDirection();
			break;
		case 20:
			response.getWriter().print(throwDice());
			break;
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	private String throwDice() {
		List<String> faces = gameUcc.throwDice();

		JsonArrayBuilder aBuilder = Json.createArrayBuilder();
		
		for (String face : faces) {
			aBuilder.add(face);
		}
		
		return aBuilder.build().toString();

	}

}
