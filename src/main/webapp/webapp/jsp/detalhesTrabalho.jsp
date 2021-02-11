<%@page import="br.com.n2s.sara.util.Facade"%>
<%@page import="java.util.List"%>
<%@page import="br.com.n2s.sara.dao.DAOSubmissao"%>
<%@page import="br.com.n2s.sara.dao.DAOAvaliaTrabalho"%>
<%@page import="br.com.n2s.sara.dao.DAOTrabalho"%>
<%@page import="br.com.n2s.sara.dao.DAOEvento"%>
<%@page import="br.com.n2s.sara.dao.DAOTrilha"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="br.com.n2s.sara.model.*" %>
<%@page import="br.com.n2s.sara.util.Constantes"%>
	<% 
		int idTrabalho = Integer.parseInt(request.getParameter("idTrabalho")); 	
		Trabalho trabalho = new DAOTrabalho().getTrabalho(idTrabalho);
		session.setAttribute("trabalho", trabalho);
		
		AvaliaTrabalho avaliaTrabalho = null;
		avaliaTrabalho = new DAOAvaliaTrabalho().getAvaliaTrabalho(idTrabalho);
		Periodo atual = Facade.periodoAtual(trabalho.getTrilha());
    %>
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
		  <div class="row">
				<div class="col-lg-12">
					<h3 class="page-header"><i class="fa fa-table"></i> Submissão</h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="indexAutor.jsp">Home</a></li>
						<li><i class="icon_document_alt"></i>Submissão</li>
					</ol>
				</div>
			</div>
 			<%if(session.getAttribute(Constantes.getSESSION_MGS()) != null){ %>
				<div class="alert alert-success" role="alert">	
					<%=session.getAttribute(Constantes.getSESSION_MGS()) %>
					<%session.setAttribute(Constantes.getSESSION_MGS(), null); %>
				</div>
			<%} %>
			<%if(session.getAttribute(Constantes.getSESSION_MGS_ERROR()) != null){ %>
				<div class="alert alert-danger" role="alert">
					<%=session.getAttribute(Constantes.getSESSION_MGS_ERROR()) %>
					<%session.setAttribute(Constantes.getSESSION_MGS_ERROR(), null); %>
				</div>
			<%} %>     
      <!-- page start-->
              
              <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                          <header class="panel-heading">
                              Submissão
                          </header>
                        <table class="table table-striped table-advance table-hover">
	                        <tbody>
				                    
				                   	<tr>
				                    	<td>
									        <form action="submissaoFinal.jsp" id="form" method="post" style="margin-bottom: 5px;" >
									         	
									         	
									         	<h4>Título</h4>
									         	<p><%= trabalho.getTitulo() %></p>
									            
									            <% if (!trabalho.getResumo().equals("")) { %>
									           	
									           		<h4>Resumo/Abstract</h4>
									            	<p> <textarea cols="100" rows="10" disabled="disabled"><%= trabalho.getResumo()%></textarea></p>
									            
									            <% } %>

												<% if (!trabalho.getPalavrasChaves().equals("")) { %>									            	
									            	
									            	<h4>Palavras-chave</h4> 
									           		<p> <input type="text" size="100" value="<%= trabalho.getPalavrasChaves() %>" disabled="disabled"></p>
									           	
									           	 <% } %>
									           	 <%if (trabalho.getAutores() != null && trabalho.getAutores().size()>0){ %>
									           	 	<h4>Coautor(es)</h4>
									           	 	<%for(Usuario u : trabalho.getAutores()){ %> 
									           			<%-- <p> <input type="text" size="100" value="<%= u.getNome() %>" disabled="disabled"></p> --%>
									           	 <%}} %>
									           	<%if (avaliaTrabalho != null) {%>									           	
									           		<h4>Feedback do Avaliador</h4>
									           		<p> <textarea cols="100" rows="10" disabled="disabled"><%= avaliaTrabalho.getFeedback()%></textarea></p>
									           	<%} %>
									           	
									           	<h4>Status: <%= trabalho.getStatus() %> </h4> 
									            
									            <% 
									             if (atual!=null && atual.getDescricao() == DescricaoPeriodo.SUBMISSAO_FINAL) 
									            		if ( trabalho.getStatus()==StatusTrabalho.ACEITO || trabalho.getStatus()==StatusTrabalho.ACEITO_FINAL) { %>
									            
								          			<input type="hidden" name="idTrilha" value="<%= trabalho.getTrilha().getIdTrilha() %>" />
									           		<input type="hidden" name="idEvento" value="<%= trabalho.getTrilha().getEvento().getIdEvento() %>" />
									            	<input type="hidden" name="idTrabalho" value="<%= trabalho.getIdTrabalho()%>" />
									            	<button class="btn btn-primary" type = "submit">Submeter Versão Final</button>
									            
									            <% }  %>
									            <br><br>
									            </form>
									            <form action="DownloadTrabalho" method="post">
	               					 				<input type="hidden" value="<%= trabalho.getIdTrabalho() %>" name="idTrabalho"><input type="hidden" value="inicial" name="opcaoDownload">  
	                  								<button class="btn btn-primary" type = "submit" style="min-width: 350px;">Download da versão inicial do Trabalho</button>
	               					 			</form>
	               					 		
	               					 		<% if(trabalho.getEndereco()!=null){%>
	               					 		
									        	<form action="DownloadTrabalho" method="post" style="margin-top: 5px;" >                  					 
		                   							<input type="hidden" value="<%= trabalho.getIdTrabalho() %>" name="idTrabalho"><input type="hidden" value="AAAAAA" name="opcaoDownload"> 
		                  							<button class="btn btn-primary" type = "submit" style="min-width: 350px;">Download versão final do Trabalho</button>
		               					 		</form>
	               					 			<br>
	               					 		<%} %>
	               					 		<% 
	               					 		if(atual.getDescricao()==DescricaoPeriodo.RECURSO && (trabalho.getStatus()==StatusTrabalho.REJEITADO 
	               					 				|| trabalho.getStatus()==StatusTrabalho.REJEITADO_ORIENTADOR)){%>
	               					 			<form action="paginaDeSubmissao.jsp" method="post" onsubmit="return confirm('Deseja reenviar este trabalho?');"> 
			                   						<input type="hidden" value="<%= trabalho.getTrilha().getIdTrilha()%>" name="idTrilha">
			                   						<input type="hidden" value="<%= trabalho.getTrilha().getEvento().getIdEvento()%>" name="idEvento"> 
			                   						<input type="hidden" value="<%= trabalho.getIdTrabalho()%>" name="idTrabalho">  
			                  						<button class="btn btn-primary" type = "submit">Recurso</button>
               					 				</form> 	
	               					 		<%}%>
	               					 		<%if( trabalho.getOrientador()!=null
	               					 				&& usuario.getCpf().equals(trabalho.getOrientador().getCpf())
	               					 				&& trabalho.getStatus()==StatusTrabalho.ENVIADO 
	               					 				&& (atual.getDescricao()==DescricaoPeriodo.AVAL || atual.getDescricao()==DescricaoPeriodo.SUBMISSAO_MANUSCRITO ) ) {%>
	               					 			<h2>Aval do orientador:</h2>
	               					 			<p>O trabalho pode seguir para avaliação?</p>
	               					 			<form action="Aval" method="post">
	               					 				<input type="radio" id="aceito" name="resultado" value="aceito" required><label for="aceito">Aceito</label>
	               					 				<input type="radio" id="recusado" name="resultado" value="recusado"><label for="recusado">Recusado</label>
	               					 				<input type="hidden" value="<%= trabalho.getIdTrabalho() %>" name="idTrabalho"> 
	               					 				<input type="submit" name="Avaliar" value="Avaliar">
	               					 			</form>	
	               					 		<%} %>
								        	<form action="ApagarTrabalho" method="post" >                  					 
	                   							<input type="hidden" value="<%= trabalho.getIdTrabalho() %>" name="idTrabalho"> 
	                   						</form>	 
				                   		</td>
				                   </tr>
	                       </tbody>
	                   </table>
                     </section>
                  </div>
              </div>
         </section>
         
              <!-- page end-->
  </section>
</section>
</body>
</html>
  <!-- container section start -->