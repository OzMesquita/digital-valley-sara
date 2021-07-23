package br.com.n2s.sara.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOAvaliaTrabalho;
import br.com.n2s.sara.dao.DAOCriterio;
import br.com.n2s.sara.dao.DAOCriterioTrilha;
import br.com.n2s.sara.dao.DAOItem;
import br.com.n2s.sara.dao.DAOTrabalho;
import br.com.n2s.sara.model.Trabalho;
import br.com.n2s.sara.model.Usuario;
import br.com.n2s.sara.util.Constantes;
import br.com.n2s.sara.util.Facade;
import br.com.n2s.sara.model.AvaliaTrabalho;
import br.com.n2s.sara.model.Criterio;
import br.com.n2s.sara.model.Item;
import br.com.n2s.sara.model.StatusTrabalho;

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
			
			DAOItem daoItem = new DAOItem();
			DAOTrabalho daoTrabalho = new DAOTrabalho();
			DAOAvaliaTrabalho daoAvaliatrabalho = new DAOAvaliaTrabalho();
			
			Trabalho trabalho = daoTrabalho.getTrabalho(idTrabalho);
			AvaliaTrabalho av = daoAvaliatrabalho.getAvaliaTrabalho(idTrabalho, avaliador.getCpf());
			
			
			ArrayList<Item> itens = new ArrayList<Item>(); 
			for (Criterio c : trabalho.getTrilha().getCriterios()) {
				int idItem = Integer.parseInt(request.getParameter("criterio-"+c.getIdCriterio()));
				Item item = daoItem.getItem(idItem);
				item.setCriterio(c);
				itens.add(item);
				daoAvaliatrabalho.updateCriterioAvaliados(av.getId(), item.getIdItem(), c.getIdCriterio());
			}
			
			av.setItens(itens);
			av.setNota(Facade.calcularNota(av));
			av.setFeedback(feedback);
			daoAvaliatrabalho.updatePerAvaliador(av);
		
			Float nota = Facade.calcularNota(av);
			
			if(nota >= 6) {
				trabalho.setStatus(StatusTrabalho.ACEITO);
				av.setStatus(StatusTrabalho.ACEITO);
			}else {
				trabalho.setStatus(StatusTrabalho.REJEITADO);
				av.setStatus(StatusTrabalho.REJEITADO);
			}
			daoTrabalho.update(trabalho);
			daoAvaliatrabalho.update(av);		
			
			session.setAttribute(Constantes.getSESSION_MGS(), "Avalia��o realizada com sucesso!");
			response.sendRedirect("avaliacao.jsp");
			
		}catch (Exception e) {
			session.setAttribute(Constantes.getSESSION_MGS_ERROR(), "Erro durante avalia��o");
			response.sendRedirect("avaliacao.jsp");	
			throw new RuntimeException(e);
		}
	}

}
