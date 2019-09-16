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
		trilha = daoTrilha.getTrilha(Integer.parseInt(request.getParameter("idTrilha")));
		if( (trilha==null || trilha.getAvaliadores()==null)) {
			session.setAttribute(Constantes.getSESSION_MGS_ERROR(), "Trilha inválida");
			response.sendRedirect("Sara/webapp/jsp/indexAutor.jsp");	
		}
		if (trilha.getAvaliadores().size()<0) {
			session.setAttribute(Constantes.getSESSION_MGS_ERROR(), "Quantia de avaliadores inválida!");
		}
		ArrayList<AvaliaTrabalho> av = (ArrayList<AvaliaTrabalho>) InterfaceAlgoritmo.distribuirPorTrilha(trilha, 1);
		for(AvaliaTrabalho a : av ){
			new DAOAvaliaTrabalho().create(a);
		}		
		
		/*Periodo periodo = new Periodo();
		periodo = Facade.periodoAtual(trilha);
		if(periodo.getDescricao()!=DescricaoPeriodo.AVALIACAO) {
			response.sendRedirect("Sara/webapp/jsp/indexAutor.jsp");
		}*/
//		DAOAvaliaTrilha daoAvaliaTrilha = new DAOAvaliaTrilha();
//		DAOTrabalho daoTrabalho = new DAOTrabalho();
//		ArrayList<Trabalho> trabalhos = new ArrayList<Trabalho>();
//		trabalhos = (ArrayList<Trabalho>) daoTrabalho.readTrilha(trilha.getIdTrilha());
//		ArrayList<Usuario> avaliadores = (ArrayList<Usuario>) daoAvaliaTrilha.getAvaliadores(trilha.getIdTrilha());
//		if(trabalhos.size()>0) {
//			int razao = trabalhos.size() / avaliadores.size();
//			int alocados = 0;
//			int resto = trabalhos.size() % avaliadores.size();
//			for(Usuario av :avaliadores) {
//					for(int i=0;i<trabalhos.size();i++)
//						if(alocados < razao) {	
//							if(isAutor(av.getCpf(), trabalhos.get(i))) {
//								continue;
//							}else {
//								SalvarAvaliador(av, trabalhos.get(i));
//								trabalhos.remove(trabalhos.get(i));
//								alocados ++;
//							}
//						}else { 
//							alocados=0;
//							break;
//						}
//					}
//			alocados=0;
//			while (resto>0) {
//				if (!trabalhos.isEmpty()) {
//					if(! (isAutor(avaliadores.get(alocados).getCpf(), trabalhos.get(0))) ) {
//						SalvarAvaliador(avaliadores.get(alocados), trabalhos.get(0));
//						trabalhos.remove(0);
//						resto--;
//						alocados++;
//					}else {
//						alocados--;
//					}					
//				}
//			}		
//		}
		
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
