// FIXME Don't know if we need it
var currentPlayer;

// TODO Implement this shit - Alfred
var code = "";
var data = "target";
var value = "";

var isBusy = false;
var diceRolled = false;

// TODO Retrieve datas from html with
// $(variable).data('dataname')

// TODO For example, a boolean variable that indicates if a card if currently
// being played. If so, ignore every other request

function dispatchAction(caller) {
	// TODO After dispatching, block every other action until it completes,
	// callback ?

	if (!isMyTurn) {
		return;
	}

	if (isBusy) {
		return;
	}

	isBusy = true;

	code = $(caller).data('action-code');
	value = "";

	switch (code) {
	case 1:
		discardDice(1);
		break;
	case 2:
		changeDirection();
		break;
	case 3:
		discardDie(2);
		break;
	case 4:
		giveDie(target);
		break;
	case 5:
		stealCard(target);
		break;
	case 6:
		keepOneCard(target);
		break;
	case 7:
		drawCard(number);
		break;
	case 8:
		limitToOneCard();
		break;
	case 9:
		skipTurn(target);
		break;
	case 10:
		replay();
		changeDirection();
		break;
	case 20:
		if (diceRolled) {
			break;
		}
		diceRolled = true;
		rollDice();
		break;
	case 21:
		endTurn();
		break;
	}
}

function rollDice() {
	var $request = sendAction();

	$request.done(function(response, textStatus, xhr) {
		console.log(response);
		var dice = $('#my-dice');
		dice.empty();

		$.each(response, function(i, die) {
			dice.append('<span>' + die + '&nbsp;</span>');
		});
	});

	isBusy = false;
}

function drawCards(number) {

}

function discardDice(number) {

}

function discardCard(target) {

}

function giveDie(target) {

}

function stealCard(target) {

}

function changeDirection() {

}

function replay() {

}

function keepOneCard(target) {

}

function limitToOneCard() {

}

function skipTurn(target) {

}

function endTurn() {

	diceRolled = false;
}

function endGame(hasWon) {
	$("#overlay").show();
}

// Data is a map of attribute names and their values
function sendAction() {
	return $.ajax({
		url : "compute-action.html",
		data : {
			"action-code" : code,
			"target" : value
		},
		type : "get",
		dataType : "json",
	});

	/*
	 * var $request = $.ajax({ url: "compute-action.html" + action, type: "get",
	 * dataType: "json", });
	 * 
	 * $request.done(function (response, textStatus, xhr) { });
	 * 
	 * $request.fail(function (xhr, textStatus, errorThrown) {
	 * alert(errorThrown); });
	 */
}
