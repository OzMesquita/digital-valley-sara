<%@page import="java.util.List"%>
<%@page import="br.com.n2s.sara.dao.DAOEvento"%>
<%@page import="br.com.n2s.sara.dao.DAOTrilha"%>
<%@ page import="br.com.n2s.sara.model.*"%>
<%@page import="br.com.n2s.sara.util.Constantes"%>
<%
	String idEvento = request.getParameter("idEvento");
	if (idEvento == null) {
		response.sendRedirect("indexAutor.jsp");
	}
	
	DAOEvento daoEvento = new DAOEvento();
	Evento evento = Facade.pegarEventoPeloId(Integer.parseInt(idEvento));
	
	session.setAttribute("usuarioSara", usuario);
	session.setAttribute("evento", evento);
%>

<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<i class="fa fa-table"></i> Evento:
					<%=evento.getNome()%>
				</h3>
				<ol class="breadcrumb">
					<li><i class="fa fa-home"></i><a href="indexAutor.jsp">Home</a></li>
					<li><i class="icon_document_alt"></i>Editar Evento</li>
				</ol>
			</div>
		</div>

		<!-- page start-->

		<%
			if (session.getAttribute("feedbackSucesso") != null) {
		%>
		<div class="alert alert-success" role="alert">
			<%=session.getAttribute("feedbackSucesso")%>
		</div>

		<%
			}
		session.setAttribute("feedbackSucesso", null);
		%>
		<%
			if (session.getAttribute(Constantes.getSESSION_MGS()) != null) {
		%>
		<div class="alert alert-success" role="alert">
			<%=session.getAttribute(Constantes.getSESSION_MGS())%>
			<%
				session.setAttribute(Constantes.getSESSION_MGS(), null);
			%>
		</div>
		<%
			}
		%>
		<%
			if (session.getAttribute(Constantes.getSESSION_MGS_ERROR()) != null) {
		%>
		<div class="alert alert-danger" role="alert">
			<%=session.getAttribute(Constantes.getSESSION_MGS_ERROR())%>
			<%
				session.setAttribute(Constantes.getSESSION_MGS_ERROR(), null);
			%>
		</div>
		<%
			}
		%>

		<!-- Form validations -->
		<div class="row">
			<div class="col-lg-12">
				<section class="panel">
					<header class="panel-heading"> Formulário de Edição do
						Evento </header>
					<div class="panel-body">
						<div class="form">
							<form action="EditarEvento" method="post" id="formEnviar">
								<div class="form-validate form-horizontal" id="feedback_form">
									<div class="form-group ">
										<label for="cemail" class="control-label col-lg-2">Nome
											<span class="required">*</span>
										</label>
										<div class="col-lg-6">
											<input class="form-control " id="nome" type="text"
												name="nome" required value="<%=evento.getNome()%>" />
										</div>
									</div>

									<div class="form-group ">
										<label for="cemail" class="control-label col-lg-2">Coordenador
											<span class="required">*</span>
										</label>
										<div class="col-lg-6">
											<input class="form-control" maxlength="14" size="14"
												onkeypress="this.value=Cpf(this.value)"
												onblur="validarCPF(this.value);" id="cpf" type="text"
												name="cpfCoordenador"
												pattern="^[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}$"
												placeholder="111.111.111-11" required
												value="<%=evento.getCoordenadores().get(0).getCpf()%>">
										</div>
									</div>

									<div class="form-group ">
										<label for="cemail" class="control-label col-lg-2">Site
											<!-- <span class="required">*</span> -->
										</label>
										<div class="col-lg-6">
											<input class="form-control " id="site" type="text"
												name="site" value="<%=evento.getSite()%>" />
										</div>
									</div>

									<div class="form-group ">
										<label for="cemail" class="control-label col-lg-2">Localização
											<span class="required">*</span>
										</label>
										<div class="col-lg-6">
											<input class="form-control " id="localizacao" type="text"
												name="localizacao" required
												value="<%=evento.getLocalizacao()%>" />
										</div>
									</div>

									<div class="form-group ">
										<label for="cemail" class="control-label col-lg-2">Data
											Inicial <span class="required">*</span>
										</label>
										<div class="col-lg-6">
											<input class="form-control " id="data" type="date"
												name="dataInicial" placeholder="AAAA-MM-DD" required
												value="<%=evento.getDataInicial()%>" />
										</div>
									</div>

									<div class="form-group ">
										<label for="cemail" class="control-label col-lg-2">Data
											Final <span class="required">*</span>
										</label>
										<div class="col-lg-6">
											<input class="form-control " id="dataFinal" type="date"
												name="dataFinal" placeholder="AAAA-MM-DD" required value="<%=evento.getDataFinal()%>" />
										</div>
									</div>

									<div class="form-group">
										<label for="ccomment" class="control-label col-lg-2">Descrição
											<span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control " id="descricao"
												name="descricao" required value="<%=evento.getDescricao()%>"></input>
										</div>
									</div>
									<div class="form-group">
										<label for="ccomment" class="control-label col-lg-2">Tipo
											do Evento <span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input type="radio" value="encontrosUniversitarios" name="tipoEvento" checked required> Encontros Universitários
											<br>
											<input type="radio" value="relatorioEstagio" name="tipoEvento"> Relatório de Estágio
										</div>
									</div>

									<div class="form-group">
										<div class="col-lg-offset-2 col-lg-10">
											<button id="sucesso" class="btn btn-primary" type="submit"
												onclick="">Salvar Evento</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</section>
			</div>
		</div>
		<!-- page end-->
	</section>
