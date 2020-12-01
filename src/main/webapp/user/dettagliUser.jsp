<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Dettagli User</title>
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	<main role="main" class="container">
		<div class='card'>
		    <div class='card-header'>
		        <h5>Dettagli User</h5> 
		    </div>
		    <div class='card-body'>
				<dl class="row">
				  <dt class="col-sm-3 text-right">Id:</dt>
				  <dd class="col-sm-9">${requestScope.user.id}</dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Nome:</dt>
				  <dd class="col-sm-9">${requestScope.user.nome}</dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Cognome:</dt>
				  <dd class="col-sm-9">${requestScope.user.cognome}</dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Username:</dt>
				  <dd class="col-sm-9">${requestScope.user.username}</dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Esperienza:</dt>
				  <dd class="col-sm-9">${requestScope.user.esperienza}</dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Credito:</dt>
				  <dd class="col-sm-9">${requestScope.user.credito}</dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Data:</dt>
				  <dd class="col-sm-9">${requestScope.user.dataRegistrazione}</dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Stato:</dt>
				  <dd class="col-sm-9">${requestScope.user.stato}</dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Ruoli:</dt>
				  <c:forEach var="ruolo" items="${requestScope.utente.ruoli}">
				  <c:out value = "${ruolo}" /> <br>
				  </c:forEach>
		    	</dl>
			<!-- end card-body -->			   
		    </div>
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
</body>
</html>