package br.com.n2s.sara.util;

import java.awt.Event;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.text.MaskFormatter;

import org.apache.http.params.CoreConnectionPNames;

import br.com.n2s.sara.dao.DAO;
import br.com.n2s.sara.dao.DAOAvaliaTrabalho;
import br.com.n2s.sara.dao.DAOAvaliaTrilha;
import br.com.n2s.sara.dao.DAOCoordenacaoEvento;
import br.com.n2s.sara.dao.DAOEvento;
import br.com.n2s.sara.dao.DAOPeriodo;
import br.com.n2s.sara.dao.DAOTrilha;
import br.com.n2s.sara.dao.DAOUsuario;
import br.com.n2s.sara.dao.DAOUsuarioSemCadastro;
import br.com.n2s.sara.model.AvaliaTrilha;
import br.com.n2s.sara.model.DescricaoPeriodo;
import br.com.n2s.sara.model.Evento;
import br.com.n2s.sara.model.NivelUsuario;
import br.com.n2s.sara.model.Periodo;
import br.com.n2s.sara.model.Trabalho;
import br.com.n2s.sara.model.Trilha;
import br.com.n2s.sara.model.Usuario;
import dao.DAOFactory;
import dao.PessoaDAO;
import model.Email;
import model.Pessoa;
import util.Constantes;
import java.util.List;



public class Facade {
	private Facade() {
		//
	}
	
	public static Usuario buscarUsuarioPorCPF(String cpf) {
		
		Usuario usuario = new Usuario();
		DAOUsuario daoUsuario = new DAOUsuario();
		usuario = daoUsuario.getUsuario(cpf);
	
		return usuario;
	}
	
	public static Usuario pegarUsuario(String cpf) {
		if(buscarUsuarioPorCPF(cpf)!=null) {
			return buscarUsuarioPorCPF(cpf);
		}else {
			if (buscarUsuarioGuardiao(cpf)!=null) {
				return buscarUsuarioGuardiao(cpf);
			}else {
				return new DAOUsuarioSemCadastro().getUsuario(cpf);
			}
		}
	}
	
	public static Boolean isUsuarioCadastrado(String cpf){
		if (buscarUsuarioPorCPF(cpf)!=null) {
			return true;
		}
		else if (buscarUsuarioGuardiao(cpf) != null) {
			Usuario user = new Usuario();
			user = buscarUsuarioGuardiao(cpf);
			new DAOUsuario().create(user);
			return true;
		}
		return false;
	}
	