</section>
<!-- container section start -->


<script
	src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script type="text/javascript" src="../js/jquery.mask.min.js"></script>
<script type="text/javascript">
	$("#cpf").mask("000.000.000-09");
</script>
<script src="../js/jquery.js"></script>
<script src="../js/bootstrap.min.js"></script>
<!-- nice scroll -->
<script src="../js/jquery.scrollTo.min.js"></script>
<script src="../js/jquery.nicescroll.js" type="text/javascript"></script>
<!-- jquery validate js -->
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

<!-- custom form validation script for this page-->
<!-- <script src="../js/form-validation-script.js"></script> -->
<!--custome script for all page-->
<script src="../js/scripts.js"></script>
<script src="../SweetAlert/sweetalert.min.js"></script>

<script type="text/javascript">
	function Cpf(v) {

		v = v.replace(/\D/g, "")

		v = v.replace(/(\d{3})(\d)/, "$1.$2")

		v = v.replace(/(\d{3})(\d)/, "$1.$2")

		v = v.replace(/(\d{3})(\d{1,2})$/, "$1-$2")

		return v

	}

	function validarCPF(cpf) {
		cpf = cpf.replace(/[^\d]+/g, '');
		if (cpf == '')
			return false;
		// Elimina CPFs invalidos conhecidos	
		if (cpf.length != 11 || cpf == "00000000000" || cpf == "11111111111"
				|| cpf == "22222222222" || cpf == "33333333333"
				|| cpf == "44444444444" || cpf == "55555555555"
				|| cpf == "66666666666" || cpf == "77777777777"
				|| cpf == "88888888888" || cpf == "99999999999")
			alert("CPF INVÁLIDO");
		// Valida 1o digito	
		add = 0;
		for (i = 0; i < 9; i++)
			add += parseInt(cpf.charAt(i)) * (10 - i);
		rev = 11 - (add % 11);
		if (rev == 10 || rev == 11)
			rev = 0;
		if (rev != parseInt(cpf.charAt(9)))
			return false;
		// Valida 2o digito	
		add = 0;
		for (i = 0; i < 10; i++)
			add += parseInt(cpf.charAt(i)) * (11 - i);
		rev = 11 - (add % 11);
		if (rev == 10 || rev == 11)
			rev = 0;
		if (rev != parseInt(cpf.charAt(10)))
			alert("CPF INVÁLIDO");
		;
		return true;
	}

	function CPF() {
		var mensagem = 'CPF Inválido'
		if (validarCPF(document.getElementById('cpf').value) === true) {
			mensagem = 'CPF Válido'
		}

		alert(mensagem);
	};
</script>

<script>
	document.querySelector('#formEnviar').addEventListener('submit',
			function(e) {
				var form = this;
				e.preventDefault();
				swal({
					title : "Sucesso!",
					timer : 1000,
					type : "success",
					showConfirmButton : false
				}, function() {
					form.submit();
				});

			});
</script>

</body>
</html>
