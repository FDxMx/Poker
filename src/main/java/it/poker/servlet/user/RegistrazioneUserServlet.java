package it.poker.servlet.user;

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

import it.poker.dto.UserDTO;
import it.poker.model.StatoUser;
import it.poker.model.User;
import it.poker.service.user.UserService;

/**
 * Servlet implementation class RegistrazioneUserServlet
 */
@WebServlet("/RegistrazioneUserServlet")
public class RegistrazioneUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserService userService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneUserServlet() {
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
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String data = request.getParameter("data");
		
		UserDTO userDTO = new UserDTO(nome, cognome, username, password, data);
		List<String> userErrors = userDTO.errors();
		if(!userErrors.isEmpty()) {
			request.setAttribute("user", userDTO);
			request.setAttribute("errori", userErrors);
			request.getRequestDispatcher("registrazione.jsp").forward(request, response);
			return;
		} else {
			User user = UserDTO.buildModelFromDto(userDTO);
			user.setEsperienza(0);
			user.setCredito(0);
			user.setStato(StatoUser.CREATO);
			userService.insert(user);
			request.setAttribute("effettuato", "Registrazione avvenuta con successo, attendi la mail di confermata abilitazione!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
