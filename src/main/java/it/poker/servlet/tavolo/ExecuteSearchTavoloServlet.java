package it.poker.servlet.tavolo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.Servlet;
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
 * Servlet implementation class ExecuteSearchTavoloServlet
 */
@WebServlet("/ExecuteSearchTavoloServlet")
public class ExecuteSearchTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private TavoloService tavoloService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteSearchTavoloServlet() {
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("userSession");
		
		String esperienzaMinimaString = request.getParameter("esperienzaMinima") != null && !request.getParameter("esperienzaMinima").equals("") ? request.getParameter("esperienzaMinima") : null;
		String creditoMinimoString = request.getParameter("creditoMinimo") != null && !request.getParameter("creditoMinimo").equals("") ? request.getParameter("creditoMinimo") : null;
		String denominazione = request.getParameter("denominazione") != null && !request.getParameter("denominazione").equals("") ? request.getParameter("denominazione") : null;
		String dataCreazione = request.getParameter("dataCreazione") != null && !request.getParameter("dataCreazione").equals("") ? request.getParameter("dataCreazione") : null;

		TavoloDTO tavoloDTO = new TavoloDTO(esperienzaMinimaString, creditoMinimoString, dataCreazione);
		List<String> tavoloErrorsSearch = tavoloDTO.errorsSearch();
		if(!tavoloErrorsSearch.isEmpty()) {
			request.setAttribute("errori", tavoloErrorsSearch);
			tavoloDTO.setDenominazione(denominazione);
			request.setAttribute("tavolo", tavoloDTO);
			request.getRequestDispatcher("PrepareSearchTavoloServlet").forward(request, response);
			return;
		}else {
			Integer esperienzaMinima = esperienzaMinimaString != null ? Integer.parseInt(esperienzaMinimaString) : 0;
			Integer creditoMinimo = creditoMinimoString != null ? Integer.parseInt(creditoMinimoString) : 0;
			Date data;
			try {
				data = dataCreazione != null ? new SimpleDateFormat("yyyy-MM-dd").parse(dataCreazione) : null;
				Tavolo tavolo = new Tavolo(esperienzaMinima, creditoMinimo, denominazione, data);
				List<Tavolo> listaTavoli = tavoloService.findTavoloByExample(tavolo, userSession);
				request.setAttribute("listaTavoli", listaTavoli);
				if(listaTavoli.size() < 1) {
					request.setAttribute("avvertimento", "Nessun risultato per questa ricerca!");
				}
				request.getRequestDispatcher("/tavolo/listaTavoli.jsp").forward(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
}
