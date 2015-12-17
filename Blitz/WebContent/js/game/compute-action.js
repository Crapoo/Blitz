// FIXME Don't know if we need it
var currentPlayer;

// TODO Implement this shit - Alfred
/*var action = "";
var data = "target";
var value = "";*/

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

	action = $(caller).data('action-code');
	value = "";

}

function rollDice() {
	var $request = sendAction("roll-dice", "");

	$request.done(function(response, textStatus, xhr) {
		var dice = $('#my-dice');
		dice.empty();

		$.each(response, function(i, die) {
			dice.append(createDie(die));
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
	sendAction("give-die", target);
	$('#target-enemy-modal').modal('hide');
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

function prepareGiveDieModal() {
	var list = $('#target-list');
	list.empty();

	$.each(playerList, function(i, player) {
		list.append('<button type="button" class="list-group-item" onclick="giveDie(\'' + player + '\')">' + player + '</button>');
	});
}

function prepareTargetModal(title, message) {

}

// Data is a map of attribute names and their values
function sendAction(action, target) {
	return $.ajax({
		url : "compute-action.html",
		data : {
			"action" : action,
			"target" : target
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
