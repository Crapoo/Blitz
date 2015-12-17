function refresh() {
  var $request = $.ajax({
    url: "refresh-lobby.html",
    type: "post",
    dataType: "json",
  });
  $request.done(function (response, textStatus, xhr) {
    // Min players -> launch game
    if (response.playersCount == 2) {
      window.location.href = "board.html";
    }
    var playersList = "";

    $.each(response.playersList, function(i, player) {
      playersList += '<li class="list-group-item">' + player + '</li>';
    });

    $('#lobby-players-list').html(playersList);
    $('#players-count').text(response.playersCount);
    console.log(response);
  });
  $request.fail(function (xhr, textStatus, errorThrown) {
    //alert(errorThrown);
    console.log(errorThrown);
  });
}

$(function () {
  refresh();
  t = setInterval(refresh, 1500);
});
