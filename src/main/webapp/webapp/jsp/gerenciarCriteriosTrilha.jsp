<%@page import="br.com.n2s.sara.dao.DAOCriterioTrilha"%>
<%@page import="br.com.n2s.sara.dao.DAOCriterio"%>
<%@page import="br.com.n2s.sara.model.Usuario"%>
<%@page import="br.com.n2s.sara.model.CriterioTrilha"%>
<%@page import="br.com.n2s.sara.model.Trilha"%>
<%@page import="br.com.n2s.sara.model.Criterio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.com.n2s.sara.util.Constantes"%>

      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
		  <div class="row">
				<div class="col-lg-12">
					<h3 class="page-header"><i class="fa fa-table"></i> Crit�rios de Avalia��o</h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="indexAutor.jsp">Home</a></li>
						<li><i class="icon_document_alt"></i>Crit�rios de Avalia��o</li>
					</ol>
				</div>
			</div>
      
      <!-- page start-->
              <%
      			Trilha trilha = (Trilha) session.getAttribute("trilha");
              	session.setAttribute("trilha", trilha); 			
              	DAOCriterio daoCriterio = new DAOCriterio();
              	DAOCriterioTrilha daoCriterioTrilha = new DAOCriterioTrilha();     			
      			List<CriterioTrilha> listaCriterioTrilha = daoCriterioTrilha.read();             	
      			
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
              <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                          <header class="panel-heading">
                             Crit�rios de Avalia��o
                          </header>
                          
                          <table class="table table-striped table-advance table-hover">
                           <tbody>
                              <tr>                               
                                 <th><i class="icon_documents_alt"></i> Crit�rio</th>
                                 <th><i class="icon_balance"></i> Peso</th>
                                 <th></th>
                                 <th></th>
                              </tr>
                              
                              <%
                          		
                		        for(int i=0; i < trilha.getCriterios().size(); i++){
	               			 	%>
                                      <tr>
                                         <td><%=trilha.getCriterios().get(i).getDescricao()%></td>
                                         <td><%=trilha.getCriterios().get(i).getPeso()%></td>
		                   				 <td><form action="editarCriterio.jsp" method="post"> 
		                           				<input type="hidden" value="<%= trilha.getCriterios().get(i).getIdCriterio()%>" name="idCriterio"> 
		                           				<button class="btn btn-primary" type="submit">Alterar</button>
		                       				</form> 
		                   				</td>
		                   
		                   				<td><form action="RemoverCriterio" method="post" id="formRemover" onsubmit="return confirm('Deseja remover este crit�rio?');"> 
		                           				<input type="hidden" value="<%= trilha.getCriterios().get(i).getIdCriterio()%>" name="idCriterio"> 
		                           				<button class="btn btn-primary" type="submit">Remover</button>
		                       				</form> 
		                   				</td>
                                      </tr>                              			 
                              <% } %>
                              
                                 </tbody>
                                 </table>
                               </section>
                           </div>
                       </div>
                                 
       	<center>
        <form action="adicionarCriterio.jsp" method="post">
            <button class="btn btn-primary" type="submit" name="addCri">Adicionar Crit�rio</button>
        </form>
        </center>

              <!-- page end-->


  </section>
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