package br.com.n2s.sara.util;

import br.com.n2s.sara.dao.DAOAvaliaEvento;
import br.com.n2s.sara.dao.DAOTrabalho;
import br.com.n2s.sara.model.AvaliaTrabalho;
import br.com.n2s.sara.model.Evento;
import br.com.n2s.sara.model.StatusTrabalho;
import br.com.n2s.sara.model.Trabalho;
import br.com.n2s.sara.model.Trilha;
import br.com.n2s.sara.model.Usuario;
import br.com.n2s.sara.model.Algoritmo;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;


public class InterfaceAlgoritmo {
	
	private InterfaceAlgoritmo interfaceA;

	public InterfaceAlgoritmo getInterfaceA() {
		return interfaceA;
	}

	public void setInterfaceA(InterfaceAlgoritmo interfaceA) {
		this.interfaceA = interfaceA;
	}
	
	public static List<AvaliaTrabalho> distribuirPorTrilha(Trilha t, int numCorrecoes){
		ArrayList<AvaliaTrabalho> distribuidos = new ArrayList<AvaliaTrabalho>();
		
		ArrayList<Usuario> avaliadores = t.getAvaliadores();
		ArrayList<Trabalho> trabalhos = new DAOTrabalho().readTrilha(t.getIdTrilha());		
		
		int grafo [][] = new int [trabalhos.size()+avaliadores.size()+2][trabalhos.size()+avaliadores.size()+2];
		
		float a = (trabalhos.size() * t.getQtdCorrecoes())/avaliadores.size();
		int fatorCorrecao = Math.round(a);
		int tamanho = avaliadores.size()+trabalhos.size()+2;
		
		//int grafo [][] = new int [p.tamanhoMatriz()][p.tamanhoMatriz()];
		System.out.println(tamanho + " Avaliadores: "+avaliadores.size()+" trabalhos: "+trabalhos.size()+" Media="+a);
		
		//Criando o grafo zerado
		for (int i=0;i<tamanho;i++) {
			for(int j=0;j<tamanho;j++) {
				grafo[j][i]=0;
			}
		}
		
		//preenchendo a primeira linha
		for (int i=1;i<=trabalhos.size();i++) {
			grafo[0][i]=t.getQtdCorrecoes();
		}
		//Primeira linha foi preenchida, Preenchendo a ultima coluna 
		for (int i=trabalhos.size()+1;i<tamanho;i++) {
			grafo[i][tamanho-1]= fatorCorrecao;
		}
		//preenchendo a linha onde so existe se o tabalho não pertencer aquele autor
		for (int i=1; i<=trabalhos.size();i++) {//contador da linha, inicia na linha 1 e vai até igual aos total de trabalhos
			for(int j = trabalhos.size()+1;j<=tamanho-2;j++) {
				int posA = j-trabalhos.size()-1, posT = i-1;
				if(verficaAutores(avaliadores.get(posA).getCpf(),trabalhos.get(posT))) {
					grafo[i][j]=0;
				}else {
					grafo[i][j]=1;
				}
			}
		}
		//preenchendo com zero a ultima linha
		for (int i=0;i<tamanho;i++) {
			grafo[tamanho-1][i]=0;
		}

		File f = new File("C:\\Users\\Fernando Willian\\Desktop\\Dist\\GrafoEntrada.txt");
		try{
		   if(!f.exists())
		f.createNewFile();
		   FileWriter out = new FileWriter(f);   
		   for (int i = 0; i < tamanho; i++) {
					for (int j = 0; j < tamanho; j++) {
						out.append(String.valueOf(grafo[i][j])+"|");
					}
					out.append('\n');
				}
				out.close();
				}catch(Throwable e){
					e.printStackTrace();
				}
		Algoritmo instAlgoritmo = new Algoritmo();
		instAlgoritmo.setGrafo(grafo);
		instAlgoritmo.setV(tamanho);
		instAlgoritmo.executar(trabalhos.size(), avaliadores.size(), numCorrecoes);
		int rgraph [][]=instAlgoritmo.getRGrafo();
		//Falta terminar de implementar pq até aqui ele executa, falta remover e pegar o grafo e transformar  novamente em duas listas. 
		//Transformando o grafo em Lista dnv
		for (int i=0; i<=avaliadores.size()-1;i++) {//contador da linha, inicia na linha 1 e vai até igual aos total de trabalhos
			for(int j=1;j<=trabalhos.size();j++) {//contador da linha, inicia na linha 1 e vai até igual aos total de trabalhos
				int posA = i+trabalhos.size()+1, posT = j;
				if(rgraph[posA][posT]!=0) {
					AvaliaTrabalho av = new AvaliaTrabalho();
					av.setAvaliador(avaliadores.get(i));
					trabalhos.get(j-1).setStatus(StatusTrabalho.EM_AVALIACAO);
					av.setTrabalho(trabalhos.get(j-1));
					av.setStatus(StatusTrabalho.EM_AVALIACAO);
					distribuidos.add(av);
				}
			}
		}
		
		return distribuidos;
	}
	
