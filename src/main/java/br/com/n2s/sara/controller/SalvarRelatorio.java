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

import br.com.n2s.sara.dao.DAOAvaliaTrabalho;
import br.com.n2s.sara.dao.DAOEvento;
import br.com.n2s.sara.dao.DAOSubmissao;
import br.com.n2s.sara.dao.DAOTrabalho;
import br.com.n2s.sara.dao.DAOTrilha;
import br.com.n2s.sara.model.AvaliaTrabalho;
import br.com.n2s.sara.model.Evento;
import br.com.n2s.sara.model.NivelUsuario;
import br.com.n2s.sara.model.StatusTrabalho;
import br.com.n2s.sara.model.TipoApresentacao;
import br.com.n2s.sara.model.Trabalho;
import br.com.n2s.sara.model.Trilha;
import br.com.n2s.sara.model.Usuario;
import br.com.n2s.sara.util.Facade;

/**
 * Servlet implementation class SalvarRelatorio
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 10,   // 10MB
        maxRequestSize = 1024 * 1024 * 10 // 10MB
        )
public class SalvarRelatorio extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String trilha, evento;
		trilha = request.getParameter("trilha"); 
		evento= request.getParameter("evento");
		int idTrilha = Integer.parseInt(trilha);
		int idEvento = Integer.parseInt(evento);
		Trilha nomeTrilha = new DAOTrilha().getTrilha(idTrilha);
		Evento nomeEvento = new DAOEvento().getEvento(idEvento);
		Usuario userLogado = (br.com.n2s.sara.model.Usuario) session.getAttribute("usuarioSara");
		String endereco=null;
		br.com.n2s.sara.model.Trabalho trabalho = new Trabalho();
        trabalho.setTrilha(nomeTrilha);
        trabalho.setAutor(userLogado);
		trabalho.setTitulo(request.getParameter("titulo").toUpperCase().trim());
		trabalho.setPalavrasChaves(request.getParameter("palavras_chave"));
		trabalho.setResumo(request.getParameter("resumo"));
		trabalho.setStatus(StatusTrabalho.EM_AVALIACAO);
		ArrayList <br.com.n2s.sara.model.Usuario> banca = new ArrayList();
		//Ele está pegando a lista de membros da banca
		String [] nomesAutores = request.getParameterValues("nomeAutor");
		String [] emailAutores = request.getParameterValues("emailAutor");
		String [] cpfAutores = request.getParameterValues("cpfAutor");
		if (nomesAutores != null && emailAutores != null && cpfAutores != null) {
			if (!(nomesAutores[0].isEmpty() || cpfAutores[0].isEmpty() || emailAutores[0].isEmpty()) ) {
				Usuario orientador = new Usuario();
				orientador.setCpf(nomesAutores[0]);
				orientador.setEmail(emailAutores[0]);
				orientador.setSobrenome("");
				orientador.setCpf(cpfAutores[0].replaceAll("[.-]", ""));
				trabalho.setOrientador(orientador);
				banca.add(orientador);
			}
			for (int i=1;i<nomesAutores.length;i++) {
				if ( nomesAutores[i].isEmpty() || cpfAutores[i].isEmpty() || emailAutores[i].isEmpty() ) {
					continue;
				}
				boolean teste = false;
				for (Usuario u : banca) {
					if (u.getCpf().equals(cpfAutores[i].replaceAll("[.-]", ""))) { 
						teste = true;
						break;
					}else {
						teste = false;
					}
				}
				if(!teste) {
					Usuario autor= new Usuario();
					autor.setNome(nomesAutores[i]);
					autor.setCpf(cpfAutores[i].replaceAll("[.-]", ""));
					autor.setEmail(emailAutores[i]);
					autor.setSobrenome(" ");
					autor.setTipo(NivelUsuario.AUTOR);
					banca.add(autor);
				}
			}			
		}
		//Aqui estï¿½ tratando do arquivo
		File dir = new File(util.Constantes.getArticlesDir()+File.separator+nomeEvento.getIdEvento()+File.separator+nomeTrilha.getIdTrilha()+File.separator);
		
		if( !dir.isDirectory() ){
	        dir.mkdirs();
	    }
		
	    for(Part part: request.getParts()){    	           	
	        
	        //Isso Ã© para pegar a data/hora de maneira interessante
	    	Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Brazil/East"));
	    	int ano = calendar.get(Calendar.YEAR);
	    	int mes = calendar.get(Calendar.MONTH); // O mï¿½s vai de 0 a 11.
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
        trabalho.setEnderecoInicial(endereco);
        DAOTrabalho daoTrabalho = new DAOTrabalho();
        trabalho.setIdTrabalho(daoTrabalho.create(trabalho));
        trabalho.setAutores(new ArrayList<Usuario>());
        Facade.EnviarEmail(trabalho);
        
        // Essas linhas sÃ³ serÃ£o executadas caso aja alguma substituiÃ§Ã£o
        
        if(request.getParameter("idTrabalho") != null) {
        	int idTrabalho = Integer.parseInt(request.getParameter("idTrabalho"));
        	Trabalho tAntigo = daoTrabalho.getTrabalho(idTrabalho); 
        	File arquivo = new File(tAntigo.getEnderecoInicial());
        	arquivo.delete();
        	new DAOSubmissao().delete(idTrabalho);
        	daoTrabalho.delete(idTrabalho);
        	
        }
        for (Usuario av : banca) {
        	SalvarAvaliador(av, trabalho);
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
		avalia.setNota(0);
		daoAvaliaTrab.create(avalia);
		t.setStatus(StatusTrabalho.EM_AVALIACAO);
		new DAOTrabalho().update(t);
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

