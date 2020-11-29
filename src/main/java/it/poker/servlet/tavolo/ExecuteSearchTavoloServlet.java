package it.poker.servlet.tavolo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.poker.model.Tavolo;
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
		Integer esperienzaMinima = StringUtils.isNumeric(request.getParameter("esperienzaMinima")) ? Integer.parseInt(request.getParameter("esperienzaMinima")):null;
		Integer creditoMinimo = StringUtils.isNumeric(request.getParameter("creditoMinimo")) ? Integer.parseInt(request.getParameter("creditoMinimo")):null;
		String denominazione = StringUtils.isNotEmpty(request.getParameter("denominazione"))?(request.getParameter("denominazione")):null;
		Date dataCreazione = StringUtils.isNotEmpty(request.getParameter("dataCreazione"))? new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dataCreazione")):null;
		request.setAttribute("listaTavoli", tavoloService.findTavoloByExample(new Tavolo(esperienzaMinima, creditoMinimo, denominazione, dataCreazione)));
		request.getRequestDispatcher("/tavolo/listaTavoli.jsp").forward(request, response);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("LE DATEEEE!!!!!!");
		}
	}

}
