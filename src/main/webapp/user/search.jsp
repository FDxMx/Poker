<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it">
<head>
<style type="text/css">
    	body {
		  padding-top: 3.5rem;
		}	
	   html,body{
       background-image: url('http://getwallpapers.com/wallpaper/full/6/4/7/517704.jpg');
	   background-size: cover;
	   background-repeat: no-repeat;
	   height: 100%;
	   font-family: 'Numans', sans-serif;
	   }
    </style>
	<jsp:include page="../header.jsp" />
	<title>Cerca User</title>
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	<main role="main" class="container">
		<div class='card'>
		    <div class='card-header'>
		    <c:forEach items = "${requestScope.errori}" var = "errore">
		        	<div class="alert alert-danger  ${errore == null?'d-none':''}" role="alert">
		  			${errore}
		  			</div>
		        </c:forEach>
		        <p style = color:blue >${requestScope.avvertimento}</p>
		        <h5>Cerca User</h5> 
		    </div>
		    <div class='card-body'>
					<form id="form" method="post" action="ExecuteSearchUserServlet" novalidate="novalidate">
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Nome</label>
								<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" value = "${requestScope.user.nome}" required>
							</div>
							<div class="form-group col-md-6">
								<label>Cognome</label>
								<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome" value = "${requestScope.user.cognome}" required>
							</div>
							<div class="form-group col-md-3">
								<label>Username</label> 
								<input type="text" class="form-control" name="username" id="username" placeholder="Inserire l'username" value = "${requestScope.user.username}" required>
							</div>
							<div class="form-group col-md-3">
								<label>Data Registrazione</label>
								<input type="date" class="form-control" name="dataRegistrazione" id="dataRegistrazione" placeholder="Inserire la data" value = "${requestScope.user.dataRegistrazione}" required>
							</div>
							<div class="form-group col-md-6">
								<label>Stato</label>
								<select id = "stato" name = "stato" class = "form-control" required>
								<option value = ""> Cerca per stato</option>
								<c:forEach items = "${requestScope.stati}" var = "stato">
								<option value ="${stato}" ${stato == requestScope.user.stato ? 'selected' : ''}/>
								<c:out value = "${stato}" />
								</c:forEach>
								</select>
							</div> 
							<div class="form-group col-md-6">
								<label>Ruolo</label>
								<select id = "ruolo" name = "ruolo" class = "form-control" required>
								<option value = ""> Cerca per ruolo</option>
								<c:forEach items = "${requestScope.ruoli}" var = "ruolo">
								<option value ="${ruolo}" ${ruolo == requestScope.user.ruolo ? 'selected' : ''}/>
								<c:out value = "${ruolo}" />
								</c:forEach>
								</select>
							</div>
						</div>
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-danger">Cerca!</button>
					</form>
			<!-- end card-body -->		
			<a style = "color: grey"  href="ListaUserServlet">Indietro <span class="sr-only">(current)</span></a>	   
		    </div>
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
</body>
</html>