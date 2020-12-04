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
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.poker.dto.TavoloDTO;
import it.poker.model.Tavolo;
import it.poker.model.User;
import it.poker.service.tavolo.TavoloService;
import it.poker.service.user.UserService;

/**
 * Servlet implementation class ExecuteSearchTavoloConUserServlet
 */
@WebServlet("/ExecuteSearchTavoloConUserServlet")
public class ExecuteSearchTavoloConUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TavoloService tavoloService;
	@Autowired
	private UserService userService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteSearchTavoloConUserServlet() {
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
		User userSession = (User) session.getAttribute("userSession");
		User user = request.getParameter("user") != null && !request.getParameter("user").equals("") ? userService.findById(Long.parseLong(request.getParameter("user"))) : null;
		String creditoMinimoString = request.getParameter("creditoMinimo") != null && !request.getParameter("creditoMinimo").equals("") ? request.getParameter("creditoMinimo") : null;
		String denominazione = request.getParameter("denominazione") != null && !request.getParameter("denominazione").equals("") ? request.getParameter("denominazione") : null;
		String dataCreazione = request.getParameter("dataCreazione") != null && !request.getParameter("dataCreazione").equals("") ? request.getParameter("dataCreazione") : null;

		TavoloDTO tavoloDTO = new TavoloDTO(null, creditoMinimoString, denominazione, dataCreazione);
		List<String> tavoloErrorsSearch = tavoloDTO.errorsSearch();
		if(!tavoloErrorsSearch.isEmpty()) {
			request.setAttribute("errori", tavoloErrorsSearch);
			tavoloDTO.setUser(user);
			request.setAttribute("tavolo", tavoloDTO);
			request.getRequestDispatcher("PrepareSearchTavoloConUserServlet").forward(request, response);
			return;
		}else {
				Tavolo tavolo = TavoloDTO.buildModelFromDto(tavoloDTO);
				List<Tavolo> listaTavoli = tavoloService.findTavoloByExampleWithUser(tavolo, user, userSession);
				request.setAttribute("listaTavoli", listaTavoli);
				if(listaTavoli.size() < 1) {
					request.setAttribute("avvertimento", "Nessun risultato per questa ricerca!");
				}
				request.getRequestDispatcher("/tavolo/listaTavoliTotali.jsp").forward(request, response);
		}
	}

}
