<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="it.poker.model.Tavolo"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Lista Tavoli</title>
<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">
</head>
<style>
	   html,body{
       background-image: url('http://getwallpapers.com/wallpaper/full/b/1/5/517682.jpg');
	   background-size: cover;
	   background-repeat: no-repeat;
	   height: 100%;
	   font-family: 'Numans', sans-serif;
	   }
	  </style>
<body>
	<jsp:include page="../navbar.jsp" />
	<main role="main" class="container">
		<div class='card'>
			<div class='card-body'>
				<div class='card-header'>
				<p style = color:blue >${requestScope.avvertimento}</p>
				<p style = color:red >${requestScope.errore}</p>
				<p style = color:green >${requestScope.effettuato}</p>
				<a class="btn-lg btn-danger" href="PrepareInsertTavoloServlet">Crea un Tavolo</a>
       	 		<a class="btn-lg btn-danger" href="PrepareSearchTavoloServlet">Search Tavolo</a>
			</div>
			&nbsp;
				<h5>I MIEI TAVOLI</h5>
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
									<td>
										<a class="btn  btn-sm btn-outline-secondary"
										href="GiocatoriTavoloServlet?idParametro=${tavolo.id}">Giocatori</a>
										<a class="btn  btn-sm btn-outline-primary ml-2 mr-2"
										href="PrepareUpdateTavoloServlet?idParametro=${tavolo.id}">Aggiorna</a>
										<a class="btn btn-outline-danger btn-sm"
										href="PrepareDeleteTavoloServlet?idParametro=${tavolo.id}">Elimina</a>
									</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
				<!-- end card-body -->
			</div>
		</div>
		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />
</body>
</html>