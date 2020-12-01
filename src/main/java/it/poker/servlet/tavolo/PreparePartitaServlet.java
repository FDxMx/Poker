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

import it.poker.model.Tavolo;
import it.poker.model.User;
import it.poker.service.tavolo.TavoloService;
import it.poker.service.user.UserService;

/**
 * Servlet implementation class PreparePartitaServlet
 */
@WebServlet("/PreparePartitaServlet")
public class PreparePartitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private TavoloService tavoloService;
	@Autowired
	private UserService userService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PreparePartitaServlet() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("userSession");

		String idTavolo = request.getParameter("idParametro");

		if (idTavolo == null || idTavolo.equals("")) {
			request.setAttribute("errore", "Nessun tavolo selezionato!");
			request.getRequestDispatcher("ListaTavoliTotaliServlet").forward(request, response);
			return;
		} else if (userSession.getTavolo() != null) {
			request.setAttribute("errore", "Hai gia una partita in corso, terminala per poterne iniziare una nuova!");
			request.getRequestDispatcher("ListaTavoliTotaliServlet").forward(request, response);
			return;
		} else {
			Tavolo tavolo = tavoloService.findById(Long.parseLong(idTavolo));
			if (userSession.getEsperienza() >= tavolo.getEsperienzaMinima() && userSession.getCredito() >= tavolo.getCreditoMinimo()) {
				userSession.setTavolo(tavolo);
				userService.update(userSession);
				request.setAttribute("effettuato", "Sei stato aggiunto alla partita!");
				request.getRequestDispatcher("/tavolo/partita.jsp").forward(request, response);
				return;
			} else if (userSession.getEsperienza() < tavolo.getEsperienzaMinima()) {
				request.setAttribute("errore", "Non hai abbastanza esperienza per sederti a questo tavolo!");
				request.getRequestDispatcher("ListaTavoliTotaliServlet").forward(request, response);
				return;
			} else if (userSession.getCredito() < tavolo.getCreditoMinimo()) {
				request.setAttribute("errore", "Non hai abbastanza credito per sederti a questo tavolo!");
				request.getRequestDispatcher("ListaTavoliTotaliServlet").forward(request, response);
				return;
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
