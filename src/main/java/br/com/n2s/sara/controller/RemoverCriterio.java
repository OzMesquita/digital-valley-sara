package br.com.n2s.sara.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOCriterio;
import br.com.n2s.sara.model.Criterio;

/**
 * Servlet implementation class RemoverCriterio
 */
@WebServlet("/RemoverCriterio")
public class RemoverCriterio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoverCriterio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String chave = request.getParameter("criterio");
		Criterio criterio = new Criterio();
		
		criterio = (Criterio) session.getAttribute(chave);
		
		DAOCriterio daoCriterio = new DAOCriterio();
		daoCriterio.delete(criterio.getIdCriterio());
		
		response.sendRedirect("gerenciaCriterios.jsp");
		
	}

}
