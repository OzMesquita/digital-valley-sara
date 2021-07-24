<%@page import="br.com.n2s.sara.dao.DAOTrabalho"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.n2s.sara.util.Constantes"%>
<%@ page import="br.com.n2s.sara.model.*" %>
	<% 
		int idTrabalho = Integer.parseInt(request.getParameter("idTrabalho"));
		Trabalho trabalho = new DAOTrabalho().getTrabalho(idTrabalho);
		session.setAttribute("trabalho", trabalho);

	//if (! Facade.isAvaliador(trabalho.getIdTrabalho(), usuario.getCpf())){
          		%>
<!--           		<iframe onload="permissao()" src="/adicionarCoordenadorEvento.jsp"></iframe>
    			<script>
    				function permissao(){
    					alert('Você não possue permissão para acessar está área!!!!');
    					var myVar = setInterval(redirect, 1000);
    				}
    				function redirect(){
    					windows.location.href = 'indexAutor.jsp';
    				}
    			</script> -->      		
          		<% 
//          		response.sendRedirect("indexAutor.jsp");          	}
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
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
		  <div class="row">
				<div class="col-lg-12">
					<h3 class="page-header"><i class="fa fa-table"></i> Avaliar Trabalho</h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="indexAutor.jsp">Home</a></li>
						<li><i class="icon_document_alt"></i>Avaliar Trabalho</li>
					</ol>
				</div>
			</div>
      
      <!-- page start-->
              
              <div class="row">
                  <div class="col-lg-12" style="background-color: #EEEEEE !important;">
                      <section class="panel" style="background-color: #EEEEEE !important;">
                          <header class="panel-heading">
                              <h2>Avaliar Trabalho</h2>
                          </header>
                        <table class="table table-striped table-advance table-hover">
	                        <form method="post" onsubmit="myFunction();" action="SalvarAvaliacaoArtigo">
	                        <input type="hidden" value="<%=trabalho.getIdTrabalho() %>" name="t-a" >
				            
	                        <tbody>
	                                <tr>
	                                   <h3><b>Trilha</b></h3> <%= trabalho.getTrilha().getDescricao() %>
				                       <h3><b>Trabalho</b></h3> <%= trabalho.getTitulo() %>
				                    </tr>
				                    <tr>
				                    	<hr>
				                    	<h3><b>Critério Inicial</b></h3>
				                    	<p>O resumo está identificado? (Caso esteja identificado, o resumo deve ser rejeitado)</p>
				                    	<div class="input-group form-row">
				                 			<div class="input-group-prepend col">
    											<div class="input-group-text col">	    											
					                    	 	 		<button onclick="ativa()" class="btn-sm btn-primary">Resumo NÃO está identificado</button>					                    	 	 		     	  										
	  													<button onclick="desativa()" class="btn-sm btn-primary">Resumo está identificado</button>	  												
  												</div>
  											</div>	
  										</div>
  										<hr style="border-top: 3px solid #bbb;">	
				                    </tr>
				                    <%for(Criterio c :trabalho.getTrilha().getCriterios()) {%>
				                    <tr>
				                   		<h3><b><%=c.getNome()%></b></h3>
				                   		<p><%=c.getDescricao()%></p>
				                   		<div class="input-group form-row">
				                 			<div class="input-group-prepend col">
    											<div class="input-group-text col">
				                   		
							                   		<%for(Item i : c.getItens()) {%>
															<div id=nota>
				    											<label class="radio-inline">
					    											<input type="radio" class="radio" name="criterio-<%=c.getIdCriterio()%>" value="<%=i.getIdItem()%>" required />
					    												<div style="height: 100%;margin-top: 5px;"> <%=i.getDescricao()%> Nota: <%=i.getPeso()%></div>
					    										</label>
					    										<input type="hidden" class="radio-nota" value="<%=i.getPeso()%>" style="width: 0px;height: 0px;"/>
																
			    											</div>
							                   		<%} %>
				                   				</div>
  											</div>
				                   		</div>
				                   	</tr>
				                   	<%} %>
				                   	<tr>
				                   		<br><br><br>
				                   		<p>*Descreva abaixo suas considerações sobre o trabalho:</p> 
									    <p><textarea id="feed" name="feedback" cols="60" rows="5" maxlength="5000" required></textarea></p>
				                   	</tr>
				                   	<button type="submit" onclick="myFunction();" class="btn btn-primary">Enviar Avaliação</button>
				                   	
				              </form>
				                   	
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
    <!-- Este é o script para calcular a nota -->
    <script>
			function ativa(){
				console.log("socorro");
				var doc1 = document.getElementsByClassName('radio-nota');
					  		for (var i=0;i<doc.length;i++){
									doc[i].checked=false;
									doc[i].disabled=false;
									
							}
			}
	</script>
	 <script>
			function desativa(){
				var doc = document.getElementsByClassName('radio');
				var doc1 = document.getElementsByClassName('radio-nota');
					  		for (var i=0;i<doc.length;i++){
									if( doc1[i].value == '0'){
										doc[i].checked=true;
									}else{
										doc[i].checked=false;
									}
										

									console.log("socorro");
							}
			}
	</script>
	 <script>
			function myFunction() {
				var doc = document.getElementsByClassName('radio');
				var doc1 = document.getElementsByClassName('radio-nota');
						  		var nota=0;
						  		var cont=0;
						  		for (var i=0;i<doc.length;i++){
									if(doc[i].checked){
										nota = nota+parseInt(doc1[i].value);
										cont = cont +1;
									}
								}
						  		
				alert("A nota para o trabalho é: "+nota/cont);
			}
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
	        newDiv.style.display = '';
	        console.log('newDiv => ', newDiv);
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