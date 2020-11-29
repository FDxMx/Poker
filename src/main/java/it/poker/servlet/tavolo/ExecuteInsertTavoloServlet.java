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

import it.poker.dto.TavoloDTO;
import it.poker.model.Tavolo;
import it.poker.model.User;
import it.poker.service.tavolo.TavoloService;

/**
 * Servlet implementation class InsertTavoloServlet
 */
@WebServlet("/ExecuteInsertTavoloServlet")
public class ExecuteInsertTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TavoloService tavoloService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteInsertTavoloServlet() {
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String esperienzaMinima = request.getParameter("esperienzaMinima");
		String creditoMinimo = request.getParameter("creditoMinimo");
		String denominazione = request.getParameter("denominazione");
		String dataCreazione = request.getParameter("dataCreazione");
		
		TavoloDTO tavoloDTO = new TavoloDTO(esperienzaMinima, creditoMinimo, denominazione, dataCreazione);
		List<String> tavoloErrors = tavoloDTO.errors();
		if (!tavoloErrors.isEmpty()) {
			request.setAttribute("tavolo", tavoloDTO);
			request.setAttribute("errori", tavoloErrors);
			request.getRequestDispatcher("/tavolo/insert.jsp").forward(request, response);
			return;
		} else {
			Tavolo tavolo = TavoloDTO.buildModelFromDto(tavoloDTO);
			tavolo.setCreatoreTavolo((User)session.getAttribute("userSession"));
			tavoloService.insert(tavolo);
			request.setAttribute("effettuato", "Creazione tavolo avvenuta con successo!");
			request.getRequestDispatcher("ListaTavoliServlet").forward(request, response);
		}
	}

}