	public static String[] lerArquivoBancoDeDados() {
		String[] bd = new String[3];
		try {			
			FileReader arquivo = new FileReader(Constantes.getDatabaseConfDirSara());
			BufferedReader reader = new BufferedReader(arquivo);
			try {
				bd[0] = reader.readLine();
				bd[1] = reader.readLine();
				bd[2] = reader.readLine();
			} catch (IOException e) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return bd;
	}
	
	public static ArrayList<Periodo> atualizarPeriodos(Trilha t) {
		DAOPeriodo daoPeriodo = new DAOPeriodo();
		ArrayList<Periodo> periodos = (ArrayList<Periodo>) daoPeriodo.readById(t.getIdTrilha());
		return periodos;
	}
	public static boolean dataValida(Periodo p) {
		if ((LocalDate.now().isBefore(p.getDataFinal()) || LocalDate.now().isEqual(p.getDataFinal()) )&& (LocalDate.now().isAfter(p.getDataInicial()) || LocalDate.now().isEqual(p.getDataInicial())))
				return true;
		return false;
	}
	
	public static Periodo periodoAtual(Trilha t) {
		Periodo atual = null;
		t.setPeriodos((ArrayList) new DAOPeriodo().readById(t.getIdTrilha()));
		for (Periodo p : t.getPeriodos()) {
			LocalDate.now();
			if ( (LocalDate.now().isBefore(p.getDataFinal()) || LocalDate.now().isEqual(p.getDataFinal())) && 
					(LocalDate.now().isAfter(p.getDataInicial()) || LocalDate.now().isEqual(p.getDataInicial())) ){
				atual = p;
			}else{
				if(p.getDescricao().equals(DescricaoPeriodo.RESULTADO_FINAL)) {
					atual=p;
				}
			}
		}
		return atual;
	}
	
	public static void EnviarEmail(Trabalho t) {
		if(t != null) {
			String msg = "";
			t.getAutores().add(t.getAutor());
			Email e = new Email();
			if (!t.getAutores().isEmpty()) {
				for (Usuario u : t.getAutores()){
					msg = "Prezado "+u.getNome() +",\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"Seu trabalho "+ t.getTitulo() +" para o evento "+t.getTrilha().getEvento().getNome()+" foi submetido com sucesso na trilha "+t.getTrilha().getNome()+".\r\n" + 
							"Agradecemos a sua participação!\r\n" + 
							"\r\n" + 
							"E-mail automático, não responda.\r\n" + 
							"\r\n" + 
							"Sistema SARA -  Submissção Avaliação e Revisãoo de Artigos\r\n" + 
							"Por: Núcleo de Soluçõees em Software - N2S\r\n" + 
							"\r\n" + 
							"Núcleo de Soluçõees em Software- N2S";
					e.sendEmail("SubmissÃ£o de trabalho - SARA- Submissão, Avaliação e Revisão de Artigos", msg, u.getEmail(), u.getNome());
				}
			}
		}
		
	}
	
	public static Usuario buscarUsuarioGuardiao(String cpf){
		PessoaDAO pDAO = DAOFactory.criarPessoaDAO();
		Pessoa p = pDAO.buscarPorCpf(cpf);
		if (p==null) {
			return null;
		}else {
			Usuario usuario = new Usuario();
			usuario.setNome(p.getNome());
			usuario.setCpf(p.getCpf());
			usuario.setEmail(p.getEmail());
			usuario.setTipo(NivelUsuario.AUTOR);
			return usuario;
		}
		
	}
	
	public static boolean isAvaliador(int idTrilha, String cpf) {
		AvaliaTrilha av = new AvaliaTrilha();
		av = new DAOAvaliaTrilha().getAvaliaTrilha(cpf, idTrilha);
		if (av != null && av.getAvaliador().getCpf().equals(cpf) && av.getTrilha().getIdTrilha() == idTrilha)
			return true;
		return false;
	}
	public static boolean isCoordenador(int idEvento, String cpf) {
		Evento evento = new Evento();
		evento = new DAOEvento().getEvento(idEvento);
		if (evento != null) {
			for (Usuario u : evento.getCoordenadores()) {
				if (u.getCpf() != null && u.getCpf().equals(cpf))
					return true;
			}
		}			
		return false;
	}
	public static boolean isCoordenadorTrilha(int idTrilha, String cpf) {
		Trilha trilha = new Trilha();
		trilha = new DAOTrilha().getTrilha(idTrilha);
		if (trilha != null) {
			for (Usuario u : trilha.getCoordenadores()) {
				if (u.getCpf() != null && u.getCpf().equals(cpf))
					return true;
			}
		}			
		return false;
	}
	
	public static Evento pegarEventoPeloId(int idEvento) {
		DAOEvento daoEvento = new DAOEvento();
		DAOCoordenacaoEvento daoCoorEvento = new DAOCoordenacaoEvento();
		DAOTrilha daoTrilha = new DAOTrilha();
		Evento evento = daoEvento.getEvento(idEvento);
		if (evento!=null) {
			evento.setCoordenadores(daoCoorEvento.ListarCoordenadores(idEvento));
			evento.setTrilhas(daoTrilha.readById(idEvento));
		}	
		return evento;
	}
	
	public static Integer getQuantidadePessoasPorNome(String nome) {
		DAOUsuario daoUsuario = new DAOUsuario();
		DAOUsuarioSemCadastro daoUsuarioS = new DAOUsuarioSemCadastro();
		int i;
		i = daoUsuario.getQuantidadePorNome(nome);
		//i += daoUsuarioS.getQuantidadePorNome(nome);
		
		return i;
	}
	
	public static List<Usuario> buscarPessoasPorNome (String nome, int inicio, int fim){
		List<Usuario> usuarios = null;
		DAOUsuario daoUsuario = new DAOUsuario();
		usuarios = daoUsuario.buscarPorNome(nome, inicio, fim);
		return usuarios;
	}
	

}
