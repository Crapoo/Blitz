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
      htmlResponse = '<p class="bg-primary"><strong><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>Aucune partie en cours</p></strong>';
      break;
      case "INITIAL":
      htmlResponse = '<p class="bg-success"><strong><span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>Partie en attente</p> Joueurs : ' + response['players-count'] +'</strong>';
      break;
      case "IN_PROGERSS":
      htmlResponse = '<p class="bg-danger"><strong><span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>Partie en cours</p></strong>';
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