	public static List<AvaliaTrabalho> distribuPorTrilhaComOrientador(Trilha t, int numCorrecoes){
		ArrayList<AvaliaTrabalho> distribuidos = new ArrayList<AvaliaTrabalho>();
		
		ArrayList<Usuario> avaliadores = t.getAvaliadores();
		ArrayList<Trabalho> trabalhos = new DAOTrabalho().readTrilha(t.getIdTrilha(), StatusTrabalho.ACEITO_ORIENTADOR);		
		
		int grafo [][] = new int [trabalhos.size()+avaliadores.size()+2][trabalhos.size()+avaliadores.size()+2];
		
		float a = (trabalhos.size() * t.getQtdCorrecoes())/avaliadores.size();
		int fatorCorrecao = Math.round(a);
		int tamanho = avaliadores.size()+trabalhos.size()+2;
		
		//int grafo [][] = new int [p.tamanhoMatriz()][p.tamanhoMatriz()];
		System.out.println(tamanho + " Avaliadores: "+avaliadores.size()+" trabalhos: "+trabalhos.size()+" Media="+a);
		
		//Criando o grafo zerado
		for (int i=0;i<tamanho;i++) {
			for(int j=0;j<tamanho;j++) {
				grafo[j][i]=0;
			}
		}
		
		//preenchendo a primeira linha
		for (int i=1;i<=trabalhos.size();i++) {
			grafo[0][i]=t.getQtdCorrecoes();
		}
		//Primeira linha foi preenchida, Preenchendo a ultima coluna 
		for (int i=trabalhos.size()+1;i<tamanho;i++) {
			grafo[i][tamanho-1]= fatorCorrecao;
		}
		//preenchendo a linha onde so existe se o tabalho não pertencer aquele autor
		for (int i=1; i<=trabalhos.size();i++) {//contador da linha, inicia na linha 1 e vai até igual aos total de trabalhos
			for(int j = trabalhos.size()+1;j<=tamanho-2;j++) {
				int posA = j-trabalhos.size()-1, posT = i-1;
				if(verficaAutores(avaliadores.get(posA).getCpf(),trabalhos.get(posT))) {
					grafo[i][j]=0;
				}else {
					grafo[i][j]=1;
				}
			}
		}
		//preenchendo com zero a ultima linha
		for (int i=0;i<tamanho;i++) {
			grafo[tamanho-1][i]=0;
		}

		File f = new File("C:\\Users\\Fernando Willian\\Desktop\\Dist\\GrafoEntrada.txt");
		try{
		   if(!f.exists())
		f.createNewFile();
		   FileWriter out = new FileWriter(f);   
		   for (int i = 0; i < tamanho; i++) {
					for (int j = 0; j < tamanho; j++) {
						out.append(String.valueOf(grafo[i][j])+"|");
					}
					out.append('\n');
				}
				out.close();
				}catch(Throwable e){
					e.printStackTrace();
				}
		Algoritmo instAlgoritmo = new Algoritmo();
		instAlgoritmo.setGrafo(grafo);
		instAlgoritmo.setV(tamanho);
		instAlgoritmo.executar(trabalhos.size(), avaliadores.size(), numCorrecoes);
		int rgraph [][]=instAlgoritmo.getRGrafo();
		//Falta terminar de implementar pq até aqui ele executa, falta remover e pegar o grafo e transformar  novamente em duas listas. 
		//Transformando o grafo em Lista dnv
		for (int i=0; i<=avaliadores.size()-1;i++) {//contador da linha, inicia na linha 1 e vai até igual aos total de trabalhos
			for(int j=1;j<=trabalhos.size();j++) {//contador da linha, inicia na linha 1 e vai até igual aos total de trabalhos
				int posA = i+trabalhos.size()+1, posT = j;
				if(rgraph[posA][posT]!=0) {
					AvaliaTrabalho av = new AvaliaTrabalho();
					av.setAvaliador(avaliadores.get(i));
					trabalhos.get(j-1).setStatus(StatusTrabalho.EM_AVALIACAO);
					av.setTrabalho(trabalhos.get(j-1));
					av.setStatus(StatusTrabalho.EM_AVALIACAO);
					distribuidos.add(av);
				}
			}
		}
		
		return distribuidos;
	}
	
