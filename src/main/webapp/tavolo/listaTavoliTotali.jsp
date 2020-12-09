<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="it.poker.model.Tavolo"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="it">
<head>
<style>
	   html,body{
       background-image: url('http://getwallpapers.com/wallpaper/full/8/7/b/517637.jpg');
	   background-size: cover;
	   background-repeat: no-repeat;
	   height: 100%;
	   font-family: 'Numans', sans-serif;
	   }
	  </style>
<jsp:include page="../header.jsp" />
<title>Lista Tavoli</title>
<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	<main role="main" class="container">
		<div class='card'>
			<div class='card-body'>
				<div class='card-header'>
				<div class="alert alert-primary  ${requestScope.avvertimento == null?'d-none':''}" role="alert">
		 		 ${requestScope.avvertimento}
		 	 	</div>
				<div class="alert alert-danger  ${requestScope.errore == null?'d-none':''}" role="alert">
		 		 ${requestScope.errore}
		  		</div>
				<div class="alert alert-success  ${requestScope.effettuato == null ?'d-none':''}" role="alert">
		  		${requestScope.effettuato}
		  		</div>
       	 		<a class="btn btn-danger " href="PrepareSearchTavoloConUserServlet">Search Tavolo</a>
			</div>
			&nbsp;
				<h5>TAVOLI DISPONIBILI</h5>
				<div class='table-responsive'>
					<table class='table table-striped '>
						<thead>
							<tr>
								<th>Id</th>
								<th>Esperienza Minima</th>
								<th>Credito Minimo</th>
								<th>Denominazione</th>
								<th>Data Creazione</th>
								<th>Creatore</th>
								<th>Azioni</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="tavolo" items="${requestScope.listaTavoli}">
								<tr>
									<td><c:out value="${tavolo.id}" /></td>
									<td><c:out value="${tavolo.esperienzaMinima}" /></td>
									<td><c:out value="${tavolo.creditoMinimo}" /></td>
									<td><c:out value="${tavolo.denominazione}" /></td>
									<td><c:out value="${tavolo.dataCreazione}" /></td>
									<td><c:out value="${tavolo.creatoreTavolo.nome} ${tavolo.creatoreTavolo.cognome}" /></td>
									<td><a class="btn  btn-sm btn-outline-success" href="PreparePartitaServlet?idParametro=${tavolo.id}">Gioca</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- end card-body -->
				<a style = "color: grey"  href="PlayManagementServlet">Indietro <span class="sr-only">(current)</span></a>
			</div>
		</div>
		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />
</body>
</html>