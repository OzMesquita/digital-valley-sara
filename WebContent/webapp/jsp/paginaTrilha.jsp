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
            HttpSession secao = session;
            String nome = request.getParameter("trilha");
            Trilha trilha = (Trilha) secao.getAttribute(nome);
            ArrayList<Usuario> autores = (ArrayList<Usuario>) session.getAttribute("autores");
            session.setAttribute("autores", autores);
        %>
        <h2><%= trilha.getNome()%></h2>
        <p><%= trilha.getDescricao()%></p>
        
        <form action="submterTrabalho.jsp" method="post">
            <p>Título: </p>
            <p><input type="text" name="titulo" size="80"></p>
            <p>Resumo:</p> 
            <p><textarea name="resumo" cols="80" rows="15" maxlength="1000"></textarea> </p>
            
            <form action="buscarAutor.jsp">
            	<p>Autores: </p>
            	<p><%if(autores != null) 
            			for(Usuario user : autores){ 
            				System.out.println(user.getNome()); %> 
            				<form action="retirarAutor.jsp" method="post">
            					<input type="submit" value="Remover">
            				</form>	
            			<%
            			} %> 
            		
            	</p>
            	<p>Busca por CPF: <input type="text" name="cpf"> </p>
            	<input type="submit" value="Buscar">
            </form>
            
            <p>Palavras-chave: (Separe por vígula)</p>
            <p><input type="text" name="palavras_chave" size="80"></p>
            <button type="submit">Submeter</button>
            <input type="button" value="Voltar" onClick="history.go(-1)">
            
        </form>
        
    </center>
    </body>
</html>
