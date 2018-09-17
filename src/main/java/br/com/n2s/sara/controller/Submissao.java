package br.com.n2s.sara.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOTrilha;
import br.com.n2s.sara.model.Trilha;
import br.com.n2s.sara.model.Evento;
import br.com.n2s.sara.dao.DAOEvento;
public class Submissao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Submissao() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		DAOTrilha daoTrilha = new DAOTrilha();
		DAOEvento daoEvento = new DAOEvento();
		String pagina = "/Sara/webapp/jsp/indexAutor.jsp";
		if (request.getParameter("Evento") != null && request.getParameter("Trilha") != null) {
			try {
				int id = Integer.parseInt(request.getParameter("Trilha").toString());
				Trilha trilha = daoTrilha.getTrilha(id);
				id = Integer.parseInt(request.getParameter("Evento").toString());
				Evento evento = daoEvento.getEvento(id);
				session.setAttribute("evento", evento);
				session.setAttribute("trilha", trilha);
				pagina = "/Sara/webapp/jsp/paginaTrilha.jsp";
				response.sendRedirect(pagina);
			}catch (Exception e) {
				
				response.sendRedirect(pagina);
			}
		}else {
			if (session.getAttribute("trilha") != null && session.getAttribute("evento") != null) {
				response.sendRedirect("/Sara/webapp/jsp/paginaTrilha.jsp");
			}else {
				response.sendRedirect("/Sara/webapp/jsp/indexAutor.jsp");
			}
		}
		
	}


}
