package br.com.n2s.sara.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.model.ManipulacaoArquivo;

/**
 * Servlet implementation class SalvarArquivoController
 */
@WebServlet("/SalvarArquivo")
public class SalvarArquivoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String nomeTrilha = (String) session.getAttribute("trilha");
		String nomeEvento = (String) session.getAttribute("evento");
		ManipulacaoArquivo arq = new ManipulacaoArquivo();
		File arquivoRecebido = (File) request.getAttribute("trabalho");
		arq.gravarArquivo(arquivoRecebido, nomeEvento, nomeTrilha);
		System.out.println("Deu certo");
		
		PrintWriter out = response.getWriter();
		out.println("Deu Certo HTML");
	}

}
