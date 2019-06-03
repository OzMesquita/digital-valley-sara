package br.com.n2s.sara.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOCriterio;
import br.com.n2s.sara.dao.DAOCriterioTrilha;
import br.com.n2s.sara.dao.DAOTrilha;
import br.com.n2s.sara.model.Criterio;
import br.com.n2s.sara.model.CriterioTrilha;
import br.com.n2s.sara.model.Trilha;
import br.com.n2s.sara.util.Constantes;

/**
 * Servlet implementation class AdicionarCriterio
 */
@WebServlet("/AdicionarCriterio")
public class AdicionarCriterio extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdicionarCriterio() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		try {
		Trilha trilha = new Trilha();
		Criterio criterio = new Criterio();

		trilha = (Trilha) session.getAttribute("trilha");

		String descricaoCriterio = request.getParameter("descricaoCriterio");
		int pesoCriterio = Integer.parseInt(request.getParameter("pesoCriterio"));
		
		criterio.setDescricao(descricaoCriterio);
		criterio.setPeso(pesoCriterio);
		
		criterio.setCriterioTrilha(trilha.getCriterioTrilha());
		
		new DAOCriterio().create(criterio);
		session.setAttribute(Constantes.getSESSION_MGS(), "Sucesso durante a adição do criterio!");
		response.sendRedirect("eventosCoordenados.jsp");
		}catch (Exception e) {
			session.setAttribute(Constantes.getSESSION_MGS_ERROR(), "Erro durante o cadastro do critério!\n Erro: "+e.getMessage());
			response.sendRedirect("adicionarCriterio.jsp");
		}

		
	}

}
