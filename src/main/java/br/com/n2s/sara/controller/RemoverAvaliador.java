package br.com.n2s.sara.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOAvaliaTrilha;
import br.com.n2s.sara.model.AvaliaTrilha;
import br.com.n2s.sara.model.Trilha;
import br.com.n2s.sara.model.Usuario;
import br.com.n2s.sara.util.Constantes;
import br.com.n2s.sara.util.Facade;

/**
 * Servlet implementation class RemoverAvaliador
 */

public class RemoverAvaliador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoverAvaliador() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		try {
		Trilha trilha = new Trilha();
		trilha = (Trilha) session.getAttribute("trilha");
		String cpfAvaliador = request.getParameter("cpfAvaliador");
		DAOAvaliaTrilha daoAvTrilha = new DAOAvaliaTrilha();
		AvaliaTrilha avTrilha = new AvaliaTrilha(); 
		avTrilha = daoAvTrilha.getAvaliaTrilha(cpfAvaliador, trilha.getIdTrilha());
		daoAvTrilha.delete(avTrilha);
		session.setAttribute("trilha", trilha);
		
		String feedbackSucesso = "Avaliador removido com sucesso!";
		session.setAttribute(Constantes.getSESSION_MGS(), feedbackSucesso);
		
		response.sendRedirect("gerenciarAvaliadoresTrilha.jsp");
		}catch (Exception e){
			session.setAttribute(Constantes.getSESSION_MGS_ERROR(), "Erro ao remover o avaliador!");
		}
	}

}
