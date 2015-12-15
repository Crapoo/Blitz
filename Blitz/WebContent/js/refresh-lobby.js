function refresh() {
  var $request = $.ajax({
    url: "refresh-lobby.html",
    type: "post",
  });
  $request.done(function (response, textStatus, xhr) {
    $('#lobby-players-list').html(response);
  });
  $request.fail(function (xhr, textStatus, errorThrown) {
    alert(errorThrown);
  });
}

$(function () {
  refresh();
  t = setInterval(refresh, 2000);
});
