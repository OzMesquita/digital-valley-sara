package br.com.n2s.sara.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOCoordenacaoEvento;
import br.com.n2s.sara.dao.DAOEvento;
import br.com.n2s.sara.dao.DAOUsuario;
import br.com.n2s.sara.model.CoordenacaoEvento;
import br.com.n2s.sara.model.Evento;
import br.com.n2s.sara.model.NivelUsuario;
import br.com.n2s.sara.model.Usuario;


/**
 * Servlet implementation class CadastrarEvento
 */
@WebServlet("/CadastrarEvento")
public class CadastrarEvento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String nome = request.getParameter("nome");
		String cpfCoordenador = request.getParameter("cpfCoordenador");
		String site = request.getParameter("site");
		String descricao = request.getParameter("descricao");
		String localizacao = request.getParameter("localizacao");
		LocalDate dataInicial = LocalDate.parse(request.getParameter("dataInicial"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate dataFinal = LocalDate.parse(request.getParameter("dataFinal"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		Evento evento = new Evento();
		evento.setNome(nome);
		evento.setDescricao(descricao);
		evento.setLocalizacao(localizacao);
		evento.setSite(site);
		evento.setDataInicial(dataInicial);
		evento.setDataFinal(dataFinal);
		
		DAOEvento daoEvento = new DAOEvento();
		evento = daoEvento.create(evento);
		
		DAOUsuario daoUsuario = new DAOUsuario();
		Usuario usuario = daoUsuario.getUsuario(cpfCoordenador);
		
		if(!usuario.getTipo().equals((NivelUsuario.COORDENADOR_EVENTO))) {
			usuario.setTipo(NivelUsuario.COORDENADOR_EVENTO);
			daoUsuario.update(usuario);
		}
		CoordenacaoEvento coordEvento = new CoordenacaoEvento();
		coordEvento.setCoordenador(usuario);
		coordEvento.setEvento(evento);
		DAOCoordenacaoEvento daoCoordEvento = new DAOCoordenacaoEvento();
		daoCoordEvento.create(coordEvento);
		
		session.setAttribute("evento", evento);
		response.sendRedirect("gerenciaEvento.jsp");
	}

}
