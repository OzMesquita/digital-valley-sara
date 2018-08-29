<%-- <%@page import="br.com.n2s.sara.controller.EventoController"%> --%>
<%@page import="java.util.List"%>
<%-- <%@page import="br.com.n2s.sara.controller.TrilhaController"%> --%>
<%@page import="br.com.n2s.sara.dao.*"%>
<%@page import="br.com.n2s.sara.controller.*"%>
<%@page import="br.com.n2s.sara.model.*" %>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    
<head>
    <style>
    table{    
        border-spacing: 10px; 
    }
    </style>
    
    <!-- http-equiv meta tag metadados que caracterizam o objeto 
     HTTP-Equiv são equivalentes aos cabeçalhos http. Normalmente 
     elas controlam as ações dos browsers e podem ser usadas para melhor especificar as informações.
	 Tags usadas desta forma têm um efeito equivalente quando sendo usadas como um cabeçalho HTTP.-->
    
    
    
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sara</title>
</head>
<body>
     <% 	
    
    
    	UsuarioController userCon = new UsuarioController(); //cria um controler
    	Usuario usuario = (Usuario) session.getAttribute("usuario");  //recebe o cpf, vai na sessao e procura o usuário

    	
    	
    	//cria um usuario a partir dos dados da sessao
    	
    	
	%>
    

<center> Bem Vindo!  
    <% 
    
    	
    	EventoController evCon = new EventoController();
		CoordenacaoTrilhaController coordtrCon = new CoordenacaoTrilhaController();
		int idTrilha = Integer.parseInt(request.getParameter("estaTrilha"));//pega na requisicao
		DAOTrilha recebeTrilha = new DAOTrilha();
		Trilha trilha = new Trilha();
		trilha = recebeTrilha.getTrilha(idTrilha);
        List<Usuario> listCoordTrilha = trilha.getCoordenadores();
        Evento ev = (Evento)session.getAttribute("evento");
        //cria um evCon que traz info do banco
        //cria lista de eventos que vem do banco pelo controller
       
    %>
    
        
        <table>
            <tr>
                <th>Coordenadores Trilha</th>
                
            </tr>
<tr>
	<td>
	<table border="1">
            <tr>
                <th>Nome</th>
                <th>Sobrenome</th>
                <th>E-mail</th>
            </tr>
	<% 
            for(int i=0; i <listCoordTrilha.size(); i++){
                
                
               %>
               
               <tr>
                   <td><%= listCoordTrilha.get(i).getNome()  %></td>
                   <td><%= listCoordTrilha.get(i).getSobrenome() %> </td>
                   <td><%= listCoordTrilha.get(i).getEmail() %> </td>
                   
                   <!-- DELETAR -->
                   <td><form action="delete" method="post"> 
                   
                   <% switch(usuario.getTipo()){
                   
                   case ADMINISTRADOR: %>



									<%
										case COORDENADOR_EVENTO:
									%>
									<td><form action="removerCoordenadorTrilha.jsp"
											method="post"
											onsubmit="return confirm('Deseja remover este critério?');> 
                           <input type="hidden" value="<%= listCoordTrilha.get(i).getCpf()%>" name="delCoord"> 
                           <button type="submit">Remover</button>
                       </form> 
                   </td>
                   <%case COORDENADOR_TRILHA: %>
                   <input type="button" onclick="";
											value="del">
											<td><form action="removerCoordenadorTrilha.jsp"
													method="post"
													onsubmit="return confirm('Deseja remover este critério?');> 
                           <input type="hidden" value="<%= listCoordTrilha.get(i).getCpf()%>" name="delCoord"> 
                           <button type="submit">Remover</button>
                       </form> 
                   </td>
                   <%case AVALIADOR: %>
                    <input type="button" onclick="";
													value="del">
													<td><form action="removerCoordenadorTrilha.jsp"
															method="post"
															onsubmit="return confirm('Deseja remover este critério?");> 
                           <input type="hidden" value="<%=listCoordTrilha.get(i).getCpf()%>" name="delCoord"> 
                           <button type="submit">Remover</button>
                       </form> 
                   	</td>
                   	
                   
                  <%}

			}%>
        			<tr> 
   				     		
<a href="adicionaCoordenadorTrilha.jpg"> <button value="Deletado com sucesso."></a>       
    
	               </tr>
    </center>          
	</table>
</body>
</html>