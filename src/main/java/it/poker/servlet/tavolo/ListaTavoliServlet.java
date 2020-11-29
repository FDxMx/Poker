package it.poker.servlet.tavolo;

import java.io.IOException;
import java.util.List;

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

/**
 * Servlet implementation class ListaTavoliServlet
 */
@WebServlet("/ListaTavoliServlet")
public class ListaTavoliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TavoloService tavoloService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaTavoliServlet() {
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
		User user = (User)session.getAttribute("userSession");
		List<Tavolo> listaTavoli = tavoloService.findTavoliByCreatore(user.getId());
		if (listaTavoli.size() < 1) {
			request.setAttribute("avvertimento", "Non hai nessun Tavolo! Creane uno adesso!");
			request.getRequestDispatcher("/tavolo/listaTavoli.jsp").forward(request, response);
			return;
		}else {
			request.setAttribute("listaTavoli", listaTavoli);
			request.getRequestDispatcher("/tavolo/listaTavoli.jsp").forward(request, response);
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
