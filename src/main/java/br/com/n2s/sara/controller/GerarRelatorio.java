package br.com.n2s.sara.controller;

import java.io.FileOutputStream;
import java.io.IOException;
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
import br.com.n2s.sara.model.Trabalho;
import br.com.n2s.sara.dao.DAOEvento;
import br.com.n2s.sara.dao.DAOSubmissao;
import br.com.n2s.sara.dao.DAOTrabalho;
import br.com.n2s.sara.dao.DAOTrilha;
import br.com.n2s.sara.util.Constantes;

/**
 * Servlet implementation class GerarRelatorio
 */
@WebServlet("/GerarRelatorio")
public class GerarRelatorio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Evento evento = new DAOEvento().getEvento(Integer.parseInt(request.getParameter("evento")));
		
		try {
			//preparou o pdf
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(Constantes.getARTICLES_DIR()));
			document.open();

			// cabecalho
				// logo
				Image image = Image.getInstance(Constantes.getLOGO_UFC());
				image.setAlignment(Image.MIDDLE);
				image.scaleAbsoluteWidth(90);
				image.scaleAbsoluteHeight(60);
				document.add(image);
				Paragraph cabecalho = new Paragraph("UNIVERDADE FEDERAL DO CEARÁ \n"
						+ "CAMPUS RUSSAS \n" + evento.getNome() + "\n");
				cabecalho.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(cabecalho);
				Font bold = new Font(FontFamily.UNDEFINED, 12, Font.BOLD, BaseColor.BLACK);
				Font normal = new Font(FontFamily.UNDEFINED, 12, Font.NORMAL, BaseColor.BLACK);
			//Conteudo
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
	            
	            for (Trilha trilha: evento.getTrilhas()) {
	            	Paragraph tituloTrilha = new Paragraph(trilha.getNome().toUpperCase() +"- Trabalhos submetidos"+ evento.getTrilhas().size());
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
		            ArrayList<Trabalho> trabalhos = new DAOTrabalho().readTrilha(trilha.getIdTrilha()); 
		            for (Trabalho trabalho: trabalhos) {
		            	table.addCell(trabalho.getTitulo().toUpperCase());

		            	String nomeAutor = trabalho.getAutor().getNome().toUpperCase();
		            	for (Usuario u : trabalho.getAutores()) {
		            		nomeAutor = nomeAutor + "; " + u.getNome().toUpperCase();
		            	}
		            	PdfPCell autores = new PdfPCell(new Paragraph(nomeAutor));
		            	autores.setHorizontalAlignment(Element.ALIGN_CENTER);
		            	table.addCell(autores);
		            }
		            	
		            
		        	/*for (int i=0;i<resultado.size();i++) {
						Object[] participante = resultado.get(i);
						table.addCell(((ParticipanteBeans) participante[0]).getCandidato().getNome().toUpperCase());
						
						PdfPCell resu  = new PdfPCell(new Paragraph(participante[2].toString().toLowerCase()));
						resu.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(resu);
		                //table.addCell(participante[2].toString().toLowerCase());
					}*/
		            document.add(table);
	            
	            }
	            Paragraph assAluno = new Paragraph(
	            		"\n\n(Assinatura(s) do(s) Responsável(is))");
				assAluno.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(assAluno);
				document.close();				
				
				System.out.println("tudo certo aqui");
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
