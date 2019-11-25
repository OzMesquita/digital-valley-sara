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
	 * ESSE SERVLET TEM COMO OBJETIVO REALIZAR A OPERAÇÃO DE SALVAR PARA O RELATORIO DE ESTAGIO, BURLANDO A IDEIA DE CRITERIOS E ITENS
	 * PARA ALTERAÇÕES FUTURAS OU EM CASO VERIFICAR O SALVAR AVALIAÇÃO ARTIGO.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String feedback = request.getParameter("feedback");
		/** Eu recebo o status mas não sei pq, nem uso, seja criativo **/
		String status = request.getParameter("status");
		float nota = Float.parseFloat(request.getParameter("nota"));
		Trabalho trabalho = (Trabalho) session.getAttribute("trabalho");
		
		AvaliaTrabalho avaliaTrabalho = new DAOAvaliaTrabalho().getAvaliaTrabalho(trabalho.getIdTrabalho());
		avaliaTrabalho.setFeedback(feedback);
		avaliaTrabalho.setNota(nota);
		if (nota>=7)
			avaliaTrabalho.setStatus(StatusTrabalho.ACEITO_FINAL);
		else
			avaliaTrabalho.setStatus(StatusTrabalho.REJEITADO);
		new DAOAvaliaTrabalho().update(avaliaTrabalho);
		response.sendRedirect("avaliacao.jsp");
		
	}

}
