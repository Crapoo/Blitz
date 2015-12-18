function getList() {
	var $request = $.ajax({
		url : "list-games.html",
		type : "post",
		dataType : "json",
	});

	$request.done(function(response, textStatus, xhr) {
		var htmlResponse = "";
		var list = $("#game-list");

		if (list.css('display') == 'none') {
			console.log('show');
			showList(list, response);
		} else {
			console.log('hide');
			hideList(list);
		}
	});

	$request.fail(function(xhr, textStatus, errorThrown) {
		console.log(errorThrown);
	});

	function showList(list, response) {
		list.empty();
		if ($.isEmptyObject(response)) {
			list.append($('<p class="bg-initial">Aucune partie n\'a été jouée pour le moment ;(</p>'));
		} else {
			$.each(response, function(i, val) {
				console.log(val);
				var row = $('<p></p>');
				row.append($('<h2>' + val.name + '</h2>'));
				row.append($('<p>' + val.startDate + '</p>'));
				if (val.winner === "annulée") {
					row.append($("<p> Cette partie a été annulée :(</p>"));
				} else {
					row.append($('<p> Winner : ' + val.winner + '</p>'));
				}
				list.append(row);
			});
		}
		list.show();
	}
	
	function hideList(list) {
		list.hide();
	}
}