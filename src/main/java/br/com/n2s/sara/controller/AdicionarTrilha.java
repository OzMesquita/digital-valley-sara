package br.com.n2s.sara.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOTrilha;
import br.com.n2s.sara.model.DescricaoPeriodo;
import br.com.n2s.sara.model.Evento;
import br.com.n2s.sara.model.Trilha;
import br.com.n2s.sara.util.Constantes;
import br.com.n2s.sara.model.Periodo;

/**
 * Servlet implementation class AdicionarTrilha
 */

public class AdicionarTrilha extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		SimpleDateFormat datas = new SimpleDateFormat("dd-MM-yyyy");
		ArrayList<Periodo> p =new ArrayList<Periodo>();		
		for (DescricaoPeriodo dp : DescricaoPeriodo.values()) {
			Periodo p1 = new Periodo();
			p1.setTrilha(trilha);
			p1.setDataFinal( datas.parse( request.getParameter("dataInicial-"+dp.getDescricao())).toInstant().atZone(ZoneId.systemDefault()).toLocalDate() );
			p1.setDescricao(dp);
			p.add(p1);
		}
		
		trilha.setPeriodos(p);
		daoTrilha.update(trilha);
		session.setAttribute(Constantes.getSESSION_MGS(), "Sucesso durante o cadastro da trilha!");
		response.sendRedirect("eventosCoordenados.jsp");
		}catch (Exception e) {
			session.setAttribute(Constantes.getSESSION_MGS_ERROR(), "Erro durante o cadastro da trilha!");
		}
	}

}
