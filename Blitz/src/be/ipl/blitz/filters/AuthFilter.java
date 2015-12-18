package be.ipl.blitz.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AuthFilter
 */
//@WebFilter("/index.html", "/create-game.html", "/compute-action.html", "/lobby.html", "/board.html")
@WebFilter(urlPatterns = {"/index.html", "/create-game.html", "/compute-action.html", "/lobby.html", "/board.html", 
		"/disconnect.html","/join-lobby.html","/refresh-index.html", "/refresh-lobby.html","/index.html/*", "/create-game.html/*", "/compute-action.html/*", "/lobby.html/*", "/board.html/*", 
		"/disconnect.html/*","/join-lobby.html/*","/refresh-index.html/*", "/refresh-lobby.html/*","/login.html/*"})
public class AuthFilter implements Filter {

	protected FilterConfig fConfig;

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String username = (String) req.getSession().getAttribute("username");
		if(username==null){
			((HttpServletResponse) response).sendRedirect(req.getContextPath()+"/login.html");
			return;
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig = fConfig;
	}

}
