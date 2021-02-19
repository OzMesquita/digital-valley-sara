<%@page import="br.com.n2s.sara.dao.DAOEvento"%>
<%@page import="br.com.n2s.sara.dao.DAOTrilha"%>
<%@ page import="br.com.n2s.sara.model.*"%>
<%@page import="br.com.n2s.sara.util.Constantes"%>
<%
     	
     		String idEvento = request.getParameter("idEvento");
     		if(idEvento == null){
     			response.sendRedirect("indexAutor.jsp");
     		}else{     		
     	
     		DAOEvento daoEvento = new DAOEvento();
     		DAOTrilha daoTrilha = new DAOTrilha();
     	     		
     		Evento evento = Facade.pegarEventoPeloId(Integer.parseInt(idEvento));
     		evento.setTrilhas(daoTrilha.readById(evento.getIdEvento()));
	        
     		session.setAttribute("usuarioSara", usuario);
	        session.setAttribute("evento", evento);
     	%>

<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<!--<div class="row"> -->
			<!--<div class="col-lg-12">-->
				<h3 class="page-header">
					<i class="fa fa-table"></i> Trilhas Coordenadas - Evento
					<%=evento.getNome() %>
				</h3>
				<ol class="breadcrumb">
					<li><i class="fa fa-home"></i><a href="indexAutor.jsp">Home</a></li>
					<li><i class="icon_document_alt"></i>Trilhas Coordenadas</li>
				</ol>
			<!--</div>-->
		<!--</div> -->

		<!-- page start-->

		<%	 
			
		if ( session.getAttribute("feedbackSucesso") != null){ %>
		<div class="alert alert-success" role="alert">
			<%= session.getAttribute("feedbackSucesso") %>
		</div>

		<% } 
			session.setAttribute("feedbackSucesso", null);	
		%>
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
		<!--<div class="row"> -->
			<!--<div class="col-lg-12"> -->
				<section class="panel">
					<header class="panel-heading"> Trilhas Coordenadas </header>

					<table class="table table-striped table-advance table-hover">
						<tbody>
							<tr>
								<th><i class="icon_documents_alt"></i> Trilha</th>
								<th><i class="icon_document_alt"></i> Descrição</th>
								<th></th>
								<th></th>
								<th></th>
							</tr>

							<%     
			       			for(int i = 0; i < evento.getTrilhas().size(); i++){
			                
			               %>

							<tr>

								<td><%= evento.getTrilhas().get(i).getNome() %></td>
								<td><%= evento.getTrilhas().get(i).getDescricao() %></td>
								<td>
									<form action="gerenciarTrilha.jsp" method="post">
										<input type="hidden"
											value="<%= evento.getTrilhas().get(i).getIdTrilha()%>"
											name="idTrilha">
										<button class="btn btn-primary" type="submit">
											Alterar Dados</button>
									</form>
								</td>
								<td><form action="Distribuir" method="post">
										<input type="hidden"
											value="<%=evento.getTrilhas().get(i).getIdTrilha() %>"
											name="idTrilha">
										<button class="btn btn-primary" type="submit">Distribuir
											Trabalhos</button>
									</form></td>
								<% 
						            if(usuario.getTipo().equals(NivelUsuario.COORDENADOR_EVENTO) || usuario.getTipo().equals(NivelUsuario.ADMINISTRADOR)	){
			        			%>
								<!--  
			        			<td>
			        				<form action="gerenciarCoordenadoresTrilha.jsp" method="post">
			        					<input type="hidden" value="" name="idTrilha">
							            <button class="btn btn-primary" type = "submit"> Gerenciar Coordenadores</button>
							        </form>
							    </td>
							    -->
								<td>
									<form action="RemoverTrilha" method="post"
										onsubmit="return confirm('Deseja remover esta trilha?');">
										<input type="hidden"
											value="<%= evento.getTrilhas().get(i).getIdTrilha()%>"
											name="idTrilha">
										<button class="btn btn-primary" type="submit">Remover
											Trilha</button>
									</form>
								</td>

								<% 
							        }
							    %>

							</tr>

							<%  
			            }
			        %>


						</tbody>
					</table>
				</section>
			<!--</div> -->
		<!--</div> -->

		<%
					if(evento.isCoordenador(usuario) || usuario.getTipo().equals(NivelUsuario.ADMINISTRADOR)){ %>

		<table class="table table-striped table-advance table-hover">
			<tr>
				<td>
					<form action="adicionarTrilha.jsp" method="post">
						<button class="btn btn-primary" type="submit">Adicionar
							Trilha</button>
					</form>
				</td>
				<td>
					<form action="gerenciarCoordenadoresEvento.jsp" method="post">
						<button class="btn btn-primary" type="submit">Gerenciar
							Coordenadores do Evento</button>
					</form>
				</td>
				<td>
					<form action="GerarRelatorio" method="post">
						<input type="hidden" value="<%= evento.getIdEvento()%>"
							name="idEvento"> <input type="hidden"
							value="relatorioInicial" name="tipoRelatorio">
						<button class="btn btn-primary" type="submit">Gerar
							Relatório de Trabalhos Submetidos</button>
					</form>
				</td>
				<td>
					<form action="GerarRelatorio" method="post">
						<input type="hidden" value="<%= evento.getIdEvento()%>"
							name="idEvento"> <input type="hidden"
							value="relatorioFinal" name="tipoRelatorio">
						<button class="btn btn-primary" type="submit">Gerar
							Relatório Final</button>
					</form>
				</td>
				<td>
					<form action="GerarRelatorio" method="post">
						<input type="hidden" value="<%= evento.getIdEvento()%>"
							name="idEvento"> <input type="hidden"
							value="relatorioAceito" name="tipoRelatorio">
						<button class="btn btn-primary" type="submit">Gerar
							Relatório de Aceitos</button>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<form action="relacaoDeTrabalhos.jsp" method="post">
						<select name="statusTrabalho" class="form-control">
							<%for (StatusTrabalho e : StatusTrabalho.values()){%>
							<option value="<%=e.ENVIADO.toString()%>"><%=e.getDescricao()%></option>
							<%--   								<option value="<%= StatusTrabalho.ENVIADO.toString()%>">Enviado</option> --%>
							<%--   								<option value="<%= StatusTrabalho.EM_AVALIACAO.toString()%>">Em Avaliação</option> --%>
							<%--   								<option value="<%= StatusTrabalho.ACEITO.toString()%>">Aceito</option> --%>
							<%--   								<option value="<%= StatusTrabalho.EM_AVALIACAO.toString()%>">Rejeitado</option> --%>
							<%--   								<option value="<%= StatusTrabalho.SUBMISSAO_FINAL.toString()%>">Submissao Final</option> --%>
							<%--   								<option value="<%= StatusTrabalho.ACEITO_FINAL.toString()%>">Aceito Final</option> --%>
							<%} %>
						</select> <br>
						<button class="btn btn-primary" type="submit">Relação de
							Trabalhos</button>
					</form>
				</td>
				<td><form action="Distribuir" method="post">
						<input type="hidden" value="<%=evento.getIdEvento()%>"
							name="idEvento">
						<button class="btn btn-primary" type="submit">Distribuir
							Todos os Trabalhos</button>
					</form></td>
				<td><form action="Distribuir" method="post">
						<input type="hidden" value="<%=evento.getIdEvento()%>"
							name="idEvento"> <input type="hidden" value="recurso"
							name="tipo">
						<button class="btn btn-primary" type="submit">Distribuir
							Todos os Trabalhos do Recurso</button>
					</form></td>
			</tr>
		</table>

		<%  } %>

		<!-- page end-->

	</section>
</section>
<%} %>
<!-- container section start -->

