package br.com.n2s.sara.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOCriterio;
import br.com.n2s.sara.dao.DAOCriterioTrilha;
import br.com.n2s.sara.dao.DAOTrilha;
import br.com.n2s.sara.model.Criterio;
import br.com.n2s.sara.model.Trilha;
import br.com.n2s.sara.util.Constantes;

@WebServlet("/RemoverCriterioListado")
public class RemoverCriterioListado extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
		int idTrilha = Integer.parseInt(request.getParameter("idTrilha"));
		int idCriterio = Integer.parseInt(request.getParameter("idCriterio"));

		DAOCriterio daoCriterio = new DAOCriterio();
		Criterio criterio = daoCriterio.getCriterio(idCriterio);

		DAOTrilha daoTrilha = new DAOTrilha();
		Trilha trilha = daoTrilha.getTrilha(idTrilha);
		
		DAOCriterioTrilha daoCriterioTrilha = new DAOCriterioTrilha();
		

		daoCriterioTrilha.delete(criterio, trilha);
		session.setAttribute(Constantes.getSESSION_MGS(), "Sucesso durante a remo��o do crit�rio!");
		response.sendRedirect("gerenciarCriteriosTrilha.jsp");
		}catch (Exception e) {
			System.out.println(e);
			response.sendRedirect("gerenciarCriteriosTrilha.jsp");
			session.setAttribute(Constantes.getSESSION_MGS_ERROR(), "Erro durante a remo��o do crit�rio!");
		}
	}
}
