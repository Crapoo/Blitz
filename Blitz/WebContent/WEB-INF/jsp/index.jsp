<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<body>
  <div class="container-fluid">
    <div>
      <h4 class="pull-left">Bonjour ${sessionScope['nickname']}</h4>
      <button class="btn btn-warning pull-right">Se D&eacute;connecter</button>
    </div>

    <div class="container text-center">
      <div class="btn-group-vertical btn-group-lg">
        <button type="button" class="btn btn-lg btn-primary">Cr&eacute;er une partie</button>
        <button type="button" class="btn btn-lg btn-primary">Rejoindre une partie</button>
        <button type="button" class="btn btn-lg btn-primary">Historique</button>
      </div>
    </div>
  </div>
