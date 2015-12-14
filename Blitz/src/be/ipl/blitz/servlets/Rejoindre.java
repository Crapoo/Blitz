package be.ipl.blitz.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.ipl.blitz.domaine.User;
import be.ipl.blitz.usecases.GameUcc;
import be.ipl.blitz.usecases.UserUcc;

/**
 * Servlet implementation class Rejoindre
 */
@WebServlet("/Rejoindre")
public class Rejoindre extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GameUcc gucc;
	private UserUcc uucc;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rejoindre() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String nickname = null;
		
		HttpSession se = request.getSession();
		synchronized (se) {
			nickname = (String) se.getAttribute("nickname");
		}
		
		gucc.getCurrentGame().addPlayer(uucc.findByNick(nickname));

		
		
		List<User> players = gucc.getCurrentGame().getPlayers();
		
		getServletContext().setAttribute("joueurs", players);
		getServletContext().getNamedDispatcher("rejoindre.html").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
