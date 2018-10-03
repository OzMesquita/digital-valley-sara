package br.com.n2s.sara.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import br.com.n2s.sara.dao.DAOPeriodo;
import br.com.n2s.sara.dao.DAOTrilha;
import br.com.n2s.sara.dao.DAOUsuario;
import br.com.n2s.sara.model.DescricaoPeriodo;
import br.com.n2s.sara.model.Periodo;
import br.com.n2s.sara.model.Trabalho;
import br.com.n2s.sara.model.Trilha;
import br.com.n2s.sara.model.Usuario;
import model.Email;
import util.Constantes;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


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
		for (Periodo p : t.getPeriodos()) {
			if ( (LocalDate.now().isBefore(p.getDataFinal()) || LocalDate.now().isEqual(p.getDataFinal())) && (LocalDate.now().isAfter(p.getDataInicial()) || LocalDate.now().isEqual(p.getDataInicial())) ){
				atual = p;
				break;
			}else{
				if(p.getDescricao().equals(DescricaoPeriodo.RESULTADO_FINAL )) {
					atual=p;
					break;
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
							"Seu trabalho "+ t.getTitulo() +" para o evento "+t.getTrilha().getEvento().getNome()+" foi submetido com sucesso na "+t.getTrilha().getNome()+".\r\n" + 
							"Agradecemos a sua participação!\r\n" + 
							"\r\n" + 
							"E-mail automático, não responda.\r\n" + 
							"\r\n" + 
							"Sistema SARA -  Submissão Avaliação e Revisão de Artigos\r\n" + 
							"Por: Núcleo de Soluções em Software - N2S\r\n" + 
							"\r\n" + 
							"(logo do Sara e do N2S)";
					e.sendEmail("Submissão de trabalho - SARA- Submissão, Avaliação e Revisão de Artigos", msg, u.getEmail(), u.getNome());
				}
			}
		}
		
	}

}
