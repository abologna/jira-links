function buttonsLoad(){

  $("#new-link").click(function(){
    $("#new-delicious-link-form").fadeOut(function(){
			$('#link-list').fadeOut(function(){
				$("#new-link-form").fadeIn("slow");
			})
    });
  });

  $("#new-delicious-link").click(function(){
    $("#new-link-form").fadeOut(function(){
				$('#link-list').fadeOut(function(){
					$("#new-delicious-link-form").fadeIn("slow");
			})
    });
  });

	$('input.cancel').click(function(){
		$("#new-link-form").fadeOut(function(){
			$("#new-delicious-link-form").fadeOut(function(){
				$('#link-list').fadeIn('fast')
			})
		})
	})
}

gadgets.util.registerOnLoadHandler(buttonsLoad);
