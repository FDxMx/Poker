package it.poker.servlet.tavolo;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.poker.model.User;

/**
 * Servlet implementation class PreparePartitaServlet
 */
@WebServlet("/PreparePartitaServlet")
public class PreparePartitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreparePartitaServlet() {
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
		String idTavolo = request.getParameter("idParametro");
		
		if(idTavolo == null || idTavolo.equals("")) {
			request.setAttribute("errore", "Nessun tavolo selezionato!");
			request.getRequestDispatcher("/tavolo/listaTavoli.jsp").forward(request, response);
			return;
		} else {
			//METODO PER AGGIUNGERE UN GIOCATORE (colui che è in sessione) ALLA LISTA DEI GIOCATORI DI UN TAVOLO
			request.getRequestDispatcher("/tavolo/partita.jsp").forward(request, response);
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
