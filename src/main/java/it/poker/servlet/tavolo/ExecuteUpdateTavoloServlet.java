package it.poker.servlet.tavolo;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.poker.dto.TavoloDTO;
import it.poker.model.Tavolo;
import it.poker.service.tavolo.TavoloService;
import it.poker.service.user.UserService;

/**
 * Servlet implementation class ExecuteUpdateTavoloServlet
 */
@WebServlet("/ExecuteUpdateTavoloServlet")
public class ExecuteUpdateTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TavoloService tavoloService;
	@Autowired
	private UserService userService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteUpdateTavoloServlet() {
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
		String idTavolo = request.getParameter("id");
		String esperienzaMinima = request.getParameter("esperienzaMinima");
		String creditoMinimo = request.getParameter("creditoMinimo");
		String denominazione = request.getParameter("denominazione");
		String dataCreazione = request.getParameter("dataCreazione");
		String idCreatore = request.getParameter("idCreatore");
		
		if(idTavolo == null || idTavolo.equals("")) {
			request.setAttribute("errore", "Nessun tavolo selezionato!");
			request.getRequestDispatcher("ListaTavoliServlet").forward(request, response);
		} else {
			TavoloDTO tavoloDTO = new TavoloDTO(esperienzaMinima, creditoMinimo, denominazione, dataCreazione);
			List<String> tavoloErrors = tavoloDTO.errors();
			if(!tavoloErrors.isEmpty()) {
				request.setAttribute("errori", tavoloErrors);
				request.setAttribute("tavolo", tavoloService.findById(Long.parseLong(idTavolo)));
				request.getRequestDispatcher("/tavolo/update.jsp").forward(request, response);
				return;
			}else {
				Tavolo tavolo = TavoloDTO.buildModelFromDto(tavoloDTO);
				tavolo.setId(Long.parseLong(idTavolo));
				tavolo.setGiocatori(userService.findUserByTavolo(Long.parseLong(idTavolo)));
				tavolo.setCreatoreTavolo(userService.findById(Long.parseLong(idCreatore)));
				tavoloService.update(tavolo);
				request.setAttribute("effettuato", "Aggiornamento effettuato!");
				request.getRequestDispatcher("ListaTavoliServlet").forward(request, response);
			}
		}
		
	}

}
