package br.com.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.breadcrumb.Breadcrumb;
import br.com.breadcrumb.BreadcrumbHelper;

/**
 * Servlet Filter implementation class BreadcrumbFilter
 */
@WebFilter(filterName="/BreadcrumbFilter", urlPatterns="/page/*")
public class BreadcrumbFilter implements Filter {

    /**
     * Default constructor. 
     */
    public BreadcrumbFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpSession session = ((HttpServletRequest) request).getSession();
		ArrayList<Breadcrumb> list = (ArrayList<Breadcrumb>) session.getAttribute("breadcrumb");
		if (list == null) {
			list = (ArrayList<Breadcrumb>) new ArrayList<Breadcrumb>();
		}
		
		BreadcrumbHelper helper = new BreadcrumbHelper();
		Breadcrumb breadcrumb = helper.write(request);
		
		//valid
		if (helper.validate(list, breadcrumb)) {
			list.add(breadcrumb);
		}
				
		//remove
		String parameter = request.getParameter("index");
		if (parameter != null) {
			int index = Integer.parseInt(parameter);
			for (int i = list.size()-1; i > index; i--) {
				list.remove(i);
			}
		}
		
		
		
		session.setAttribute("breadcrumb",list);
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
