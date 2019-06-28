package br.com.n2s.sara.controller;

import java.io.IOException;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOCriterio;
import br.com.n2s.sara.dao.DAOCriterioTrilha;
import br.com.n2s.sara.dao.DAOItem;
import br.com.n2s.sara.dao.DAOTrilha;
import br.com.n2s.sara.model.Criterio;
import br.com.n2s.sara.model.CriterioTrilha;
import br.com.n2s.sara.model.Item;
import br.com.n2s.sara.model.Trilha;

/**
 * Servlet implementation class SelecionarCriterioTrilha
 */
@WebServlet("/SelecionarCriterioTrilha")
public class SelecionarCriterioTrilha extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelecionarCriterioTrilha() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Trilha trilha = (Trilha) session.getAttribute("trilha");
		int idCriterioTrilha = Integer.parseInt(request.getParameter("idCriterioTrilha"));
		/*******
		 *  
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 *         FALTA IMPLEMENTAR         
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * *********/
		response.sendRedirect("eventosCoordenados.jsp");

	}
}
