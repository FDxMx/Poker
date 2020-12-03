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
	    <p style = color:blue >${requestScope.avvertimento}</p>
	      <h1 class="display-3">PLAY MANAGEMENT!</h1>
	      <p><a class="btn btn-primary btn-lg" href="PrepareCompraCreditoServlet" role="button">Compra credito &raquo;</a></p>
	      <c:if test="${sessionScope.userSession.tavolo == null}">
	      <p><a class="btn btn-primary btn-lg" href="ListaTavoliTotaliServlet" role="button">Cerca partita &raquo;</a></p>
	      </c:if>
	      <c:if test="${sessionScope.userSession.tavolo != null}">
	   	  <p><a class="btn btn-primary btn-lg" href="RecuperaParitaServlet" role="button">Recupera partita &raquo;</a></p>
	   	  </c:if>
	   	  <p style = color:red >${requestScope.errore}</p>
	    </div>
	  </div>
	  <div class="container">
	    <!-- Example row of columns -->
	    <div class="row">
	      <div class="col-md-4">
	        <h2>Heading</h2>
	        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
	        <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>
	      </div>
	      <div class="col-md-4">
	        <h2>Heading</h2>
	        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
	        <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>
	      </div>
	      <div class="col-md-4">
	        <h2>Heading</h2>
	        <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
	        <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>
	      </div>
	    </div>
	    <hr>
	  </div> 
	</main>
	<jsp:include page="../footer.jsp" />
  </body>
</html>