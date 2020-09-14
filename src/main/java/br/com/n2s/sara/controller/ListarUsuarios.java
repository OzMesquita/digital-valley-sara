package br.com.n2s.sara.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.util.Constantes;
import br.com.n2s.sara.util.Facade;
import br.com.n2s.sara.model.*;

/**
 * Servlet implementation class ListarUsuarios
 */
@WebServlet("/ListarUsuarios")
public class ListarUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// paginacao de pessoas

		HttpSession session = request.getSession();
		Usuario user = (Usuario) session.getAttribute("usuarioSara");
		if (! user.getTipo().equals(NivelUsuario.ADMINISTRADOR)) {
			request.getSession().setAttribute(Constantes.getSESSION_MGS_ERROR(),"Não possue permissão suficiente para acessar!");
			response.sendRedirect("indexAutor.jsp");
		}
		try {
					Integer paginaAtual = request.getParameter("pagina") != null
							? Integer.valueOf(request.getParameter("pagina"))
							: 1;
					Integer fim = Constantes.getNUMBER_OF_ROWS_PER_PAGE() * paginaAtual;
					Integer inicio = fim - Constantes.getNUMBER_OF_ROWS_PER_PAGE();
					Integer quantidadePorPagina = fim - inicio;
					Integer quantidadeDePessoas = null;
					// pegar dados de pessoas
					String nomePessoa = request.getParameter("nome") != null ? (String) request.getParameter("nome") : "";
					String tipo = (String) request.getParameter("tipo");
					List<Usuario> pessoas = null;
					if (tipo == null) {
						tipo = "todos";
					}
					if (tipo.equals("todos")) {
						quantidadeDePessoas = Facade.getQuantidadePessoasPorNome(nomePessoa);
						pessoas = Facade.buscarPessoasPorNome(nomePessoa, inicio, fim);
					}
					// enviar dados
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("adm/listarUsuarios.jsp");
					request.setAttribute("url", Constantes.getAdmUrl());
					request.setAttribute("pessoas", pessoas);
					request.setAttribute("quantidadeDePaginas", (quantidadeDePessoas+(quantidadePorPagina-1)) / quantidadePorPagina);
					request.setAttribute("paginaAtual", paginaAtual);
					request.setAttribute("nome", nomePessoa);
					request.setAttribute("tipo", tipo);
					requestDispatcher.forward(request, response);
		}catch (Exception e) {
			request.getSession().setAttribute(Constantes.getSESSION_MGS_ERROR(), e.getMessage());
			response.sendRedirect("adm/listarUsuarios.jsp");
		}
		
	}

}
