<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<body>

  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-nav">
        <p class="navbar-text">Bonjour ${sessionScope['nickname']}</p>
      </div>
      <span class="navbar-nav navbar-right"><button class="btn navbar-btn">Se D&eacute;connecter</button></span>
    </div>
  </nav>



  <div class="menu container text-center">

    <div class="btn-group-vertical btn-group-lg" id="create-join-buttons">
      <button type="button" class="btn btn-lg btn-primary" data-toggle="modal" data-target="#create-game-modal">Cr&eacute;er une partie</button>
      <a href="join-lobby.html"><button type="button" class="btn btn-lg btn-primary" value="join-game-button">Rejoindre une partie</button></a>
      <button type="button" class="btn btn-lg btn-primary">Historique</button>
    </div>
    <div class="well" id="is-game-in-progress"></div>
  </div>

  <!--<div class="container">
  <div class="row">

  <div class="span4 offset4">

  <ul class="list-group text-center span4 offset4">
  <li class="list-group-item">gamers : </li>

</ul>
</div>
</div>
</div>-->

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
            <input type="text" class="form-control" id="create-game-name" name="create-game-name">
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
