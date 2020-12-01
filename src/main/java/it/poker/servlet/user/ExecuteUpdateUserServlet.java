package it.poker.servlet.user;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
import it.poker.model.User;
import it.poker.service.user.UserService;

/**
 * Servlet implementation class ExecuteUpdateUserServlet
 */
@WebServlet("/ExecuteUpdateUserServlet")
public class ExecuteUpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserService userService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteUpdateUserServlet() {
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
		String idUser = request.getParameter("id");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String username = request.getParameter("username");
		String dataRegistrazione = request.getParameter("data");
		String[] ruoli = request.getParameterValues("ruolo");
		User user = userService.findUserWithRuoli(Long.parseLong(idUser));
		
		if(idUser == null || idUser.equals("")) {
			request.setAttribute("errore", "Nessun user selezionato!");
			request.getRequestDispatcher("ListaUserServlet").forward(request, response);
		} else {
			UserDTO userDTO = new UserDTO(nome, cognome, username, user.getPassword(), dataRegistrazione);
			List<String> userErrors = userDTO.errors();
			if(!userErrors.isEmpty()) {
				request.setAttribute("errori", userErrors);
				request.setAttribute("user", user);
				request.setAttribute("listaRuoli", RuoloUser.listaEnum());
				request.getRequestDispatcher("/user/update.jsp").forward(request, response);
				return;
			} else {
				user.setNome(nome);
				user.setCognome(cognome);
				user.setUsername(username);
				try {
					user.setDataRegistrazione(new SimpleDateFormat("yyyy-MM-dd").parse(dataRegistrazione));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if(ruoli != null) {
					List<RuoloUser> listaRuoli = new ArrayList<>();
					for (String ruolo : ruoli) {
						listaRuoli.add(RuoloUser.valueOf(ruolo));
						user.setRuoli(listaRuoli);
					}
				}
				userService.update(user);
				request.setAttribute("effettuato", "Aggiornamento effettuato!");
				request.getRequestDispatcher("ListaUserServlet").forward(request, response);
			}
		}
	}

}
