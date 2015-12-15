<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

  <body>
    <div class="container-fluid">
      <div class="row">
        <!-- PLAYERS LIST -->
        <div class="col-md-6">
          <h2>Joueurs connect&eacute;s</h2>
          <ul class="list-group" id="lobby-players-list">
          </ul>
        </div>

        <!-- TIMER AND ADDITIONAL INFO -->
        <div class="col-md-6">
          <h2>Minimum : ${applicationScope['min-players']} joueurs</h2>
        </div>
      </div>
    </div>

    <script src="js/refresh-lobby.js"></script>
    <link href="css/lobby.css" rel="stylesheet" type="text/css">
