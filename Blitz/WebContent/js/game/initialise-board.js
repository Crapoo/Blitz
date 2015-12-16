$(function() {
	function initialise() {
		var $request = $.ajax({
			url : "initialise-game.html",
			type : "post",
			dataType : "json",
		});

		$request.done(function(response, textStatus, xhr) {
			console.log(response);
		});

		$request.fail(function(xhr, textStatus, errorThrown) {
			console.log(errorThrown);
			// alert(errorThrown);
		});
	}

	function createEnemy(username, nbCards, nbDice, avatarPath) {
		var enemy = $('<div class="enemy" id="' + username + '">');
		enemy
				.append('<div class="col-md-4 avatar"><img src="" alt="" class="img-responsive"></div>');
		var infoPanel = $('<div class="col-md-8 info-panel">');
	}

	initialise();
});
