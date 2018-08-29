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

		Trilha trilha = new Trilha();
		Criterio criterio = new Criterio();

		trilha = (Trilha) session.getAttribute("trilha");

		String descricaoCriterio = request.getParameter("descricaoCriterio");
		int pesoCriterio = Integer.parseInt(request.getParameter("pesoCriterio"));

		//ArrayList<String> itensDescricao = new ArrayList<String>();
		//ArrayList<Integer> itensPeso = new ArrayList<Integer>();

		//String desc = "";
		//String peso = "";

		criterio.setDescricao(descricaoCriterio);
		criterio.setPeso(pesoCriterio);

		if(trilha.getCriterioTrilha() != null){ //Caso a trilha já possua um CriterioTrilha, apenas associa o novo critério com este CriterioTrilha
			criterio.setCriterioTrilha(trilha.getCriterioTrilha());

		}else {

			String nomeCriterioTrilha = request.getParameter("nomeCriterioTrilha");

			CriterioTrilha criterioTrilha = new CriterioTrilha();
			criterioTrilha.setNome(nomeCriterioTrilha);
			criterioTrilha.setDataCriacao(LocalDate.now());

			DAOCriterioTrilha daoCriterioTrilha = new DAOCriterioTrilha();
			daoCriterioTrilha.create(criterioTrilha);

			criterioTrilha = daoCriterioTrilha.getCriterioTrilha(daoCriterioTrilha.getLastId());
			criterio.setCriterioTrilha(criterioTrilha);

			trilha.setCriterioTrilha(criterioTrilha);
			DAOTrilha daoTrilha = new DAOTrilha();
			daoTrilha.update(trilha);
		}

		DAOCriterio daoCriterio = new DAOCriterio();
		daoCriterio.create(criterio);		
		
		response.sendRedirect("gerenciaCriterios.jsp");

		/* Item item = new Item();
		ItemController itemCont = new ItemController();

		for(int i = 0; i < 20; i++){
			desc = request.getParameter("descricao" + i);
			peso = request.getParameter("peso" + i);

			if (desc != "" && peso != "" && desc != null && peso != null) {
				//itensDescricao.add(desc);
				//itensPeso.add(Integer.parseInt(peso));
				item.setDescricao(desc);
				item.setPeso(Integer.parseInt(peso));
				item.setCriterio(crit);

				itemCont.criar(item);
			} 
		}
		 */

		/* System.out.println("TAMANHO: " + itensDescricao.size());

		for(int i = 0; i < itensPeso.size(); i++){
			System.out.println(itensDescricao.get(i));
			System.out.println(itensPeso.get(i));
		}   */
	}

}
