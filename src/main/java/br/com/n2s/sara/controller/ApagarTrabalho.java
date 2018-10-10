package br.com.n2s.sara.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.n2s.sara.dao.DAOSubmissao;
import br.com.n2s.sara.dao.DAOTrabalho;

/**
 * Servlet implementation class ApagarTrabalho
 */
@WebServlet("/ApagarTrabalho")
public class ApagarTrabalho extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApagarTrabalho() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idTrabalho = Integer.parseInt(request.getParameter("idTrabalho"));
		
		DAOSubmissao daoSubmissao = new DAOSubmissao();
		DAOTrabalho daoTrabalho = new DAOTrabalho();
		
		daoSubmissao.delete(idTrabalho);
		daoTrabalho.delete(idTrabalho);
		
		response.sendRedirect("trabalhosAutor.jsp");
		

	}

}
