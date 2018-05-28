<%@page import="br.com.n2s.sara.model.CriterioTrilha"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="br.com.n2s.sara.controller.CriterioTrilhaController"%>
<%@page import="br.com.n2s.sara.model.Trilha"%>
<%@page import="br.com.n2s.sara.controller.CriterioController"%>
<%@page import="br.com.n2s.sara.model.Criterio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
        <%
			//PEGAR TODOS OS CRITERIOS CADASTRADOS DA TRILHA
			
			Trilha trilha = (Trilha) session.getAttribute("trilha");
        	session.setAttribute("trilha", trilha);
			
			CriterioController critCon = new CriterioController();
			CriterioTrilhaController criterioTrilhaController = new CriterioTrilhaController();
			
        	List<Criterio> criterios = critCon.listar();
      		List<CriterioTrilha> listCriterioTrilha = criterioTrilhaController.listar();
        	
      		if(criterios.isEmpty()){%>
        	
        	<h1>Selecionar Crit�rio Existente</h1>
        	<table border="1">
            <tr>
                <th>Crit�rios Existentes</th>
            </tr>
	<% 
			for(int i = 0; i < listCriterioTrilha.size(); i++){
                
               session.setAttribute("critTri"+listCriterioTrilha.get(i).getIdCriterioTrilha(), listCriterioTrilha.get(i));
               %>
               
               <tr>
                   <td><%=listCriterioTrilha.get(i).getNome()%></td>
                   <td><form action="visualizarCriterio.jsp" method="post"> 
                           <input type="hidden" value="critTri"+<%= listCriterioTrilha.get(i).getIdCriterioTrilha()%>" name="criterioTrilha"> 
                           <button type="submit">Visualizar</button>
                       </form> 
                   </td>
                   <td><form action="selecionarCriterioTrilha.jsp" method="post"> 
                           <input type="hidden" value="critTri"+<%= listCriterioTrilha.get(i).getIdCriterioTrilha()%>" name="criterioTrilha"> 
                           <button type="submit">Selecionar</button>
                       </form> 
                   </td>
            <%}
            }%>
       
			</table>
			
       <% //	}else{
        %>
        
        <h1>Criar Novos Crit�rios</h1>
        
        <%if(!criterios.isEmpty()){ %>
        <table border="1">
            <tr>
                <th>Crit�rios</th>
            </tr>
	<% 
            for(int i=0; i < criterios.size(); i++){
                
               session.setAttribute("crit"+criterios.get(i).getIdCriterio(), criterios.get(i));
               %>
               
               <tr>
                   <td><%=criterios.get(i).getDescricao()%></td>
                   <td><form action="alterarCriterio.jsp" method="post"> 
                           <input type="hidden" value="crit<%= criterios.get(i).getIdCriterio()%>" name="criterio"> 
                           <button type="submit">Alterar</button>
                       </form> 
                   </td>
                   <td><form action="removerCriterio.jsp" method="post"> 
                           <input type="hidden" value="crit<%= criterios.get(i).getIdCriterio()%>" name="criterio"> 
                           <button type="submit">Remover</button>
                       </form> 
                   </td>
                   
            <%}%>
       <% } %>
	</table>
    	
    	<br />
    	
       	<p>
        <form action="adicionarCriterio.jsp" method="post">
            <input type="submit" name="addCri" value="Adicionar Crit�rio">
        </form>
        </p>
        
        <br />
        
       	<br /> <br /> <br />
       	
		<input type="button" onclick="location.href='indexAutor';" value="Voltar"/>        
    </center>
	
</body>
</html>