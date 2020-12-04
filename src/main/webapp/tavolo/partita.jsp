<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it">
<head>

<style>
	   html,body{
       background-image: url('http://getwallpapers.com/wallpaper/full/5/9/c/518758.jpg');
	   background-size: cover;
	   background-repeat: no-repeat;
	   height: 100%;
	   font-family: 'Numans', sans-serif;
	   }
	  </style>
	<jsp:include page="../header.jsp" />
	<title>Partita</title>
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	<main role="main" class="container">
		<div class='card'>
		    <div class='card-header'>
		        <h5>Sei in gioco!</h5> 
		        <div class="alert alert-success  ${requestScope.effettuato == null ?'d-none':''}" role="alert">
		  		${requestScope.effettuato}
		  		</div>
		        <div class="alert alert-success  ${requestScope.vincita == null ?'d-none':''}" role="alert">
		  		${requestScope.vincita}
		  		</div>
		        <div class="alert alert-danger  ${requestScope.perdita == null?'d-none':''}" role="alert">
		 		 ${requestScope.perdita}
		  		</div>
		        <div class="alert alert-danger  ${requestScope.perditaTotale == null?'d-none':''}" role="alert">
		 		 ${requestScope.perditaTotale}
		  		</div>
		    </div>
		    <div class='card-body'>
					<form method="get" novalidate="novalidate">
						<button type="submit" name="submit" value="submit" id="submit" formaction="ExecutePartitaServlet" class="btn btn-danger">Punta</button>
						<button type="submit" name="submit" value="submit" id="submit" formaction="LasciaPartitaServlet" class="btn btn-danger">Lascia</button>
					</form>
			<!-- end card-body -->			   
		    </div>
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
</body>
</html>