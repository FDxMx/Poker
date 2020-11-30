<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it">
  <head>
    <jsp:include page="../header.jsp" />
    <!-- Custom styles for this template -->
    <link href="./assets/css/global.css" rel="stylesheet">
    <style type="text/css">
    	body {
		  padding-top: 3.5rem;
		}	
    </style>
    <title>Poker</title>
  </head>
  <body>
	<jsp:include page="../navbar.jsp"></jsp:include>
	<main role="main">

	  <div class="jumbotron" >
	    <div class="container">
	    <p style = color:green >${requestScope.effettuato}</p>
	      <h1 class="display-3">PLAY MANAGEMENT!</h1>
	      <p><a class="btn btn-primary btn-lg" href="PrepareCompraCreditoServlet" role="button">Compra credito &raquo;</a></p>
	      <p><a class="btn btn-primary btn-lg" href="ListaTavoliTotaliServlet" role="button">Cerca partita &raquo;</a></p>
	   	  <p><a class="btn btn-primary btn-lg" href="PlayManagementServlet" role="button">Recupera partita &raquo;</a></p>
	    </div>
	  </div>
	</main>
	<jsp:include page="../footer.jsp" />
  </body>
</html>