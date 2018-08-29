package br.com.n2s.sara.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOPeriodo;
import br.com.n2s.sara.model.Periodo;

/**
 * Servlet implementation class RemoverPeriodo
 */
@WebServlet("/RemoverPeriodo")
public class RemoverPeriodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoverPeriodo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		Periodo periodo = new Periodo();
		String key = request.getParameter("periodo");
		
		periodo = (Periodo) session.getAttribute(key);
		DAOPeriodo daoPeriodo = new DAOPeriodo();
		daoPeriodo.delete(periodo.getIdPeriodo());
		response.sendRedirect("gerenciaPeriodos.jsp");
	}

}
