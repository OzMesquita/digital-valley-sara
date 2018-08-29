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

/**
 * Servlet implementation class EditarCriterio
 */
@WebServlet("/EditarCriterio")
public class EditarCriterio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public EditarCriterio() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String descricaoCrit = request.getParameter("descricaoCrit");
		int pesoCrit = Integer.parseInt(request.getParameter("pesoCrit"));
		Criterio crit = (Criterio) session.getAttribute("criterio");
		ArrayList<String> itensDescricao = new ArrayList<String>();
		ArrayList<Integer> itensPeso = new ArrayList<Integer>();
		
		String desc = "";
		String peso = "";

		crit.setDescricao(descricaoCrit);
		crit.setPeso(pesoCrit);
		
		Item item = new Item();
		List<Item> itens = new ArrayList<Item>();

		DAOItem daoItem = new DAOItem();
		
		for(int i = 0; i < itens.size(); i++){
			daoItem.delete(itens.get(i).getIdItem());
		}
		
		for(int i = 0; i < 20; i++){
			desc = request.getParameter("descricao" + i);
			peso = request.getParameter("peso" + i);
			
			if (desc != "" && peso != "" && desc != null && peso != null) {
				item.setDescricao(desc);
				item.setPeso(Integer.parseInt(peso));
				item.setCriterio(crit);
				daoItem.create(item);
				
			}  
		}
		
		DAOCriterio daoCriterio = new DAOCriterio();
		daoCriterio.create(crit);
		
		response.sendRedirect("gerenciaCriterios.jsp");
	}

}
