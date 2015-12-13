<%@ page contentType="text/html; charset=UTF-8" %>
<body>
	<div class="container">
		<div class="row">

			<!-- SIGN-UP -->
			<div class="col-sm-5">
				<div class="panel panel-default">
					<div class="panel-heading">Inscription</div>
					<div class="panel-body">
						<form class="form" id="signup-form" role="form" method="post" action="login.html">
							<!-- NICKNAME -->
							<div class="form-group">
								<label class="control-label" for="signup-nickname">Pseudo :</label> <input
								type="text" class="form-control" name="signup-nickname">
							</div>
							<!-- PASSWORD -->
							<div class="form-group">
								<label class="control-label" for="signup-password">Mot
									de Passe :</label> <input type="password" class="form-control"
									name="signup-password">
								</div>
								<!-- REPEAT PASSWORD -->
								<div class="form-group">
									<label class="control-labl" for="signup-repeat-password">Répétez
										le mot-de-passe :</label> <input type="password" class="form-control"
										name="signup-repeat-password">
									</div>
								</form>
							</div>
							<div class="panel-footer">
								<!-- <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button> -->
								<button type="submit" form="signup-form" class="btn btn-primary" name="connection" value="signup">S'inscrire</button>
							</div>
						</div>
					</div>

					<div class="col-sm-5">
						<!-- SIGN-IN -->
						<div class="panel panel-default">
							<div class="panel-heading">Connexion</div>
							<div class="panel-body">
								<form class="form" id="signin-form" role="form" method="post" action="login.html">
									<!-- NICKNAME -->
									<div class="form-group">
										<label class="control-label" for="signin-nickname">Pseudo :</label> <input
										type="text" class="form-control" name="signin-nickname">
									</div>
									<!-- PASSWORD -->
									<div class="form-group">
										<label class="control-label" for="signin-password">Mot
											de Passe :</label> <input type="password" class="form-control"
											name="signin-password">
										</div>
										<!-- REPEAT PASSWORD -->
										</form>
									</div>
									<div class="panel-footer">
										<!-- <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button> -->
										<button type="submit" form="signin-form" class="btn btn-primary" name="connection" value="signin">Se Connecter</button>
									</div>
								</div>
							</div>
						</div>

					</div>

					<script src="lib/js/signup.js"></script>
					<script src="lib/js/signin.js"></script>
