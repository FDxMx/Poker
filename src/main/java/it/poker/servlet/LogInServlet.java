package it.poker.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.poker.model.StatoUser;
import it.poker.model.User;
import it.poker.service.user.UserService;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogInServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username == null || username.equals("") || password == null || password.equals("")) {
			request.setAttribute("errore", "Inserire tutti i campi!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		} else {
			User user = userService.findByUsernameAndPassword(username, password);
			if (user == null) {
				request.setAttribute("errore", "Username o Password errati!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			} else if (user.getStato() != StatoUser.ABILITATO) {
				request.setAttribute("errore", "Devi prima essere abilitato!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			} else {
				session.setAttribute("userSession", user);
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}
		}
	}
}
