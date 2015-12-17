<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<body>
	<div id="wrap">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<p class="navbar-text">Tour de : <span id="current-player"></span></p>
				<a href="forfeit.html"><button type="button"
					class="btn btn-default navbar-btn navbar-right">
					<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>Abandonner
				</button></a>
			</div>
		</nav>

		<div class="container-fluid">
			<div class="row text-center" id="enemy-row"></div>
		</div>
	</div>
	<div id="my-board">
		<div id="my-info" class="col-sm-12">
			<div id="my-dice"></div>
			<button type="button" class="btn btn-default pull-right" id="my-button" disabled="true" data-action-code="20" onclick="dispatchAction(this)">Lancer d&eacute;s</button>
		</div>

		<div id="my-cards" class="col-sm-12">
		</div>
	</div>

	<script src="js/game/initialise-board.js"></script>
	<script src="js/game/compute-action.js"></script>
	<script src="js/game/refresh-board.js"></script>
	<link href="css/board.css" rel="stylesheet" type="text/css">
