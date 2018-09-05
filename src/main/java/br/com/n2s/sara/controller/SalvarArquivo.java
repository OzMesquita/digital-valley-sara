package br.com.n2s.sara.controller;

import java.io.File;
import java.io.IOException;


import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import javax.faces.annotation.RequestMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.model.Evento;
import br.com.n2s.sara.model.Trilha;

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
		
		File dir = new File("C:\\n2s\\sara\\"+nomeEvento.getNome()+"\\"+nomeTrilha.getNome()+"\\");
		if( !dir.isDirectory() ){
	        dir.mkdirs();
	    }
		
	    for(Part part: request.getParts()){    	           	
	        
	        for(String headerName : part.getHeaderNames()){

	        }
	        
			File arquivo = new File(dir.getAbsolutePath() + File.separator + getFileName(part)); 
	        part.write( arquivo.getAbsolutePath() );
	        response.sendRedirect("indexAutor.jsp");
	        
	    }

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
