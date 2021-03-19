package br.com.n2s.sara.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOCriterio;
import br.com.n2s.sara.dao.DAOCriteriosAvaliados;
import br.com.n2s.sara.util.Constantes;
import br.com.n2s.sara.model.Criterio;

/**
 * Servlet implementation class RemoverCriterio
 */
@WebServlet("/RemoverCriterio")
public class RemoverCriterio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
		int idCriterio = Integer.parseInt(request.getParameter("idCriterio"));
		int idTrilha = Integer.parseInt(request.getParameter("idTrilha"));

		DAOCriterio daoCriterio = new DAOCriterio();
		Criterio criterio = daoCriterio.getCriterio(idCriterio);
		
		DAOCriteriosAvaliados daoCriCriteriosAvaliados = new DAOCriteriosAvaliados();
		if (daoCriCriteriosAvaliados.exist(idCriterio, criterio)) {
			response.sendRedirect("gerenciarCriteriosTrilha.jsp");
			session.setAttribute(Constantes.getSESSION_MGS_ERROR(),
					"Erro durante a remoção do critério. Critério já foi avaliado!");
		} else {
			
			daoCriterio.delete(criterio,idTrilha);
			session.setAttribute(Constantes.getSESSION_MGS(), "Sucesso durante a remo��o do crit�rio!");
			response.sendRedirect("gerenciarCriteriosTrilha.jsp");
		}
		
		
		}catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			response.sendRedirect("gerenciarCriteriosTrilha.jsp");
			session.setAttribute(Constantes.getSESSION_MGS_ERROR(), "Erro durante a remoção do critério! Critério presente na lista de critérios da trilha");
		}
	}

}
