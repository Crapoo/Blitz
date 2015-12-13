$(document).ready(function() {
  $("#signup-button").click(function() {
    var nickname = $("#signup-nickname").val();
    var password = $("#signup-password").val();
    var passwordRepeat = $("#signup-repeat-password").val();

    var errorMessage = "";

    if (!nickname || !password || !passwordRepeat) {
      errorMessage = "Veuillez remplir tous les champs"
    }
    if (password != passwordRepeat) {
      errorMessage = "Les mots de passe ne sont pas identiques"
    }

    // TODO Refactor

    if (errorMessage) {
      $("#signup-repeat-password").parent().after('<div class="alert alert-danger" role="alert"><strong><span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span></strong>&nbsp;&nbsp;' + errorMessage + '</div>');
    } else {
      $("#signup-repeat-password").parent().after('<div class="alert alert-success" role="alert"><strong><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>&nbsp;&nbsp;</strong>Inscription complétée</div>');
    }
  });
});