	/*
	 * public static List<AvaliaTrabalho> distribuirPorEvento(Evento e, int
	 * numCorrecoes){ ArrayList<AvaliaTrabalho> distribuidos = new
	 * ArrayList<AvaliaTrabalho>(); ArrayList<Usuario> avaliadores =
	 * (ArrayList<Usuario>) new DAOAvaliaEvento().buscarPorEvento(e.getIdEvento());
	 * ArrayList<Trabalho> trabalhos; for(Trilha t : e.getTrilhas()) { trabalhos =
	 * new DAOTrabalho().readTrilha(t.getIdTrilha()); }
	 * 
	 * int grafo [][] = new int
	 * [trabalhos.size()+avaliadores.size()+2][trabalhos.size()+avaliadores.size()+2
	 * ];
	 * 
	 * float a = (trabalhos.size() * t.getQtdCorrecoes())/avaliadores.size(); int
	 * fatorCorrecao = Math.round(a); int tamanho =
	 * avaliadores.size()+trabalhos.size()+2;
	 * 
	 * //int grafo [][] = new int [p.tamanhoMatriz()][p.tamanhoMatriz()];
	 * System.out.println(tamanho +
	 * " Avaliadores: "+avaliadores.size()+" trabalhos: "+trabalhos.size()+" Media="
	 * +a);
	 * 
	 * //Criando o grafo zerado for (int i=0;i<tamanho;i++) { for(int
	 * j=0;j<tamanho;j++) { grafo[j][i]=0; } }
	 * 
	 * //preenchendo a primeira linha for (int i=1;i<=trabalhos.size();i++) {
	 * grafo[0][i]=t.getQtdCorrecoes(); } //Primeira linha foi preenchida,
	 * Preenchendo a ultima coluna for (int i=trabalhos.size()+1;i<tamanho;i++) {
	 * grafo[i][tamanho-1]= fatorCorrecao; } //preenchendo a linha onde so existe se
	 * o tabalho não pertencer aquele autor for (int i=1; i<=trabalhos.size();i++)
	 * {//contador da linha, inicia na linha 1 e vai até igual aos total de
	 * trabalhos for(int j = trabalhos.size()+1;j<=tamanho-2;j++) { int posA =
	 * j-trabalhos.size()-1, posT = i-1;
	 * if(verficaAutores(avaliadores.get(posA).getCpf(),trabalhos.get(posT))) {
	 * grafo[i][j]=0; }else { grafo[i][j]=1; } } } //preenchendo com zero a ultima
	 * linha for (int i=0;i<tamanho;i++) { grafo[tamanho-1][i]=0; } Algoritmo
	 * instAlgoritmo = new Algoritmo(); instAlgoritmo.setGrafo(grafo);
	 * instAlgoritmo.executar(trabalhos.size(), avaliadores.size(), numCorrecoes);
	 * int rgraph [][]=instAlgoritmo.getRGrafo(); //Falta terminar de implementar pq
	 * até aqui ele executa, falta remover e pegar o grafo e transformar novamente
	 * em duas listas. //Transformando o grafo em Lista dnv
	 * 
	 * for(int i=trabalhos.size()+1;i< avaliadores.size();i++) { for(int
	 * j=0;j<tamanho;j++) { if (rgraph[i][j]==1) { AvaliaTrabalho av = new
	 * AvaliaTrabalho(); av.setAvaliador(avaliadores.get(i));
	 * av.setTrabalho(trabalhos.get(i)); distribuidos.add(av); } } }
	 * 
	 * return distribuidos; }
	 */	
	private static boolean verficaAutores(String v, Trabalho t) {
		if(v.equals(t.getAutor().getCpf())) return true;
		if(t.getOrientador() != null && v.equals(t.getOrientador().getCpf()) ) return true;
		if (t.getAutores() != null) {
			for(Usuario i : t.getAutores()) {
				if (i.getCpf().equals(v)) { 
					return true;
				}
			}
		}
		return false;
	}

}
