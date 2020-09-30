package br.com.n2s.sara.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOCriterio;
import br.com.n2s.sara.dao.DAOItem;
import br.com.n2s.sara.model.Criterio;
import br.com.n2s.sara.model.Item;
import br.com.n2s.sara.util.Constantes;

/**
 * Servlet implementation class EditarCriterio
 */
@WebServlet("/RemoverItemCriterio")
public class RemoverItemCriterio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RemoverItemCriterio() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
		int idItem = Integer.parseInt(request.getParameter("idItem"));
		new DAOItem().delete(idItem);
		
		session.setAttribute(Constantes.getSESSION_MGS(), "Sucesso durante a remoção do item!");
		response.sendRedirect("gerenciarCriteriosTrilha.jsp");
		}catch (Exception e) {
			response.sendRedirect("gerenciarCriteriosTrilha.jsp");
			session.setAttribute(Constantes.getSESSION_MGS_ERROR(), "Erro durante a remoção do item!");
		}
	}

}
