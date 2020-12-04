<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it">
  <head>
    <jsp:include page="./header.jsp" />
    <!-- Custom styles for this template -->
    <link href="./assets/css/global.css" rel="stylesheet">
    <style type="text/css">
    	body {
		  padding-top: 3.5rem;
		}	
	   html,body{
       background-image: url('http://getwallpapers.com/wallpaper/full/4/8/6/567374.jpg');
	   background-size: cover;
	   background-repeat: no-repeat;
	   height: 100%;
	   font-family: 'Numans', sans-serif;
	   }
    </style>
    <title>Poker</title>
  </head>
  <body class="text-left">
	<jsp:include page="./navbar.jsp"></jsp:include>
	&nbsp;
	&nbsp;
	<main role="main">
	  <!-- Main jumbotron for a primary marketing message or call to action -->
	    <div class="container">
	      <h1 style = "color: white" class="display-3">Benvenuto in<br> POKER SPRING DATA!</h1>
	      &nbsp;
		  &nbsp;
	      <c:forEach var="ruolo" items="${sessionScope.userSession.ruoli}">
          <c:if test="${ruolo == 'ADMIN_ROLE' or ruolo == 'SPECIAL_PLAYER_ROLE'}">
	      <p><a class="btn btn-danger btn-lg" href="ListaTavoliServlet" role="button">I miei tavoli &raquo;</a></p>
	      </c:if>
       	  </c:forEach>
	      <p><a class="btn btn-danger btn-lg" href="PlayManagementServlet" role="button">Play Management &raquo;</a></p>
	      <c:forEach var="ruolo" items="${sessionScope.userSession.ruoli}">
          <c:if test="${ruolo == 'ADMIN_ROLE'}">
	      <p><a class="btn btn-danger btn-lg" href="ListaUserServlet" role="button">Gestione Amministrazione &raquo;</a></p>
	  	  </c:if>
       	  </c:forEach>
	    </div>
	</main>
	<jsp:include page="./footer.jsp" />
  </body>
</html>