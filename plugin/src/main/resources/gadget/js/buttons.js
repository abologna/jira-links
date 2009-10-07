function buttonsLoad(){

  $("#new-link").click(function(){
    $("#new-delicious-link-form").fadeOut(function(){
			$('#link-list').fadeOut(function(){
				$("#new-link-form").fadeIn("slow");
			});
    });
  });

  $("#new-delicious-link").click(function(){
    $("#new-link-form").fadeOut(function(){
				$('#link-list').fadeOut(function(){
					$("#new-delicious-link-form").fadeIn("slow");
			});
    });
  });

	$('input.cancel').click(function(){
		$("#new-link-form").fadeOut(function(){
			$("#new-delicious-link-form").fadeOut(function(){
				$('#link-list').fadeIn('fast')
			});
		});
	});
	
	$('#submitLink').click(function(){
		var project = $('#project-selector');
		var url = $('#link').val();
		var desc = $('#desc').val();
		Links.saveLink(project,url,desc);
		
	});
	
	$('#submitDelicious').click(function(){
		var project = $('#project-selector');
		var url = 'http://feeds.delicious.com/v2/json/'
		url = url + $('#user').val() + '/' + $('#tag').val();
		Links.saveLink(project,url,'');
		$($('input.cancel')[0]).click()
	});
}

gadgets.util.registerOnLoadHandler(buttonsLoad);
