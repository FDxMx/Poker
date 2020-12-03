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

import it.poker.service.user.UserService;

/**
 * Servlet implementation class PrepareSearchTavoloConUserServlet
 */
@WebServlet("/PrepareSearchTavoloConUserServlet")
public class PrepareSearchTavoloConUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserService userService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareSearchTavoloConUserServlet() {
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
		request.setAttribute("giocatori", userService.findAll());
		request.setAttribute("avvertimento", "Visualizzerai solo le partite a cui potrai partecipare!");
		request.getRequestDispatcher("/tavolo/searchConUser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
