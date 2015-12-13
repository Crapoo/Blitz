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

@WebFilter(filterName = "FilterController", urlPatterns = "/*", dispatcherTypes = { DispatcherType.REQUEST })
public class FilterController implements Filter {
	private FilterConfig fConfig;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String name = req.getServletPath();

		String[] pages = { "/index.html", "/login.html" };

		if (!Arrays.asList(pages).contains(name)) {
			request.getServletContext().getNamedDispatcher("error.html").forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig = fConfig;
	}

}
