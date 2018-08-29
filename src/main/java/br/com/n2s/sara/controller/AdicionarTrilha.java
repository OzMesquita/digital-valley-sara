package br.com.n2s.sara.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOTrilha;
import br.com.n2s.sara.model.Evento;
import br.com.n2s.sara.model.Trilha;

/**
 * Servlet implementation class AdicionarTrilha
 */
@WebServlet("/AdicionarTrilha")
public class AdicionarTrilha extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdicionarTrilha() {
        super();      
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Trilha trilha = new Trilha();
		DAOTrilha daoTrilha = new DAOTrilha();
		Evento evento = new Evento();
		
		evento = (Evento) session.getAttribute("evento");
		trilha.setNome(request.getParameter("nome"));
		trilha.setDescricao(request.getParameter("descricao"));
		trilha.setEvento(evento);
		
		daoTrilha.create(trilha);
		
		response.sendRedirect("indexCoordTrilha.jsp");
	}

}
