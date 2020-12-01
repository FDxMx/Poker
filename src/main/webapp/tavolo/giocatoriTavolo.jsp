<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="it.poker.model.Tavolo"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Lista Giocatori</title>
<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	<main role="main" class="container">
		<div class='card'>
			<div class='card-body'>
				<div class='card-header'>
			</div>
			&nbsp;
				<h5>Lista Giocatori Nel Tavolo</h5>
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
								<th>Data Registrazione</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="user" items="${requestScope.giocatori}">
								<tr>
									<td><c:out value="${user.id}" /></td>
									<td><c:out value="${user.nome}" /></td>
									<td><c:out value="${user.cognome}" /></td>
									<td><c:out value="${user.username}" /></td>
									<td><c:out value="${user.esperienza}" /></td>
									<td><c:out value="${user.credito}" /></td>
									<td><c:out value="${user.dataRegistrazione}" /></td>
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