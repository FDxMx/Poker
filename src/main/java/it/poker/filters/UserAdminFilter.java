package it.poker.filters;

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
import javax.servlet.http.HttpSession;

import it.poker.model.RuoloUser;
import it.poker.model.StatoUser;
import it.poker.model.User;

/**
 * Servlet Filter implementation class UserAdminFilter
 */
@WebFilter(urlPatterns = {"/PrepareAbilita_DisabilitaServlet", "/ExecuteAbilita_DisabilitaServlet", "/DettagliUserServlet", "/ExecuteSearchUserServlet",
						  "/ExecuteUpdateUserServlet", "/ListaUserServlet", "/PrepareSearchUserServlet",
						  "/PrepareUpdateUserServlet", "/user/dettagliUser.jsp", "/user/listaUser.jsp",
						  "/user/search.jsp", "/user/update.jsp", "/user/attiva_disattiva.jsp"})
public class UserAdminFilter implements Filter {

    /**
     * Default constructor. 
     */
    public UserAdminFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest myRequest = (HttpServletRequest) request;
		HttpServletResponse myResponse = (HttpServletResponse)response;
		
		HttpSession session = myRequest.getSession();

		User user = (User)session.getAttribute("userSession");
		
		if (user == null || !user.getStato().equals(StatoUser.ABILITATO)) {
			myRequest.setAttribute("errore", "Devi prima accedere!");
			myRequest.getServletContext().getRequestDispatcher("/login.jsp").forward(myRequest, myResponse);;
		} else {
			for (RuoloUser ruolo : user.getRuoli()) {
				if(!ruolo.equals(RuoloUser.ADMIN_ROLE)) {
					myRequest.setAttribute("errore", "Non hai i permessi per questa operazione!!!");
					session.invalidate();
					myRequest.getRequestDispatcher("/login.jsp").forward(myRequest, myResponse);
				}
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
