<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<body>

  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <p class="navbar-text" id="turn-info">Test turn</p>
      <a href="disconnect.html"><button type="button" class="btn btn-default navbar-btn navbar-right"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>Abandonner</button></a>
    </div>
  </nav>

  <div class="container-fluid">
    <div class="row text-center" id="enemy-row">
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

  <script src="js/game/initialise-board.js"></script>
  <link href="css/board.css" rel="stylesheet" type="text/css">
