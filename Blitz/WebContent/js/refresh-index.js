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

/*var $request = $.ajax({
url: "refresh-index.html",
type: 'POST',
dataType: 'json',
data: data,
contentType: 'application/json',
mimeType: 'application/json',

success: function (data) {
console.log(data);
},
error:function(data,status,er) {
alert("error: "+data+" status: "+status+" er:"+er);
}
});*/

$(function () {
  refresh();
  t = setInterval(refresh, 5000);
});
