package br.com.n2s.sara.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOTrabalho;
import br.com.n2s.sara.model.Trabalho;
import br.com.n2s.sara.model.Criterio;

/**
 * Servlet implementation class SalvarAvaliacaoArtigo
 */
@WebServlet("/SalvarAvaliacaoArtigo")
public class SalvarAvaliacaoArtigo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalvarAvaliacaoArtigo() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int idTrabalho = Integer.parseInt(request.getParameter("t-a"));
		Trabalho trabalho = new DAOTrabalho().getTrabalho(idTrabalho);
		
		for (Criterio c : trabalho.getTrilha().getCriterios()) {
			
		}
	}

}
