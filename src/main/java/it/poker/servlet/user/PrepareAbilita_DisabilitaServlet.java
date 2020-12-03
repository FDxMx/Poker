package it.poker.servlet.user;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.poker.model.StatoUser;
import it.poker.model.User;
import it.poker.service.user.UserService;

/**
 * Servlet implementation class PrepareAbilita_DisabilitaServlet
 */
@WebServlet("/PrepareAbilita_DisabilitaServlet")
public class PrepareAbilita_DisabilitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PrepareAbilita_DisabilitaServlet() {
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
		String idUser = request.getParameter("idParametro");
		if (idUser != null && !idUser.equals("")) {
			User user = userService.findUserWithRuoli(Long.parseLong(idUser));
			if (user.getStato().equals(StatoUser.CREATO) && user.getRuoli().size() < 1) {
				request.setAttribute("avvertimento", "Non puoi abilitare uno user senza assegnargli un ruolo!");
				request.getRequestDispatcher("ListaUserServlet").forward(request, response);
				return;
			}else {
				request.setAttribute("id", idUser);
				request.getRequestDispatcher("/user/attiva_disattiva.jsp").forward(request, response);
				return;
			}
		} else {
			request.setAttribute("errore", "Nessun user selezionato!");
			request.getRequestDispatcher("ListaUserServlet").forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
