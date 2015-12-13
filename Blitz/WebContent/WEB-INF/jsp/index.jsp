<body>
<div class=".container-fluid">
<!-- <h1>Bienvenue au e-Bar</h1>
<hr
	<a href=<c:url  value="carte.html" />>Notre carte</a><br>
	<a href=<c:url  value="rechercher.html" />>Rechercher des bi&egrave;res</a><br>
	Nous contacter: <a href = "mailto:${initParam.eBarEmail}">eBar</a> -->

<nav class="pull-right">
	<ul class="nav nav-pills">
		<li role="presentation">
			<button type="button" id="signin-button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#signup-modal">
				S'inscrire
			</button></li>
			<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#signin-modal">
				Se Connecter
			</button></li>
	</ul>
</nav>

<!--  SIGN-UP MODAL -->
<div class="modal fade" id="signup-modal" tabindex="-1" role="dialog" aria-labelledby="signup-modal-label">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Annuler">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="signup-modal-label">Inscription</h4>
			</div>
			<div class="modal-body">
				<p>Blabla</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
				<button type="button" class="btn btn-primary">S'inscrire</button>
			</div>
		</div>
	</div>
</div>

</div>

<script src="lib/js/signup.js"></script>
<script src="lib/js/signin.js"></script>
