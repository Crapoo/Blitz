function refresh() {
  var $request = $.ajax({
    url: "update-game.html",
    type: "post",
    dataType: "json",
  });

  $request.done(function (response, textStatus, xhr) {
    if (typeof response.hasWon !== 'undefined') {
      endGame(response.hasWon);
    } else {
      $.each(reponse.players, function (i, player) {
        updateInfoOf(player);
      });
      updateMyInfo(response.nbDice, response.myCards, response.myTurn);
    }
  });

  $request.fail(function (xhr, textStatus, errorThrown) {
    alert(errorThrown);
  });
}

function updateInfoOf(player) {
  var nbCards = $('#' + player.username + '#nbCards').text(player.nbCards);
  var nbDice = $('#' + player.username + '#nbCards').text(player.nbDice);
}

function updateMyInfo(nbDice, cards, myTurn) {
  $('#my-dice').text(nbDice);
  updateMyCards(cards);
  updateMyButton(myTurn);
}

function updateMyButton(myTurn) {
  var myButton = $('#myButton');

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
    myCards.append(buildCardDiv(card));
  });
}

$(function () {
  refresh();
  refreshBoardInterval = setInterval(refresh, 500);
});
