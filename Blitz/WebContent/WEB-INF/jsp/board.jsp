<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<body>
	<div class="container-fluid">
		<nav class="navbar navbar-default">
			<p class="navbar-text lead" id="my-username"></p>
			<p class="navbar-text">Tour de : <mark><span id="current-player"></span></mark></p>
			<div class="navbar-nav navbar-right">
				<button type="button" class="btn btn-default" id="#end-turn" onclick="endTurn()"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>Passer le tour</button>
				<a href="forfeit.html"><button type="button"
					class="btn btn-danger navbar-btn">
					<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>Abandonner
				</button></a>
			</div>
		</nav>

		<div class="container-fluid">
			<div class="row text-center" id="enemy-row"></div>
		</div>

		<div id="my-board">
			<div id="my-info" class="row text-center">
				<div id="my-dice" class="text-center"></div>
				<button type="button" class="btn btn-default pull-right" id="my-button" disabled="true" onclick="rollDice()">Lancer d&eacute;s</button>
			</div>

			<div id="my-cards" class="row text-center">
			</div>
		</div>
	</div>

	<!-- TARGET ENEMY MODAL -->
	<div class="modal fade" id="target-enemy-modal" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">Choisissez votre cible</h4>
				</div>
				<div class="modal-body">
					<div class="list-group" id="target-list">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
				</div>
			</div>
		</div>
	</div>

	<script src="js/game/initialise-board.js"></script>
	<script src="js/game/compute-action.js"></script>
	<script src="js/game/refresh-board.js"></script>

	<!-- Toastr https://github.com/CodeSeven/toastr -->
	<script src="libs/toastr/toastr.min.js"></script>
	<link href="libs/toastr/toastr.min.css" rel="stylesheet" type="text/css">

		<link href="css/board.css" rel="stylesheet" type="text/css">
