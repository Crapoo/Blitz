<%@ page contentType="text/html; charset=UTF-8" %>
<body>
	<div class="container">

		<div class="text-center">
			<h1>Blitz !</h1>
			<h2>Un jeu auquel m&ecirc;me toi tu peux jouer!</h2>
		</div>

		<div class="row vertical-align">

			<!-- SIGN-UP -->
			<div class="col-sm-5">
				<div class="panel panel-default">
					<div class="panel-heading">Inscription</div>
					<div class="panel-body">
						<form class="form" id="signup-form" role="form" method="post" action="signup.html">
							<!-- USERNAME -->
							<div class="form-group">
								<label class="control-label" for="signup-username">Pseudo :</label> <input
								type="text" class="form-control" name="signup-username">
							</div>
							<!-- PASSWORD -->
							<div class="form-group">
								<label class="control-label" for="signup-password">Mot
									de Passe :</label> <input type="password" class="form-control"
									name="signup-password">
								</div>
								<!-- REPEAT PASSWORD -->
								<div class="form-group">
									<label class="control-labl" for="signup-repeat-password">R&eacute;p&eacute;tez
										le mot-de-passe :</label> <input type="password" class="form-control"
										name="signup-repeat-password">
									</div>
								</form>
								<c:if test='${status == "signup-error"}'>
									<div class="alert alert-danger">
										<span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
										&nbsp;&nbsp;${applicationScope['error-message']}
									</div>
								</c:if>
							</div>
							<div class="panel-footer">
								<button type="submit" form="signup-form" class="btn btn-primary" name="connection" value="signup">S'inscrire</button>
							</div>
						</div>
					</div>

					<div class="col-sm-5">
						<!-- SIGN-IN -->
						<div class="panel panel-default">
							<div class="panel-heading">Connexion</div>
							<div class="panel-body">
								<form class="form" id="signin-form" role="form" method="post" action="signin.html">
									<!-- USERNAME -->
									<div class="form-group">
										<label class="control-label" for="signin-username">Pseudo :</label> <input
										type="text" class="form-control" name="signin-username">
									</div>
									<!-- PASSWORD -->
									<div class="form-group">
										<label class="control-label" for="signin-password">Mot
											de Passe :</label> <input type="password" class="form-control"
											name="signin-password">
										</div>
										<!-- REPEAT PASSWORD -->
									</form>
									<c:if test='${status == "signin-error"}'>
										<div class="alert alert-danger">
											<span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
											&nbsp;&nbsp;${applicationScope['error-message']}
										</div>
									</c:if>
								</div>
								<div class="panel-footer">
									<button type="submit" form="signin-form" class="btn btn-primary" name="connection" value="signin">Se Connecter</button>
								</div>
							</div>
						</div>
					</div>

				</div>

				<link href="css/login.css" rel="stylesheet" type="text/css">
