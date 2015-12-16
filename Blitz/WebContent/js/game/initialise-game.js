$(function() {
  function initialise() {
    var $request = $.ajax({
      url: "initialise-game.html",
      type: "post",
      dataType: "json",
    });
    $request.done(function (response, textStatus, xhr) {

    });
    $request.fail(function (xhr, textStatus, errorThrown) {
      alert(errorThrown);
    });
  }

});
