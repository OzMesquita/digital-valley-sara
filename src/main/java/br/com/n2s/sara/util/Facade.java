package br.com.n2s.sara.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import br.com.n2s.sara.dao.DAOUsuario;
import br.com.n2s.sara.model.Usuario;


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
			FileReader arquivo = new FileReader(Constantes.getDATABASE_CONF_DIR());
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
	
	
}
