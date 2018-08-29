package br.com.n2s.sara.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOCoordenacaoTrilha;

/**
 * Servlet implementation class RemoverCoordenadorTrilha
 */
@WebServlet("/RemoverCoordenadorTrilha")
public class RemoverCoordenadorTrilha extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String cpf = (String) request.getAttribute("delCood");
		DAOCoordenacaoTrilha daoCoordTrilha = new DAOCoordenacaoTrilha();
		daoCoordTrilha.delete(cpf);
		response.sendRedirect("gerenciarCoordenadorTrilha.jsp");
	}

}
