package br.com.n2s.sara.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import br.com.n2s.sara.dao.DAOTrilha;
import br.com.n2s.sara.model.Trilha;
import br.com.n2s.sara.model.Usuario;
import br.com.n2s.sara.util.Facade;
import br.com.n2s.sara.model.Evento;
import br.com.n2s.sara.model.NivelUsuario;
import br.com.n2s.sara.model.StatusTrabalho;
import br.com.n2s.sara.model.Trabalho;
import br.com.n2s.sara.dao.DAOEvento;
import br.com.n2s.sara.dao.DAOSubmissao;
import br.com.n2s.sara.dao.DAOTrabalho;


@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 10,   // 10MB
        maxRequestSize = 1024 * 1024 * 10 // 10MB
        )
public class Submissao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario userLogado = (Usuario) session.getAttribute("usuarioSara");
		int idTrilha = Integer.parseInt(request.getParameter("trilha"));
		int idEvento = Integer.parseInt(request.getParameter("evento"));
		Trilha nomeTrilha = new DAOTrilha().getTrilha(idTrilha);
		Evento nomeEvento = new DAOEvento().getEvento(idEvento);
		String endereco=null;
			br.com.n2s.sara.model.Trabalho trabalho = new Trabalho();
	        trabalho.setTrilha(nomeTrilha);
	        trabalho.setIdTrabalho(Integer.parseInt(request.getParameter("idTrabalho")));
			trabalho.setTitulo(request.getParameter("titulo").toUpperCase());
			trabalho.setPalavrasChaves(request.getParameter("palavras_chave"));
			trabalho.setResumo(request.getParameter("resumo"));
			trabalho.setStatus(StatusTrabalho.ACEITO_FINAL);
			//Aqui est� tratando do arquivo
			File dir = new File(util.Constantes.getArticlesDir()+File.separator+nomeEvento.getIdEvento()+File.separator+nomeTrilha.getIdTrilha()+File.separator+"versaoFinal"+File.separator);
			
			if( !dir.isDirectory() ){
		        dir.mkdirs();
		    }
			
		    for(Part part: request.getParts()){    	           	
		        
		        //Isso é para pegar a data/hora de maneira interessante
		    	Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Brazil/East"));
		    	int ano = calendar.get(Calendar.YEAR);
		    	int mes = calendar.get(Calendar.MONTH); // O m�s vai de 0 a 11.
		    	int dia = calendar.get(Calendar.DAY_OF_MONTH);
		    	int hora = calendar.get(Calendar.HOUR_OF_DAY);
		    	int minuto = calendar.get(Calendar.MINUTE);
		    	int segundo = calendar.get(Calendar.SECOND);
		    	Random random = new Random();
		        String nome = dia+"_"+mes+"_"+ano+"_"+hora+"_"+minuto+"_"+segundo+random.nextInt(10000);
		    	//Acima tudo que eu fiz foi construir uma super string para completar essa hora
		    	
				File arquivo = new File(dir.getAbsolutePath() + File.separator+ nome + ".pdf"); 
		        part.write( arquivo.getAbsolutePath() );
		        endereco = arquivo.getAbsolutePath();
		     //Salvou o Arquivo no Servidor	        
		    }
		    
		     
	        trabalho.setEndereco(endereco);
	        DAOTrabalho daoTrabalho = new DAOTrabalho();
	        daoTrabalho.update(trabalho);      
		 
		  
        response.sendRedirect("indexAutor.jsp");	    		
	}
		
	private boolean isAutor(String cpf, Trabalho t) {
		if (cpf.equals(t.getAutor().getCpf())) {
			return true;
		}
		if(!t.getAutores().isEmpty()) {
			for (Usuario u : t.getAutores()) {
				if(u.getCpf().equals(cpf))
					return true;
			}
		}
		return false;
	}


}
