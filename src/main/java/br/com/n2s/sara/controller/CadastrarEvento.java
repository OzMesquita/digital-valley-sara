package br.com.n2s.sara.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
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
import br.com.n2s.sara.model.TipoEvento;
import br.com.n2s.sara.model.Usuario;
import br.com.n2s.sara.util.Constantes;


/**
 * Servlet implementation class CadastrarEvento
 */
public class CadastrarEvento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		try {
		String nome = request.getParameter("nome");
		String cpfCoordenador = request.getParameter("cpfCoordenador").replaceAll("[.-]", "");
		String site = request.getParameter("site");
		String descricao = request.getParameter("descricao");
		String localizacao = request.getParameter("localizacao");
		LocalDate dataInicial = LocalDate.parse(request.getParameter("dataInicial"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate dataFinal = LocalDate.parse(request.getParameter("dataFinal"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String tipoEvento = request.getParameter("tipoEvento");
				
		Evento evento = new Evento();
		evento.setNome(nome);
		evento.setDescricao(descricao);
		evento.setLocalizacao(localizacao);
		evento.setSite(site);
		evento.setDataInicial(dataInicial);
		evento.setDataFinal(dataFinal);
		evento.setDivulgada(false);
		
		if (tipoEvento.equals("encontrosUniversitarios")) {
			evento.setDescriEvento(TipoEvento.EU);
		}else {
			evento.setDescriEvento(TipoEvento.ESTAGIO);
		}
		
		DAOEvento daoEvento = new DAOEvento();
		evento = daoEvento.create(evento);
		
		DAOUsuario daoUsuario = new DAOUsuario();
		Usuario usuario = daoUsuario.getUsuario(cpfCoordenador);
		
		if( usuario.getTipo().equals(NivelUsuario.AUTOR) || usuario.getTipo().equals(NivelUsuario.USUARIO) || usuario.getTipo().equals(NivelUsuario.COORDENADOR_TRILHA)) {
			usuario.setTipo(NivelUsuario.COORDENADOR_EVENTO);
			daoUsuario.update(usuario);
		}
		CoordenacaoEvento coordEvento = new CoordenacaoEvento();
		coordEvento.setCoordenador(usuario);
		coordEvento.setEvento(evento);
		DAOCoordenacaoEvento daoCoordEvento = new DAOCoordenacaoEvento();
		daoCoordEvento.create(coordEvento);

		session.setAttribute("evento", evento);
		session.setAttribute(Constantes.getSESSION_MGS(), "Evento cadastrado com sucesso!");
		response.sendRedirect("../eventosCoordenados.jsp");
		}catch (Exception e) {
			session.setAttribute(Constantes.getSESSION_MGS_ERROR(), "Erro ao cadastrar o Evento: "+e.getMessage());
		}
		

	}

}
