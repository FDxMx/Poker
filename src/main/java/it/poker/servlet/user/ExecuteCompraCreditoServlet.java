package it.poker.servlet.user;

import java.io.IOException;

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

import it.poker.model.User;
import it.poker.service.user.UserService;

/**
 * Servlet implementation class ExecuteCompraCreditoServlet
 */
@WebServlet("/ExecuteCompraCreditoServlet")
public class ExecuteCompraCreditoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserService userService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteCompraCreditoServlet() {
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
		
		String credito = request.getParameter("credito");
		
		if (credito != null && !credito.equals("") && StringUtils.isNumeric(credito)) {
			User user = (User)session.getAttribute("userSession");
			user.setCredito(user.getCredito() + Integer.parseInt(credito));
			userService.update(user);
			request.setAttribute("effettuato", "Transazione effettuata con successo!");
			request.getRequestDispatcher("/user/playManagement.jsp").forward(request, response);
			return;
		}else {
			request.setAttribute("avvertimento", "Nessuna cifra selezionata!");
			request.getRequestDispatcher("/user/compraCredito.jsp").forward(request, response);
		}
	}

}
