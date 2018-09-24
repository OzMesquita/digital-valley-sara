package br.com.n2s.sara.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import br.com.n2s.sara.dao.DAOPeriodo;
import br.com.n2s.sara.dao.DAOTrilha;
import br.com.n2s.sara.dao.DAOUsuario;
import br.com.n2s.sara.model.DescricaoPeriodo;
import br.com.n2s.sara.model.Periodo;
import br.com.n2s.sara.model.Trilha;
import br.com.n2s.sara.model.Usuario;
import util.Constantes;


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
}
