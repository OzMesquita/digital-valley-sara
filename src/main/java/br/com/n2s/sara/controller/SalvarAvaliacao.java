package br.com.n2s.sara.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOAvaliaTrabalho;
import br.com.n2s.sara.dao.DAOTrabalho;
import br.com.n2s.sara.model.AvaliaTrabalho;
import br.com.n2s.sara.model.StatusTrabalho;
import br.com.n2s.sara.model.Trabalho;

/**
 * Servlet implementation class SalvarAvaliacao
 */
@WebServlet("/SalvarAvaliacao")
public class SalvarAvaliacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalvarAvaliacao() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String feedback = request.getParameter("feedback");
		String status = request.getParameter("status");
		
		Trabalho trabalho = (Trabalho) session.getAttribute("trabalho");
		
		AvaliaTrabalho avaliaTrabalho = new DAOAvaliaTrabalho().getAvaliaTrabalho(trabalho.getIdTrabalho());
		
		if (status.equals("aceitar")) {
			avaliaTrabalho.setFeedback(feedback);
			avaliaTrabalho.setStatus(StatusTrabalho.ACEITO);
			trabalho.setStatus(StatusTrabalho.ACEITO);
			new DAOAvaliaTrabalho().update(avaliaTrabalho);
			new DAOTrabalho().update(trabalho);
			response.sendRedirect("avaliacao.jsp");
		}else {
			avaliaTrabalho.setFeedback(feedback);
			avaliaTrabalho.setStatus(StatusTrabalho.REJEITADO);
			new DAOAvaliaTrabalho().update(avaliaTrabalho);
			trabalho.setStatus(StatusTrabalho.REJEITADO);
			new DAOTrabalho().update(trabalho);
			response.sendRedirect("avaliacao.jsp");
		}
		
	}

}
