<%@page import="br.com.n2s.sara.model.Trilha"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%-- 
    Document   : adicionaPeriodo
    Created on : 18/04/2018, 11:18:16
    Author     : Hugo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sara</title>
    </head>
    <body>
        <% 
        	Trilha trilha = (Trilha) session.getAttribute("trilha");
        	session.setAttribute("trilha", trilha);
            LocalDate time = LocalDate.now();
            String dia_atual = time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        %>
    <center>		  
        <form action="confirmaPeriodo.jsp" method="POST">
            <p>Tipo: <select name="descricao">
					  <option value="SUBMISSAO_MANUSCRITO">Período de Submissão</option>
					  <option value="AVALIACAO">Período de Avaliaçâo</option>
					  <option value="SUBMISSAO_FINAL">Período de Submissôes Finais</option>
					  <option value="RESULTADO_FINAL">Divulgaçâo de Resultados</option>
					</select>
            <p>Data de Inicio: <input type="date" name="dataInicial" required> </p>
            <p>Data de Fim: <input type="date" name="dataFinal" required> </p>
            <input type="submit" value="Adicionar">
            <input type="button" value="Voltar" onClick="history.go(-1)">
            
        </form>
        
    </center>
    </body>
</html>
