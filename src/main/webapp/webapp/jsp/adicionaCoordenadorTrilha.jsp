<%@page import="br.com.n2s.sara.model.CoordenacaoTrilha"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Adicione um Coordenador de Trilha<\h1>

<%
		CoordenacaoTrilha coordenador = new CoordenacaoTrilha() ;
	/* 	Usuario user = (Usuario) session.getAttribute("usuarioSara");  so entra aqui se já tiver autorização */
		
	%>
	<form action="transformaUsuarioCoordTrilha.jsp" method="post">
		<p>Nome: <input type="text" name="nome"></p>
		<p>CPF: <input type="text" name="cpf"></p>

		<p><input type="submit" value="AddCoordTrilha">
		
		<input type="button" value="Voltar" onClick="history.go(-1)">
	</form>
	
	
</center>
</body>
</html>