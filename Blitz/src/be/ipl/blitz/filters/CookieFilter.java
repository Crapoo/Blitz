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

@WebFilter(urlPatterns = "/*")
public class CookieFilter implements Filter {
	protected FilterConfig fConfig;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		/*if (!httpRequest.isRequestedSessionIdFromCookie()) {
			request.setAttribute("error-message", "No Cookies For You!");
			fConfig.getServletContext().getNamedDispatcher("error.html").forward(request, response);
			return;
		}*/
		// FIXME Always shows the error page at least once

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig = fConfig;
	}

}
