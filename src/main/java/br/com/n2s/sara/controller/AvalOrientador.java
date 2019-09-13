package br.com.n2s.sara.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOTrabalho;
import br.com.n2s.sara.model.StatusTrabalho;
import br.com.n2s.sara.model.Trabalho;
import br.com.n2s.sara.util.Constantes;

/**
 * Servlet implementation class AvalOrientador
 */

public class AvalOrientador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Trabalho trabalho = new DAOTrabalho().getTrabalho(Integer.parseInt(request.getParameter("idTrabalho")));
		
		String aval = request.getParameter("resultado");
		if (aval.equals("aceito")) {
			trabalho.setStatus(StatusTrabalho.ACEITO_ORIENTADOR);
		}else {
			trabalho.setStatus(StatusTrabalho.REJEITADO_ORIENTADOR);
		}
		new DAOTrabalho().update(trabalho);
		session.setAttribute(Constantes.getSESSION_MGS(), "Trabalho avaliado!");
		response.sendRedirect(request.getRequestURI());
	}

}
