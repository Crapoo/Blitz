$(document).ready(function() {
  $("#signup-button").click(function() {
    var nickname = $("#signup-nickname").val();
    var password = $("#signup-password").val();
    var passwordRepeat = $("#signup-repeat-password").val();

    console.log(nickname + " " + password + " " + passwordRepeat);
  });
});
