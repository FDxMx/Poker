package it.poker.servlet.tavolo;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.poker.model.Tavolo;
import it.poker.service.tavolo.TavoloService;
import it.poker.service.user.UserService;

/**
 * Servlet implementation class PrepareUpdateTavoloServlet
 */
@WebServlet("/PrepareUpdateTavoloServlet")
public class PrepareUpdateTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private TavoloService tavoloService;

	@Autowired
	private UserService userService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PrepareUpdateTavoloServlet() {
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
		String idTavolo = request.getParameter("idParametro");

		if (idTavolo == null || idTavolo.equals("")) {
			request.setAttribute("errore", "Nessun tavolo selezionato!");
			request.getRequestDispatcher("ListaTavoliServlet").forward(request, response);
		} else if (userService.findUserByTavolo(Long.parseLong(idTavolo)).size() > 0) {
			request.setAttribute("errore", "Non puoi modificare un tavolo se ci sono dei giocatori seduti!");
			request.getRequestDispatcher("ListaTavoliServlet").forward(request, response);
		} else {
			Tavolo tavolo = tavoloService.findById(Long.parseLong(idTavolo));
			request.setAttribute("tavolo", tavolo);
			request.getRequestDispatcher("/tavolo/update.jsp").forward(request, response);
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
