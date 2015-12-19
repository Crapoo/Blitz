function refresh() {
  var $request = $.ajax({
    url: "refresh-lobby.html",
    type: "post",
    dataType: "json",
  });
  $request.done(function (response, textStatus, xhr) {
	  console.log("start : " + response.startGame + " - cancel : " + response.cancelGame);
  if (response.startGame) {
	  window.location.href = "board.html";
  } else if (response.cancelGame) {
	  /* TODO : Matt - Ajouter un message pour expliquer à l'user le retour à l'index */
	  window.location.href = "index.html";
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
