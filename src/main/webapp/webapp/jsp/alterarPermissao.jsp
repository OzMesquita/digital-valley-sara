 <!--main content start-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="br.com.n2s.sara.dao.DAOUsuario"%>
<%@page import="java.util.ArrayList"%>
<section id="main-content">
          <section class="wrapper">
		  <div class="row">
				<div class="col-lg-12">
					<h3 class="page-header"><i class="fa fa-table"></i> Alterar Permissões de Usuário </h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="indexAutor.jsp">Home</a></li>
						<li><i class="icon_document_alt"></i>Alterar Permissões</li>
					</ol>
				</div>
			</div>
      
      <!-- page start-->
      
      	<%	 
			
		if ( session.getAttribute("feedbackSucesso") != null){ %>
			<div class="alert alert-success" role="alert">
  				<%= session.getAttribute("feedbackSucesso") %>
			</div>
				
		<% } 
			session.setAttribute("feedbackSucesso", null);	
		%>
              <%ArrayList<Usuario> usuarios = new ArrayList<Usuario>() ;
              DAOUsuario userDAo = new DAOUsuario();
              usuarios = (ArrayList<Usuario>) userDAo.read();
              %>
              <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                          <header class="panel-heading">
                              Editar permissões 
                          </header>
                          
                          <table class="table table-striped table-advance table-hover">
                           <tbody>
                              <tr>                               
                                 <th><i class="icon_documents_alt"></i>Usuário</th>
                                 
                                 <th><i class="icon_document_alt"></i>Permissão </th>
                                 <th></th>
                                 <th></th>
                              </tr>
                              <%-- <%for(Usuario u : usuarios) {%> --%>
                              <%pageContext.setAttribute("usuarios", usuarios);%>
                              <c:forEach items="${usuarios}" var="u"> 
                              <form method="POST" id="permissao-user${u.email}" action="atualizaPermissao">                            
                              <tr>
                              		<td><c:out value="${u.nome}"></c:out></td>
                              		<input type="hidden" value="${u.cpf}" name="codUsuario">
                              		
                              		<td>
                              			<input class="form-check-input" type="checkbox" value="1" onclick="submeteAtualizacao('permissao-user${u.email}')" name="codPermissao" ${ (fn:contains(u.tipo, 'AUTOR') ? 'checked' : '' )} >Autor &nbsp;&nbsp; 
                              			<input class="form-check-input" type="checkbox" value="2" onclick="submeteAtualizacao" name="codPermissao"  ${ (fn:contains(u.tipo, 'AVALIADOR') ? 'checked' : '' )} >Avaliador&nbsp;&nbsp;
                              			<input class="form-check-input" type="checkbox" value="3" onclick="submeteAtualizacao" name="codPermissao" ${ (fn:contains(u.tipo, 'COORDENADOR_TRILHA') ? 'checked' : '' )} >Coordenador de trilha&nbsp;&nbsp;
                              			<input class="form-check-input" type="checkbox" value="4" onclick="submeteAtualizacao" name="codPermissao" ${ (fn:contains(u.tipo, 'COORDENADOR_EVENTO') ? 'checked' : '' )} >Coordenador de evento&nbsp;&nbsp;
                              			<input class="form-check-input" type="checkbox" value="5" onclick="submeteAtualizacao" name="codPermissao" ${ (fn:contains(u.tipo, 'ADMINISTRADOR') ? 'checked' : '' )} >Administrador&nbsp;&nbsp;
                              			<input class="form-check-input" type="checkbox" value="6" onclick="submeteAtualizacao" name="codPermissao" ${ (fn:contains(u.tipo, 'USUARIO') ? 'checked' : '' )} >Usuario&nbsp;&nbsp;
                              		</td>
                              </tr>
                              </c:forEach>
                         	<%-- <%} %> --%>
                           </tbody>
                        </table>
                      </section>
                  </div>
              </div>
			
              
              <!-- page end-->
            <script>
            function submeteAtualizacao(formSelecionado){
                document.getElementById(formSelecionado).submit();
            }
            </script>
  </section>
</section>