function getList() {
	var $request = $.ajax({
		url : "list-games.html",
		type : "post",
		dataType : "json",
	});

	$request.done(function(response, textStatus, xhr) {
		var htmlResponse = "";
		var listHolder = $("#game-list");
		if (listHolder.css('display') == 'none') {
			console.log('show');
			showList(listHolder, response);
		} else {
			console.log('hide');
			hideList(listHolder);
		}
	});

	$request.fail(function(xhr, textStatus, errorThrown) {
		console.log(errorThrown);
	});

	function showList(listHolder, response) {
		var list = listHolder.children("ul");
		list.empty();
		if ($.isEmptyObject(response)) {
			list.append($('<li class="list-group-item">Aucune partie n\'a été jouée pour le moment ;(</li>'));
		} else {
			$.each(response, function(i, val) {
				console.log(val);
				var row = $('<li class="list-group-item"><span class="badge">' + val.startDate + '</span><p>' + val.name +' - <span id="winner"></span></p></li>');
				
				if (val.winner === "annulée") {
					row.find("#winner").text("annulée");
				} else {
					row.find("#winner").text("Gagnée par " + val.winner);
				}
				list.append(row);
			});
		}
		listHolder.show();
	}

	function hideList(list) {
		list.hide();
	}
}