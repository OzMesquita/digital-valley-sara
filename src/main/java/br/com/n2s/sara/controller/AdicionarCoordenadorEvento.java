package br.com.n2s.sara.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOCoordenacaoEvento;
import br.com.n2s.sara.dao.DAOUsuario;
import br.com.n2s.sara.model.CoordenacaoEvento;
import br.com.n2s.sara.model.Evento;
import br.com.n2s.sara.model.NivelUsuario;
import br.com.n2s.sara.model.Usuario;
import br.com.n2s.sara.util.Facade;

/**
 * Servlet implementation class AdcionarCoordenador
 */
@WebServlet("/AdicionarCoordenadorEvento")
public class AdicionarCoordenadorEvento extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Evento evento = (Evento) session.getAttribute("evento");
		String cpfCoordenador = request.getParameter("cpfCoordenador");
		
		Usuario usuario = new Usuario();
		DAOUsuario daoUsuario = new DAOUsuario();
		usuario = Facade.buscarUsuarioPorCPF(cpfCoordenador);
		
		if (usuario != null){
			
			if(usuario.getTipo().equals("ADMINISTRADOR")) {
				
				CoordenacaoEvento coordenacaoEvento = new CoordenacaoEvento();
				coordenacaoEvento.setCoordenador(usuario);
				coordenacaoEvento.setEvento(evento);
				DAOCoordenacaoEvento daoCoordenacaoEvento = new DAOCoordenacaoEvento();
				daoCoordenacaoEvento.create(coordenacaoEvento);
				
				response.sendRedirect("gerenciarCoordenadoresEvento.jsp");
				
			}else if(!usuario.getTipo().equals("COORDENADOR_EVENTO")){
				usuario.setTipo(NivelUsuario.COORDENADOR_EVENTO);
				daoUsuario.update(usuario);
				
				CoordenacaoEvento coordenacaoEvento = new CoordenacaoEvento();
				coordenacaoEvento.setCoordenador(usuario);
				coordenacaoEvento.setEvento(evento);
				DAOCoordenacaoEvento daoCoordenacaoEvento = new DAOCoordenacaoEvento();
				daoCoordenacaoEvento.create(coordenacaoEvento);
				
				response.sendRedirect("eventosCoordenados.jsp");
				
			}else if(!usuario.getTipo().equals("COORDENADOR_EVENTO")){
				usuario.setTipo(NivelUsuario.COORDENADOR_EVENTO);
			}
			
		}else {
			response.sendRedirect("eventosCoordenados.jsp");
		}
	
	}
}
