package br.com.n2s.sara.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOAvaliaTrilha;
import br.com.n2s.sara.model.AvaliaTrilha;
import br.com.n2s.sara.model.Trilha;
import br.com.n2s.sara.model.Usuario;
import br.com.n2s.sara.util.Facade;

/**
 * Servlet implementation class RemoverAvaliador
 */
@WebServlet("/RemoverAvaliador")
public class RemoverAvaliador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoverAvaliador() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Trilha trilha = new Trilha();
		trilha= (Trilha) session.getAttribute("trilha");
		String cpf = request.getParameter("cpf");
		
		Usuario avaliador = Facade.buscarUsuarioPorCPF(cpf);
		AvaliaTrilha avTrilha = new AvaliaTrilha();
		avTrilha.setAvaliador(avaliador);
		avTrilha.setTrilha(trilha);
		
		DAOAvaliaTrilha daoAVTrilha = new DAOAvaliaTrilha();
		daoAVTrilha.delete(avTrilha);
		
		for(int i=0;i < trilha.getAvaliadores().size();i++) {
			if(trilha.getAvaliadores().get(i).equals(avaliador)) {
				trilha.getAvaliadores().remove(i);
				break;
			}
		}
		
		session.setAttribute("trilha", trilha);
		response.sendRedirect("gerenciaEvento.jsp");
	}

}
