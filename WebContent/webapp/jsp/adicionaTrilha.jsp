<%@page import="br.com.n2s.sara.model.Usuario"%>
<%@page import="br.com.n2s.sara.model.Evento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
	<%
		Evento evento = (Evento) session.getAttribute("evento");
		session.setAttribute("evento", evento);
		Usuario user = (Usuario) session.getAttribute("usuario");
		
	%>
	<form action="confirmaTrilha.jsp" method="post">
		Nome: <input type="text" name="nome">
		Descri��o: <input type="text" name="descricao">
		<input type="submit" value="Criar">
	</form>
</center>
</body>
</html>