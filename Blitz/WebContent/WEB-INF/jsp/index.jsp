<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<body>
  <script type="text/javascript" src="lib/js/refresh-index.js"></script>

  <div class="container-fluid">
    <div>
      <h4 class="pull-left">Bonjour ${sessionScope['nickname']}</h4>
      <button class="btn btn-warning pull-right">Se D&eacute;connecter</button>
    </div>

    <div class="container text-center">
      <div class="well" id="is-game-in-progress"></div>

      <div class="btn-group-vertical btn-group-lg">
        <button type="button" class="btn btn-lg btn-primary" data-toggle="modal" data-target="#create-game-modal">Cr&eacute;er une partie</button>
        <!--<button type="button" class="btn btn-lg btn-primary" data-toggle="modal" data-target="#join-game-modal">Rejoindre une partie</button>-->
        <button type="button" class="btn btn-lg btn-primary">Historique</button>
      </div>
    </div>
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
              <label for="create-game-label-name">Nom de la partie :</label>
              <input type="text" class="form-control" id="create-game-label-name">
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
            <button type="button" class="btn btn-primary" form="create-game-form">Cr&eacute;er</button>
          </div>
        </div>
      </div>
    </div>
