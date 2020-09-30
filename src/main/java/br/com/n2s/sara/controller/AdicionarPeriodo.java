package br.com.n2s.sara.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOPeriodo;
import br.com.n2s.sara.model.DescricaoPeriodo;
import br.com.n2s.sara.model.Periodo;
import br.com.n2s.sara.model.Trilha;
import br.com.n2s.sara.util.Constantes;

/**
 * Servlet implementation class AdicionarPeriodo
 */
@WebServlet("/AdicionarPeriodo")
public class AdicionarPeriodo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		try {
		Trilha trilha = (Trilha) session.getAttribute("trilha");
		
		DAOPeriodo daoPeriodo = new DAOPeriodo();
		Periodo periodo = new Periodo();
		
		periodo.setDescricao(DescricaoPeriodo.valueOf(request.getParameter("descricao")));
		periodo.setDataInicial(LocalDate.parse((String)request.getParameter("dataInicial"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		periodo.setDataFinal(LocalDate.parse((String)request.getParameter("dataFinal"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		
		periodo.setTrilha(trilha);
		daoPeriodo.create(periodo);
		
		session.setAttribute(Constantes.getSESSION_MGS(), "Período adicionado com sucesso!");
		
		response.sendRedirect("gerenciarPeriodosTrilha.jsp");
		}catch (Exception e) {
			response.sendRedirect("indexAutor.jsp");
			session.setAttribute(Constantes.getSESSION_MGS_ERROR(), "Erro durante o cadastro do periodo!");
		}
	}

}













