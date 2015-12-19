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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String username = (String) request.getSession().getAttribute("username");
		int effectCode = Integer.parseInt(request.getParameter("effect-code"));
		String target;
		int number;

		response.setContentType("application/json");

		if (effectCode != -1) {
			if (!gameUcc.discard(username, effectCode)) {
				/* TODO : Matt - Peut être afficher une erreur au joueur? */
				System.out.println("pas possible d'utiliser cette carte - inexistante");
				return;
			}
		}

		switch (action) {
		case "roll-dice":
			response.getWriter().print(rollDiceToJson());
			break;
		case "give-die":
			target = (String) request.getParameter("data");
			gameUcc.giveDice(target, 1);
			break;
		case "draw-cards":
			number = Integer.parseInt(request.getParameter("data"));
			gameUcc.drawCard(username, number);
			break;
		case "end-turn":
			String next = gameUcc.nextPlayer();
			break;
		case "skip-turn":
			target = (String) request.getParameter("data");
			gameUcc.skipTurn(target);
			break;
		case "discard-dice":
			number = Integer.parseInt(request.getParameter("data"));
			gameUcc.deleteDice(number, username);
			break;
		case "replay":
			gameUcc.replay();
			break;
		case "exchange-dice":
			target = (String) request.getParameter("data");
			gameUcc.exchangeDice(target);
			break;
		case "steal-card":
			target = (String) request.getParameter("data");
			gameUcc.giveMeCards(target);
			break;
		case "limit-target-to-one-card":
			target = (String) request.getParameter("data");
			gameUcc.keepRandomCards(target, 1);
			break;
		case "limit-all-to-two-cards":
			gameUcc.limitAllToNumCards(2);
			break;
		case "change-direction":
			gameUcc.changeDirection();
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
