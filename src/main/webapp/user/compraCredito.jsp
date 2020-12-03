<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Compra Credito</title>
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
    	$("#form").submit(function( event ) {
    		var messaggioErrore = "";
    	  if ( $( "#credito" ).val() == "" || $( "#credito" ).val() == null){
    	  	messaggioErrore = messaggioErrore + "Campo CREDITO obbligatorio!\n";
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
		        <p style = color:red >${requestScope.avvertimento}</p>
		        <h5>Compra credito</h5> 
		    </div>
		    <div class='card-body'>
					<form id="form" method="post" action="ExecuteCompraCreditoServlet" novalidate="novalidate">
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Immetti una cifra</label>
								<input type="number" name="credito" id="credito" class="form-control" placeholder="Inserire una cifra" required>
							</div>
						</div>
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Compra!</button>
					</form>
			<!-- end card-body -->			   
		    </div>
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
</body>
</html>