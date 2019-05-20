<%@page import="br.com.n2s.sara.dao.DAOEvento"%>
<%@page import="br.com.n2s.sara.dao.DAOTrilha"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="br.com.n2s.sara.model.*" %>
<%@page import="br.com.n2s.sara.util.Constantes"%>

	<% 
		DAOEvento daoEvento = new DAOEvento();
		DAOTrilha daoTrilha = new DAOTrilha();
		Evento evento = daoEvento.getEvento(Integer.parseInt(request.getParameter("idEvento")));
		Trilha trilha = daoTrilha.getTrilha(Integer.parseInt(request.getParameter("idTrilha")));
 
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
				                       <th><h2><%= trilha.getDescricao() %></h2> </th>
				                    </tr>
				                   	<tr>
				                    	<td>				                    	
									        <form action="SalvarArquivo" id="form" method="post" onsubmit="return Validate(this);" enctype="multipart/form-data">
									            <input type="hidden" name="trilha" value="<%=trilha.getIdTrilha()%>" />
									            <input type="hidden" name="evento" value="<%=trilha.getEvento().getIdEvento()%>" />
									            <p>*Título:</p>
									            <p><input type="text" onblur="this.value=value.toUpperCase()" required="required" name="titulo" size="80"></p>
									            <p>Resumo/Abstract (Opcional):</p> 
									            <p><textarea name="resumo" cols="80" rows="15" maxlength="5000"></textarea> </p>
									            <p>Palavras-chave: (Separe como ponto e vírgula)</p>
									            <p><input type="text" name="palavras_chave" size="80"></p>
									            
									            <!-- Verificacao  -->
									            
									            <p>Autor Principal</p>
									            Nome: <input type="text" value="<%=usuario.getNome()%>" disabled="disabled" name="autor" />
												Email: <input type="text" value="<%=usuario.getEmail() %>" name="autor" disabled="disabled"/>
												CPF: <input type="text" id="cpf" name="cpf" size="14" value="<%=usuario.getCpf()%>" disabled="disabled">
												<br/>
												<br/>
												Adicione seu orientador e, se houver, adicione os co-autores: 
												<br/>
												<br/>
									            <div id="divAutorBase">
														Nome: <input type="text" name="nomeAutor" required="required"/>
														Email: <input type="text" name="emailAutor" required="required" />
														CPF: <input type="text" id="cpf" maxlength="14" onkeypress="this.value=Cpf(this.value)" onblur="validarCPF(this.value);"  required="required" name="cpfAutor">
														<input type="button" value="Remover" onclick="autorList.remove(this.parentNode)" />
												</div>
											    <div id="divAutorList" >
											    </div>
											    <input type="button" value="Adicionar Co-Autor" onclick="autorList.insert()" />
						     					<br/>
						     					
						     					<%if (request.getParameter("idTrabalho") != null){%>
						     						<input type="hidden" value="<%=request.getParameter("idTrabalho")%>" name="idTrabalho">
						     					<%}%>
						     					<br/>
												<label>*Anexar Trabalho:</label>
												
								         		<input type="file" id="file_Input" required="required" onChange="tamanho();" name="trabalho">
								         		<br/>
								         		<p style="font-size: 9; color:red;">(*)Campos Obrigatórios</p>
								          		<input type="submit" onsubmit="verificacao();" value="Enviar">
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
  <!-- container section start -->
    
    <!-- javascripts -->
    
    <!-- verificação geral -->
    <script  type="text/javascript">
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
                document.getElementById('form').reset();
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
	    },
	    
	    'insert': function()
	    {
	        var newDiv = this.divAutorBase.cloneNode(true);
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
