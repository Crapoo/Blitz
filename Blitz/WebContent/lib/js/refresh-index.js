function refresh() {
  var $request = $.ajax({
    url: "refresh-index.html",
    type: "post",
    dataType: "json",
  });
  $request.done(function (response, textStatus, xhr) {
    //$('#is-game-in-progress').html(response);
    console.log(response);
  });
  $request.fail(function (xhr, textStatus, errorThrown) {
    alert(errorThrown);
  });
}

$(function () {
  refresh();
  t = setInterval(refresh, 5000);
});
