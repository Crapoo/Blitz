package be.ipl.blitz.servlets;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "FilterController", urlPatterns = "/*", dispatcherTypes = { DispatcherType.REQUEST })
public class FilterController implements Filter {
	private FilterConfig fConfig;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/*
		 * if (!(request instanceof HttpServletRequest)) {
		 * request.setAttribute("error-message",
		 * "Veuillez ne pas essayer de pirater le serveur et de passer par de l'HTTP."
		 * );
		 * request.getServletContext().getNamedDispatcher("error.html").forward(
		 * request, response); return; }
		 */

		HttpServletRequest req = (HttpServletRequest) request;
		/*
		 * if (!req.isRequestedSessionIdFromCookie()) {
		 * request.setAttribute("error-message",
		 * "Cookies. Il te faut des cookies. Ok ?");
		 * request.getServletContext().getNamedDispatcher("error.html").forward(
		 * request, response); return; }
		 */

		String name = req.getServletPath();
		String[] pages = { "/index.html", "/login.html", "/signin.html", "/signup.html", "/refresh-index.html", "/create-game.html", "/rejoindre.html" };

		if (!name.contains("/lib/") && !Arrays.asList(pages).contains(name)) {
			request.setAttribute("error-message",
					"Bonjour, la page que vous recherchez n'est actuellement pas disponible, veuillez retourner demain ou bien le jour qui vient après le jour de demain. Eventuellement, changez de fournisseurs d'accès internet et émmigrez au Kenya.");
			request.getServletContext().getNamedDispatcher("error.html").forward(request, response);
			return;
		}

		// If not logged, send to login.html
		/*HttpSession session = req.getSession();
		if (session.getAttribute("connected") == null) {
			request.getServletContext().getNamedDispatcher("login.html").forward(request, response);
			return;
		}*/

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig = fConfig;
	}

}
