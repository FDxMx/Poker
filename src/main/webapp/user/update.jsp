<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Aggiorna User</title>
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
    	$("#form").submit(function( event ) {
    		var messaggioErrore = "";
    		if ( $( "#nome" ).val() == "" || $( "#nome" ).val() == null){
        	  	messaggioErrore = messaggioErrore + "Campo NOME obbligatorio!\n";
            }
        	if ( $( "#cognome" ).val() == "" || $( "#cognome" ).val() == null){
        		  messaggioErrore = messaggioErrore + "Campo COGNOME obbligatorio!\n";
        	}
    	    if ( $( "#username" ).val() == "" || $( "#username" ).val() == null){
    		  messaggioErrore = messaggioErrore + "Campo USERNAME obbligatorio!\n";
      	    }
    	    if ( $( "#data" ).val() == "" || $( "#data" ).val() == null){
      		  messaggioErrore = messaggioErrore + "Campo DATA REGISTRAZIONE obbligatorio!\n";
        	    }
    	    if(messaggioErrore != ""){
      		  alert(messaggioErrore);
      		  event.preventDefault();
        	  return;
      	    }
    	});
    });
    </script>
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	<main role="main" class="container">
		<div class='card'>
		    <div class='card-header'>
		    	<c:forEach items = "${requestScope.errori}" var = "errore">
		        	<p style = color:red ><c:out value = "${errore}"></c:out> <br></p>
		        </c:forEach>
		        <h5>Aggiorna User</h5> 
		    </div>
		    <div class='card-body'>
					<form id="form" method="post" action="ExecuteUpdateUserServlet" novalidate="novalidate">
					<input type="hidden" name="id" id="id" value="${requestScope.user.id}">
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
								<input type="date" class="form-control" name="data" id="data" placeholder="Inserire la data" value = "${requestScope.user.dataRegistrazione}" required>
							</div>
							<c:if test="${requestScope.user.stato == 'CREATO'}">
							 <div class="form-group col md-4">
								<label>Ruoli</label>
									<c:forEach items="${requestScope.listaRuoli}" var="ruolo"><br>
										<input class="form-check form-check-inline" type="checkbox" value="${ruolo}" id="ruolo" name="ruolo"
									<c:forEach items="${requestScope.user.ruoli}" var="ruoloUser">
										${ruoloUser eq ruolo ? 'checked' : ''}
									</c:forEach>>
									<label> ${ruolo} </label>
									</c:forEach>
							</div>
							</c:if>
						</div>
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Aggiorna</button>
					</form>
			<!-- end card-body -->			   
		    </div>
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
</body>
</html>