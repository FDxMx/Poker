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
import it.poker.model.RuoloUser;
import it.poker.model.StatoUser;
import it.poker.model.User;
import it.poker.service.user.UserService;

/**
 * Servlet implementation class ExecuteSearchUserServlet
 */
@WebServlet("/ExecuteSearchUserServlet")
public class ExecuteSearchUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserService userService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteSearchUserServlet() {
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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome") != null && !request.getParameter("nome").equals("") ? request.getParameter("nome") : null;
		String cognome = request.getParameter("cognome") != null && !request.getParameter("cognome").equals("") ? request.getParameter("cognome") : null;
		String username = request.getParameter("username") != null && !request.getParameter("username").equals("") ? request.getParameter("username") : null;
		String dataRegistrazione = request.getParameter("dataRegistrazione") != null && !request.getParameter("dataRegistrazione").equals("") ? request.getParameter("dataRegistrazione") : null;
		StatoUser stato = request.getParameter("stato") != null && !request.getParameter("stato").equals("") ? StatoUser.valueOf(request.getParameter("stato")) : null;
		RuoloUser ruolo = request.getParameter("ruolo") != null && !request.getParameter("ruolo").equals("") ? RuoloUser.valueOf(request.getParameter("ruolo")) : null;
		
		UserDTO userDTO = new UserDTO (nome, cognome, username, dataRegistrazione, stato, ruolo);
		List<String> userErrorsSearch = userDTO.errorsSearch();
		if(!userErrorsSearch.isEmpty()) {
			request.setAttribute("errori", userErrorsSearch);
			request.setAttribute("user", userDTO);
			request.getRequestDispatcher("PrepareSearchUserServlet").forward(request, response);
			return;
		}else {
				User user = UserDTO.buildModelFromDto(userDTO);
				List<User> listaUser = userService.findUserByExample(user, ruolo);
				request.setAttribute("listaUser", listaUser);
				if(listaUser.size() < 1) {
					request.setAttribute("avvertimento", "Nessun risultato per questa ricerca!");
				}
				request.getRequestDispatcher("/user/listaUser.jsp").forward(request, response);
		}
	}

}
