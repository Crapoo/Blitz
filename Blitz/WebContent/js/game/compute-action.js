// FIXME Don't know if we need it
var currentPlayer;

// TODO Implement this shit - Alfred
var code = "";
var data = "";
var value = "";

// TODO Retrieve datas from html with
// $(variable).data('dataname')

// TODO For example, a boolean variable that indicates if a card if currently being played. If so, ignore every other request

function dispatchEffects(caller) {
  // TODO After dispatching, block every other action until it completes, callback ?


  switch (called.data('effect-code')) {
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
    passTurn(target);
    break;
    case 10:
    replay();
    changeDirection();
    break;
    default:
    break;
  }

  sendAction();
}

function rollDice() {
  code = 20;
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

function passTurn(target) {

}

function endGame(hasWon) {
  $("#overlay").show();
}

// Data is a map of attribute names and their values
function sendAction(actionCode, dataName, dataValue) {
  var action = "?effect=" + actionCode + (dataName === "" ? "" : "&" + dataName + "=" + dataValue);

  var $request = $.ajax({
    url: "compute-action.html" + action,
    type: "get",
    dataType: "json",
  });

  code = "";
  data = "";
  value = "";

  $request.done(function (response, textStatus, xhr) {
  });

  $request.fail(function (xhr, textStatus, errorThrown) {
    alert(errorThrown);
  });
}
