<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<body>
	<div id="wrap">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<p class="navbar-text" id="turn-info">Test turn</p>
				<a href="disconnect.html"><button type="button"
						class="btn btn-default navbar-btn navbar-right">
						<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>Abandonner
					</button></a>
			</div>
		</nav>
		<!-- TODO Use datas to pass elements to functions -->
		<button type="button" class="btn btn-success" data-id="test"
			onclick="rollDice(this)"></button>

		<div class="container-fluid">
			<div class="row text-center" id="enemy-row"></div>
		</div>
	</div>
	<div class="row" id="my-board">
		<div id="my-cards" class="col-md-9">
		
		</div>
		<div id="my-dice" class="col-md-3"></div>
	</div>

	<script src="js/game/initialise-board.js"></script>
	<script src="js/game/compute-action.js"></script>
	<link href="css/board.css" rel="stylesheet" type="text/css">