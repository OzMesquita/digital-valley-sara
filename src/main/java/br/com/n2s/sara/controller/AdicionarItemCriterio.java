package br.com.n2s.sara.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOItem;
import br.com.n2s.sara.model.Criterio;
import br.com.n2s.sara.model.Item;
import br.com.n2s.sara.util.Constantes;

/**
 * Servlet implementation class AdicionarItemCriterio
 */
@WebServlet("/AdicionarItemCriterio")
public class AdicionarItemCriterio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdicionarItemCriterio() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		try {
		Criterio criterio = (Criterio) session.getAttribute("criterio");
		
		String descricaoItem = request.getParameter("descricaoItem");
		int pesoItem = Integer.parseInt(request.getParameter("pesoItem"));
		
		Item item = new Item();
		item.setCriterio(criterio);
		item.setDescricao(descricaoItem);
		item.setPeso(pesoItem);
		
		new DAOItem().create(item);
		
		session.setAttribute(Constantes.getSESSION_MGS(), "Sucesso durante a alteração do criterio!");
		response.sendRedirect("gerenciarCriteriosTrilha.jsp");
		}catch (Exception e) {
			session.setAttribute(Constantes.getSESSION_MGS_ERROR(), "Erro durante a edição do critério ");
			response.sendRedirect("gerenciarCriteriosTrilha.jsp");
		}
		
	}

}
