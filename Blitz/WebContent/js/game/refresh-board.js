var currentPlayer = "";

function refresh() {
	var $request = $.ajax({
		url : "update-game.html",
		type : "post",
		dataType : "json",
	});

	$request.done(function(response, textStatus, xhr) {
		if (typeof response.hasWon !== 'undefined') {
			clearInterval(refreshBoardInterval);
			endGame(response.hasWon);
		} else {
			$.each(response.players, function(i, player) {
				updateInfoOf(player);
			});

			updateMyInfo(response.nbDice, response.myCards, response.myTurn);

			if (currentPlayer !== response.currentPlayer) {
				currentPlayer = response.currentPlayer;
				toastr.success("Tour de " + currentPlayer);
			}

			$('#current-player').text(currentPlayer);
		}
	});

	$request.fail(function(xhr, textStatus, errorThrown) {
		//alert(errorThrown);
		console.log(errorThrown);
		clearInterval(refreshBoardInterval);
	});
}

function updateInfoOf(player) {
	$('#' + player.username).find(".nb-cards").text(player.nbCards);
	$('#' + player.username).find('.nb-dice').text(player.nbDice);
}

function updateMyInfo(nbDice, cards, myTurn) {
	$('#my-dice').text(nbDice);
	updateMyCards(cards);
	updateMyButton(myTurn);

	isMyTurn = myTurn;
}

function updateMyButton(myTurn) {
	var myButton = $('#my-button');
	myButton.prop('disabled', !myTurn);

	var endTurnButton = $('#end-turn');
	endTurnButton.prop('disabled', !myTurn);
}

function updateMyCards(cards) {
	var myCards = $('#my-cards');
	myCards.empty();

	$.each(cards, function(i, card) {
		myCards.append(createCard(card));
	});
}

function createDie(face) {
	var buttonAction = "";
	var actionData = "";

	shekels = 0;

	switch (face) {
		case 'b':
		shekels++;
		break;
		case 'c':
		buttonAction = "drawCards";
		actionData = 1; // Number of cards to draw - parameter
		break;
		case 'd':
		buttonAction = "prepareGiveDieModal";
		break;
	}

	var dieSpan = $('<button class="die btn btn-default" data-toggle="modal" data-target="#target-enemy-modal" onclick="' + buttonAction + '">');

	dieSpan.on('click', function() {
		currentCode = -1;
		window[buttonAction](actionData);
		$(this).prop('disabled', true);
	});
	dieSpan.append('<strong>' + face + '</strong>');

	return dieSpan;
}

function createCard(card) {
	var cardElt = $('<div class="card col-xs-4 col-md-2"></div>');
	var cost = $('<ul class="cost"></ul>');

	if (card.cost == "0") {
		cost.append($('<li>Gratuit</li>'));
	} else {
		for (var i = 0; i < card.cost; i++) {
			var costLi = $('<li><img src="images/shekel.png"/></li>');
			cost.append(costLi);
		}
	}

	cardElt.append(cost);
	// TODO : Matt - Ajouter des images?
	// cardElt.append($('<img src="' + card.src + '" />'));
	cardElt.append($('<p>' + card.effect + '</p>'));

	// TODO : Matt - rendre cliquable uniquement si tour du joueur ET peut payer
	// le prix
	cardElt.append($('<button class="btn btn-default"  data-id="' + card.id + '" data-effect-code="' + card.effectCode + '">Utiliser</button>'));

	cardElt.on('click', function() {
		currentCode = card.effectCode;
		executeFunctionFromCode(card.effectCode);
		//window[getFuctionFromCode(card.effectCode)]();
	});
	// TODO Replace action code by onclick
	return cardElt;
}

$(function() {
	refresh();
	refreshBoardInterval = setInterval(refresh, 1000);
});
