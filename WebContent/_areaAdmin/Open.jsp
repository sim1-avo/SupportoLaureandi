<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" import="controller.CheckSession"%>
<%@ page
	import="java.util.*, model.*, interfacce.*, java.sql.SQLException,java.text.SimpleDateFormat, java.time.*, model.Request,controller.DbConnection,controller.ServletAdmin,java.sql.ResultSet,java.sql.Statement"%>

<%
	String pageName = "Open.jsp";
String pageFolder = "_areaAdmin";
CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());
if(!ck.isAllowed()){
  response.sendRedirect(request.getContextPath()+ck.getUrlRedirect());  
}
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js">  </script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/cssOpen.css">
</head>
<body onLoad="showData()">
	<div class="page-wrapper">

		<!-- Preloader -->
		<!--  <div class="preloader"></div> -->


		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%= pageName %>" />
			<jsp:param name="pageFolder" value="<%= pageFolder %>" />
		</jsp:include>



		<div class="sidebar-page-container basePage viewRequestAdmin">
			<div class="auto-container">
				<div class="clearfix">
					<div class="content-side">
						<div class="content contentSL">
							<div class="news-block-seven">
							
							<%UserInterface user = (UserInterface) request.getSession().getAttribute("user"); %>
							
							<div id="open">
							<h2>Salve <%=user.getName()%> <%=user.getSurname() %></h2>
									<p><%=user.getEmail() %></p>
									<h4>Questo portale ti consente di monitorare e gestire servizi durante il percorso di laurea e per il post-laurea.</h4>
								<div id="tableView" class="row mt-5">
									
									<div class="col md-1"></div>
									<div class="col md-5"><b>Servizi pre-laurea:</b><li>Convalida della certificazione di esame Inglese</li></div>
									<div class="col md-5"><b>Servizi post-laurea:</b><li>Preferenza di iscrizione ad una Laurea Magistrale</li><li>Iscrizione alla iniziativa di Orientamento in uscita</li><li>Iscrizione a singoli corsi magistrali</li></div>
									<div class="col md-1"></div>
								</div>
								<br>
								
								<div class="form-group">
									<button class="btn btn-primary btn-submit" id="cambiaP" onclick="myFunction()">Modifica password</button>
									</div>
								
								
							</div>
							
							
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
	<script src="<%= request.getContextPath() %>/js/pages/scripts_ReqFormLM.js"></script>
				
<script>
function myFunction() {
  var setP = prompt("Inserisci la nuova password", "");
  if (setP != null) {
	  console.log(setP);
		$.ajax({
			url : absolutePath + "/ServletChangePsw",
			type : "POST",
			dataType : 'JSON',
			async : false,
			data : {
				"password" : setP
			},
			success : function(msg) {
				alert(msg.content);
			},
			error : function(msg) {
				showAlert(1, "errore interno");
			}
		});
  	
  }
}
</script>
	
	
</body>
</html>
