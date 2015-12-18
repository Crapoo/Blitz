package be.ipl.blitz.game;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
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
		String action = request.getParameter("action");
		String username = (String) request.getSession().getAttribute("username");
		String target;

		response.setContentType("application/json");

		switch (action) {
		case "roll-dice":
			response.getWriter().print(rollDiceToJson());
			break;
		case "give-die":
			target = (String) request.getParameter("data");
			gameUcc.giveDice(target, 1);
			break;
		case "draw-cards":
			int number = Integer.parseInt(request.getParameter("data"));
			gameUcc.drawCard(username, number);
			break;
		case "end-turn":
			String next = gameUcc.nextPlayer();
			System.out.println("Next Player : " + next);
			break;
		case "replay":
			
			break;
		case "skip-turn":
			
			break;
		case "discard-dice":
			
			break;
		case "change-direction":
			break;
		case "steal-card":
			break;
		case "limit-target-to-one-cards":
			break;
		case "limit-all-to-two-cards":
			break;
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	private String rollDiceToJson() {
		List<String> faces = gameUcc.throwDice();

		JsonArrayBuilder aBuilder = Json.createArrayBuilder();

		for (String face : faces) {
			aBuilder.add(face);
		}

		return aBuilder.build().toString();

	}

}
