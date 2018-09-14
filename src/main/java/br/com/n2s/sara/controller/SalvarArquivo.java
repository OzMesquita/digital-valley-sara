package br.com.n2s.sara.controller;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import javax.faces.annotation.RequestMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOTrabalho;
import br.com.n2s.sara.model.Evento;
import br.com.n2s.sara.model.NivelUsuario;
import br.com.n2s.sara.model.StatusTrabalho;
import br.com.n2s.sara.model.Trabalho;
import br.com.n2s.sara.model.Trilha;
import br.com.n2s.sara.model.Usuario;

/**
 * Servlet implementation class SalvarArquivoController
 */

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 4,   // 4MB
        maxRequestSize = 1024 * 1024 * 4 // 4MB
        )
public class SalvarArquivo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Trilha nomeTrilha = (Trilha) session.getAttribute("trilha");
		Evento nomeEvento = (Evento) session.getAttribute("evento");
		Usuario userLogado = (br.com.n2s.sara.model.Usuario) session.getAttribute("usuarioSara");
		String endereco=null;
		br.com.n2s.sara.model.Trabalho trabalho = new Trabalho();
        trabalho.setTrilha(nomeTrilha);
        trabalho.setAutor(userLogado);
		trabalho.setTitulo(request.getParameter("titulo"));
		trabalho.setPalavrasChaves(request.getParameter("palavras_chave"));
		trabalho.setResumo(request.getParameter("resumo"));
		trabalho.setStatus(StatusTrabalho.ENVIADO);
		//Aqui ele está pegando a lista de Autores do trabalho
		ArrayList <br.com.n2s.sara.model.Usuario> autores = new ArrayList();
		String [] nomesAutores = request.getParameterValues("nomeAutor");
		String [] emailAutores = request.getParameterValues("emailAutor");
		String [] cpfAutores = request.getParameterValues("cpfAutor");
		Usuario autor= new Usuario();
		if (nomesAutores != null && emailAutores != null && cpfAutores != null) {
			for (int i=0;i<nomesAutores.length;i++) {
				autor.setNome(nomesAutores[i]);
				autor.setCpf(cpfAutores[i]);
				autor.setEmail(emailAutores[i]);
				autor.setSobrenome(" ");
				autor.setTipo(NivelUsuario.AUTOR);
				autores.add(autor);
			}			
		}
		trabalho.setAutores(autores);
		//Aqui está tratando do arquivo
		File dir = new File("C:\\n2s\\sara\\"+nomeEvento.getNome()+"\\"+nomeTrilha.getNome()+"\\");
		if( !dir.isDirectory() ){
	        dir.mkdirs();
	    }
		
	    for(Part part: request.getParts()){    	           	
	        
	        for(String headerName : part.getHeaderNames()){

	        }
	        
			File arquivo = new File(dir.getAbsolutePath() + File.separator + getFileName(part)); 
	        part.write( arquivo.getAbsolutePath() );
	        endereco = arquivo.getAbsolutePath();
	     //Salvou o Arquivo no Servidor	        
	    }
        trabalho.setEndereco(endereco);
        DAOTrabalho daoTrabalho = new DAOTrabalho();
        trabalho.setIdTrabalho(daoTrabalho.create(trabalho));      
        
        response.sendRedirect("indexAutor.jsp");
	    

	}
	public String getFileName(Part part) {
	    String header = part.getHeader( "content-disposition" );
	    for( String tmp : header.split(";") ){
	        if( tmp.trim().startsWith("filename") ){
	            return tmp.substring( tmp.indexOf("=")+2 , tmp.length()-1 );
	        }
	    }
	    return null;
	}

}
