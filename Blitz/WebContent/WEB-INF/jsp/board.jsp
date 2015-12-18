<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<body>
	<div id="wrap">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<p class="navbar-text">Tour de : <span id="current-player"></span></p>
				<div class="navbar-nav navbar-right">
				<button type="button" class="btn btn-default" onclick="endTurn()"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>Passer le tour</button>
				<a href="forfeit.html"><button type="button"
					class="btn btn-danger navbar-btn">
					<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>Abandonner
				</button></a>
			</nav>
			</div>
		</nav>

		<div class="container-fluid">
			<div class="row text-center" id="enemy-row"></div>
		</div>
	</div>
	<div id="my-board">
		<div id="my-info" class="col-sm-12">
			<div id="my-dice"></div>
			<button type="button" class="btn btn-default pull-right" id="my-button" disabled="true" onclick="rollDice()">Lancer d&eacute;s</button>
		</div>

		<div id="my-cards" class="col-sm-12">
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
	<link href="css/board.css" rel="stylesheet" type="text/css">
