<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="br.com.n2s.sara.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../css/centralizar.css" rel="stylesheet" />
<title>Avaliação de Trabalhos</title>
</head>
<body>

	<% 
    	String trab = request.getParameter("trabalho");
        Trabalho trabalho = (Trabalho) session.getAttribute(trab);
    %>
	
	<center>
		<h1><%=trabalho.getTitulo()%></h1>
		
		<div id="resumo">
			<p><h2>Resumo</h2></p>
			<%=trabalho.getResumo() %>
		</div>
		
		<br />
		<br />
		
		<!-- PEGAR PDF DO BANCO DE DADOS -->
		<a href="teste.txt" download="teste.txt"><button>Baixar Trabalho</button></a>
		
		<div id="criterios">
			<h2>Critérios de Avaliação</h2>
							
		<%	
			//PEGAR CRITERIOS ESTABELECIDOS PARA ESTA TRILHA
			ArrayList<ArrayList<String>> criterios = new ArrayList<ArrayList<String>>();
			
			ArrayList<String> itens = new ArrayList<String>();
			itens.add("Muito irrelevante");
			itens.add("Irrelevante");
			itens.add("Regular");
			itens.add("Relevante");
			itens.add("Muito relevante");
			
			criterios.add(itens);
			
			for(ArrayList<String> criterio : criterios){ %>
				<h3>Criterio X</h3> 
				<form action="enviarAvaliacao.jsp">
			<%	for(String item : criterio){ %>
					<input type="radio" name="criterio" value=<%=item %> /> <%=item %>
			<%	}
				out.println();
			}%>
				<br />
				<br />	
		</div>
		
		<div id="observacoes">
			<h2>Observações</h2>
			<textarea name="observacoes" rows="10" cols="75"></textarea>		
			<br />
			<br />
		</div>
		
			<button type="submit">Enviar Avaliação</button>
			<input type="button" onclick="location.href='indexAvaliador.jsp';" value="Cancelar"/>
			</form>
		
	</center>

</body>
</html>