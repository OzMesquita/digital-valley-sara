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
            Usuario user = (Usuario) session.getAttribute("usuario");
            String chave = request.getParameter("geEvento");
            Evento evento = (Evento) session.getAttribute(chave);
        %>
        <table border="1" >
        <tr>
            <th>Trilha</th>
            <th>Descrição</th>
            <th>Data</th>
        </tr>
        <% 
            for(int i=0; i < evento.getTrilhas().size(); i++){ 
                if(evento.getTrilhas().get(i).getCoordenadores().get(i).equals(user.getCpf()) ){
                
                session.setAttribute("gt"+Integer.toString(evento.getTrilhas().get(i).getIdTrilha()), evento.getTrilhas().get(i));
               %>
               
               <tr>
                   
                   <td><%= evento.getTrilhas().get(i).getNome() %> </td> 
                   <td>Descrição</td> 
                   <td>Data</td>
                   <td> <form action="manterTrilha.jsp" method="post"> 
                           <input type="hidden" value="gt<%= evento.getTrilhas().get(i).getIdTrilha()%>" name="trilha"> 
                           <button type="submit">pressione</button>
                       </form> 
                   </td>
                   
               </tr>
               
            <%  }
            }
        %>    
    </table>
    <input type="button" onclick="location.href='indexCoordTrilha';" value="Voltar"/>
    </center>
    </body>
</html>
