package br.com.n2s.sara.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOAvaliaTrabalho;
import br.com.n2s.sara.dao.DAOAvaliaTrilha;
import br.com.n2s.sara.dao.DAOEvento;
import br.com.n2s.sara.dao.DAOPeriodo;
import br.com.n2s.sara.dao.DAOTrabalho;
import br.com.n2s.sara.dao.DAOTrilha;
import br.com.n2s.sara.model.*;
import br.com.n2s.sara.util.Constantes;
import br.com.n2s.sara.util.Facade;
import br.com.n2s.sara.util.InterfaceAlgoritmo;


/**
 * Servlet implementation class Distribuir
 */
@WebServlet("/Distribuir")
public class Distribuir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Trilha trilha = new Trilha();
		DAOTrilha daoTrilha = new DAOTrilha();
		ArrayList<AvaliaTrabalho> av = new ArrayList<AvaliaTrabalho>(); 
		if (request.getParameter("idTrilha") != null) {	
			trilha = daoTrilha.getTrilha(Integer.parseInt(request.getParameter("idTrilha")));
			if( (trilha==null || trilha.getAvaliadores()==null)) {
				session.setAttribute(Constantes.getSESSION_MGS_ERROR(), "Trilha inválida");
				response.sendRedirect("Sara/webapp/jsp/indexAutor.jsp");	
			}
			if (trilha.getAvaliadores().size()<0) {
				session.setAttribute(Constantes.getSESSION_MGS_ERROR(), "Quantia de avaliadores inválida!");
			}
			 av = (ArrayList<AvaliaTrabalho>) InterfaceAlgoritmo.distribuPorTrilhaComOrientador(trilha, trilha.getQtdCorrecoes());
		}else if(request.getParameter("idEvento") != null && request.getParameter("tipo") == null ){
			Evento evento = new DAOEvento().getEvento(Integer.parseInt(request.getParameter("idEvento")));
			av = (ArrayList<AvaliaTrabalho>) InterfaceAlgoritmo.distribuPorEventoComOrientador(evento, 1);
		}else if (request.getParameter("idEvento") != null && request.getParameter("tipo") != null) {
			Evento evento = new DAOEvento().getEvento(Integer.parseInt(request.getParameter("idEvento")));
			av = (ArrayList<AvaliaTrabalho>) InterfaceAlgoritmo.distribuPorEventoRecurso(evento, 1);
		}		
		for(AvaliaTrabalho a : av ){
			new DAOAvaliaTrabalho().create(a);
		}		
		
		response.sendRedirect("indexAutor.jsp");
	}
	private  void SalvarAvaliador(Usuario av, Trabalho t) {
		DAOAvaliaTrabalho daoAvaliaTrab = new DAOAvaliaTrabalho();
		AvaliaTrabalho avalia = new AvaliaTrabalho();
		avalia.setAvaliador(av);
		avalia.setTrabalho(t);
		avalia.setFeedback("");
		avalia.setStatus(StatusTrabalho.EM_AVALIACAO);
		daoAvaliaTrab.create(avalia);
		t.setStatus(StatusTrabalho.EM_AVALIACAO);
		new DAOTrabalho().update(t);
	}
	private boolean isAutor(String cpf, Trabalho t) {
		if (cpf.equals(t.getAutor().getCpf())) {
			return true;
		}
		if(!t.getAutores().isEmpty()) {
			for (Usuario u : t.getAutores()) {
				if (u != null)
					if(u.getCpf().equals(cpf))
						return true;
			}
		}
		return false;
	}
	
}
