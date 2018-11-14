package br.com.n2s.sara.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOAvaliaTrilha;
import br.com.n2s.sara.dao.DAOUsuario;
import br.com.n2s.sara.model.AvaliaTrilha;
import br.com.n2s.sara.model.NivelUsuario;
import br.com.n2s.sara.model.Trilha;
import br.com.n2s.sara.model.Usuario;
import br.com.n2s.sara.util.Facade;

/**
 * Servlet implementation class AdicionarAvaliador
 */
@WebServlet("/AdicionarAvaliador")
public class AdicionarAvaliador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdicionarAvaliador() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Trilha trilha = (Trilha) session.getAttribute("trilha");

		DAOAvaliaTrilha daoAvaliaTrilha = new DAOAvaliaTrilha();
		AvaliaTrilha avaliaTrilha;

		String cpfAvaliador = request.getParameter("cpfAvaliador").replaceAll("[.-]", "");

		Usuario usuario = new Usuario();
		usuario = Facade.buscarUsuarioPorCPF(cpfAvaliador);

		if (usuario == null) {
			
			String feedbackInfo = "Não existe usuário cadastrado com o CPF informado! Por favor, realize o cadastro para continuar.";
			session.setAttribute("feedbackInfo", feedbackInfo);
			response.sendRedirect("cadastrarAvaliador.jsp");
		} else {
			
			avaliaTrilha = daoAvaliaTrilha.getAvaliaTrilha(cpfAvaliador, trilha.getIdTrilha());
			
			if (avaliaTrilha == null) {
				
				avaliaTrilha = new AvaliaTrilha();
				avaliaTrilha.setAvaliador(usuario);
				avaliaTrilha.setTrilha(trilha);
				daoAvaliaTrilha.create(avaliaTrilha);
				
				String feedbackSucesso = "Avaliador adicionado com sucesso!";
				session.setAttribute("feedbackSucesso", feedbackSucesso);
				response.sendRedirect("gerenciarAvaliadoresTrilha.jsp");
			}else {
				
				String feedbackSucesso = "Avaliador adicionado com sucesso!";
				session.setAttribute("feedbackSucesso", feedbackSucesso);
				response.sendRedirect("gerenciarAvaliadoresTrilha.jsp");
			}
		}
	}

}
