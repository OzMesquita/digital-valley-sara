<%@page import="java.util.List"%>
<%@page import="br.com.n2s.sara.dao.DAOUsuario"%>
<%@page import="br.com.n2s.sara.dao.DAOEvento"%>
<%@page import="br.com.n2s.sara.dao.DAOTrilha"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.n2s.sara.model.*" %>
<%@page import="br.com.n2s.sara.util.Constantes"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
	<% 
		DAOEvento daoEvento = new DAOEvento();
		DAOTrilha daoTrilha = new DAOTrilha();
		String idEvento = request.getParameter("idEvento");
		String idTrilha = request.getParameter("idTrilha");
		if (idEvento == null || idTrilha == null){
			response.sendRedirect("indexAutor.jsp");	
		}else{		
			Evento evento = Facade.pegarEventoPeloId(Integer.parseInt(idEvento));
			Trilha trilha = daoTrilha.getTrilha(Integer.parseInt(idTrilha));
			DAOUsuario daoUsuario = new DAOUsuario();
			List<Usuario> listaUsuario = daoUsuario.readLeve();
			List<Usuario> listaCoautores = new ArrayList<Usuario>();
		
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
                        <table class="table table-striped table-advance">
	                        <tbody>
				                    <tr>                               
				                       <th><h2><%= trilha.getDescricao() %></h2> </th>
				                    </tr>
				                   	<tr style="background-color: #F9F9F9;">
				                    	<td >				                    	
									        <form action="SalvarArquivo" id="form" method="post" onsubmit="return Validate(this);" enctype="multipart/form-data">
									            <input type="hidden" name="trilha" value="<%=trilha.getIdTrilha()%>" />
									            <input type="hidden" name="evento" value="<%=trilha.getEvento().getIdEvento()%>" />
									            <p><b>*Título:</b></p>
									            <p><input type="text" onblur="this.value=value.toUpperCase()" required="required" name="titulo" size="80"></p>
									            <p><b>*Resumo/Abstract: (De acordo com o trabalho enviado)</b></p> 
									            <p><textarea name="resumo" cols="80" rows="15" maxlength="5000" required="required"></textarea> </p>
									            <p><b>Palavras-chave: (Separe como ponto e vírgula)</b></p>
									            <p><input type="text" name="palavras_chave" size="80"></p>
									            
									            <% if(trilha.getTipoApresentacao()!= null && trilha.getTipoApresentacao().name() == "TODAS"){ %>
										            <p><b>*Selecione a forma de apresentação do seu resumo nos Encontros Universitários:</b></p>
										            <p>
										            	<input type="radio" id="poster" name="tipoApresentacao" value="POSTER" required>
										            	<label for="poster" style="margin-right: 25px;">Apresentação via pôster</label>
										            	<input type="radio" id="oral" name="tipoApresentacao" value="ORAL">
										            	<label for="oral">Apresentação oral</label>
										            </p>
									            <% } %>
									            
									            <% if(trilha.getTipoApresentacao()!= null && trilha.getTipoApresentacao().name() != "TODAS"){ %>
										            <p><b>*Forma de apresentação obrigatória do seu resumo nos Encontros Universitários:</b></p>
										            <p>
										            	<input type="text" disabled="disabled" id="tipoApresentacao" name="tipoApresentacao" value="<%= trilha.getTipoApresentacao().getLabel() %>" />
										            </p>
									            <% } %>
									            
									            <p><b>*Selecione seu orientador abaixo:</b></p> 
												<div>
													<select name="usuarioOrientador" required="required">  
														<option value="">Selecione</option>
	 													<c:forEach var="usuarioOrientador" items="<%= listaUsuario %>">  
	    													<option value="${usuarioOrientador}">${usuarioOrientador.nome}</option>  
	  													</c:forEach>  
													</select>
												</div>
												<br/>
									            
									            <p><b>Autor Principal:</b></p>
									            <p>
										            Nome: <input type="text" value="<%=usuario.getNome()%>" disabled="disabled" name="autor" />
													Email: <input type="text" value="<%=usuario.getEmail() %>" name="autor" disabled="disabled"/>
													CPF: <input type="text" id="cpf" name="cpf" size="14" value="<%=usuario.getCpf()%>" disabled="disabled">
												</p>
												
												<p><b>Co-autores:</b></p>
												<div id="divUsuarioCoautor">
													<select id="selectCoautor" name="usuarioCoautor">  
														<option value="">Selecione</option>
	 													<c:forEach var="usuarioCoautor" items="<%= listaUsuario %>">  
	    													<option value="${usuarioCoautor}">${usuarioCoautor.nome}</option>  
	  													</c:forEach>  
													</select>
													 <button type="button" onclick="autorList.insert()" class="btn-sm btn-primary">Adicionar Co-Autor</button>
												</div>
												
									            <div id="divAutorBase" style="visibility: hidden;">
														Nome: <input id="nomeCoAutor" type="text" name="nomeAutor" required="required" readonly="readonly" />
														Email: <input id="emailCoAutor" type="text" name="emailAutor" required="required" readonly="readonly" />
														<input id="cpfCoAutor" type="hidden" id="cpf" maxlength="14" onkeypress="this.value=Cpf(this.value)" onblur="validarCPF(this.value);"  required="required" name="cpfAutor">
														<button type="button" onclick="autorList.remove(this.parentNode)"  class="btn-sm btn-primary">
															<i class="icon_trash"></i>
														</button>
												</div>
											
											    <div id="divAutorList" >
											    </div>
											    
						     					
						     					<%if (request.getParameter("idTrabalho") != null){%>
						     						<input type="hidden" value="<%=request.getParameter("idTrabalho")%>" name="idTrabalho">
						     					<%}%>
						     					<br/>
												<p><b>*Anexar Trabalho:</b></p>
												
												<label for="file_Input" class="btn-sm btn-primary">
   													 <i class="fa fa-cloud-upload"></i> Upload de Arquivo
												</label>
												<input type="file" accept=".pdf" id="file_Input" required="required" onChange="tamanho();" name="trabalho" style="display: none;">
												<span id='file-name'></span>
								         		
								         		<br/>
								         		<p style="font-size: 9; color:red;">(*)Campos Obrigatórios</p>
								          		<button type="submit" onsubmit="verificacao();" class="btn-sm btn-primary">Enviar</button>
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
<%} %>
  <!-- container section start -->
    
    <!-- javascripts -->
    
    <!-- verificação geral -->
    <script  type="text/javascript">
    	 
    	 var $input = document.getElementById('file_Input'),
   		 $fileName = document.getElementById('file-name');
   		
   		 $input.addEventListener('change', function(){
   		 	$fileName.textContent = this.value;
   		 });	
    
    	function verificacao(){
    		var cpfs = document.getElementById("cpf");
    		for (var i=0;i <cpfs.length;i++){
    			for(var j=1+1; j<cpfs; i++){
    				if(j=i){
    					break;
    				}else{
    					var cpf1 = cpfs[i];
    					var cpf2 = cpfs[j];
    					console(cpf1);console(cpf2);
    					if (cpf1.value == cpf2.value){
    						alert("ERRO: CPFS REPETIDOS! POR FAVOR VERIFICAR.");
    					}
    					validarCPF(cpf1);
    					validarCPF(cpf2);
    				}
    			}
    		}
    		CPF();
    	}    
    </script>
    
    <!-- verificação do arquivo -->
    <script>	
    function tamanho (){
    	var tamanhoArquivo = parseInt(document.getElementById("file_Input").files[0].size);
      	if(tamanhoArquivo > 5242880 ){
      			 alert("TAMANHO DO ARQUIVO EXCEDE O PERMITIDO (5MB)!");
      			document.getElementById('file_Input').type='';
      			document.getElementById('file_Input').type='file';
            }
    };
    
    var _validFileExtensions = [".pdf"];    
    function Validate(oForm) {
        var arrInputs = oForm.getElementsByTagName("input");
        for (var i = 0; i < arrInputs.length; i++) {
            var oInput = arrInputs[i];
            if (oInput.type == "file") {
                var sFileName = oInput.value;
                if (sFileName.length > 0) {
                    var blnValid = false;
                    for (var j = 0; j < _validFileExtensions.length; j++) {
                        var sCurExtension = _validFileExtensions[j];
                        if (sFileName.substr(sFileName.length - sCurExtension.length, sCurExtension.length).toLowerCase() == sCurExtension.toLowerCase()) {
                            blnValid = true;
                            break;
                        }
                    }
                    
                    if (!blnValid) {
                        alert("Desculpe, " + sFileName + " é inválido, as extensões permitidas são: " + _validFileExtensions.join(", "));
                        return false;
                    }
                }
            }
        }
      
        return true;
    }
    function Cpf(v){

    	v=v.replace(/\D/g,"")

    	v=v.replace(/(\d{3})(\d)/,"$1.$2")

    	v=v.replace(/(\d{3})(\d)/,"$1.$2")



    	v=v.replace(/(\d{3})(\d{1,2})$/,"$1-$2")

    	return v

    }
    
    function validarCPF(cpf) {	
    	cpf = cpf.replace(/[^\d]+/g,'');	
    	if(cpf == '') return false;	
    	// Elimina CPFs invalidos conhecidos	
    	if (cpf.length != 11 || 
    		cpf == "00000000000" || 
    		cpf == "11111111111" || 
    		cpf == "22222222222" || 
    		cpf == "33333333333" || 
    		cpf == "44444444444" || 
    		cpf == "55555555555" || 
    		cpf == "66666666666" || 
    		cpf == "77777777777" || 
    		cpf == "88888888888" || 
    		cpf == "99999999999")
    			alert("CPF INVÁLIDO");		
    	// Valida 1o digito	
    	add = 0;	
    	for (i=0; i < 9; i ++)		
    		add += parseInt(cpf.charAt(i)) * (10 - i);	
    		rev = 11 - (add % 11);	
    		if (rev == 10 || rev == 11)		
    			rev = 0;	
    		if (rev != parseInt(cpf.charAt(9)))		
    			return false;		
    	// Valida 2o digito	
    	add = 0;	
    	for (i = 0; i < 10; i ++)		
    		add += parseInt(cpf.charAt(i)) * (11 - i);	
    	rev = 11 - (add % 11);	
    	if (rev == 10 || rev == 11)	
    		rev = 0;	
    	if (rev != parseInt(cpf.charAt(10)))
    		alert("CPF INVÁLIDO");;		
    	return true;   
    }
    
	 function CPF(){
    	  var mensagem = 'CPF Inválido'
    	  if ( validarCPF(document.getElementById('cpf').value) === true ) {
    	    mensagem = 'CPF Válido'
    	  }

    	  alert(mensagem);
    };
    </script>
    
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
    <script src="../js/owl.carousel.js" ></script>
    <!-- jQuery full calendar -->
    <script src="../js/fullcalendar.min.js"></script> <!-- Full Google Calendar - Calendar -->
	<script src="../assets/fullcalendar/fullcalendar/fullcalendar.js"></script>
    <!--script for this page only-->
    <script src="../js/calendar-custom.js"></script>
	<script src="../js/jquery.rateit.min.js"></script>
    <!-- custom select -->
    <script src="../js/jquery.customSelect.min.js" ></script>
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
	<!-- este é o script para gerar os campos do autor -->
	<script>
     var  autorList = {
	    'init': function()
	    {
	        this.divAutorList = document.getElementById('divAutorList');
	        this.divAutorBase = document.getElementById('divAutorBase');
	        //alert(document.getElementById('selectCoautor').value);
	    },
	    
	    'insert': function()
	    {
	        
	    	var coautores = document.getElementById('selectCoautor').value.split(":");;
	    	
	    	if(coautores == ''){
	    		alert("Selecione um coautor");
	    		return;
	    	}
	    	
	    	var divAutorBase = document.getElementById('divAutorBase');
	    	
	    	var newDiv = divAutorBase.cloneNode(true);
	    	newDiv.style.visibility='visible';
	    	newDiv.style.marginTop = '10px';
	    	
	        for (var i=0;i<newDiv.getElementsByTagName('input').length;i++){
	        	newDiv.querySelector("#nomeCoAutor").value = coautores[1];
	        	newDiv.querySelector("#cpfCoAutor").value = coautores[0];
	        	newDiv.querySelector("#emailCoAutor").value = coautores[2];
		    }
	        
	        document.getElementById('selectCoautor').value = '';
	        
	        this.divAutorList.appendChild(newDiv); 
	    },
	    
	    'remove': function(el)
	    {
	        el.parentNode.removeChild(el);
	    }
		};
	autorList.init();
	</script>
	
	
  <script>

      //knob
      $(function() {
        $(".knob").knob({
          'draw' : function () { 
            $(this.i).val(this.cv + '%')
          }
        })
      });

      //carousel
      $(document).ready(function() {
          $("#owl-slider").owlCarousel({
              navigation : true,
              slideSpeed : 300,
              paginationSpeed : 400,
              singleItem : true

          });
      });

      //custom select box

      $(function(){
          $('select.styled').customSelect();
      });
	  
	  /* ---------- Map ---------- */
	$(function(){
	  $('#map').vectorMap({
	    map: 'world_mill_en',
	    series: {
	      regions: [{
	        values: gdpData,
	        scale: ['#000', '#000'],
	        normalizeFunction: 'polynomial'
	      }]
	    },
		backgroundColor: '#eef3f7',
	    onLabelShow: function(e, el, code){
	      el.html(el.html()+' (GDP - '+gdpData[code]+')');
	    }
	  });
	});
		
	$(function () {
        var data = {
        labels: ["January", "February", "March", "April", "May", "June", "July"],
        datasets: [
            {
                label: "My First dataset",
                fillColor: "rgba(220,220,220,0.2)",
                strokeColor: "rgba(220,220,220,1)",
                pointColor: "rgba(220,220,220,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(220,220,220,1)",
                data: [65, 59, 80, 81, 56, 55, 40]
            },
            {
                label: "My Second dataset",
                fillColor: "rgba(151,187,205,0.2)",
                strokeColor: "rgba(151,187,205,1)",
                pointColor: "rgba(151,187,205,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(151,187,205,1)",
                data: [28, 48, 40, 19, 86, 27, 90]
            }
            ]
        };
   
        var option = {
        responsive: true,
        };
   
        // Get the context of the canvas element we want to select
        var ctx = document.getElementById("teste").getContext('2d');
        var myLineChart = new Chart(ctx).Line(data, option); //'Line' defines type of the chart.
    });

  </script>
    </body>
</html>
