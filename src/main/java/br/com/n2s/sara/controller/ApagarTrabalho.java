package br.com.n2s.sara.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOSubmissao;
import br.com.n2s.sara.dao.DAOTrabalho;
import br.com.n2s.sara.model.Trabalho;
import br.com.n2s.sara.model.Usuario;

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
		HttpSession session = request.getSession();
		int idTrabalho = Integer.parseInt(request.getParameter("idTrabalho"));
		Usuario usuerLogado = (Usuario) session.getAttribute("usuarioSara");
		DAOSubmissao daoSubmissao = new DAOSubmissao();
		DAOTrabalho daoTrabalho = new DAOTrabalho();
		Trabalho trabalho = daoTrabalho.getTrabalho(idTrabalho);
		if(trabalho.getAutor().getCpf().equals(usuerLogado.getCpf())) {
			daoSubmissao.delete(idTrabalho);
			daoTrabalho.delete(idTrabalho);
			response.sendRedirect("trabalhosAutor.jsp");
		}
		for (Usuario autores: trabalho.getAutores()) {
			
		}
		
			
		response.sendRedirect("trabalhosAutor.jsp");
		

	}

}