<!-- javascripts -->
<script src="../js/jquery.js"></script>
<script src="../js/jquery-ui-1.10.4.min.js"></script>
<script src="../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui-1.9.2.custom.min.js"></script>
<!-- bootstrap -->
<script src="../js/bootstrap.min.js"></script>
<!-- nice scroll -->
<script src="../js/jquery.scrollTo.min.js"></script>
<script src="../js/jquery.nicescroll.js" type="text/javascript"></script>
<!-- charts scripts -->
<script src="../assets/jquery-knob/js/jquery.knob.js"></script>
<script src="../js/jquery.sparkline.js" type="text/javascript"></script>
<script src="../assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
<script src="../js/owl.carousel.js"></script>
<!-- jQuery full calendar -->
<script src="../js/fullcalendar.min.js"></script>
<!-- Full Google Calendar - Calendar -->
<script src="../assets/fullcalendar/fullcalendar/fullcalendar.js"></script>
<!--script for this page only-->
<script src="../js/calendar-custom.js"></script>
<script src="../js/jquery.rateit.min.js"></script>
<!-- custom select -->
<script src="../js/jquery.customSelect.min.js"></script>
<!--custome script for all page-->
<script src="../js/scripts.js"></script>
<!-- chartjs -->
<script src="../assets/chart-master/Chart.js"></script>
<!-- custom script for this page-->
<script src="../js/sparkline-chart.js"></script>
<script src="../js/easy-pie-chart.js"></script>
<script src="../js/jquery-jvectormap-1.2.2.min.js"></script>
<script src="../js/jquery-jvectormap-world-mill-en.js"></script>
<script src="../js/xcharts.min.js"></script>
<script src="../js/jquery.autosize.min.js"></script>
<script src="../js/jquery.placeholder.min.js"></script>
<script src="../js/gdp-data.js"></script>
<script src="../js/morris.min.js"></script>
<script src="../js/sparklines.js"></script>
<script src="../js/charts.js"></script>
<script src="../js/jquery.slimscroll.min.js"></script>

</body>
</html>
