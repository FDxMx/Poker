package it.poker.servlet.tavolo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

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
		try {
			HttpSession session = request.getSession();
			User userSession = (User) session.getAttribute("userSession");
			Integer creditoMinimo = StringUtils.isNumeric(request.getParameter("creditoMinimo")) ? Integer.parseInt(request.getParameter("creditoMinimo")) : 0;
			String denominazione = StringUtils.isNotEmpty(request.getParameter("denominazione")) ? (request.getParameter("denominazione")) : null;
			Date dataCreazione = StringUtils.isNotEmpty(request.getParameter("dataCreazione")) ? new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dataCreazione").toString()) : null;
			User user = request.getParameter("user") != null && !request.getParameter("user").equals("") ? userService.findById(Long.parseLong(request.getParameter("user"))) : null;
			if(user != null && user.getTavolo() == null) {
				request.setAttribute("avvertimento", "Questo user non sta giocando a nessun tavolo!");
			}
			request.setAttribute("listaTavoli", tavoloService.findTavoloByExampleWithUser(new Tavolo(creditoMinimo, denominazione, dataCreazione), user, userSession));
			request.getRequestDispatcher("/tavolo/listaTavoliTotali.jsp").forward(request, response);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
