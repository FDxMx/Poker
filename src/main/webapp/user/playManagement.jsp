<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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

	    <div class="container">
	      <h1 style = "color: white" class="display-3">PLAY MANAGEMENT!</h1>
	      <div class="alert alert-success  ${requestScope.effettuato == null ?'d-none':''}" role="alert">
		  ${requestScope.effettuato}
		  </div>
		  <div class="alert alert-primary  ${requestScope.avvertimento == null?'d-none':''}" role="alert">
		  ${requestScope.avvertimento}
		  </div>
	      <p><a class="btn btn-danger btn-lg" href="PrepareCompraCreditoServlet" role="button">Compra credito &raquo;</a></p>
	      <c:if test="${sessionScope.userSession.tavolo == null}">
	      <p><a class="btn btn-danger btn-lg" href="ListaTavoliTotaliServlet" role="button">Cerca partita &raquo;</a></p>
	      </c:if>
	      <c:if test="${sessionScope.userSession.tavolo != null}">
	   	  <p><a class="btn btn-danger btn-lg" href="RecuperaParitaServlet" role="button">Recupera partita &raquo;</a></p>
	   	  </c:if>
	   	  <p style = color:red >${requestScope.errore}</p>
	    </div>
	  <div class="container">
	    <hr>
	  </div> 
	</main>
	<jsp:include page="../footer.jsp" />
  </body>
</html>