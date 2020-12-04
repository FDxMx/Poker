<!doctype html>
<html lang="it">
	<head>
	  <!-- Required meta tags -->
	  <meta charset="utf-8">
	  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	  <!-- Bootstrap CSS -->
	  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" >
	  <title>Accedi</title>
	  <!-- Favicons -->
	<link rel="apple-touch-icon" href="${pageContext.request.contextPath}/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
	<link rel="icon" href="${pageContext.request.contextPath}/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
	<link rel="icon" href="${pageContext.request.contextPath}/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
	<link rel="manifest" href="${pageContext.request.contextPath}/assets/img/favicons/manifest.json">
	<link rel="mask-icon" href="${pageContext.request.contextPath}/assets/img/favicons/safari-pinned-tab.svg" ><!-- color="#563d7c" -->
	<link rel="icon" href="${pageContext.request.contextPath}/assets/brand/straight-poker.svg">
	<meta name="msapplication-config" content="${pageContext.request.contextPath}/assets/img/favicons/browserconfig.xml">
	<meta name="theme-color" content="#563d7c">
	   <style>
	   html,body{
       background-image: url('http://getwallpapers.com/wallpaper/full/c/a/a/567504.jpg');
	   background-size: cover;
	   background-repeat: no-repeat;
	   height: 100%;
	   font-family: 'Numans', sans-serif;
	   }
	  </style>
	  <!-- Custom styles for this template -->
	  <link href="${pageContext.request.contextPath}/assets/css/signin.css" rel="stylesheet">
	  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
      <script type="text/javascript">
      $(document).ready(function() {
    	$("#form").submit(function( event ) {
    		var messaggioErrore = "";
    		if ( $( "#username" ).val() == "" || $( "#username" ).val() == null){
        	  	messaggioErrore = messaggioErrore + "Campo USERNAME obbligatorio!\n";
            }
        	if ( $( "#password" ).val() == "" || $( "#password" ).val() == null){
        		  messaggioErrore = messaggioErrore + "Campo PASSWORD obbligatorio!\n";
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

<body class="text-center">
  <div class="container">
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
            <form id = "form" class="form-signin" action="LogInServlet" method="post" novalidate = "novalidate">
		  <img class="mb-4" src="${pageContext.request.contextPath}/assets/brand/straight-poker.svg" alt="" width="72" height="72"><br>
		  <div class="alert alert-danger  ${requestScope.errore == null?'d-none':''}" role="alert">
		  ${requestScope.errore}
		  </div>
		  <div class="alert alert-success  ${requestScope.effettuato == null ?'d-none':''}" role="alert">
		  ${requestScope.effettuato}
		  </div>
		  <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
		  <label for="inputUsername" class="sr-only">Username</label>
		  <input type="text" id="username" name="username" class="form-control" placeholder="Username" required autofocus>
		  &nbsp;
		  <label for="inputPassword" class="sr-only">Password</label>
		  <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
		  &nbsp;
		  &nbsp;
		  <div class="checkbox mb-3">
		    <label>
		    <button id="submit" class="btn btn-danger btn-lg btn-icon" type="submit" name="submit" value="submit">Sign in</button>
		      <!-- <input type="checkbox" value="remember-me"> Remember me -->
		    </label>
		  </div>
		  <a style = "color: red" class="nav-link" href="registrazione.jsp">Non sono registrato... <span class="sr-only">(current)</span></a>
		  <p class="mt-5 mb-3 text-muted">&copy; 2017-2020</p>
		</form>	
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>