var currentPlayer = "";
var myUsername = "";

function refresh() {
	var $request = $.ajax({
		url : "update-game.html",
		type : "post",
		dataType : "json",
	});

	$request.done(function(response, textStatus, xhr) {
		if (typeof response.hasWon !== 'undefined') {
			clearInterval(refreshBoardInterval);
			endGame(response.hasWon, response.winner);
		} else {
			$.each(response.players, function(i, player) {
				updateInfoOf(player);
			});

			updateMyInfo(response.nbDice, response.myCards, response.myTurn);

			highlight(response.currentPlayer);

			$('#current-player').text(currentPlayer);
		}
	});

	$request.fail(function(xhr, textStatus, errorThrown) {
		console.log(errorThrown);
		clearInterval(refreshBoardInterval);
	});
}

function highlight(newPlayer) {
	if (currentPlayer !== newPlayer) {
		if (currentPlayer == myUsername) {
			$('.nav').removeClass('highlighted');
		} else {
			$('#' + currentPlayer).removeClass('highlighted');
		}

		currentPlayer = newPlayer;

		if (newPlayer == myUsername) {
			toastr.info("Votre tour");
			$('.nav').addClass('highlighted');
		} else {
			toastr.info("Tour de " + currentPlayer);
			$('#' + currentPlayer).addClass('highlighted');
		}
	}
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
	var dieSpan = "";
	var buttonAction = "";
	var actionData = "";
	var hasAction = false;
	var title = "";
	var message = "";

	switch (face) {
		case 'b':
		shekels++;
		break;
		case 'c':
		hasAction = true;
		break;
		case 'd':
		hasAction = true;
		break;
	}

	if (hasAction) {
		dieSpan = $('<button class="die btn" data-toggle="modal">');

		if (face == 'c') {
			dieSpan.on('click', function() {
				currentCode = -1;
				drawCards(1);
				$(this).prop('disabled', true);
			});
		} else if (face == 'd') {
			dieSpan.on('click', function() {
				currentCode = -1;
				prepareTargetModal("Donnez un dé à", "Choisissez votre cible", giveDie);
				$(this).prop('disabled', true);
			});
		}
	} else {
		dieSpan = $('<button class="die btn">');
	}

	dieSpan.append('<strong>' + face + '</strong>');

	return dieSpan;
}

function createCard(card) {
	var cardElt = $('<div class="card"></div>');
	var cost = $('<ul class="cost"></ul>');

	if (card.cost == "0") {
		cost.append($('<li>Gratuit</li>'));
	} else {
		for (var i = 0; i < card.cost; i++) {
			var costLi = $('<li><img src="images/shekel.png" alt="S"></li>');
			cost.append(costLi);
		}
	}

	cardElt.append(cost);
	cardElt.append($('<p>' + card.effect + '</p>'));

	cardElt.on('click', function() {
		if (hasPlayedCard) {
			toastr.warning("Vous avez d&eacute;j&agrave; jou&eacute; une carte!");
			return;
		}

		if (!diceRolled) {
			toastr.warning("Vous n'avez pas lanc&eacute; vos d&eacute;s.");
			return;
		}

		currentCode = card.effectCode;
		currentCost = card.cost;

		executeFunctionFromCode(card.effectCode);
		//hasPlayedCard = true;
		currentCost = -1;
	});

	return cardElt;
}

$(function() {
	refresh();
	refreshBoardInterval = setInterval(refresh, 1000);
});
