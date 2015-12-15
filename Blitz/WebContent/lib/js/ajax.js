$(function() {
    $('.fRejoindre').submit(function() {
        $.ajax({
            type: 'GET',
            url: 'submit.php',
            url: "rejoindre.html",
            success: function(data) {
          	  $(".menu").hide('blind', {}, 2000);
          	  
          	  $(".list-group").append('<li class="list-group-item">'+ "hii" +"</li>");
            },
            error: function (request, status, error) {
            	 $(".list").append("<li>"+error+"</li>");
              
            }
        });
        return false;
    }); 
})