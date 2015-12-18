// FIXME Don't know if we need it
var currentPlayer;

var isMyTurn = false;
var isBusy = false;
var diceRolled = false;

var shekels = -1;

// TODO For example, a boolean variable that indicates if a card if currently
// being played. If so, ignore every other request

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

	diceRolled = true;
	isBusy = false;
}

function drawCards(number) {
	if (!canPlay()) return;

	isBusy = true;

	sendAction("draw-cards", number);

	isBusy = false;
}

function discardDice(number) {

}

function discardCard(target) {
}

function giveDie(target) {
	if (!canPlay()) return;

	isBusy = true;

	sendAction("give-die", target);
	$('#target-enemy-modal').modal('hide');

	isBusy = false;
}

function stealCard(target) {

}

function changeDirection() {

}

function replay() {

}

function keepOneCard(target) {

}

function limitToTwOCard() {

}

function skipTurn(target) {

}

function endTurn() {

	if (!canPlay() || !diceRolled) {
		return;
	}

	sendAction("end-turn", "");

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

	$('#target-enemy-modal').modal('show');
}

function prepareTargetModal(title, message) {

}

function canPlay() {
	if (!isMyTurn) return false;
	if (isBusy) return false;
	return true;
}

// Data is a map of attribute names and their values
function sendAction(action, data) {
	return $.ajax({
		url : "compute-action.html",
		data : {
			"action" : action,
			"data" : data
		},
		type : "get",
		dataType : "json",
	});
}
