package service;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter implements Filter {

	private FilterConfig filterConfig = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String var = filterConfig.getInitParameter("var");
		System.out.println(var);
		// chain.doFilter(request, response);

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		if (acceptsGZipEncoding(httpRequest)) {
			httpResponse.addHeader("Content-Encoding", "gzip");
			GZipServletResponseWrapper gzipResponse = new GZipServletResponseWrapper(httpResponse);
			chain.doFilter(request, gzipResponse);
			gzipResponse.close();
		} else {
			chain.doFilter(request, response);
		}

	}

	private boolean acceptsGZipEncoding(HttpServletRequest httpRequest) {
		String acceptEncoding = httpRequest.getHeader("Accept-Encoding");

		return acceptEncoding != null && acceptEncoding.indexOf("gzip") != -1;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
