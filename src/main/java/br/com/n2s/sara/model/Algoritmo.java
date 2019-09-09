package br.com.n2s.sara.model;

import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Random;

public class Algoritmo {
	
	private int vertices;
	private int rGrafo [][];
	private int grafo [][];	
	public void setV(int v) {
		this.vertices=v;
	}
	public int getV() {
		return vertices;
	}
	public void setGrafo(int grafo [][]) {
		this.grafo=grafo;
	}
	public void setRGrafo(int rGrafo [][]) {
		this.rGrafo=rGrafo;
	}
	public int [][] getRGrafo() {
		return rGrafo;
	}
	public int [][] getGrafo() {
		return grafo;
	}
		
	
	boolean bfs(int rGraph[][], int s, int t, int parent[]) 
	{ 
		// Create a visited array and mark all vertices as not 
		// visited 
		boolean visited[] = new boolean[vertices]; 
		for(int i=0; i<vertices; ++i) 
			visited[i]=false; 

		// Create a queue, enqueue source vertex and mark 
		// source vertex as visited 
		LinkedList<Integer> queue = new LinkedList<Integer>(); 
		queue.add(s); 
		visited[s] = true; 
		parent[s]=-1; 

		// Standard BFS Loop 
		while (queue.size()!=0) 
		{ 
			int u = queue.poll(); 

			for (int v=0; v<vertices; v++) 
			{ 
				if (visited[v]==false && rGraph[u][v] > 0) 
				{ 
					queue.add(v); 
					parent[v] = u; 
					visited[v] = true; 
				} 
			} 
		} 

		// If we reached sink in BFS starting from source, then 
		// return true, else false 
		return (visited[t] == true); 
	} 
	public int fordFulkerson(int graph[][], int s, int t) { 
		int u, v; 

		// Create a residual graph and fill the residual graph 
		// with given capacities in the original graph as 
		// residual capacities in residual graph 

		// Residual graph where rGraph[i][j] indicates 
		// residual capacity of edge from i to j (if there 
		// is an edge. If rGraph[i][j] is 0, then there is 
		// not) 
		int rGraph[][] = new int[vertices][vertices]; 

		for (u = 0; u < vertices; u++) 
			for (v = 0; v < vertices; v++) 
				rGraph[u][v] = graph[u][v]; 

		// This array is filled by BFS and to store path 
		int parent[] = new int[vertices]; 

		int max_flow = 0; // There is no flow initially 

		// Augment the flow while there is path from source 
		// to sink 
		while (bfs(rGraph, s, t, parent)) 
		{ 
			// Find minimum residual capacity of the edges 
			// along the path filled by BFS. Or we can say 
			// find the maximum flow through the path found. 
			int path_flow = Integer.MAX_VALUE; 
			for (v=t; v!=s; v=parent[v]) 
			{ 
				u = parent[v]; 
				path_flow = Math.min(path_flow, rGraph[u][v]); 
			} 

			// update residual capacities of the edges and 
			// reverse edges along the path 
			for (v=t; v != s; v=parent[v]) 
			{ 
				u = parent[v]; 
				rGraph[u][v] -= path_flow; 
				rGraph[v][u] += path_flow; 
			} 
			// Add path flow to overall flow 
			max_flow += path_flow; 
		} 
		// Return the overall flow
		rGrafo = rGraph;
		File f2 = new File("C:\\Users\\Fernando Willian\\Desktop\\Dist\\graficoResidual.txt");
		try{
		   if(!f2.exists())
		f2.createNewFile();
		   FileWriter out = new FileWriter(f2);   
			for (u = 0; u < vertices; u++) {
				for (v = 0; v < vertices; v++) {
					out.append(String.valueOf(rGraph[u][v])+"|");
				}
				out.append('\n');
			}
				out.close();
				}catch(Throwable e){
					e.printStackTrace();
				}
		return max_flow; 
	} 
	private void Roleta (int grafo [][], int fator, int tamanho, int diferenca, int qtdTrabalho) {
		Random gerador = new Random();
//		int a = gerador.nextInt(Preparacao.avaliadores.size()/2);*/
//		int a = diferenca;
//		for (int i=qtdTrabalho+1;i<tamanho-1;i++) {
//			grafo[i][tamanho-1]= fator;
//			if (i-qtdTrabalho ==a) break;
//		}
		int a;
		for (int i=0;i<diferenca;i++) {
			a = ( int ) (gerador.nextInt(tamanho-qtdTrabalho-2) + 1+qtdTrabalho);
			grafo[a][tamanho-1] = fator;
		}
	}

	public void executar (int totalTrabalhos, int totalAvaliadores, int numCorrecoes) { 
		
			float a = (totalTrabalhos * numCorrecoes)/totalAvaliadores;
			int fatorCorrecao = Math.round(a);
			int tamanho = totalAvaliadores+totalTrabalhos+2;

					
			//Algoritmo m = new Algoritmo();
			//long start = System.currentTimeMillis();
			int max = fordFulkerson(this.grafo, 0, tamanho-1);
			//int sobrecarga = totalTrabalhos- max;
			System.out.println("O fluxo maximo é " + max);
			System.out.println("O fluxo necessário é "+totalTrabalhos*numCorrecoes);
			if (max<totalTrabalhos*numCorrecoes) {	
				while (max<totalTrabalhos*numCorrecoes) {
					fatorCorrecao ++;
					System.out.println("---------------------------------------------");
					System.out.println("Fator aumentando em 1");
					System.out.println("---------------------------------------------");
					Roleta(this.grafo, fatorCorrecao, tamanho, totalTrabalhos*numCorrecoes-max,totalTrabalhos);
					max = fordFulkerson(this.grafo, 0, tamanho-1);
				}
			}
			//long elapsed = System.currentTimeMillis() - start;
			//System.out.println("O fluxo maximo é " + max);
			//System.out.println("O fluxo necessário é "+totalTrabalhos*numCorrecoes);
			//System.out.println("O tempo de execução foi: "+elapsed);
			
	}

}
