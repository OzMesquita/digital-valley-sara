package br.com.n2s.sara.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOPeriodo;
import br.com.n2s.sara.dao.DAOTrilha;
import br.com.n2s.sara.model.DescricaoPeriodo;
import br.com.n2s.sara.model.Evento;
import br.com.n2s.sara.model.Periodo;
import br.com.n2s.sara.model.Trilha;
import br.com.n2s.sara.util.Constantes;

/**
 * Servlet implementation class AdicionarTrilha
 */

@WebServlet("/AdicionarTrilha")
public class AdicionarTrilha extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			Trilha trilha = new Trilha();
			DAOTrilha daoTrilha = new DAOTrilha();
			Evento evento = new Evento();

			evento = (Evento) session.getAttribute("evento");
			trilha.setNome(request.getParameter("nome"));
			trilha.setDescricao(request.getParameter("descricao"));
			trilha.setEvento(evento);
			trilha.setQtdCorrecoes(Integer.parseInt(request.getParameter("correcoes")));
			trilha.setPeso(Integer.parseInt(request.getParameter("peso")));
			trilha = daoTrilha.create(trilha);

			ArrayList<Periodo> p = new ArrayList<Periodo>();
			DAOPeriodo daoPeriodo = new DAOPeriodo();
			int i=0, j=0;
			for (DescricaoPeriodo dp : DescricaoPeriodo.values()) {
				Periodo p1 = new Periodo();
				p1.setTrilha(trilha);
				p1.setDataInicial(LocalDate.parse((String)request.getParameter("dataInicial-"+i), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				p1.setDataFinal(LocalDate.parse((String)request.getParameter("dataFinal-"+j), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				p1.setDescricao(dp);
				
				daoPeriodo.create(p1);
				i+=1;
				j+=1;
			}

			String feedbackSucesso = "Trilha cadastrada com sucesso!";
			response.sendRedirect("eventosCoordenados.jsp");
			session.setAttribute(Constantes.getSESSION_MGS(), feedbackSucesso);
		} catch (Exception e) {
			response.sendRedirect("eventosCoordenados.jsp");
			session.setAttribute(Constantes.getSESSION_MGS_ERROR(),
					"Erro durante o cadastro da trilha!" + e.getMessage());
		}
	}

}
