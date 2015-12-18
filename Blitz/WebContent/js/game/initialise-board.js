var playerList = [];

function initialise() {
	var $request = $.ajax({
		url : "initialise-board.html",
		type : "post",
		dataType : "json",
	});


	$request.done(function(response, textStatus, xhr) {
		var nbCards = response.nbCards;
		var nbDice = response.nbDice;

		$.each(response.players, function(i, player) {
			playerList[i] = player;
			$('#enemy-row').append(createEnemy(player, nbCards, nbDice, ""));
		});

		createMyCards(response.myCards);

		var overlay = $('<div id="overlay"><div id="img-btn">'+
		'<p><img id="resultImg" src="" alt="result" height="" width=""></p>'+
		'<p><a href="forfeit.html"><button type="button" class="btn btn-default navbar-btn navbar-right">'+
		'<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>Quitter</button></a></p></div></div>');
		$('body').append(overlay);

		overlay.hide();

		myUsername = response.myUsername;
		$('#my-username').text(myUsername);
		highlight(response.currentPlayer);
		currentPlayer = response.currentPlayer;
	});


	$request.fail(function(xhr, textStatus, errorThrown) {
		console.log(errorThrown);
		// alert(errorThrown);
	});
}

function createEnemy(username, nbCards, nbDice, avatarPath) {
	var enemy = $('<div class="enemy" id="' + username + '">');
	enemy.append('<div"><h3 class="lead">' +	username + '</h3></div>');

	var infoRow = $('<div">');

	infoRow.append('<p>Cartes : <span class="nb-cards">' + nbCards +
	'</span></p><p>D&eacute;s : <span class="nb-dice">' + nbDice +
	'</span></p>');

	enemy.append(infoRow);

	return enemy;
}

function createMyCards(cards) {
	var myCards = $('#my-cards');

	$.each(cards, function(i, card) {
		var cardElt = createCard(card);
		myCards.append(cardElt);
	});
}

$(function() {
	initialise();
	toastr.options = {
		"closeButton": false,
		"debug": false,
		"newestOnTop": false,
		"progressBar": false,
		"positionClass": "toast-top-center",
		"preventDuplicates": false,
		"onclick": null,
		"showDuration": "300",
		"hideDuration": "1000",
		"timeOut": "5000",
		"extendedTimeOut": "1000",
		"showEasing": "swing",
		"hideEasing": "linear",
		"showMethod": "fadeIn",
		"hideMethod": "fadeOut"
	};
});
