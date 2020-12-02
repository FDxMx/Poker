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
 * Servlet Filter implementation class UserSpecialPlayerFilter
 */
@WebFilter(urlPatterns = {"/ExecuteDeleteTavoloServlet", "/ExecuteInsertTavoloServlet", "/ExecuteSearchTavoloServlet",
						  "/ExecuteUpdateTavoloServlet", "/GiocatoriTavoloServlet", "/ListaTavoliServlet", "/PrepareDeleteTavoloServlet",
						  "/PrepareInsertTavoloServlet", "/PrepareSearchTavoloServlet", "/PrepareUpdateTavoloServlet", "/tavolo/delete.jsp",
						  "/tavolo/giocatoriTavolo.jsp", "/tavolo/insert.jsp", "/tavolo/listaTavoli.jsp", "/tavolo/search.jsp", "/tavolo/update.jsp"})
public class UserSpecialPlayerFilter implements Filter {

    /**
     * Default constructor. 
     */
    public UserSpecialPlayerFilter() {
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
			myRequest.getRequestDispatcher("login.jsp").forward(myRequest, myResponse);
		} else {
			for (RuoloUser ruolo : user.getRuoli()) {
				if(ruolo.equals(RuoloUser.SPECIAL_PLAYER_ROLE)) {
					myRequest.setAttribute("errore", "Non hai i permessi per questa operazione!!!");
					session.invalidate();
					myRequest.getRequestDispatcher("login.jsp").forward(myRequest, myResponse);
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
