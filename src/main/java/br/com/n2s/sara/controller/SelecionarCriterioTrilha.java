package br.com.n2s.sara.controller;

import java.io.IOException;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOCriterio;
import br.com.n2s.sara.dao.DAOCriterioTrilha;
import br.com.n2s.sara.dao.DAOItem;
import br.com.n2s.sara.dao.DAOTrilha;
import br.com.n2s.sara.model.Criterio;
import br.com.n2s.sara.model.CriterioTrilha;
import br.com.n2s.sara.model.Item;
import br.com.n2s.sara.model.Trilha;

/**
 * Servlet implementation class SelecionarCriterioTrilha
 */
@WebServlet("/SelecionarCriterioTrilha")
public class SelecionarCriterioTrilha extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelecionarCriterioTrilha() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		Trilha trilha = (Trilha) session.getAttribute("trilha");

		int idCriterioTrilha = Integer.parseInt(request.getParameter("idCriterioTrilha"));

		CriterioTrilha criterioTrilha = new DAOCriterioTrilha().getCriterioTrilha(idCriterioTrilha);

		/* Criando cópia do Critério Trilha */

		CriterioTrilha criterioTrilhaCopia = new CriterioTrilha();
		criterioTrilhaCopia.setNome(criterioTrilha.getNome() + " Cópia");
		criterioTrilhaCopia.setDataCriacao(LocalDate.now());

		DAOCriterioTrilha daoCriterioTrilha = new DAOCriterioTrilha();
		daoCriterioTrilha.create(criterioTrilhaCopia);
		criterioTrilhaCopia.setIdCriterioTrilha(daoCriterioTrilha.getLastId()); //Fazer essa busca de último id inserido de outra forma

		/* Copiando os critérios e os itens */

		DAOCriterio daoCriterio = new DAOCriterio();
		List<Criterio> criterios = daoCriterio.obterCriteriosPorTrilha(criterioTrilha.getIdCriterioTrilha());

		DAOItem daoItem = new DAOItem();

		for (Criterio c : criterios) {
			Criterio criterio = new Criterio();
			criterio.setCriterioTrilha(criterioTrilhaCopia);
			criterio.setDescricao(c.getDescricao());
			criterio.setPeso(c.getPeso());
			daoCriterio.create(criterio);

			criterio.setIdCriterio(daoCriterio.getLastId());

			List<Item> itens = daoItem.readById(c.getIdCriterio());

			for (Item i : itens) {
				i.setCriterio(criterio);
				daoItem.create(i);
			}
		}

		
		session.setAttribute("trilha", trilha);

		trilha.setCriterioTrilha(criterioTrilhaCopia);
		DAOTrilha daoTrilha = new DAOTrilha();
		daoTrilha.update(trilha);
		
		response.sendRedirect("eventosCoordenados.jsp");

	}
}
