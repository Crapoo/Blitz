<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<body>

  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <p class="navbar-text">Bonjour ${sessionScope['username']}</p>
      <a href="disconnect.html"><button type="button" class="btn btn-default navbar-right navbar-btn"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>Se D&eacute;connecter</button></a>
    </div>
  </nav>

  <div class="container text-center">

    <div class="row" id="create-join-buttons">
      <button type="button" class="btn btn-lg btn-primary col-md-6" data-toggle="modal" data-target="#create-game-modal" id="create-game-button"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Cr&eacute;er une partie</button>
      <a href="join-lobby.html"><button type="button" class="btn btn-lg btn-primary col-md-6" id="join-game-button"><span class="glyphicon glyphicon-king" aria-hidden="true"></span>Rejoindre une partie</button></a>
      <button type="button" class="btn btn-lg btn-primary col-md-12" onclick="getList()"><span class="glyphicon glyphicon-time" aria-hidden="true"></span>Historique</button>
    </div>
    <div id="is-game-in-progress"></div>
    <div id="game-list" style="display:none;"></div>
  </div>

  <!-- CREATE GAME FORM -->
  <div class="modal fade" id="create-game-modal" tabindex="-1" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">Cr&eacute;er une nouvelle partie</h4>
        </div>
        <div class="modal-body">
          <form class="form" id="create-game-form" role="form" method="post" action="create-game.html">
            <div class="form-group">
              <label for="create-game-name">Nom de la partie :</label>
              <input type="text" class="form-control" id="create-game-name" name="create-game-name" autofocus>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
            <button type="submit" class="btn btn-primary" form="create-game-form">Cr&eacute;er</button>
          </div>
        </div>
      </div>
    </div>

    <link href="css/index.css" type="text/css" rel="stylesheet">
      <script type="text/javascript" src="js/refresh-index.js"></script>
      <script type="text/javascript" src="js/listGames.js"></script>

      <script>
      $('#create-game-modal').on('shown.bs.modal', function() {
        $(this).find('[autofocus]').focus();
      });
      </script>
