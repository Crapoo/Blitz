<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<body>

  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-nav">
        <p class="navbar-text" id="turn-info">Test turn</p>
      </div>
      <span class="navbar-nav navbar-right"><a href="disconnect.html"><button type="button" class="btn btn-lg btn-default"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>Abandonner</button></a></span>
    </div>
  </nav>

  <div class="container-fluid text-center">
    <div class="row" id="enemy-row1">
    </div>

    <div class="row" id="enemy-row2">
    </div>
  </div>

  <div id="my-board">
    <div id="my-cards">
    </div>
    <div id="my-info">
    </div>
    <div id="my-buttons">
    </div>
  </div>

  <script src="js/game/initialise-game.js"></script>
