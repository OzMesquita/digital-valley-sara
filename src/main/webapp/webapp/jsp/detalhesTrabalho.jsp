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
		
		AvaliaTrabalho avaliaTrabalho = new DAOAvaliaTrabalho().getAvaliaTrabalho(idTrabalho);
		List<Usuario> coAutores = new ArrayList<Usuario>(); 
		coAutores = new DAOSubmissao().getAutores(idTrabalho);
		Periodo atual = Facade.periodoAtual(trabalho.getTrilha());
    %>
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
		  <div class="row">
				<div class="col-lg-12">
					<h3 class="page-header"><i class="fa fa-table"></i> Submiss√£o</h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="indexAutor.jsp">Home</a></li>
						<li><i class="icon_document_alt"></i>Submiss√£o</li>
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
                              Submiss„o
                          </header>
                        <table class="table table-striped table-advance table-hover">
	                        <tbody>
				                    
				                   	<tr>
				                    	<td>
									        <form action="submissaoFinal.jsp" id="form" method="post" >
									         	
									         	
									         	<h4>TÌtulo:</h4>
									         	<p><%= trabalho.getTitulo() %>"</p>
									            
									            <% if (!trabalho.getResumo().equals("")) { %>
									           	
									           		<h4>Resumo/Abstract</h4>
									            	<p> <textarea cols="100" rows="10" disabled="disabled"><%= trabalho.getResumo()%></textarea></p>
									            
									            <% } %>

												<% if (!trabalho.getPalavrasChaves().equals("")) { %>									            	
									            	
									            	<h4>Palavras-chave</h4> 
									           		<p> <input type="text" size="100" value="<%= trabalho.getPalavrasChaves() %>" disabled="disabled"></p>
									           	
									           	 <% } %>
									           	<%if (avaliaTrabalho != null) {%>									           	
									           		<h4>Feedback do Avaliador</h4>
									           		<p> <textarea cols="100" rows="10" disabled="disabled"><%= avaliaTrabalho.getFeedback()%></textarea></p>
									           	<%} %>
									           	
									           	<h4>Status: <%= trabalho.getStatus() %> </h4> 
									            
									            <% 
									             if ( atual.getDescricao().equals(DescricaoPeriodo.SUBMISSAO_FINAL) && 
									            		( trabalho.getStatus().equals(StatusTrabalho.ACEITO) || trabalho.getStatus().equals(StatusTrabalho.ACEITO_FINAL))) { %>
									            
								          			<input type="hidden" name="idTrilha" value="<%= trabalho.getTrilha().getIdTrilha() %>" />
									           		<input type="hidden" name="idEvento" value="<%= trabalho.getTrilha().getEvento().getIdEvento() %>" />
									            	<input type="hidden" name="idTrabalho" value="<%= trabalho.getIdTrabalho()%>" />
									            	<button class="btn btn-primary" type = "submit">Submeter Vers„o Final</button>
									            
									            <% }  %>
									            <br><br>
									            </form>
									            <form action="DownloadTrabalho" method="post">
	               					 	<input type="hidden" value="<%= trabalho.getIdTrabalho() %>" name="idTrabalho"><input type="hidden" value="inicial" name="opcaoDownload">  
	                  							<button class="btn btn-primary" type = "submit">Download da vers„o inicial do Trabalho</button>
	               					 		</form>
	               					 		
	               					 		<% if(trabalho.getEndereco()!=null){%>
	               					 		
								        	<form action="DownloadTrabalho" method="post" >                  					 
	                   							<input type="hidden" value="<%= trabalho.getIdTrabalho() %>" name="idTrabalho"><input type="hidden" value="AAAAAA" name="opcaoDownload"> 
	                  							<button class="btn btn-primary" type = "submit">Download vers„o final do Trabalho</button>
	               					 		</form>
	               					 		<br>
	               					 		<%} %>
								        	<form action="ApagarTrabalho" method="post" >                  					 
	                   							<input type="hidden" value="<%= trabalho.getIdTrabalho() %>" name="idTrabalho">  
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
  <!-- container section start -->