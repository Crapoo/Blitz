$(function() {
    $('.fRejoindre').submit(function() {
        $.ajax({
            type: 'GET',
            url: 'submit.php',
            url: "rejoindre.html",
            success: function(data) {
          	  $(".container").hide('blind', {}, 2000);
          	  if (data === undefined || data === '') {
          		  $(".creer").click();
          		  return;
          	  }
          	  $(".list").append("<li>"+data+"</li>");
            },
            error: function (request, status, error) {
            	 $(".list").append("<li>"+error+"</li>");
              
            }
        });
        return false;
    }); 
})