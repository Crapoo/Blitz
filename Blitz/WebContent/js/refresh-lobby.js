function refresh() {
  var $request = $.ajax({
    url: "refresh-lobby.html",
    type: "post",
    dataType: "json",
  });
  $request.done(function (response, textStatus, xhr) {
    // Min players -> launch game
    if (response['players-count'] == 2) {
      window.location.href = "game.html";
    }
    var playersList = "";

    $.each(response['players-list'], function(i, player) {
      playersList += '<li class="list-group-item">' + player + '</li>';
    });

    $('#lobby-players-list').html(playersList);
    $('#players-count').text(response['players-count']);
    console.log(response);
  });
  $request.fail(function (xhr, textStatus, errorThrown) {
    alert(errorThrown);
  });
}

$(function () {
  refresh();
  t = setInterval(refresh, 1500);
});
