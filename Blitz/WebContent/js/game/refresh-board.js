var isMyTurn = false;

function refresh() {
  var $request = $.ajax({
    url: "update-game.html",
    type: "post",
    dataType: "json",
  });

  $request.done(function (response, textStatus, xhr) {
    if (typeof response.hasWon !== 'undefined') {
      clearInterval(refreshBoardInterval);
      endGame(response.hasWon);
    } else {
      $.each(response.players, function (i, player) {
        updateInfoOf(player);
      });
      updateMyInfo(response.nbDice, response.myCards, response.myTurn);

      $('#current-player').text(response.currentPlayer);
    }
  });

  $request.fail(function (xhr, textStatus, errorThrown) {
    alert(errorThrown);
    clearInterval(refreshBoardInterval);
  });
}

function updateInfoOf(player) {
  var nbCards = $('#' + player.username + '.nb-cards').text(player.nbCards);
  var nbDice = $('#' + player.username + '.nb-cards').text(player.nbDice);
}

function updateMyInfo(nbDice, cards, myTurn) {
  $('#my-dice').text(nbDice);
  updateMyCards(cards);
  updateMyButton(myTurn);

  isMyTurn = myTurn;
}

function updateMyButton(myTurn) {
  var myButton = $('#my-button');

  if (myTurn) {
    myButton.prop('disabled', false);
  } else {
    myButton.prop('disabled', true);
  }
}

function updateMyCards(cards) {
  var myCards = $('#my-cards');
  myCards.empty();

  $.each(cards, function(i, card) {
    myCards.append(createCard(card));
  });
}

$(function () {
  refresh();
  refreshBoardInterval = setInterval(refresh, 1000);
});
