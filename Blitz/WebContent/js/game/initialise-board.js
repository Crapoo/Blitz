function initialise() {
	var $request = $.ajax({
		url : "initialise-board.html",
		type : "post",
		dataType : "json",
	});

	$request.done(function(response, textStatus, xhr) {
		var nbCards = response.nbCards;
		var nbDice = response.nbDice;
		$.each(response.players, function(i, player) {
			$('#enemy-row').append(createEnemy(player, nbCards, nbDice, ""));
		});
	});

	$request.fail(function(xhr, textStatus, errorThrown) {
		console.log(errorThrown);
		// alert(errorThrown);
	});
}

function createEnemy(username, nbCards, nbDice, avatarPath) {
	var enemy = $('<div class="enemy col-md-3" id="' + username + '">');
	enemy
	.append('<div class="col-md-4 avatar"><img src="" alt="" class="img-responsive"><h3>' + username + '</h3></div>');
	var infoPanel = $('<div class="col-md-8 info-panel">');
	var infoRow = $('<div class="row info">');
	infoRow.append('<p>Cartes : <span class="nb-cards">' + nbCards + '</span></p><p>D&eacute;s : <span class="nb-dice">' + nbDice + '</span></p>');
	var buttons = $('<div class="row buttons">');
	infoPanel.append(infoRow);
	infoPanel.append(buttons);

	enemy.append(infoPanel);

	return enemy;
}

$(function() {
	initialise();
});