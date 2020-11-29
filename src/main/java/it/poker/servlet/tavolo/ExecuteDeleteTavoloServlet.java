package it.poker.servlet.tavolo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.poker.model.Tavolo;
import it.poker.model.User;
import it.poker.service.tavolo.TavoloService;
import it.poker.service.user.UserService;

/**
 * Servlet implementation class ExecuteDeleteTavoloServlet
 */
@WebServlet("/ExecuteDeleteTavoloServlet")
public class ExecuteDeleteTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TavoloService tavoloService;
	
	@Autowired
	private UserService userService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteDeleteTavoloServlet() {
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
		String idTavolo = request.getParameter("id");
		if(idTavolo == null || idTavolo.equals("")) {
			request.setAttribute("errore", "Nessun tavolo selezionato!");
			request.getRequestDispatcher("ListaTavoliServlet").forward(request, response);
			return;
		}else {
			Tavolo tavolo = tavoloService.findById(Long.parseLong(idTavolo));
			List<User> giocatori = userService.findUserByTavolo(Long.parseLong(idTavolo));
			tavolo.setGiocatori(giocatori);
			if(giocatori.size() < 1) {
				tavoloService.delete(tavolo);
				request.setAttribute("effettuato", "Eliminazione effettuata!");
				request.getRequestDispatcher("ListaTavoliServlet").forward(request, response);
				return;
			} else {
				request.setAttribute("errore", "Questo tavolo ha dei giocatori!");
				request.getRequestDispatcher("ListaTavoliServlet").forward(request, response);
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
