<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it">
<head>
<style>
	   html,body{
       background-image: url('http://getwallpapers.com/wallpaper/full/c/a/a/567504.jpg');
	   background-size: cover;
	   background-repeat: no-repeat;
	   height: 100%;
	   font-family: 'Numans', sans-serif;
	   }
	  </style>
	<jsp:include page="./header.jsp" />
	<title>Registrazione</title>
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
    	  if ( $( "#password" ).val() == "" || $( "#password" ).val() == null){
    		  messaggioErrore = messaggioErrore + "Campo PASSWORD obbligatorio!\n";
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
	<main role="main" class="container">
		<div class='card'>
		    <div class='card-header'>
		    <div class="alert alert-danger  ${requestScope.erroreUsername == null?'d-none':''}" role="alert">
		 		 ${requestScope.erroreUsername}
		    </div>
		        <c:forEach items = "${requestScope.errori}" var = "errore">
		        	<div class="alert alert-danger  ${errore == null?'d-none':''}" role="alert">
		  			${errore}
		  			</div>
		        </c:forEach>
		        <h5>Registrati</h5> 
		    </div>
		    <div class='card-body'>
					<form id="form" method="post" action="RegistrazioneUserServlet" novalidate="novalidate">
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
								<label>Password</label>
								<input type="password" class="form-control" name="password" id="password" placeholder="Inserire la password" value = "${requestScope.user.password}" required>
							</div>
							<div class="form-group col-md-3">
								<label>Data</label>
								<input type="date" class="form-control" name="data" id="data" placeholder="Inserire la data" value = "${requestScope.user.dataRegistrazione}" required>
							</div>
						</div>
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-danger">Registrami!</button>
						<a style = "color: red" class="nav-link" href="login.jsp">...Torna alla log in <span class="sr-only">(current)</span></a>
					</form>
			<!-- end card-body -->			   
		    </div>
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="./footer.jsp" />
</body>
</html>