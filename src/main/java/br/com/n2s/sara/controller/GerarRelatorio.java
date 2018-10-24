package br.com.n2s.sara.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.*;

import br.com.n2s.sara.model.Usuario;
import br.com.n2s.sara.model.Trilha;
import br.com.n2s.sara.model.Evento;
import br.com.n2s.sara.model.StatusTrabalho;
import br.com.n2s.sara.model.Trabalho;
import br.com.n2s.sara.dao.DAOEvento;
import br.com.n2s.sara.dao.DAOSubmissao;
import br.com.n2s.sara.dao.DAOTrabalho;
import br.com.n2s.sara.dao.DAOTrilha;
import br.com.n2s.sara.util.Constantes;
import util.Facade;

/**
 * Servlet implementation class GerarRelatorio
 */
@WebServlet("/GerarRelatorio")
public class GerarRelatorio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Evento evento = new DAOEvento().getEvento(Integer.parseInt(request.getParameter("idEvento")));
		evento.setTrilhas(new DAOTrilha().readById(evento.getIdEvento()));
		String tipoRelatorio = request.getParameter("tipoRelatorio");
		File arquivo = new File(Constantes.getTEMP_DIR()+File.separator+"relatorio_de_submissoes_"+evento.getNome()+".pdf");
		try {
			//preparou o pdf
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(arquivo.getAbsolutePath()));
			document.open();

			// cabecalho
				// logo
				Image image = Image.getInstance(Constantes.getLOGO_UFC());
				image.setAlignment(Image.MIDDLE);
				image.scaleAbsoluteWidth(90);
				image.scaleAbsoluteHeight(60);
				document.add(image);
				Paragraph cabecalho = new Paragraph("UNIVERDADE FEDERAL DO CEARÁ \n"
						+ "CAMPUS RUSSAS \n\n" + evento.getNome() + "\n\n\n");
				cabecalho.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(cabecalho);
				Font bold = new Font(FontFamily.UNDEFINED, 12, Font.BOLD, BaseColor.BLACK);
				Font normal = new Font(FontFamily.UNDEFINED, 12, Font.NORMAL, BaseColor.BLACK);
				//Conteudo
				if(tipoRelatorio == "relatorioInicial"){
		            for (Trilha trilha: evento.getTrilhas()) {
		            	PdfPTable t = new PdfPTable(3);
			            PdfPCell cell = new PdfPCell();
			            cell.setBorder(PdfPCell.NO_BORDER);
			            cell.addElement(image);
			            t.addCell(cell);
			            PdfPCell cell1 = new PdfPCell();
			            cell1.setBorder(PdfPCell.NO_BORDER);
			            cell1.setColspan(2);
			            normal.setColor(10, 10, 10);
			            normal.setSize(15);
			            t.addCell(cell1);
			           // document.add(t);
			            normal.setSize(10);
			            PdfPTable table = new PdfPTable(2);
		            	int i = 1;
		            	ArrayList<Trabalho> trabalhos = new DAOTrabalho().readTrilha(trilha.getIdTrilha());
		            	Paragraph tituloTrilha = new Paragraph(trilha.getNome().toUpperCase() +"- QUANTIA DE TRABALHOS ENVIADOS: "+ trabalhos.size() + "\n\n");
		            	tituloTrilha.setAlignment(Paragraph.ALIGN_CENTER);
		            	document.add(tituloTrilha);
			            PdfPCell coluna1 = new PdfPCell(new Paragraph("Titulo", normal));
			            coluna1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			            coluna1.setHorizontalAlignment(Element.ALIGN_CENTER);
			            PdfPCell coluna2 = new PdfPCell(new Paragraph("Autores", normal));
			            coluna2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			            coluna2.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.setWidths(new int[]{450, 200});
			            table.addCell(coluna1);
			            table.addCell(coluna2);
			            //Percorrer as trilhas
			            for (Trabalho trabalho: trabalhos) {
			            	table.addCell(i+" - "+trabalho.getTitulo().toUpperCase());
			            	String nomeAutor = "";
			            	if (trabalho.getAutor() != null) {	
			            		nomeAutor = trabalho.getAutor().getNome().toUpperCase();
			            	}
			            	for (Usuario u : trabalho.getAutores()) {
			            		if(u != null) {
			            			if(u.getNome()=="" || u.getNome()==null) {
			            				Usuario usuario = br.com.n2s.sara.util.Facade.buscarUsuarioGuardiao(u.getCpf());
			            				if (usuario == null) {
			            					nomeAutor = nomeAutor + ", "+ usuario.getNome(); 
			            				}else {
			            					nomeAutor = nomeAutor + ", "+usuario.getCpf();
			            				}
			            			}else {
			            				nomeAutor = nomeAutor + ", " + u.getNome().toUpperCase();
			            			}
			            		}
			            	}
			            	PdfPCell autores = new PdfPCell(new Paragraph(nomeAutor));
			            	autores.setHorizontalAlignment(Element.ALIGN_CENTER);
			            	table.addCell(autores);
			            	i++;
			            }
			            document.add(table);
		            }
				}else if (tipoRelatorio=="relatorioFinal") {
		            for (Trilha trilha: evento.getTrilhas()) {
		            	PdfPTable t = new PdfPTable(3);
			            PdfPCell cell = new PdfPCell();
			            cell.setBorder(PdfPCell.NO_BORDER);
			            cell.addElement(image);
			            t.addCell(cell);
			            PdfPCell cell1 = new PdfPCell();
			            cell1.setBorder(PdfPCell.NO_BORDER);
			            cell1.setColspan(2);
			            normal.setColor(10, 10, 10);
			            normal.setSize(15);
			            t.addCell(cell1);
			           // document.add(t);
			            normal.setSize(10);
			            PdfPTable table = new PdfPTable(2);
		            	int i = 1;
		            	ArrayList<Trabalho> trabalhos = new DAOTrabalho().readTrilha(trilha.getIdTrilha());
		            	Paragraph tituloTrilha = new Paragraph(trilha.getNome().toUpperCase() +"- QUANTIA DE TRABALHOS ENVIADOS: "+ trabalhos.size() + "\n\n");
		            	tituloTrilha.setAlignment(Paragraph.ALIGN_CENTER);
		            	document.add(tituloTrilha);
			            PdfPCell coluna1 = new PdfPCell(new Paragraph("Titulo", normal));
			            coluna1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			            coluna1.setHorizontalAlignment(Element.ALIGN_CENTER);
			            PdfPCell coluna2 = new PdfPCell(new Paragraph("Autores", normal));
			            coluna2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			            coluna2.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.setWidths(new int[]{450, 200});
			            table.addCell(coluna1);
			            table.addCell(coluna2);
			            //Percorrer as trilhas
			            for (Trabalho trabalho: trabalhos) {
			            	if(trabalho.getStatus()== StatusTrabalho.ACEITO) {
			            		table.addCell(i+" - "+trabalho.getTitulo().toUpperCase());
				            	String nomeAutor = "";
				            	if (trabalho.getAutor() != null) {	
				            		nomeAutor = trabalho.getAutor().getNome().toUpperCase();
				            	}
				            	for (Usuario u : trabalho.getAutores()) {
				            		if(u != null) {
				            			if(u.getNome()=="" || u.getNome()==null) {
				            				Usuario usuario = br.com.n2s.sara.util.Facade.buscarUsuarioGuardiao(u.getCpf());
				            				if (usuario == null) {
				            					nomeAutor = nomeAutor + ", "+ usuario.getNome(); 
				            				}else {
				            					nomeAutor = nomeAutor + ", "+usuario.getCpf();
				            				}
				            			}else {
				            				nomeAutor = nomeAutor + ", " + u.getNome().toUpperCase();
				            			}
				            		}	
				            	}
				            	PdfPCell autores = new PdfPCell(new Paragraph(nomeAutor));
				            	autores.setHorizontalAlignment(Element.ALIGN_CENTER);
				            	table.addCell(autores);
				            	i++;
			            	}
			            	}
			            document.add(table);
			            }			            
				}		
				
				document.close();				
				
				Path path = arquivo.toPath();
		        
		        String nome = arquivo.getName();
		        int tamanho = (int) arquivo.length();
		
		        response.setContentType(Files.probeContentType(path)); // tipo do conte�do
		        response.setContentLength(tamanho);  // opcional
		        response.setHeader("Content-Disposition", "attachment; filename=\"" + nome + "\"");
		        OutputStream output = response.getOutputStream();
		        Files.copy(path, output);
				
			} catch (DocumentException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("ERRO ERRO ERRO");
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
				System.out.println("ERRO ERRO ERRO");
			}			

		}
	
	

}
