package br.com.n2s.sara.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOCoordenacaoEvento;
import br.com.n2s.sara.model.CoordenacaoEvento;

/**
 * Servlet implementation class RemoverCoordenador
 */
@WebServlet("/RemoverCoordenador")
public class RemoverCoordenadorEvento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cpfCoordenador = request.getParameter("cpfCoordenador");
		
		DAOCoordenacaoEvento daoCoordenacaoEvento = new DAOCoordenacaoEvento();
		CoordenacaoEvento coordenacaoEvento = new CoordenacaoEvento();
		coordenacaoEvento = daoCoordenacaoEvento.getCoordenacaoEvento(cpfCoordenador);
		
		daoCoordenacaoEvento.delete(coordenacaoEvento);
		
		response.sendRedirect("eventosCoordenados.jsp");
	}

}
