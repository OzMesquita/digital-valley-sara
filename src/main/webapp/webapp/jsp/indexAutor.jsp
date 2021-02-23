<%@page import="java.time.LocalDate"%>
<%@page import="java.util.List"%>
<%@page import="br.com.n2s.sara.dao.*"%>
<%@page import="br.com.n2s.sara.controller.*"%>
<%@page import="br.com.n2s.sara.model.*" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.format.DateTimeFormatter" %>
<%@page import="br.com.n2s.sara.util.Constantes"%>

<!-- 

   
   
		   _    _____         _
		   \`,""   ,'7"r-..__/ \
		  ,'\ `, ,',','    _/   \
		 /   \  7 / /     (   \ |
		J     \/ j  L______\  / |
		L   __JF"""/""\"\_,    /
		L,-"| O|  f O |  L_  _/
		F   \_ /  \__/   `-  j|
			.-'    `"""    ,' |          _..====.._
			\__/         r"_  A        ,' _..---.._`,
			 `-.______,,-L// / \  ___,' ,'_..:::.. `,`,
					  j   / / / 7"    `-<""=:'  '':. \ \
					 /   <,' /  F  . i , \   `,    :T W I
					 |    \,'  /    >X<  |     \   :| | L
					 |     `._/    ' ! ` |      I  :| |  G
					  \           \     /       |  :H T  |
					 __>-.__   __,-\   |        |  S P   |
					/     /|   | \  \  \_       | 'A R   |
				   /   __/ |   |  L  L   \      K./ /    L
				  /   |    |   4  I  I    |    E./ /   ,'
				 J    \    I    L F  I    |    // / _,'
		_________|     |   F    |J   F    |   //_/-'
		\   __   |    /   J     |/  J     |  /="
		\\  \_\  \__,' \  F     |   F     |
		\\\_____________\/      F__/      F
		 \\|  HTML for  |_____/  (______/
		  \/__Dummies___/

 -->
      
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
		  <div class="row">
				<div class="col-lg-12">
					<h3 class="page-header"><i class="fa fa-table"></i> Eventos Abertos</h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="indexAutor.jsp">Home</a></li>
						<li><i class="icon_document_alt"></i>Eventos Abertos</li>
					</ol>
				</div>
			</div>
      
      <!-- page start-->
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
                              Eventos Abertos
                          </header>
                          
                          <table class="table table-striped table-advance table-hover">
                           <tbody>
                              <tr>                               
                                 <th><i class="icon_documents_alt"></i> Evento</th>
                                 <th><i class="icon_pin"></i> Local</th>
                                 <th><i class="icon_calendar"></i> Data</th>
                                 <th></th>
                              </tr>
                              
                              <%
                              	 
                              	DAOEvento daoEvento = new DAOEvento();
                              	List<Evento> eventos = daoEvento.readAtivo();
                                
                              	 
                              	 for(Evento evento : eventos){ 
                              	 	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");%>
                                      
                                      <tr>
                                         <td><%= evento.getNome() %> </td>
                   						 <td><%= evento.getLocalizacao()%> </td>
                                         <td><%= formatter.format(evento.getDataInicial()) %> </td>
                                         <%-- <%if (evento.getDataFinal().isAfter(LocalDate.now()) || evento.getDataFinal().isEqual(LocalDate.now())){%> --%>
                                         <td><form action="paginaDeEventos.jsp" method="post"> 
                           					<input type="hidden" value="<%= evento.getIdEvento()%>" name="idEvento"> 
                          					<button class="btn btn-primary" type = "submit"><i class="icon_zoom-in"></i></button>
                       					 </form> 
                       					 <%-- <%} %> --%>
                   						</td>
                                      </tr>                              			 
                              <% }
                                 
                              %>
                                 
                           </tbody>
                        </table>
                      </section>
                  </div>
              </div>
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