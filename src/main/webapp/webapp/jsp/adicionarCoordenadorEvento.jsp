<%@page import="br.com.n2s.sara.model.Usuario"%>
<%@page import="br.com.n2s.sara.model.Evento"%>
<%@page import="br.com.n2s.sara.model.CoordenacaoEvento"%>
<%@page import="br.com.n2s.sara.util.Constantes"%>
      <%
		Evento evento = (Evento) session.getAttribute("evento");
      	if (! Facade.isCoordenador(evento.getIdEvento(), usuario.getCpf())){
      		%>
      		<iframe onload="permissao()" src="/adicionarCoordenadorEvento.jsp"></iframe>
			<script>
				function permissao(){
					alert('Você não possue permissão para acessar está área!!!!');
					var myVar = setInterval(redirect, 1000);
				}
				function redirect(){
					windows.location.href = 'indexAutor.jsp';
				}
			</script>      		
      		<% 
      		response.sendRedirect("indexAutor.jsp");
      	}
      %>
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
		  <div class="row">
				<div class="col-lg-12">
					<h3 class="page-header"><i class="fa fa-table"></i>Adicionar Coordenador</h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="indexAutor.jsp">Home</a></li>
						<li><i class="icon_document_alt"></i>Adicionar Coordenador</li>
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
      
      				<!-- Form validations -->
				<div class="row">
					<div class="col-lg-12">
						<section class="panel">
							<header class="panel-heading"> Formulário de Adição de Coordenadores </header>
							<div class="panel-body">
								<div class="form">
									<form action="AdicionarCoordenadorEvento" method="post" id = "formEnviar">
										<div class="form-validate form-horizontal" id="feedback_form">
											<div class="form-group ">
												<label for="cemail" class="control-label col-lg-2">CPF 
													<span class="required">*</span>
												</label>
												<div class="col-lg-6">
													<input class="form-control " id="subject" type="text"
														name="cpfCoordenador" required />
												</div>
											</div>

											<div class="form-group">
												<div class="col-lg-offset-2 col-lg-10">
													<button id = "sucesso" class="btn btn-primary" type="submit"onclick = "validation();">Adicionar</button>
												</div>
											</div>
										</div>
									</form>
								</div>

							</div>
						</section>
					</div>
				</div>
	
	<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script type="text/javascript" src = "../js/jquery.mask.min.js"/></script>
	<script type="text/javascript">$("#cpf").mask("000.000.000-09");</script>
	<script src="../js/jquery.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<!-- nice scroll -->
	<script src="../js/jquery.scrollTo.min.js"></script>
	<script src="../js/jquery.nicescroll.js" type="text/javascript"></script>
	<!-- jquery validate js -->
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

	<!-- custom form validation script for this page-->
	<script src="../js/form-validation-script.js"></script>
	<!--custome script for all page-->
	<script src="../js/scripts.js"></script>
	<script src="../SweetAlert/sweetalert.min.js"></script>
	<script>
        document.querySelector('#formEnviar').addEventListener('submit', function(e) {
        var form = this;
         e.preventDefault();          
                    swal({
                        title: "Sucesso!",			  
				        timer: 1000,
				        type: "success",
				        showConfirmButton: false
                    }, function() {
                        form.submit();
                    });

            });
    </script>

</body>
</html>