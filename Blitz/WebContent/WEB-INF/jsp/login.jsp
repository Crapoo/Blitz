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
								type="text" class="form-control" id="signup-nickname">
							</div>
							<!-- PASSWORD -->
							<div class="form-group">
								<label class="control-label" for="signup-password">Mot
									de Passe :</label> <input type="password" class="form-control"
									id="signup-password">
								</div>
								<!-- REPEAT PASSWORD -->
								<div class="form-group">
									<label class="control-labl" for="signup-repeat-password">Répétez
										le mot-de-passe :</label> <input type="password" class="form-control"
										id="signup-repeat-password">
									</div>
								</form>
							</div>
							<div class="panel-footer">
								<!-- <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button> -->
								<button type="submit" form="signup-form" class="btn btn-primary" id="signup-button">S'inscrire</button>
							</div>
						</div>
					</div>

					<div class="col-sm-5">
						<!-- SIGN-IN -->
					</div>
				</div>

			</div>

			<script src="lib/js/signup.js"></script>
			<script src="lib/js/signin.js"></script>
