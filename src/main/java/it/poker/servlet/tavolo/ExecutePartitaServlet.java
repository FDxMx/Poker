package it.poker.servlet.tavolo;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.poker.model.User;
import it.poker.service.user.UserService;

/**
 * Servlet implementation class ExecutePartitaServlet
 */
@WebServlet("/ExecutePartitaServlet")
public class ExecutePartitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserService userService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecutePartitaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("userSession");
		
		int punteggio = (int)Math.floor(Math.random() * 100); 
		if(punteggio < 49) {
			punteggio = -punteggio;
		}else if(punteggio > 49) {
			punteggio = +punteggio;
		}
		
		if(punteggio > 0) {
			userSession.setCredito(userSession.getCredito() + (int)punteggio);
			userSession.setEsperienza(userSession.getEsperienza() + 20);
			userService.update(userSession);
			request.setAttribute("vincita", "HAI VINTO!!!");
			request.getRequestDispatcher("/tavolo/partita.jsp").forward(request, response);
			return;
		}else if(punteggio < 0) {
			int credito = userSession.getCredito() - (int)punteggio;
			if(credito < 0) {
				userSession.setCredito(0);
				userService.update(userSession);
				request.setAttribute("perditaTotale", "HAI PERSO TUTTO!!!");
				request.getRequestDispatcher("/tavolo/partita.jsp").forward(request, response);
				return;
			}else if(credito > 0) {
				userSession.setCredito(credito);
				userService.update(userSession);
				request.setAttribute("perdita", "HAI PERSO!!!");
				request.getRequestDispatcher("/tavolo/partita.jsp").forward(request, response);
				return;
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
