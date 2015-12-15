function refresh() {
  var $request = $.ajax({
    url: "refresh-index.html",
    type: "post",
    dataType: "json",
  });
  $request.done(function (response, textStatus, xhr) {
    var htmlResponse = "";
    switch(response['game-state']) {
      case "OVER":
      htmlResponse = '<p class="bg-primary"><strong>Aucune partie en cours</p></strong>';
      break;
      case "INITIAL":
      htmlResponse = '<p class="bg-success"><strong>Partie en attente</p> Joueurs : ' + response['players-count'] +'</strong>';
      break;
      case "IN_PROGERSS":
      htmlResponse = '<p class="bg-danger"><strong>Partie en cours</p></strong>';
      break;
    }
    $('#is-game-in-progress').html(htmlResponse);
  });
  $request.fail(function (xhr, textStatus, errorThrown) {
    alert(errorThrown);
  });
}

$(function () {
  refresh();
  t = setInterval(refresh, 5000);
});
