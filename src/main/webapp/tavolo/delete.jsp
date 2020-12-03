<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="it">
<head>
<style>
	   html,body{
       background-image: url('http://getwallpapers.com/wallpaper/full/b/1/5/517682.jpg');
	   background-size: cover;
	   background-repeat: no-repeat;
	   height: 100%;
	   font-family: 'Numans', sans-serif;
	   }
	  </style>
	<jsp:include page="../header.jsp" />
	<title>Elimina Tavolo</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Eliminare tavolo?</h5> 
		        
		    </div>
		    <div class='card-body'>

					<form method="get" novalidate="novalidate">
					
						<input type="hidden" name="id" id="id" value="${requestScope.id}">
					
						<button type="submit" name="submit" value="submit" id="submit" formaction="ExecuteDeleteTavoloServlet" class="btn btn-danger">Conferma</button>
						<button type="submit" name="submit" value="submit" id="submit" formaction="ListaTavoliServlet" class="btn btn-danger">No</button>
							
					</form>
			<!-- end card-body -->			   
		    </div>
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>