package br.com.n2s.sara.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOTrabalho;
import br.com.n2s.sara.model.Trabalho;
import br.com.n2s.sara.util.Constantes;

/**
 * Servlet implementation class DownloadTrabalho
 */
@WebServlet("/DownloadTrabalho")
public class DownloadTrabalho extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		DAOTrabalho daoTrabalho = new DAOTrabalho();
		Trabalho trabalho = daoTrabalho.getTrabalho(Integer.parseInt(request.getParameter("idTrabalho")));
		int idTrabalho = Integer.parseInt(request.getParameter("idTrabalho"));
		String endereco = "";
		if (request.getParameter("opcaoDownload") != null) {
			if (request.getParameter("opcaoDownload").equals("inicial")) {
				endereco = trabalho.getEnderecoInicial();
			} else {
				endereco = trabalho.getEndereco();
			}
		}
		File arquivo = new File(endereco);
		Path path = arquivo.toPath();

		String nome = arquivo.getName();
		int tamanho = (int) arquivo.length();

		try {
			response.setContentType(Files.probeContentType(path)); // tipo do conte�do
			response.setContentLength(tamanho); // opcional
			response.setHeader("Content-Disposition", "attachment; filename=\"" + nome + "\"");

			OutputStream output = response.getOutputStream();
			Files.copy(path, output);
		} catch (Exception e) {
			System.out.println(e);
			response.sendRedirect("trabalhosAutor.jsp");
			session.setAttribute(Constantes.getSESSION_MGS_ERROR(), "Erro durante download de arquivo!");
		}

	}

}
