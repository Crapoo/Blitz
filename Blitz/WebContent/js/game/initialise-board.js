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
		createMyCards(response.myCards);
	});

	$request.fail(function(xhr, textStatus, errorThrown) {
		console.log(errorThrown);
		// alert(errorThrown);
	});
}

function createEnemy(username, nbCards, nbDice, avatarPath) {
	var enemy = $('<div class="enemy col-md-3" id="' + username + '">');
	enemy
			.append('<div class="col-md-4 avatar"><img src="" alt="" class="img-responsive"><h3>'
					+ username + '</h3></div>');
	var infoPanel = $('<div class="col-md-8 info-panel">');
	var infoRow = $('<div class="row info">');
	infoRow.append('<p>Cartes : <span class="nb-cards">' + nbCards
			+ '</span></p><p>D&eacute;s : <span class="nb-dice">' + nbDice
			+ '</span></p>');
	var buttons = $('<div class="row buttons">');
	infoPanel.append(infoRow);
	infoPanel.append(buttons);

	enemy.append(infoPanel);

	return enemy;
}

function createMyCards(cards) {
	var myCards = $('#my-cards');

	$.each(cards, function(i, card) {
		var cardElt = createCard(card);
		myCards.append(cardElt);
	});
}

function createCard(card) {
	var cardElt = $('<div class="card col-xs-4 col-md-2"></div>');
	var cost = $('<ul class="cost"></ul>');
	if (card.cost == 0) {
		cost.append($('<li>Gratuit</li>'));
	} else {
		for (var i = 0; i < card.cost; i++) {
			var costLi = $('<li><img src="images/shekel.png"/></li>')
			cost.append(costLi);
		}
	}
	cardElt.append(cost);
	// TODO : Matt - Ajouter des images?
	// cardElt.append($('<img src="' + card.src + '" />'));
	cardElt.append($('<p>' + card.effect + '</p>'));

	// TODO : Matt - rendre cliquable uniquement si tour du joueur ET peut payer
	// le prix
	cardElt.append($('<button class="btn btn-default"  data-id="' + card.id + '" data-effectCode="' + card.effectCode + '">Utiliser</button>'));
	return cardElt;
}

$(function() {
	initialise();
});
