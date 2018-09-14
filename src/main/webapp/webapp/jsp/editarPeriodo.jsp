<%@page import="br.com.n2s.sara.dao.DAOPeriodo"%>
<%@page import="br.com.n2s.sara.model.Trilha"%>
<%@page import="br.com.n2s.sara.model.Periodo"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sara</title>
    </head>
    <body>
        <% 
        	Trilha trilha = (Trilha) session.getAttribute("trilha");
        	String idPeriodo = request.getParameter("idPeriodo");
        	Periodo periodo = new DAOPeriodo().getPeriodo(Integer.parseInt(idPeriodo));
        	session.setAttribute("periodo", periodo);
        	
        	LocalDate time = LocalDate.now();
            String dia_atual = time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        %>
    <center>		  
        <form action="EditarPeriodo" method="POST">
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
