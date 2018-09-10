<%@page import="br.com.n2s.sara.model.Trilha"%>
<%@page import="br.com.n2s.sara.model.AvaliaTrilha"%>
<%@page import="br.com.n2s.sara.model.NivelUsuario"%>
<%@page import="br.com.n2s.sara.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
			
			
            <form action="CadastrarAvaliador" method="post">
	            <p>Nome: <input type="text" name="nome"> </p>
	            <p>Sobrenome: <input type="text" name="sobrenome"> </p>
	            <p>CPF: <input type="text" name="cpf"></p>
	            <p>Email: <input type="email" name="email"> </p>
	            <input type="submit">
	            <input type="button" value="Voltar" onClick="history.go(-1)">
	        </form>

</body>
</html>