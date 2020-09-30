package br.com.n2s.sara.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOCriterio;
import br.com.n2s.sara.dao.DAOCriterioTrilha;
import br.com.n2s.sara.dao.DAOPeriodo;
import br.com.n2s.sara.dao.DAOTrilha;
import br.com.n2s.sara.model.DescricaoPeriodo;
import br.com.n2s.sara.model.Evento;
import br.com.n2s.sara.model.Trilha;
import br.com.n2s.sara.util.Constantes;
import br.com.n2s.sara.model.Periodo;
import br.com.n2s.sara.model.Criterio;
import br.com.n2s.sara.model.CriterioTrilha;

/**
 * Servlet implementation class AdicionarTrilha
 */

@WebServlet("/AdicionarCriterioTrilha")
public class AdicionarCriterioTrilha extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			int idCriterio = Integer.parseInt(request.getParameter("idCriterio"));
			Trilha trilha = new Trilha();
			trilha = (Trilha) session.getAttribute("trilha");
			CriterioTrilha criTrilha = new CriterioTrilha();
			DAOCriterioTrilha daoCriTrilha = new DAOCriterioTrilha();
			DAOCriterio daoCriterio = new DAOCriterio();
			Criterio criterio = daoCriterio.getCriterio(idCriterio);
			
			criTrilha.setCriterio(criterio);
			criTrilha.setTrilha(trilha);		
			daoCriTrilha.createCriTrilha(criTrilha);
	
			String feedbackSucesso = "Criterio adicionado a trilha com sucesso!";
			response.sendRedirect("gerenciarCriteriosTrilha.jsp");
			session.setAttribute(Constantes.getSESSION_MGS(), feedbackSucesso);
		} catch (Exception e) {
			response.sendRedirect("gerenciarCriteriosTrilha.jsp");
			session.setAttribute(Constantes.getSESSION_MGS_ERROR(),
					"Erro durante a adição do criterio a trilha!" + e.getMessage());
		}
	}

}
