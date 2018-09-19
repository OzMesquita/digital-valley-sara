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

import br.com.n2s.sara.dao.DAOTrabalho;
import br.com.n2s.sara.model.Trabalho;

/**
 * Servlet implementation class DownloadTrabalho
 */
@WebServlet("/DownloadTrabalho")
public class DownloadTrabalho extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadTrabalho() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOTrabalho daoTrabalho = new DAOTrabalho();
		Trabalho trabalho = daoTrabalho.getTrabalho(Integer.parseInt(request.getParameter("idTrabalho")));
		File arquivo = new File(trabalho.getEndereco());
		Path path = arquivo.toPath();
        
        String nome = arquivo.getName();
        int tamanho = (int) arquivo.length();

        response.setContentType(Files.probeContentType(path)); // tipo do conteúdo
        response.setContentLength(tamanho);  // opcional
        response.setHeader("Content-Disposition", "attachment; filename=\"" + nome + "\"");

        OutputStream output = response.getOutputStream();
        Files.copy(path, output);
	}

}
