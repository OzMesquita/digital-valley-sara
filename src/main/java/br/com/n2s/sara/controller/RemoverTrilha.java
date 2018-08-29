package br.com.n2s.sara.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOTrilha;
import br.com.n2s.sara.model.Evento;
import br.com.n2s.sara.model.Trilha;
import br.com.n2s.sara.model.Usuario;

/**
 * Servlet implementation class RemoverTrilha
 */
@WebServlet("/RemoverTrilha")
public class RemoverTrilha extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoverTrilha() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		Evento evento = new Evento();
		Trilha trilha = new Trilha();
		DAOTrilha daoTrilha = new DAOTrilha();
		
		evento = (Evento) session.getAttribute("evento");
		trilha = (Trilha) session.getAttribute("trilha");
		daoTrilha.delete(trilha.getIdTrilha());
		
		for(int i = 0; i < evento.getTrilhas().size(); i++){
			if(evento.getTrilhas().get(i).equals(trilha)){
				evento.getTrilhas().remove(i);
				break;
			}
		}
		
		session.setAttribute("evento", evento);
		response.sendRedirect("gerenciaEvento.jsp");
	}

}
