<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Cerca Tavolo</title>
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
</head>
<style>
	   html,body{
       background-image: url('http://getwallpapers.com/wallpaper/full/8/7/b/517637.jpg');
	   background-size: cover;
	   background-repeat: no-repeat;
	   height: 100%;
	   font-family: 'Numans', sans-serif;
	   }
	  </style>
<body>
	<jsp:include page="../navbar.jsp" />
	<main role="main" class="container">
		<div class='card'>
		    <div class='card-header'>
		        <h5>Cerca un tavolo</h5> 
		        <div class="alert alert-primary  ${requestScope.avvertimento == null?'d-none':''}" role="alert">
		 		 ${requestScope.avvertimento}
		 	 	</div>
		    </div>
		    <div class='card-body'>
					<form id="form" method="post" action="ExecuteSearchTavoloConUserServlet" novalidate="novalidate">
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Credito Minimo</label>
								<input type="number" name="creditoMinimo" id="creditoMinimo" class="form-control" placeholder="Inserire il credito minimo" value = "${requestScope.tavolo.creditoMinimo}" required>
							</div>
							<div class="form-group col-md-3">
								<label>Denominazione</label>
								<input type="text" class="form-control" name="denominazione" id="denominazione" placeholder="Inserire la denominazione" value = "${requestScope.tavolo.denominazione}" required>
							</div>
							<div class="form-group col-md-3">
								<label>Data Creazione</label>
								<input type="date" class="form-control" name="dataCreazione" id="dataCreazione" placeholder="Inserire la data" value = "${requestScope.tavolo.dataCreazione}" required>
							</div>
							<div class="form-group col-md-6">
								<label>Giocatore</label>
								<select id = "user" name = "user" class = "form-control" required>
								<option value = ""> Cerca per giocatore</option>
								<c:forEach items = "${requestScope.giocatori}" var = "user">
								<option value ="${user.id}" ${user.id == requestScope.tavolo.user.id ? 'selected' : ''}/>
								<c:out value = "${user.nome} ${user.cognome}" />
								</c:forEach>
								</select>
							</div> 
						</div>
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-danger">Cerca!</button>
					</form>
			<!-- end card-body -->		
			<a style = "color: grey"  href="ListaTavoliTotaliServlet">Indietro <span class="sr-only">(current)</span></a>	   
		    </div>
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
</body>
</html>