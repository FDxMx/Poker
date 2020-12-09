<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="it.poker.model.Tavolo"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Lista User</title>
<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">
</head>
<style type="text/css">
    	body {
		  padding-top: 3.5rem;
		}	
	   html,body{
       background-image: url('http://getwallpapers.com/wallpaper/full/6/4/7/517704.jpg');
	   background-size: cover;
	   background-repeat: no-repeat;
	   height: 100%;
	   }
    </style>
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
       	 		<a class="btn btn-danger " href="PrepareSearchUserServlet">Search Users</a>
			</div>
			&nbsp;
				<h5>Lista User</h5>
				<div class='table-responsive'>
					<table class='table table-striped '>
						<thead>
							<tr>
								<th>Id</th>
								<th>Nome</th>
								<th>Cognome</th>
								<th>Username</th>
								<th>Esperienza</th>
								<th>Credito</th>
								<th>Data Reg.</th>
								<th>Stato</th>
								<th>Azioni</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="user" items="${requestScope.listaUser}">
								<tr>
									<td><c:out value="${user.id}" /></td>
									<td><c:out value="${user.nome}" /></td>
									<td><c:out value="${user.cognome}" /></td>
									<td><c:out value="${user.username}" /></td>
									<td><c:out value="${user.esperienza}" /></td>
									<td><c:out value="${user.credito}" /></td>
									<td><c:out value="${user.dataRegistrazione}"/></td>
									<td><c:out value="${user.stato}" /></td>
									<td><a class="btn  btn-sm btn-outline-secondary"
										href="DettagliUserServlet?idParametro=${user.id}">Dettagli</a>
										<a class="btn  btn-sm btn-outline-primary ml-2 mr-2"
										href="PrepareUpdateUserServlet?idParametro=${user.id}">Aggiorna</a>
										<a class="btn btn-outline-danger btn-sm"
										href="PrepareAbilita_DisabilitaServlet?idParametro=${user.id}">Abilita/Disabilita</a>
									</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
					<a style = "color: grey" class="nav-link" href="home.jsp">Indietro <span class="sr-only">(current)</span></a>
				</div>
				<!-- end card-body -->
			</div>
		</div>
		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />
</body>
</html>