<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gerenciar Crit�rios de Avalia��o</title>
</head>
<body>
	
	<center>
		
		<p>
        <form action="adicionarCriterio.jsp" method="post">
            <input type="submit" name="addCri" value="Adicionar Crit�rio">
        </form>
        </p>
        
        <p>
        <form action="alterarCriterio.jsp" method="post">
            <input type="submit" name="altCri" value="Alterar Crit�rio">
        </form>
        </p>
        
        <p>
        <form action="removerCriterio.jsp" method="post">
            <input type="submit" name="remCri" value="Remover Crit�rio">
        </form>
        </p>
        
    </center>
	
</body>
</html>