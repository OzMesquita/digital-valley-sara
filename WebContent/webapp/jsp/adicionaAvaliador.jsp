<%@page import="br.com.n2s.sara.model.Trilha"%>
<%@page import="br.com.n2s.sara.model.Usuario"%>
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
    		Trilha trilha = (Trilha) session.getAttribute("trilha");
    		session.setAttribute("trilha", trilha);
    		
    		
    		
    	%>
        <form action="CadastrarAvaliador" method="POST">
            <p>Buscar por CPF:</p>
            <p>CPF: <input type="text" name="cpfAvaliador" required></p>
            
            <input type="submit" value="Buscar">
        </form>
        <p/><input type="button" value="Voltar" onClick="history.go(-1)">
    </center>
    </body>
</html>
