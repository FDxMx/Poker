<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Partita</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
		<p style = color:green >${requestScope.effettuato}</p>
		<div class='card'>
		    <div class='card-header'>
		        <h5>Sei in gioco!</h5> 
		        <p style = color:green >${requestScope.vincita}</p>
		        <p style = color:red >${requestScope.perdita}</p>
		        <p style = color:red >${requestScope.perditaTotale}</p>
		    </div>
		    <div class='card-body'>

					<form method="get" novalidate="novalidate">
					
						<button type="submit" name="submit" value="submit" id="submit" formaction="ExecutePartitaServlet" class="btn btn-primary">Punta</button>
						<button type="submit" name="submit" value="submit" id="submit" formaction="LasciaPartitaServlet" class="btn btn-primary">Lascia</button>
							
					</form>
			<!-- end card-body -->			   
		    </div>
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>