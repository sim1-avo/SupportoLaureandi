<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" import="controller.CheckSession"%>
<%@ page
	import="java.util.*, model.*, java.sql.SQLException,java.text.SimpleDateFormat, java.time.*, model.Request,controller.DbConnection,controller.ServletAdmin,java.sql.ResultSet,java.sql.Statement"%>
<% 
	String pageName = "viewRequestCS.jsp";
	String pageFolder = "_areaAdmin";
	CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());
	if(!ck.isAllowed()){
		response.sendRedirect(request.getContextPath()+ ck.getUrlRedirect());
	}
		
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js">  </script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/newCss.css">
</head>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body onLoad="showData()">
	<div class="page-wrapper">

		<!-- Preloader -->
		<!--  <div class="preloader"></div> -->


		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%= pageName %>" />
			<jsp:param name="pageFolder" value="<%= pageFolder %>" />
		</jsp:include>


		<div class="sidebar-page-container basePage viewRequestSecretary">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven pl-5 pr-5">
								<table id="bodySecretaryTable" class="display data-results table table-striped table-hover table-bordered">
									<thead>
										<tr>
											<th class="text-center">ID</th>
											<th class="text-center">Nome</th>
											<th class="text-center">Cognome</th>
											<th class="text-center">Verifica Domanda</th>
											<th class="text-center">Stato</th>
											<th class="text-center">Conferma</th>
											<th class="text-center">Declina</th>
										</tr>
									</thead>
									<tbody id="bodySecretaryBody">
										<tr>
											<td align='center' class = 'id'><button  id='idR'>01</button></td>
											<td align='center'><button class='changeName' id='nome'>Nome1</button></td>
											<td align='center'><button  class ='changeSurname' id='cognome'>Nome2</button></td>
											<td align='center'><a href="#">Documento</a></td>
											<td align='center'><p class="list-group-item-success">Stato</p></td>
											<td align='center'>
												<button type="button" class="btn btn-default" aria-label="Conferma" id="checkConferma">
													<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
												</button>
											</td>
											<td align='center'>
												<button type="button" class="btn btn-default" aria-label="Rifiuta" id="checkRifiuta">
													<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
												</button>
											</td>	
										</tr>
										<tr>
											<td align='center' class = 'id'><button  id='idR'>01</button></td>
											<td align='center'><button class='changeName' id='nome'>Nome1</button></td>
											<td align='center'><button  class ='changeSurname' id='cognome'>Nome2</button></td>
											<td align='center'><a href="#">Documento</a></td>
											<td align='center'><p class="list-group-item-success">Stato</p></td>
											<td align='center'><input type='checkbox' id="checkConferma"></td>
											<td align='center'><input type='checkbox' id="checkRifiuta"></td>	
										</tr>
									</tbody>
								</table>
								<div class="d-flex justify-content-center"><button class="btn btn-primary btn-action generateExcel" style="width: auto !important; height: 35px !important;" id="btnConfermaRichieste">Conferma</button></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/partials/footer.jsp" />
	</div>
	<!--End pagewrapper-->

	<jsp:include page="/partials/includes.jsp" />

	<script>
			jQuery(document).ready(function($){
				$('#bodySegretaryTable').DataTable( {
			        "order": [[ 0, "desc" ]],
			        "lengthMenu": [[10, -1], [10, "Tutti"]],
			        "autoWidth": true,
			        "bAutoWidth": false,
			        "language": {
						    "sEmptyTable":     "Nessuna Richiesta Presente",
						    "sInfo":           "Vista da _START_ a _END_ di _TOTAL_ elementi",
						    "sInfoEmpty":      "Vista da 0 a 0 di 0 elementi",
						    "sInfoFiltered":   "(filtrati da _MAX_ elementi totali)",
						    "sInfoPostFix":    "",
						    "sInfoThousands":  ".",
						    "sLengthMenu":     "Visualizza _MENU_ elementi",
						    "sLoadingRecords": "Caricamento...",
						    "sProcessing":     "Elaborazione...",
						    "sSearch":         "Cerca:",
						    "sZeroRecords":    "La ricerca non ha portato alcun risultato.",
						    "oPaginate": {
						        "sFirst":      "Inizio",
						        "sPrevious":   '<i class="fa fa-caret-left"></i>',
						        "sNext":       '<i class="fa fa-caret-right"></i>',
						        "sLast":       "Fine"
						    },
						    "oAria": {
						        "sSortAscending":  ": attiva per ordinare la colonna in ordine crescente",
						        "sSortDescending": ": attiva per ordinare la colonna in ordine decrescente"
						    }
			        }        
			    } );
			});
		</script>
	<script
		src="<%= request.getContextPath() %>/js/pages/scripts_viewRequestCSsecretary.js"></script>
</body>
</html>

</body>
</html>