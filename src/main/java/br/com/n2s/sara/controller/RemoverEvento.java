package br.com.n2s.sara.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOEvento;
import br.com.n2s.sara.model.Evento;

/**
 * Servlet implementation class RemoverEvento
 * 
 * Ao invés de remover o evento permanentemente do banco é realizado apenas uma ocultação 
 * do mesmo através da mudança do atributo excluido para true
 * 
 */
@WebServlet("/RemoverEvento")
public class RemoverEvento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoverEvento() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		try {
			Evento evento = new Evento();

			String idEvento = request.getParameter("idEvento");

			DAOEvento daoEvento = new DAOEvento();
			evento = daoEvento.getEvento(Integer.parseInt(idEvento));

			evento.setExcluido(true);
			daoEvento.updateExclusaoEvento(evento);

			String feedbackSucesso = "Evento removido com sucesso!";
			session.setAttribute(br.com.n2s.sara.util.Constantes.getSESSION_MGS(), feedbackSucesso);

			response.sendRedirect("eventosCoordenados.jsp");
		} catch (Exception e) {
			session.setAttribute(br.com.n2s.sara.util.Constantes.getSESSION_MGS_ERROR(),
					"Erro durante a remossão do evento! "+e.getMessage());
		}
	}

}
