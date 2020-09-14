<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.n2s.sara.model.Usuario"%>
<%@page import="br.com.n2s.sara.model.NivelUsuario"%>
<%@page import="br.com.n2s.sara.dao.DAOUsuario"%>
<%@page import="br.com.n2s.sara.util.Constantes"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
	String cpf = (String) (request.getParameter("cpfUsuario"));

Usuario u = Facade.buscarUsuarioPorCPF(cpf);
if (u == null) {
	session.setAttribute("msg", "Nenhum usuário selecionado.");
}

session.setAttribute("usu", u);
session.setAttribute("cpfUsuario", cpf);

%>

<section id="main-content">
	<section class="wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<i class="fa fa-table"></i> Alterar Permissões de Usuário
				</h3>
				<ol class="breadcrumb">
					<li><i class="fa fa-home"></i><a href="indexAutor.jsp">Home</a></li>
					<li><i class="icon_document_alt"></i>Alterar Permissões</li>
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
		
		<div class="container" style="min-height: 400px">
			<div class="row">
				<div class="col-md-8">
					<h5>Editar Perfil</h5>
					<div class="panel-body">
						<div class="form">
					<form>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>Nome do Usuário</label> <input type="text"
										class="form-control" disabled value="<%=u.getNome()%>">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>CPF</label> <input type="text" id="cpf"
										class="form-control" disabled value="<%=u.getCpf()%>">
								</div>
							</div>
						</div>
					</form>
				</div>
				</div>
				</div>
				
				<div class="col-md-3">
					<h5>Editar Permissão</h5>
					<br>
					<div class="panel-body">
						<div class="form">
							<form id="formEnviar" action="EditarPermissaoUsuario"
								method="post">
								<div class="form-group">
									<select class="form-control" name="permissao" required>
										<option selected>
											<%=u.getTipo()%></option>
										<option value="AUTOR">Autor</option>
										<option value="AVALIADOR">Avaliador</option>
										<option value="COORDENADOR_EVENTO">Coordenador de evento</option>
										<option value="COORDENADOR_TRILHA">Coordenador de trilha</option>
										<option value="ADMINISTRADOR">Administrador</option>
									</select>
								</div>
								<button id="sucesso" type="submit" class="btn btn-primary">Salvar</button>
								<%-- <input type="hidden" name="cpfUsuario" value="<%=cpf%>"> --%>
							</form>
						</div>
					</div>
					
				</div>

			</div>
		</div>
	</section>
</section>

<script src="../../js/jquery.js"></script>
<script src="../../js/bootstrap.min.js"></script>
<script src="../../SweetAlert/sweetalert.min.js"></script>

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