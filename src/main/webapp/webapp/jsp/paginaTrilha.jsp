<%@page import="br.com.n2s.sara.dao.DAOTrilha"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="br.com.n2s.sara.model.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sara</title>
    </head>
    <body>
    <center>
        <% 
        Trilha trilha;
        Evento evento;
        	if(session.getAttribute("trilha")!=null){
            	 trilha = (Trilha) session.getAttribute("trilha");
        		 evento = (Evento) session.getAttribute("evento");
        	}else{
        		DAOTrilha daoTrilha = new DAOTrilha();
        		int idTrilha = Integer.parseInt(request.getParameter("idTrilha"));
        		trilha = daoTrilha.getTrilha(idTrilha);
        		
        	}
        	
        %>
        
        <h2><%= trilha.getNome()%></h2>
        <p><%= trilha.getDescricao()%></p>
        
        <form action="SalvarArquivo" method="post" enctype="multipart/form-data">
            <p>Título: </p>
            <p><input type="text" name="titulo" size="80"></p>
            <p>Resumo:</p> 
            <p><textarea name="resumo" cols="80" rows="15" maxlength="1000"></textarea> </p>
            <p>Palavras-chave: (Separe por vígula)</p>
            <p><input type="text" name="palavras_chave" size="80"></p>
            <button type="submit">Submeter</button>
            <input type="button" value="Voltar" onClick="history.go(-1)">
            
        
        <br/>
        <>
        	<input type="file" name="trabalho">
        	<input type="submit" value="enviar">
        </form>
    </center>
    </body>
</html>
