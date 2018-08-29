package br.com.n2s.sara.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOAvaliaTrilha;
import br.com.n2s.sara.dao.DAOUsuario;
import br.com.n2s.sara.model.AvaliaTrilha;
import br.com.n2s.sara.model.NivelUsuario;
import br.com.n2s.sara.model.Usuario;
import br.com.n2s.sara.model.Trilha;



public class CadastrarAvaliador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String nome=request.getParameter("nome");
		String sobrenome=request.getParameter("sobrenome");
		String cpf=request.getParameter("cpf");
		String email=request.getParameter("email");
		
		Usuario user = new Usuario();
		user.setNome(nome);
		user.setSobrenome(sobrenome);
		user.setCpf(cpf);
		user.setEmail(email);
		user.setTipo(NivelUsuario.AVALIADOR);
		DAOUsuario daoUsuario= new DAOUsuario();
		daoUsuario.create(user);		
		
		HttpSession session = request.getSession();
		Trilha trilha = (Trilha) session.getAttribute("trilha");
		DAOAvaliaTrilha daoAvaliaTrilha=new DAOAvaliaTrilha();
		AvaliaTrilha avaliaTrilha = new AvaliaTrilha();
		avaliaTrilha.setAvaliador(user);
		avaliaTrilha.setTrilha(trilha);
		daoAvaliaTrilha.create(avaliaTrilha);
		
		response.sendRedirect("gerenciaAvaliadores.jsp");
	}

}
