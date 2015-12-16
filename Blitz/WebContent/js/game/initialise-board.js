function initialise() {
	var $request = $.ajax({
		url : "initialise-board.html",
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
	.append('<div class="col-md-4 avatar"><img src="" alt="" class="img-responsive"><h3>' + username + '</h3></div>');
	var infoPanel = $('<div class="col-md-8 info-panel">');
	var infoRow = $('<div class="row info">');
	infoRow.append('<p>Cartes : <span class="nb-cards">' + nbCards + '</span></p><p>D&eacute;s : <span class="nb-dice">' + nbDice + '</span></p>');
	var buttons = $('<div class="row buttons">');
	infoPanel.append(infoRow);
	infoPanel.append(buttons);

	enemy.append(infoPanel);
}

$(function() {
	initialise();
});
