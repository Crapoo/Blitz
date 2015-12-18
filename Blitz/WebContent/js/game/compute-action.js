var isMyTurn = false;
var isBusy = false;
var diceRolled = false;
var hasPlayedCard = false;

var shekels = 0;

var currentCode = -1;
var currentCost = -1;

function rollDice() {
	if (!canPlay()) return;
	if (diceRolled) return;

	isBusy = true;

	var $request = sendAction("roll-dice", "");

	$request.done(function(response, textStatus, xhr) {
		var dice = $('#my-dice');
		dice.empty();

		$.each(response, function(i, die) {
			dice.append(createDie(die));
		});
	});

	$('#my-dice').show(500);

	diceRolled = true;
	isBusy = false;
}

var drawCards = function(number) {
	if (!canPlay()) return;

	isBusy = true;

	sendAction("draw-cards", number);

	toastr.success("Vous avez pioch&eacute; " + number + " carte(s) ! Bravo.");

	isBusy = false;
};

function discardDice(number) {
	if (!canPlay()) return;

	isBusy = true;

	sendAction("discard-dice", number);

	toastr.success("Vous avez supprimé un dé! La victoire approche!");

	isBusy = false;
}

var giveDie = function(target) {
	if (!canPlay()) return;

	isBusy = true;

	sendAction("give-die", target);
	$('#target-enemy-modal').modal('hide');

	toastr.success("Vous avez donn&eacute; un d&eacute; &agrave; " + target);

	isBusy = false;
};

var stealCard = function(target) {
	if (!canPlay()) return;

	isBusy = true;

	sendAction("steal-card", target);
	$('#target-enemy-modal').modal('hide');

	toastr.success("Vous avez vol&eacute; une carte &agrave; " + target);

	isBusy = false;
};

function changeDirection() {
	if (!canPlay()) return;

	isBusy = true;

	sendAction("change-direction", "");

	toastr.info("Le sens a chang&eacute;");

	isBusy = false;
}

function replay() {

}

var keepOneCard = function keepOneCard(target) {
	if (!canPlay()) return;

	isBusy = true;

	sendAction("limit-target-to-one-card", target);
	$('#target-enemy-modal').modal('hide');

	toastr.success(target + " n'a plus qu'une carte!");

	isBusy = false;
};

function limitToTwOCards() {
	if (!canPlay()) return;

	isBusy = true;

	sendAction("limit-all-to-two-cards", "");

	toastr.succes("Les enemis n'ont plus que deux cartes!");

	isBusy = false;
}

var skipTurn = function(target) {
	if (!canPlay()) return;

	isBusy = true;

	sendAction("skip-turn", target);
	$('#target-enemy-modal').modal('hide');

	toastr.info(target + " va passer son tour!");

	isBusy = false;
};

function endTurn() {
	if (!diceRolled) {
		toastr.warning("Veuillez lancer vos d&eacute;s d'abord!");
		return;
	}

	sendAction("end-turn", "");

	$('#my-dice').hide(500);

	shekels = 0;
	diceRolled = false;
	hasPlayedCard = false;
}

function endGame(hasWon, winner) {
	$("#overlay").show();
}

function prepareTargetModal(title, message, fn) {
	var list = $('#target-list');
	list.empty();

	$.each(playerList, function(i, player) {
		fctn = fn.name + '(' + player + ')';
		//var btn = $('<button type="button" class="list-group-item" onclick="' + fctn + '">' + player + '</button>');
		var btn = $('<button type="button" class="list-group-item">' + player + '</button>');
		btn.on('click', function() {
			fn(player);
			$('#target-enemy-modal').modal('hide');
		});
		list.append(btn);
	});

	$('#target-enemy-modal').modal('show');
}

function executeFunctionFromCode(effectCode) {
	switch (effectCode) {
		case 1: // Discard 1 die
		console.log('Code 1 : DiscardDice(1)');
		discardDice(1);
		break;
		case 2: // Change direction
		changeDirection();
		break;
		case 3: // Discard 2 dice
		discardDice(2);
		break;
		case 4: // Give die to target
		giveDie(1);
		break;
		case 5: // Take card from target
		prepareTargetModal("Volez une carte", "Choisissez votre cible", stealCard);
		break;
		case 6: // Limit target to 1 card
		prepareTargetModal("Limitez à une carte", "Choisissez votre cible", keepOneCard);
		break;
		case 7: // Draw 3 cards
		drawCards(3);
		break;
		case 8: // Limit everyone to 2 cards
		limitToTwOCards();
		break;
		case 9: // Skip turn
		prepareTargetModal("Passer le tour", "Choisissez votre cible", skipTurn);
		break;
		case 10: // Replay and change direction
		replay();
		changeDirection();
		break;
		default:
		toastr.warning("Pas encore implémenté");
		break;
	}
}

function canPlay() {
	if (!isMyTurn || isBusy) {
		toastr.warning("Vous ne pouvez pas faire cette action pour le moment.");
		return false;
	}

	// A die has no cost
	if (currentCode == -1) {
		return true;
	}

	if (currentCost > shekels) {
		toastr.warning("Vous n'avez pas assez de shekels pour jouer cette carte.");
		return false;
	}

	console.log("Cost to play : " + currentCost);

	shekels -= currentCost;

	console.log("Shekels left : " + shekels);

	return true;
}

// Data is a map of attribute names and their values
function sendAction(action, data) {
	return $.ajax({
		url : "compute-action.html",
		data : {
			"action" : action,
			"data" : data,
			"effect-code" : currentCode
		},
		type : "get",
		dataType : "json",
	});
}
