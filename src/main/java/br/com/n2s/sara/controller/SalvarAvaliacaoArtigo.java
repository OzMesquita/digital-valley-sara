package br.com.n2s.sara.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOAvaliaTrabalho;
import br.com.n2s.sara.dao.DAOItem;
import br.com.n2s.sara.dao.DAOTrabalho;
import br.com.n2s.sara.model.Trabalho;
import br.com.n2s.sara.model.Usuario;
import br.com.n2s.sara.util.Constantes;
import br.com.n2s.sara.util.Facade;
import br.com.n2s.sara.model.AvaliaTrabalho;
import br.com.n2s.sara.model.Criterio;
import br.com.n2s.sara.model.Item;

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
		try {
			String feedback = request.getParameter("feedback");
			Usuario avaliador =(Usuario) session.getAttribute("usuarioSara");
			int idTrabalho = Integer.parseInt(request.getParameter("t-a"));
			Trabalho trabalho = new DAOTrabalho().getTrabalho(idTrabalho);
			AvaliaTrabalho av = new DAOAvaliaTrabalho().getAvaliaTrabalho(idTrabalho, avaliador.getCpf());
			
			for (Criterio c : trabalho.getTrilha().getCriterios()) {
				int idItem = Integer.parseInt(request.getParameter("criterio-"+c.getIdCriterio()));
				Item item = new DAOItem().getItem(idItem);
				av.getItens().add(item); 
			}
			av.setNota(Facade.calcularNota(av));
			av.setFeedback(feedback);
			new DAOAvaliaTrabalho().update(av);
			session.setAttribute(Constantes.getSESSION_MGS(), "Avalia��o realizada com sucesso!");
		}catch (Exception e) {
			session.setAttribute(Constantes.getSESSION_MGS(), "Erro durante avalia��o");
			response.sendRedirect("avaliacao.jsp");
		}
		response.sendRedirect("avaliacao.jsp");
		
		
	}

}
