package it.poker.servlet.tavolo;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.poker.model.User;

/**
 * Servlet implementation class RecuperaParitaServletServlet
 */
@WebServlet("/RecuperaParitaServlet")
public class RecuperaParitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecuperaParitaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("userSession");
		if(userSession.getTavolo() != null) {
			request.setAttribute("effettuato", "Sei ritornato alla partita!");
			request.getRequestDispatcher("/tavolo/partita.jsp").forward(request, response);
			return;
		}else if (userSession.getTavolo() == null){  
			request.setAttribute("errore", "Non hai partite in corso!");
			request.getRequestDispatcher("/user/playManagement.jsp").forward(request, response);
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
