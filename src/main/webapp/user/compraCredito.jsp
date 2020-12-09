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
		        <div class="alert alert-primary  ${requestScope.avvertimento == null?'d-none':''}" role="alert">
		 		 ${requestScope.avvertimento}
		 	 	</div>
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
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-danger">Compra!</button>
					</form>
			<!-- end card-body -->	
			<a style = "color: grey"  href="PlayManagementServlet">Indietro <span class="sr-only">(current)</span></a>			   
		    </div>
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
</body>
</html>