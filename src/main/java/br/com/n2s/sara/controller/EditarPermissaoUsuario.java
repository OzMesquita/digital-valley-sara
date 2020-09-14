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
import br.com.n2s.sara.model.TipoEvento;
import br.com.n2s.sara.model.Usuario;
import br.com.n2s.sara.util.Constantes;

/**
 * Servlet implementation class EditarPermissaoUsuario
 */
@WebServlet("/EditarPermissaoUsuario")
public class EditarPermissaoUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditarPermissaoUsuario() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
		
			DAOUsuario daoUsuario = new DAOUsuario();
			Usuario usuario = (Usuario) session.getAttribute("usu");
			String permissao = request.getParameter("permissao");
		
			if (permissao.equalsIgnoreCase("ADMINISTRADOR")) {
				usuario.setTipo(NivelUsuario.ADMINISTRADOR);
			} else if (permissao.equalsIgnoreCase("AUTOR")) {
				usuario.setTipo(NivelUsuario.AUTOR);
			} else if (permissao.equalsIgnoreCase("COORDENADOR_EVENTO")) {
				usuario.setTipo(NivelUsuario.COORDENADOR_EVENTO);
			}else if (permissao.equalsIgnoreCase("COORDENADOR_TRILHA")) {
				usuario.setTipo(NivelUsuario.COORDENADOR_TRILHA);
			}else if (permissao.equalsIgnoreCase("AVALIADOR")) {
				usuario.setTipo(NivelUsuario.AVALIADOR);
			}

			daoUsuario.updatePermissao(usuario);

			session.setAttribute(Constantes.getSESSION_MGS(), "Permissão alterada com sucesso!");
			response.sendRedirect(Constantes.getJSP_URL()+"ListarUsuarios");
		} catch (Exception e) {
			session.setAttribute(Constantes.getSESSION_MGS_ERROR(),
					"Erro ao alterar a permissão do usuário: ");
		}

	}

}
